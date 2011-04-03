/*
 *
 */
package smith.james.chris.chat.client.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import smith.james.chris.chat.client.BuddyReq;
import smith.james.chris.chat.client.ChatClient;
import smith.james.chris.chat.client.MessageHandler;
import smith.james.chris.chat.messages.Buddy;
import smith.james.chris.chat.messages.Message;
import smith.james.chris.chat.messages.Session;

// TODO: Auto-generated Javadoc
/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame implements MessageHandler, ActionListener, MouseListener, WindowListener {

	/** The cc. */
	private ChatClient cc;

	/** The s. */
	private Session s = null;

	/** The name. */
	private String name;

	/** The cl. */
	private ClientLister cl;

	/** The windows. */
	private HashMap<Integer, ChatWindow> windows;

	/**
	 * Instantiates a new main frame.
	 *
	 * @throws RemoteException the remote exception
	 */
	public MainFrame() throws RemoteException {
		super("CS Chatter v1.0");

		windows = new HashMap<Integer, ChatWindow>();
		cc = new ChatClient(this);

		setJMenuBar(new Menu());

		cl = new ClientLister();
		cl.addActionListener(this);
		cl.addMouseListener(this);
		add(cl);

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(250, 400);
		setVisible(true);

		boolean loggedIn = false;

		do {
			Details d = SignIn.promptLogin(this);
			if (d == null) {
				System.exit(0);
			} else if (d.getLogin() == 1) {
				s = cc.login(d.getUsername(), d.getPassword());
				if (s != null) {
					name = d.getUsername();
					loggedIn = true;
				}
			} else {
				if (cc.register(d.getUsername(), d.getPassword())) {
					s = cc.login(d.getUsername(), d.getPassword());
					name = d.getUsername();
					loggedIn = true;
				}
			}
		} while (!loggedIn);

		cl.updateList(cc.getBuddyList(name));
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.client.MessageHandler#receive(smith.james.chris.chat.messages.Message)
	 */
	@Override
	public void receive(Message m) {
		if(windows.containsKey(m.getSesId()))
			windows.get(m.getSesId()).addMessage(m);
		else{
			ChatWindow cw = new ChatWindow(cc, m.getSesId(), name);
			cw.addWindowListener(this);
			windows.put(m.getSesId(), cw);
			windows.get(m.getSesId()).addMessage(m);
		}
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.client.MessageHandler#buddyReq(smith.james.chris.chat.messages.Buddy)
	 */
	@Override
	public boolean buddyReq(Buddy b) {
		return JOptionPane.showConfirmDialog(this,
				"Would you like to add " + b.getFrom() + "?", "Buddy Request",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}

	/* (non-Javadoc)
	 * @see smith.james.chris.chat.client.MessageHandler#buddyRemoved(smith.james.chris.chat.messages.Buddy)
	 */
	@Override
	public void buddyRemoved(Buddy b) {
		cl.updateList(cc.getBuddyList(name));
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		cl.updateList(cc.getBuddyList(name));
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			String s = (String)(((JList)e.getSource()).getSelectedValue());
			if(s != null){
				int idd = cc.getChatId(new Message(name, -1, "", s));
				ChatWindow cw = new ChatWindow(cc, idd, name);
				cw.addWindowListener(this);
				windows.put(idd, cw);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			new MainFrame();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The Class Menu.
	 */
	class Menu extends JMenuBar implements ActionListener {

		/**
		 * Instantiates a new menu.
		 */
		public Menu() {
			JMenu file = new JMenu("File");
			file.add(menu("Add Buddy"));
			file.add(menu("Remove Buddy"));
			file.add(new JSeparator());
			file.add(menu("Exit"));
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
			if (arg0.getActionCommand().equals("Exit")) {
				cc.logout(s);
				System.exit(0);
			} else if (arg0.getActionCommand().equals("Add Buddy")) {
				try {
					String bName = jOption("Add Buddy");
					if(!name.equals(bName))
						new BuddyReq(cc, new Buddy(name, bName, "")).start();
				} catch (HeadlessException e) { e.printStackTrace(); }
			} else if (arg0.getActionCommand().equals("Remove Buddy")) {
				try {
					cc.removeBuddy(new Buddy(name, jOption("Remove Buddy"), ""));
				} catch (HeadlessException e) { e.printStackTrace(); }
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent arg0) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		windows.remove(((ChatWindow)arg0.getSource()).getChatId());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent arg0) {}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent arg0) {}
}
