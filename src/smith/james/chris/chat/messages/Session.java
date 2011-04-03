/*
 * 
 */
package smith.james.chris.chat.messages;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Session.
 */
public class Session implements Serializable {
	
	/** The username. */
	private String username;
	
	/** The session id. */
	private int sessionId;
	
	/**
	 * Instantiates a new session.
	 *
	 * @param username the username
	 * @param sessionId the session id
	 */
	public Session(String username, int sessionId) {
		super();
		this.username = username;
		this.sessionId = sessionId;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public int getSessionId() {
		return sessionId;
	}
}
