/*
 * 
 */
package smith.james.chris.chat.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import smith.james.chris.chat.messages.Buddy;
import smith.james.chris.chat.messages.Message;
import smith.james.chris.chat.messages.Session;
import smith.james.chris.chat.server.Server;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatClient.
 */
public class ChatClient extends UnicastRemoteObject implements Client {
	
	/** The cs. */
	private Server cs;
	
	/** The mh. */
	private MessageHandler mh;
	
	/* (non-Javadoc)
	 * @see smith.james.chris.chat.client.Client#receive(smith.james.chris.chat.messages.Message)
	 */
	public void receive(Message m) throws RemoteException {
		mh.receive(m);
	}
	
	/**
	 * Gets the chat id.
	 *
	 * @param m the m
	 * @return the chat id
	 */
	public int getChatId(Message m){
		try {
			return cs.getChatId(m);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	 * Logout.
	 *
	 * @param s the s
	 */
	public void logout(Session s){
		try {
			cs.logout(s);
		} catch (RemoteException e) { e.printStackTrace(); }
	}
	
	/**
	 * Send.
	 *
	 * @param m the m
	 */
	public void send(Message m){
		try {
			cs.send(m);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the clients.
	 *
	 * @return the clients
	 */
	public ArrayList<String> getClients(){
		try {
			return cs.getClientList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Register.
	 *
	 * @param name the name
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean register(String name, String password){
		try{
			return cs.register(name, password);
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Gets the chats.
	 *
	 * @param username the username
	 * @return the chats
	 */
	public ArrayList<Integer> getChats(String username){
		try {
			return cs.getChatSessions(username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Send buddy request.
	 *
	 * @param b the b
	 */
	public void sendBuddyRequest(Buddy b){
		try {
			cs.buddyRequest(b);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the users in chat.
	 *
	 * @param chatId the chat id
	 * @return the users in chat
	 */
	public ArrayList<String> getUsersInChat(int chatId){
		try {
			return cs.getUsersInChat(chatId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Gets the buddy list.
	 *
	 * @param username the username
	 * @return the buddy list
	 */
	public ArrayList<String> getBuddyList(String username){
		try {
			return cs.getBuddyList(username);
		} catch (RemoteException e) { e.printStackTrace(); }
		
		return null;
	}
	
	/**
	 * Removes the buddy.
	 *
	 * @param b the b
	 */
	public void removeBuddy(Buddy b){
		try {
			cs.buddyRemove(b);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Login.
	 *
	 * @param name the name
	 * @param password the password
	 * @return the session
	 */
	public Session login(String name, String password){
		try{
			return cs.login(this, name, password);
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Instantiates a new chat client.
	 *
	 * @param m the m
	 * @throws RemoteException the remote exception
	 */
	public ChatClient(MessageHandler m) throws RemoteException{
		try {
			cs = (Server) Naming.lookup("rmi://localhost:2020/chatServ");
		} catch (MalformedURLException e) {
			System.out.println("Invalid Connection URL");
		} catch (RemoteException e) {
			System.out.println("RMI Exception: " + e.getMessage());
		} catch (NotBoundException e) {
			System.out.println("Unable to locate server, binding failed");
		}
		
		mh = m;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.client.Client#buddyRequest(smith.james.chris.chat.messages.Buddy)
	 */
	@Override
	public boolean buddyRequest(Buddy b) throws RemoteException{
		return mh.buddyReq(b);
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.client.Client#buddyRemoved(smith.james.chris.chat.messages.Buddy)
	 */
	@Override
	public void buddyRemoved(Buddy b) throws RemoteException {
		mh.buddyRemoved(b);
	}
}
