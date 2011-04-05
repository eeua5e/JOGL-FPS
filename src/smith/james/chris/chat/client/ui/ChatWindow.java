/*
 *
 */
package smith.james.chris.chat.client.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import smith.james.chris.chat.client.ChatClient;
import smith.james.chris.chat.messages.Message;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatWindow.
 */
public class ChatWindow extends JFrame implements ActionListener{

	/** The jta. */
	private JTextArea jta;

	/** The jtf. */
	private JTextField jtf;

	/** The jb. */
	private JButton jb;

	/** The chat id. */
	private int chatId;

	/** The cc. */
	private ChatClient cc;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new chat window.
	 *
	 * @param cc the cc
	 * @param chatId the chat id
	 * @param name the name
	 */
	public ChatWindow(ChatClient cc, int chatId, String name){
		this.chatId = chatId;
		this.cc = cc;
		this.name = name;
		setSize(425, 400);
		setLocationByPlatform(true);
		setJMenuBar(new Menu());
		add(jta = new JTextArea(), BorderLayout.CENTER);
		jta.setEditable(false);

		JPanel jp = new JPanel();
		jp.add(jtf = new JTextField(30));
		jp.add(jb = new JButton("Send"));

		add(jp, BorderLayout.PAGE_END);
		jb.addActionListener(this);
		
		String tmp = name + " chatting with ";
		
		for(String s:cc.getUsersInChat(chatId))
			if(!s.equals(name))
					tmp += " - " + s;
		
		setTitle(tmp);
		
		setVisible(true);
	}

	/**
	 * Gets the chat id.
	 *
	 * @return the chat id
	 */
	public int getChatId(){
		return chatId;
	}

	/**
	 * Adds the message.
	 *
	 * @param m the m
	 */
	public void addMessage(Message m){
		jta.setText(jta.getText() + m.getFrom() + ": " + m.getMessage() + "\n");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		cc.send(new Message(name, chatId, jtf.getText(), null));
		jtf.setText("");
	}

	/**
	 * The Class Menu.
	 */
	class Menu extends JMenuBar implements ActionListener{

		/**
		 * Instantiates a new menu.
		 */
		public Menu(){
			JMenu file = new JMenu("File");
			file.add(menu("Invite to Chat"));
			add(file);
		}

		/**
		 * Menu.
		 *
		 * @param txt the txt
		 * @return the j menu item
		 */
		private JMenuItem menu(String txt) {
			JMenuItem mi = new JMenuItem(txt);
			mi.addActionListener(this);
			return mi;
		}

		/**
		 * J option.
		 *
		 * @param title the title
		 * @return the string
		 */
		private String jOption(String title){
			return (String) JOptionPane.showInputDialog(this, "Enter Username: ",
					title, JOptionPane.PLAIN_MESSAGE, null, null, "");
		}

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String nameTo = jOption("Invite Buddy");

			if(nameTo != null || !nameTo.equals(""))
				cc.send(new Message(name, chatId, "has invited you to his chat", nameTo));
		}
	}
}
