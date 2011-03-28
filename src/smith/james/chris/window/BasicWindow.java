package smith.james.chris.window;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

public class BasicWindow extends Window{
	protected GLCanvas canvas;

	// Construct a window, with OpenGL Canvas object
	public BasicWindow(String title, int width, int height) {
		super(title, width, height);
	
        GLCapabilities caps = new GLCapabilities();
        canvas = new GLCanvas(caps);
        
        add(canvas);
	}

	public GLAutoDrawable getAutoDrawable(){
		return canvas;
	}
}
