/*
 * 
 */
package smith.james.chris.chat.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientLister.
 */
public class ClientLister extends JPanel{
	
	/** The al. */
	private ActionListener al;
	
	/** The update timer. */
	private Timer updateTimer;
	
	/** The l. */
	private JList l;
	
	/** The list model. */
	private DefaultListModel listModel;

	/**
	 * Instantiates a new client lister.
	 */
	public ClientLister(){		
		//Create new List & model
		listModel = new DefaultListModel();
		l = new JList(listModel);

		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(l);
		jsp.setPreferredSize(new Dimension(235, 335));
		
		l.setLayoutOrientation(JList.VERTICAL);

		add(jsp, BorderLayout.CENTER);
	}
	
	//Updates list data
	/**
	 * Update list.
	 *
	 * @param users the users
	 */
	public void updateList(ArrayList<String> users){
		listModel.clear();

		for(String s:users)
			listModel.addElement(s);
	}
	
	//Adds a mouse listener to detect double clicks
	/* (non-Javadoc)
	 * @see java.awt.Component#addMouseListener(java.awt.event.MouseListener)
	 */
	public void addMouseListener(MouseListener lis){
		l.addMouseListener(lis);
	}
	
	//Adds action listener for Timer and runs timer ever 10seconds to update
	/**
	 * Adds the action listener.
	 *
	 * @param lis the lis
	 */
	public void addActionListener(ActionListener lis){
		al = lis;
		
		updateTimer = new Timer(10000, lis);
		updateTimer.setActionCommand("updateTimer");
		updateTimer.start();
	}
}