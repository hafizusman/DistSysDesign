import edu.washington.cs.cse490h.lib.Utility;


public class FacebookNode extends FileServerNode {

	public FacebookNode() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCommand(String arg0) {
		// TODO Auto-generated method stub
		// 
		//RIOSend(1, Protocol.DATA, Utility.stringToByteArray("Hello World"));
		System.out.println("Hello from facebook");
	}

}
