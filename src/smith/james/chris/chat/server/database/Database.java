/*
 * 
 */
package smith.james.chris.chat.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Database.
 */
public class Database {
	
	/** The database. */
	private Connection database = null;
	
	/** The is registered. */
	private PreparedStatement isRegistered = null;
	
	/** The is online. */
	private PreparedStatement isOnline = null;
	
	/** The register. */
	private PreparedStatement register = null;
	
	/** The login. */
	private PreparedStatement login = null;
	
	/** The logout. */
	private PreparedStatement logout = null;
	
	/** The is valid login. */
	private PreparedStatement isValidLogin = null;
	
	/** The get user name. */
	private PreparedStatement getUserName = null;
	
	/** The get user id. */
	private PreparedStatement getUserID = null;
	
	/** The is buddy. */
	private PreparedStatement isBuddy = null;
	
	/** The add buddy. */
	private PreparedStatement addBuddy = null;
	
	/** The remove buddy. */
	private PreparedStatement removeBuddy = null;
	
	/** The get buddy list. */
	private PreparedStatement getBuddyList = null;
	
	/** The get chat id. */
	private PreparedStatement getChatID = null;
	
	/** The add chat. */
	private PreparedStatement addChat = null;
	
	/** The add user to chat. */
	private PreparedStatement addUserToChat = null;
	
	/** The get chat sessions. */
	private PreparedStatement getChatSessions = null;
	
	/** The get users in chat. */
	private PreparedStatement getUsersInChat = null;
	
	/**
	 * Instantiates a new database.
	 */
	public Database(){
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			database = DriverManager.getConnection("jdbc:hsqldb:server.db");
			Statement s = database.createStatement();
			
			s.executeUpdate("CREATE TABLE IF NOT EXISTS Users(id INTEGER PRIMARY KEY IDENTITY,"+
				  " username VARCHAR(24) NOT NULL, password VARCHAR(24) NOT NULL, UNIQUE(username))");
			
			s.executeUpdate("CREATE TABLE IF NOT EXISTS Buddy(id1 INTEGER, id2 INTEGER, "+
					"FOREIGN KEY (id1) REFERENCES Users(id), FOREIGN KEY (id2) REFERENCES Users(id))");
			
			s.executeUpdate("DROP TABLE IF EXISTS Chats");
			s.executeUpdate("DROP TABLE IF EXISTS Chat");
			
			s.executeUpdate("CREATE TABLE Chat(id INTEGER PRIMARY KEY IDENTITY, name VARCHAR(20), UNIQUE(name))");
			s.executeUpdate("CREATE TABLE Chats(id INTEGER, u_id INTEGER, FOREIGN KEY (id) REFERENCES Chat(id), FOREIGN KEY (u_id) REFERENCES Users(id))");
			
			s.executeUpdate("DROP TABLE IF EXISTS Online");
			s.executeUpdate("CREATE TABLE Online(id INTEGER, online INTEGER, FOREIGN KEY (id) REFERENCES Users(id))");
			
			isRegistered = database.prepareStatement("SELECT id FROM Users WHERE username = ?");
			isOnline = database.prepareStatement("SELECT id FROM Online WHERE id = ?");
			
			register = database.prepareStatement("INSERT INTO Users (username, password) VALUES(?, ?)");
			login = database.prepareStatement("INSERT INTO Online VALUES(?, 1)");
			logout = database.prepareStatement("DELETE FROM Online WHERE id = ?");
			isValidLogin = database.prepareStatement("SELECT id FROM Users WHERE username = ? AND password = ?");
			
			getUserName = database.prepareStatement("SELECT username FROM Users WHERE id = ?");
			getUserID = database.prepareStatement("SELECT id FROM Users WHERE username = ?");
			
			isBuddy = database.prepareStatement("SELECT * FROM Buddy WHERE id1 = ? AND id2 = ?");
			addBuddy = database.prepareStatement("INSERT INTO Buddy VALUES(?, ?)");
			removeBuddy = database.prepareStatement("DELETE FROM Buddy WHERE id1 = ? AND id2 = ?");
			getBuddyList = database.prepareStatement("SELECT id2 FROM Buddy WHERE id1 = ?");
			
			getChatID = database.prepareStatement("SELECT id FROM Chat WHERE name = ?");
			addChat = database.prepareStatement("INSERT INTO Chat (name) VALUES(?)");
			addUserToChat = database.prepareStatement("INSERT INTO Chats VALUES(?, ?)");
			getUsersInChat = database.prepareStatement("SELECT u_id FROM Chats WHERE id = ?");
			getChatSessions = database.prepareStatement("SELECT id FROM Chats WHERE u_id = ?");
		} catch (SQLException e) { e.printStackTrace(); 
		} catch (ClassNotFoundException e) { e.printStackTrace(); }
	}

	/**
	 * Adds the buddy.
	 *
	 * @param id1 the id1
	 * @param id2 the id2
	 */
	public void addBuddy(int id1, int id2){
		try {
			isBuddy.setInt(1, id1);
			isBuddy.setInt(2, id2);
			ResultSet rs = isBuddy.executeQuery();
			if(!rs.next()){
				addBuddy.setInt(1, id1);
				addBuddy.setInt(2, id2);
				addBuddy.executeUpdate();
				addBuddy.setInt(1, id2);
				addBuddy.setInt(2, id1);
				addBuddy.executeUpdate();
			}
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Adds the chat.
	 *
	 * @param name the name
	 * @return the int
	 */
	public int addChat(String name){
		try {
			addChat.setString(1, name);
			addChat.executeUpdate();
			
			getChatID.setString(1, name);
			ResultSet rs = getChatID.executeQuery();
			if(rs.next())
				return rs.getInt("id");
		} catch (SQLException e) { e.printStackTrace(); }
		
		return -1;
	}

	/**
	 * Adds the user to chat.
	 *
	 * @param chat the chat
	 * @param user the user
	 */
	public void addUserToChat(int chat, int user){
		try {
			addUserToChat.setInt(1, chat);
			addUserToChat.setInt(2, user);
			addUserToChat.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Gets the buddy list.
	 *
	 * @param id the id
	 * @return the buddy list
	 */
	public ArrayList<Integer> getBuddyList(int id){
		try {
			ArrayList<Integer> buds = new ArrayList<Integer>();
			getBuddyList.setInt(1, id);
			ResultSet rs = getBuddyList.executeQuery();
			while(rs.next())
				buds.add(rs.getInt("id2"));
			
			return buds;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}

	/**
	 * Gets the chat sessions.
	 *
	 * @param id the id
	 * @return the chat sessions
	 */
	public ArrayList<Integer> getChatSessions(int id){
		try {
			ArrayList<Integer> ses = new ArrayList<Integer>();
			getChatSessions.setInt(1, id);
			ResultSet rs = getChatSessions.executeQuery();
			while(rs.next())
				ses.add(rs.getInt("id"));
			
			return ses;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}

	/**
	 * Gets the user id.
	 *
	 * @param username the username
	 * @return the user id
	 */
	public int getUserID(String username){
		try {
			getUserID.setString(1, username);
			ResultSet rs = getUserID.executeQuery();
			if(rs.next())
				return rs.getInt("id");
		} catch (SQLException e) { e.printStackTrace(); }
		
		return -1;
	}

	/**
	 * Gets the username.
	 *
	 * @param id the id
	 * @return the username
	 */
	public String getUsername(int id){
		try {
			getUserName.setInt(1, id);
			ResultSet rs = getUserName.executeQuery();
			if(rs.next())
				return rs.getString("username");
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	/**
	 * Gets the users in chat.
	 *
	 * @param id the id
	 * @return the users in chat
	 */
	public ArrayList<Integer> getUsersInChat(int id){
		try {
			ArrayList<Integer> usr = new ArrayList<Integer>();
			getUsersInChat.setInt(1, id);
			ResultSet rs = getUsersInChat.executeQuery();
			while(rs.next())
				usr.add(rs.getInt("u_id"));
			
			return usr;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	/**
	 * Checks if is buddy.
	 *
	 * @param id1 the id1
	 * @param id2 the id2
	 * @return true, if is buddy
	 */
	public boolean isBuddy(int id1, int id2){
		try {
			isBuddy.setInt(1, id1);
			isBuddy.setInt(2, id2);
			return isBuddy.executeQuery().next();
		} catch (SQLException e) { e.printStackTrace(); }

		return false;
	}

	/**
	 * Checks if is online.
	 *
	 * @param userId the user id
	 * @return true, if is online
	 */
	public boolean isOnline(int userId){
		try {
			isOnline.setInt(1, userId);
			return isOnline.executeQuery().next();
		} catch (SQLException e) { e.printStackTrace(); }

		return false;
	}

	/**
	 * Checks if is registered.
	 *
	 * @param username the username
	 * @return true, if is registered
	 */
	public boolean isRegistered(String username){
		try {
			isRegistered.setString(1, username);
			return isRegistered.executeQuery().next();
		} catch (SQLException e) { e.printStackTrace(); }

		return true;
	}
	
	/**
	 * Login.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the int
	 */
	public int login(String username, String password){
		try {
			isValidLogin.setString(1, username);
			isValidLogin.setString(2, password);
			ResultSet rs = isValidLogin.executeQuery();
			if(rs.next()){
				login.setInt(1, rs.getInt("id"));
				return rs.getInt("id");
			}
		} catch (SQLException e) { e.printStackTrace(); }

		return -1;
	}

	/**
	 * Logout.
	 *
	 * @param userId the user id
	 */
	public void logout(int userId){
		try {
			logout.setInt(1, userId);
			logout.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
	 * Register.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public void register(String username, String password){
		try {
			register.setString(1, username);
			register.setString(2, password);
			register.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}

	/**
	 * Removes the buddy.
	 *
	 * @param id1 the id1
	 * @param id2 the id2
	 */
	public void removeBuddy(int id1, int id2){
		try {
			removeBuddy.setInt(1, id1);
			removeBuddy.setInt(2, id2);
			removeBuddy.executeUpdate();
			removeBuddy.setInt(1, id2);
			removeBuddy.setInt(2, id1);
			removeBuddy.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
}
