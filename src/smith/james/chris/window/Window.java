package smith.james.chris.window;

import javax.swing.JFrame;

public abstract class Window extends JFrame{
	// Construct a window
	public Window(String title, int width, int height){
		super(title);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
