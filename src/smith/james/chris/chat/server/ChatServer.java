/*
 *
 */
package smith.james.chris.chat.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import smith.james.chris.chat.client.Client;
import smith.james.chris.chat.messages.Buddy;
import smith.james.chris.chat.messages.Message;
import smith.james.chris.chat.messages.Session;
import smith.james.chris.chat.server.database.Database;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatServer.
 */
public class ChatServer extends UnicastRemoteObject implements Server {

	/** The clients. */
	private HashMap<String, Client> clients;

	/** The db. */
	private Database db;

	/**
	 * Instantiates a new chat server.
	 *
	 * @throws RemoteException the remote exception
	 */
	protected ChatServer() throws RemoteException {
		super();
		clients = new HashMap<String, Client>();
		db = new Database();
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#register(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean register(String username, String password) throws RemoteException {
		if(!db.isRegistered(username)){ // IF user not registered, register
			db.register(username, password);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#login(smith.james.chris.chat.client.Client, java.lang.String, java.lang.String)
	 */
	@Override
	public Session login(Client c, String username, String password) throws RemoteException {
		int id = db.login(username, password); //Login
		if(id != -1){
			clients.put(username, c);
			return new Session(username, id); // If successful, return session id
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#logout(smith.james.chris.chat.messages.Session)
	 */
	@Override
	public Session logout(Session session) throws RemoteException {
		db.logout(session.getSessionId()); // Logout
		clients.remove(session.getUsername());
		return null;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#getUsersInChat(int)
	 */
	public ArrayList<String> getUsersInChat(int chatId) throws RemoteException{
		ArrayList<Integer> u = db.getUsersInChat(chatId); // Grab user id's
		ArrayList<String> us = new ArrayList<String>();
		for(int i:u)
			us.add(db.getUsername(i));

		return us;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#send(smith.james.chris.chat.messages.Message)
	 */
	@Override
	public int send(Message mes) throws RemoteException {
		// If message with no session id and they are a friend
		if(mes.getSesId() == -1 && db.isBuddy(db.getUserID(mes.getFrom()), db.getUserID(mes.getTo()))){
			int id = db.addChat(""+Math.random()*1000);
			db.addUserToChat(id, db.getUserID(mes.getTo())); // create new chat and add
			db.addUserToChat(id, db.getUserID(mes.getFrom()));
			try{
				mes.setSesId(id); // Set the session id
				clients.get(mes.getTo()).receive(mes); // send to participants
				clients.get(mes.getFrom()).receive(mes);
			}catch(RemoteException re){
				clients.remove(mes.getTo()); // If fails, remove user
			}
			return id; // return chat session id
		}else{
			if(mes.getTo() != null){ // if sending to a user
				if(db.isOnline(db.getUserID(mes.getTo()))){
					db.addUserToChat(mes.getSesId(), db.getUserID(mes.getTo()));
					clients.get(mes.getTo()).receive(mes); // add them to chat
				}
			}else{
				for(int i:db.getUsersInChat(mes.getSesId())){ //else send to all users in chat session
					if(db.isOnline(i)){
						try{
							clients.get(db.getUsername(i)).receive(mes);
						}catch(RemoteException re){
							clients.remove(db.getUsername(i));
						}
					}
				}
			}
		}

		return mes.getSesId();
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#buddyRequest(smith.james.chris.chat.messages.Buddy)
	 */
	@Override
	public boolean buddyRequest(Buddy b) throws RemoteException {
		try{
			if(clients.get(b.getTo()).buddyRequest(b)) // If person accepts
				db.addBuddy(db.getUserID(b.getFrom()), db.getUserID(b.getTo())); //Create buddy
		}catch(NullPointerException e){
			return false;
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#buddyRemove(smith.james.chris.chat.messages.Buddy)
	 */
	@Override
	public void buddyRemove(Buddy b) throws RemoteException {
		boolean rm = false;
		if(db.isBuddy(db.getUserID(b.getFrom()), db.getUserID(b.getTo())))
			rm = true;

		db.removeBuddy(db.getUserID(b.getFrom()), db.getUserID(b.getTo()));

		if(rm){ // remove buddy
			clients.get(b.getFrom()).buddyRemoved(b);
			if(db.isOnline(db.getUserID(b.getTo())))
				clients.get(b.getTo()).buddyRemoved(b);
		}
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#getBuddyList(java.lang.String)
	 */
	@Override
	public ArrayList<String> getBuddyList(String username)
			throws RemoteException {
		ArrayList<Integer> buds = db.getBuddyList(db.getUserID(username));
		ArrayList<String> bs = new ArrayList<String>();
		for(int i:buds) // Return buddy list
			bs.add(db.getUsername(i));

		return bs;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#getChatSessions(java.lang.String)
	 */
	@Override
	public ArrayList<Integer> getChatSessions(String username)
			throws RemoteException {
		return db.getChatSessions(db.getUserID(username));
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#getClientList()
	 */
	@Override
	public ArrayList<String> getClientList() throws RemoteException {
		return new ArrayList<String>(clients.keySet()) ;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.server.Server#getChatId(smith.james.chris.chat.messages.Message)
	 */
	@Override
	public int getChatId(Message mes) throws RemoteException {
		int id = db.addChat(""+Math.random()*1000); // Create new chat add users to chat
		db.addUserToChat(id, db.getUserID(mes.getTo()));
		db.addUserToChat(id, db.getUserID(mes.getFrom()));
		return id;
	}
}
