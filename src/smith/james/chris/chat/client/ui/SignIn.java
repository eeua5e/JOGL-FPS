/*
 * SignIn.java
 * Author: Chris Smith
 * Date: 9 Nov 2010
 * About: 
 */

package smith.james.chris.chat.client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class SignIn.
 */
public class SignIn {
	
	/** The mode. */
	private static int mode = 0;
	
	/**
	 * Creates a dialog to grab data from user and then returns it.
	 *
	 * @param owner the owner
	 * @return the details
	 */
	public static Details promptLogin(JFrame owner){
		final JDialog loginBox = new JDialog(owner, "Login...", true);
		mode = 0;
		
		loginBox.setLocationByPlatform(true);
		
		loginBox.setLayout(new GridLayout(3, 2));
		JTextField name = new JTextField(10);
		loginBox.add(new JLabel("Username: "));
		loginBox.add(name);
		JPasswordField pass = new JPasswordField(10);
		loginBox.add(new JLabel("Password: "));
		loginBox.add(pass);
		JButton jb = new JButton("Login");
		JButton jb2 = new JButton("Register");
		
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeMode(1);
				loginBox.setVisible(false);
			}
		});
		
		jb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeMode(2);
				loginBox.setVisible(false);
			}
		});
		
		loginBox.add(jb2);
		loginBox.add(jb);
		loginBox.pack();
		loginBox.setVisible(true);
		
		return new Details(name.getText(), new String(pass.getPassword()), mode);
	}

	/**
	 * Change mode.
	 *
	 * @param i the i
	 */
	protected static void changeMode(int i) {
		mode = i;
	}
}
