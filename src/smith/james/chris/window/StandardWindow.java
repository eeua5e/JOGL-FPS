package smith.james.chris.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import com.sun.opengl.util.Animator;

public abstract class StandardWindow extends BasicWindow implements GLEventListener, KeyListener{
	// Create a standard window with animator and event listener
	public StandardWindow(String title, int width, int height, int fps) {
		super(title, width, height);
		canvas.addGLEventListener(this);
		Animator anim = new Animator(canvas);
		
		anim.add(canvas);
		anim.start();
		setVisible(true);
	}

	@Override
	public void display(GLAutoDrawable arg0) {}

	@Override
	public void init(GLAutoDrawable arg0) {}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {}
	

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
