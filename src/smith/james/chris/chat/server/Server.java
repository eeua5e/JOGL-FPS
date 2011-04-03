/*
 *
 */
package smith.james.chris.chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import smith.james.chris.chat.client.Client;
import smith.james.chris.chat.messages.Buddy;
import smith.james.chris.chat.messages.Message;
import smith.james.chris.chat.messages.Session;

// TODO: Auto-generated Javadoc
/**
 * The Interface Server.
 */
public interface Server extends Remote {

	/**
	 * Register.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 * @throws RemoteException the remote exception
	 */
	public boolean register(String username, String password) throws RemoteException;

	/**
	 * Login.
	 *
	 * @param c the c
	 * @param username the username
	 * @param password the password
	 * @return the session
	 * @throws RemoteException the remote exception
	 */
	public Session login(Client c, String username, String password) throws RemoteException;

	/**
	 * Logout.
	 *
	 * @param session the session
	 * @return the session
	 * @throws RemoteException the remote exception
	 */
	public Session logout(Session session) throws RemoteException;

	/**
	 * Send.
	 *
	 * @param mes the mes
	 * @return the int
	 * @throws RemoteException the remote exception
	 */
	public int send(Message mes) throws RemoteException;

	/**
	 * Gets the chat id.
	 *
	 * @param mes the mes
	 * @return the chat id
	 * @throws RemoteException the remote exception
	 */
	public int getChatId(Message mes) throws RemoteException;

	/**
	 * Buddy request.
	 *
	 * @param b the b
	 * @return true, if successful
	 * @throws RemoteException the remote exception
	 */
	public boolean buddyRequest(Buddy b) throws RemoteException;

	/**
	 * Buddy remove.
	 *
	 * @param b the b
	 * @throws RemoteException the remote exception
	 */
	public void buddyRemove(Buddy b) throws RemoteException;

	/**
	 * Gets the buddy list.
	 *
	 * @param username the username
	 * @return the buddy list
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<String> getBuddyList(String username) throws RemoteException;

	/**
	 * Gets the chat sessions.
	 *
	 * @param username the username
	 * @return the chat sessions
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<Integer> getChatSessions(String username) throws RemoteException;

	/**
	 * Gets the client list.
	 *
	 * @return the client list
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<String> getClientList() throws RemoteException;
}
