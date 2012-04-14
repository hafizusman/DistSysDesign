import java.io.FileNotFoundException;
import java.io.IOException;

import edu.washington.cs.cse490h.lib.PersistentStorageReader;
import edu.washington.cs.cse490h.lib.PersistentStorageWriter;
import edu.washington.cs.cse490h.lib.Utility;


public class FileServerNode extends RIONode {
	
	private String TEMP_PUT_FILE_NAME = "TempPutFile";
	private String TEMP_CREATE_FILE_NAME = "TempCreateFile";
	

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
		
		this.createFileOnStart();
		this.putFileOnStart();
	}
	
	private void printError(int errCode, String fileName)
	{
		System.out.println("Node " + this.addr + " Error: Put file " + fileName + " on server " + this.addr + " returned error code " + errCode + "\n");
	}
	
	public void createFile(String fileName)
	{
		try
		{
			if (Utility.fileExists(this, fileName))
			{
				this.printError(11, fileName);
			}
			else
			{
				PersistentStorageWriter pswTemp = this.getWriter(this.TEMP_CREATE_FILE_NAME, false);
				
				pswTemp.write(fileName);
				
				pswTemp.close();
				
				PersistentStorageWriter pswActual = this.getWriter(fileName, false);
				
				pswActual.close();
				
				pswTemp.delete(); // Bug: Can we delete after close? 
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void createFileOnStart()
	{
		try
		{
			if (Utility.fileExists(this, this.TEMP_CREATE_FILE_NAME))
			{
				PersistentStorageReader psrTemp = this.getReader(this.TEMP_CREATE_FILE_NAME);
				
				PersistentStorageWriter pswTemp = this.getWriter(this.TEMP_CREATE_FILE_NAME, false);
				
				if (!psrTemp.ready())
				{
					pswTemp.delete();
				}
				else
				{
					String fileName = psrTemp.readLine();
					
					PersistentStorageWriter pswActual = this.getWriter(fileName, false);
					
					pswActual.delete();
					
					pswTemp.delete();
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void putFile(String fileName, String contents)
	{
		try 
		{
			PersistentStorageReader psr = this.getReader(fileName);
		
			String oldFile = psr.readLine(); 
			
			PersistentStorageWriter pswTemp = this.getWriter(this.TEMP_PUT_FILE_NAME, false);
			
			pswTemp.write(fileName + "\n" + oldFile);
			
			pswTemp.close();
			
			PersistentStorageWriter pswActual = this.getWriter(fileName, false);
			
			pswActual.write(contents);
			
			pswActual.close();
			
			pswTemp.delete(); // Bug: Can we delete after close?
			
		} 
		catch (FileNotFoundException e)
		{
			this.printError(10, fileName);
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
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
