/*
 * 
 */
package smith.james.chris.chat.client;

import smith.james.chris.chat.messages.Buddy;

// TODO: Auto-generated Javadoc
/**
 * The Class BuddyReq.
 */
public class BuddyReq extends Thread {
	
	/** The cc. */
	private ChatClient cc;
	
	/** The b. */
	private Buddy b;

	/**
	 * Instantiates a new buddy req.
	 *
	 * @param cc the cc
	 * @param b the b
	 */
	public BuddyReq(ChatClient cc, Buddy b) {
		this.cc = cc;
		this.b = b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		cc.sendBuddyRequest(b);
	}
}