package smith.james.chris.camera;

import smith.james.chris.maths.Vector;

public class Camera {
	protected Vector pos;
	
	public Camera(Vector location){
		pos = location;
	}
	
	public Vector getPos(){
		return pos;
	}
}
