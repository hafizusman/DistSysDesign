import java.io.FileNotFoundException;
import java.io.IOException;

import edu.washington.cs.cse490h.lib.PersistentStorageReader;
import edu.washington.cs.cse490h.lib.PersistentStorageWriter;
import edu.washington.cs.cse490h.lib.Utility;


public class FileServerNode extends RIONode {

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
	}
	
	public void createFile(String fileName, String contents)
	{
		try 
		{
			PersistentStorageReader psr = this.getReader(fileName);
		
			String oldFile = psr.readLine(); 
			
			PersistentStorageWriter psw = this.getWriter("temp", false);
			
			psw.write(fileName + "\n" + oldFile);
			
			PersistentStorageWriter psw2 = this.getWriter(fileName, false);
			
			psw2.write(contents);
			
			psw.delete();
			
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
