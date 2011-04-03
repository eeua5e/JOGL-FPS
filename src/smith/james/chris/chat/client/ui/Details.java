/*
 * Details.java
 * Author: Chris Smith
 * Date: 9 Nov 2010
 * About: 
 */

package smith.james.chris.chat.client.ui;

// TODO: Auto-generated Javadoc
/**
 * The Class Details.
 */
public class Details {
	
	/** The password. */
	private final String username, password;
	
	/** The mode. */
	private int mode;
	
	/**
	 * Creates a new Details Object.
	 *
	 * @param username the username
	 * @param password the password
	 * @param mode the mode
	 */
	public Details(String username, String password, int mode){
		this.username = username;
		this.password = password;
		this.mode = mode;
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public int getLogin(){
		return mode;
	}
}
