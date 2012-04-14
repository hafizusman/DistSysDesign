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

}