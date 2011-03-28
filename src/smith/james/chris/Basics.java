package smith.james.chris;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Basics {
	// Draw image to screen with given texture
	public static void drawImage(GL gl, Texture texture, float s){
		texture.bind();
    	gl.glEnable(GL.GL_TEXTURE_2D); // Enable Textures
    	gl.glColor3f(1, 1, 1);
    	gl.glEnable(GL.GL_BLEND); // Enable belnding on image
    	
        gl.glBegin(GL.GL_QUADS);
	        gl.glTexCoord2f(0, 1);
	        gl.glVertex3f(-8*s, -8*s, -30);
	        gl.glTexCoord2f(1, 1);
	        gl.glVertex3f(12*s, -8*s, -30);
	        gl.glTexCoord2f(1, 0);
	        gl.glVertex3f(12*s, 12*s, -30);
	        gl.glTexCoord2f(0, 0);
	        gl.glVertex3f(-8*s, 12*s, -30);
        gl.glEnd();
        
        gl.glDisable(GL.GL_BLEND); // Disables bleding and textures
        gl.glDisable(GL.GL_TEXTURE_2D);
        texture.disable();
	}

	// Draw Triangle at X, Y
	public static void drawTriangle(GL gl, float x, float y) {
		gl.glColor3f(1, 0, 0);
		gl.glVertex3f(x, y, -30);
		gl.glColor3f(0, 0, 1);
		gl.glVertex3f(x + .5f, y + 1, -30);
		gl.glColor3f(0, 1, 0);
		gl.glVertex3f(x + 1, y, -30);
	}

	// Render arm from peacock (Push & Pop)
	public static void drawArm(GL gl, float offset, float angle1[], float x, float y) {
		for (int i = 0; i < 3; i++) {
			gl.glPushMatrix();
			gl.glTranslatef(0, offset, 0);
			gl.glRotatef(angle1[i], 0, 0, 1);
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glVertex3f(x, y, -30);
			gl.glVertex3f(x + 1, y + offset, -30);
			gl.glVertex3f(x + 2, y, -30);
			gl.glEnd();
		}
		gl.glPopMatrix();
		gl.glPopMatrix();
		gl.glPopMatrix();
	}

	// Render square with a different color at each vertex
	public static void drawSquare(GL gl, float x, float y) {
		gl.glColor3f(1, 0, 0);
		gl.glVertex3f(x, y, -30);
		gl.glColor3f(0, 0, 1);
		gl.glVertex3f(x, y + .4f, -30);
		gl.glColor3f(0, 1, 0);
		gl.glVertex3f(x + .4f, y + .4f, -30);
		gl.glColor3f(0, 1, 1);
		gl.glVertex3f(x + .4f, y, -30);
	}

	// Render standard square
	public static void drawSquareS(GL gl, float x, float y) {
		gl.glVertex3f(x, y, -30);
		gl.glVertex3f(x, y + .2f, -30);
		gl.glVertex3f(x + .2f, y + .2f, -30);
		gl.glVertex3f(x + .2f, y, -30);
	}
	
	// Read an image file, and create a Texture object
	public static Texture buildTexture(String image){
		BufferedImage img = null;
		Texture texture = null;
		try {
			img = ImageIO.read(new File(image));
		} catch (IOException e) {}
		
        texture = TextureIO.newTexture(img, false); // Load texture, apply filters
        texture.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        texture.setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        
        return texture;
	}
}