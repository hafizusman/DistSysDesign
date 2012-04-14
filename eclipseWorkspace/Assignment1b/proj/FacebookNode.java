import edu.washington.cs.cse490h.lib.Utility;

/*
 * The facebook class provides 
 */
public class FacebookNode extends FileServerNode {

	public FacebookNode() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Starting a facebook node");
		super.start();
		
	}
	
	// the onCommand method for facebook must parse the following commands
	// CreateUser MyUserID
	// Login MyUserID
	// Logout MyUserID
	// RequestFriend MyUserID FriendUserID
	// AcceptFriend MyUserID FriendUserID
	// PostMessageToWall MyUserID MessageToPost
	// ReadAllMessages MyUserID
	@Override
	public void onCommand(String arg0) {
		
		// TODO Auto-generated method stub
		// 
		//RIOSend(1, Protocol.DATA, Utility.stringToByteArray("Hello World"));
		
		System.out.println("Hello from facebook");
	}
	
	private void CreateUser(String MyUserID)
	{
		// create a MyUserID.txt file on the server
	}
	private void Login(String MyUserID)
	{
		// create a 
	}
	private void Logout(String MyUserID)
	{}
	private void RequestFriend(String MyUserID, String FriendUserID)
	{}
	private void AcceptFriend(String MyUserID, String FriendUserID)
	{}
	private void PostMessageToWall(String MyUserID, String MessageToPost)
	{}
	private void ReadAllMessages(String MyUserID)
	{}

}
