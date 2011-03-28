package smith.james.chris.camera;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import smith.james.chris.maths.Vector;

public class PlayerCam extends Camera{
	private float xRot = 0, yRot = 0;
	private GLU glu = new GLU();
	
	public PlayerCam(Vector location){
		super(location);
	}
	
	public void position(GL gl) {
		gl.glRotatef(xRot, 1, 0, 0);
		gl.glRotatef(yRot, 0, 1, 0);
		
		gl.glTranslatef(pos.getX() * .02f, pos.getY() * .02f, pos.getZ() * .02f);
	}
	
	public void forward(){
		float xrotrad, yrotrad;
	    yrotrad = (float) (yRot / 180 * Math.PI);
	    xrotrad = (float) (xRot / 180 * Math.PI);
	    pos.setX((float) (pos.getX() - Math.sin(yrotrad)));
	    pos.setZ((float) (pos.getZ() + Math.cos(yrotrad)));
	    pos.setY((float) (pos.getY() + Math.sin(xrotrad)));
	}
	
	public void back(){
		float xrotrad, yrotrad;
	    yrotrad = (float) (yRot / 180 * Math.PI);
	    xrotrad = (float) (xRot / 180 * Math.PI);
	    pos.setX((float) (pos.getX() + Math.sin(yrotrad)));
	    pos.setZ((float) (pos.getZ() - Math.cos(yrotrad)));
	    pos.setY((float) (pos.getY() - Math.sin(xrotrad)));
	}
	
	public void left(){
		if((yRot-=.02) < -360)
			yRot += 360;
	}
	
	public void right(){
		if((yRot+=.02) > 0)
			yRot -= 360;
	}
}
