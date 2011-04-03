/*
 * 
 */
package smith.james.chris.chat.client;

import smith.james.chris.chat.messages.Buddy;
import smith.james.chris.chat.messages.Message;

// TODO: Auto-generated Javadoc
/**
 * The Interface MessageHandler.
 */
public interface MessageHandler {
	
	/**
	 * Receive.
	 *
	 * @param m the m
	 */
	public void receive(Message m);
	
	/**
	 * Buddy req.
	 *
	 * @param b the b
	 * @return true, if successful
	 */
	public boolean buddyReq(Buddy b);
	
	/**
	 * Buddy removed.
	 *
	 * @param b the b
	 */
	public void buddyRemoved(Buddy b);
}
