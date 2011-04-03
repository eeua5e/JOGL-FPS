/*
 * 
 */
package smith.james.chris.chat.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import smith.james.chris.chat.messages.Buddy;
import smith.james.chris.chat.messages.Message;

// TODO: Auto-generated Javadoc
/**
 * The Interface Client.
 */
public interface Client extends Remote {
	
	/**
	 * Receive.
	 *
	 * @param m the m
	 * @throws RemoteException the remote exception
	 */
	public void receive(Message m) throws RemoteException;
	
	/**
	 * Buddy request.
	 *
	 * @param b the b
	 * @return true, if successful
	 * @throws RemoteException the remote exception
	 */
	public boolean buddyRequest(Buddy b) throws RemoteException;
	
	/**
	 * Buddy removed.
	 *
	 * @param b the b
	 * @throws RemoteException the remote exception
	 */
	public void buddyRemoved(Buddy b) throws RemoteException;
}
