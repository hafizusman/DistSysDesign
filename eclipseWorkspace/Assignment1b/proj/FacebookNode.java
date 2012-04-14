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
		String[] command = arg0.split(" ");
		
	}
	
	private void CreateUser(String MyUserID)
	{
		// create a MyUserID.txt file on the server
		// create Friends_MyUserID.txt
		// create Messages_MyUserID.txt
	}
	private void Login(String MyUserID)
	{
		// create a Login_MyUserID.txt file on the server
	}
	private void Logout(String MyUserID)
	{
		// delete Login_MyUserID.txt file on the server
	}
	private void RequestFriend(String MyUserID, String FriendUserID)
	{
		// create Request_FriendUserID_MyUserID.txt
	}
	private void AcceptFriend(String MyUserID, String FriendUserID)
	{
		// delete Request_MyUserID_FriendUserID.txt
		// append FriendUserID to Friends_MyUserID.txt
	}
	private void PostMessageToWall(String MyUserID, String MessageToPost)
	{
		// get Friends_MyUserID.txt
		// foreach FriendUserID in txtfile, append MessageToPost to Messages_FriendUserID.txt
	}
	private void ReadAllMessages(String MyUserID)
	{
		// get Messages_MyUserID.txt
	}

}
