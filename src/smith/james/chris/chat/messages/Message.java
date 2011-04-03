/*
 * 
 */
package smith.james.chris.chat.messages;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message implements Serializable{
	
	/** The from. */
	private String from;
	
	/** The ses id. */
	private int sesId;
	
	/** The message. */
	private String message;
	
	/** The to. */
	private String to;
	
	/**
	 * Instantiates a new message.
	 *
	 * @param from the from
	 * @param sesId the ses id
	 * @param message the message
	 * @param to the to
	 */
	public Message(String from, int sesId, String message, String to) {
		super();
		this.from = from;
		this.sesId = sesId;
		this.message = message;
		this.to = to;
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
	 * Gets the ses id.
	 *
	 * @return the ses id
	 */
	public int getSesId() {
		return sesId;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo(){
		return to;
	}
	
	/**
	 * Sets the ses id.
	 *
	 * @param id the new ses id
	 */
	public void setSesId(int id){
		sesId = id;
	}
}
