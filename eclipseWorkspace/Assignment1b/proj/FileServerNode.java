import java.io.FileNotFoundException;
import java.io.IOException;

import edu.washington.cs.cse490h.lib.PersistentStorageReader;
import edu.washington.cs.cse490h.lib.PersistentStorageWriter;
import edu.washington.cs.cse490h.lib.Utility;


public class FileServerNode extends RIONode {
	
	private String TEMP_PUT_FILE_NAME = "TempPutFile";

	public FileServerNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onRIOReceive(Integer from, int protocol, byte[] msg) {
		// TODO Auto-generated method stub
		if (protocol == Protocol.DATA)
		{
			System.out.print(Utility.byteArrayToString(msg));
		}
	}

	@Override
	public void onCommand(String arg0) {
		// TODO Auto-generated method stub
		RIOSend(1, Protocol.DATA, Utility.stringToByteArray("Hello World"));

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Starting Node " + this.addr);
		
		this.putFileOnStart();
	}
	
	public void putFile(String fileName, String contents)
	{
		try 
		{
			PersistentStorageReader psr = this.getReader(fileName);
		
			String oldFile = psr.readLine(); 
			
			PersistentStorageWriter pswTemp = this.getWriter(this.TEMP_PUT_FILE_NAME, false);
			
			pswTemp.write(fileName + "\n" + oldFile);
			
			PersistentStorageWriter pswActual = this.getWriter(fileName, false);
			
			pswActual.write(contents);
			
			pswTemp.delete();
			
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Put file " + fileName + " on " + this.addr + " does not exist\n");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void putFileOnStart()
	{
		try
		{
		
			if (Utility.fileExists(this, this.TEMP_PUT_FILE_NAME))
			{
				PersistentStorageReader psrTemp = this.getReader(this.TEMP_PUT_FILE_NAME);

				PersistentStorageWriter pswTemp = this.getWriter(this.TEMP_PUT_FILE_NAME, false);
				
				if (!psrTemp.ready())
				{
					// Bug: see if there is another way to delete
					pswTemp.delete();
				}
				else
				{
					String fileName = psrTemp.readLine();
					
					String oldContents = psrTemp.readLine();
					
					PersistentStorageWriter revertFile = this.getWriter(fileName, false);
					
					revertFile.write(oldContents);
					
					pswTemp.delete();
				}
				
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Put file on start " + this.TEMP_PUT_FILE_NAME + " on " + this.addr + " does not exist\n");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
