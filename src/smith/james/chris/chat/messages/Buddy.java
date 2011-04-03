/*
 * 
 */
package smith.james.chris.chat.messages;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Buddy.
 */
public class Buddy implements Serializable{
	
	/** The from. */
	private String from;
	
	/** The to. */
	private String to;
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new buddy.
	 *
	 * @param from the from
	 * @param to the to
	 * @param message the message
	 */
	public Buddy(String from, String to, String message) {
		super();
		this.from = from;
		this.to = to;
		this.message = message;
	}

	/**
	 * Gets the from.
	 *
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
