package smith.james.chris.maths;

public class Vector {
	private float x, y, z;
	
	public Vector(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX(){
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public void set(Vector v){
		x = v.getX();
		y = v.getY();
		z = v.getZ();
	}
	
	public Vector add(Vector v){
		return new Vector(x + v.getX(),
					      y + v.getY(),
				          z + v.getZ());
	}
	
	public Vector sub(Vector v){
		return new Vector(x - v.getX(),
					      y - v.getY(),
				          z - v.getZ());
	}
	
	public Vector normalized(){
		float len = (float) Math.sqrt( (x*x) + (y*y) + (z*z) );
		return new Vector(x/len, y/len, z/len);
	}
}
