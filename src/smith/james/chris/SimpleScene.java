package smith.james.chris;
import java.awt.event.KeyEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import smith.james.chris.camera.PlayerCam;
import smith.james.chris.maths.Vector;
import smith.james.chris.window.StandardWindow;

public class SimpleScene extends StandardWindow {
	private PlayerCam pc;
	private boolean l = false, r = false;
	private boolean f = false, b = false;
	
    // Set up objects used, add mouse listeners
    public SimpleScene(String title, int width, int height, int fps) {
		super(title, width, height, fps);

		pc = new PlayerCam(new Vector(0, 0, -50));
		canvas.addKeyListener(this);
	}
    
    // Map to render sub call, allows for separate update method
    @Override
    public void display(GLAutoDrawable drawable) {
    	if(f)
			pc.forward();
		if(b)
			pc.back();
		if(l)
			pc.left();
		if(r)
			pc.right();
		
        render(drawable);
    }

    // Setup GL settings
    public void init(GLAutoDrawable drawable){
    	GL gl = drawable.getGL();
		GLU glu = new GLU();
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE); // Blend style
		
		// Load image for texture
		gl.glViewport(0,0,500,300);
		
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		
		// Setup 3D perspective
		glu.gluPerspective(45.0f, 800.0f / 600.0f, 1.0f, 1200.0f);

		gl.glMatrixMode(GL.GL_MODELVIEW);
    }

    // Render whole screen
    private void render(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);  
        gl.glLoadIdentity(); // Reset matrix
        
        pc.position(gl);
        
        gl.glBegin(GL.GL_QUADS);
        	gl.glColor3f(1, 0, 0);
	        gl.glVertex3f(-100, -10, -100);
	        
	        gl.glColor3f(0, 1, 0);
			gl.glVertex3f(-100, -10, 100);
			
			gl.glColor3f(0, 0, 1);
			gl.glVertex3f(100, -10, 100);
			
			gl.glColor3f(1, 0, 1);
			gl.glVertex3f(100, -10, -100);
		gl.glEnd();
		
    }

    public static void main(String[] args) {
    	new SimpleScene("Assignment 1", 800, 600, 30);
    }
    
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			f = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			b = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			l = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			r = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			f = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			b = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			l = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			r = false;
	}
}