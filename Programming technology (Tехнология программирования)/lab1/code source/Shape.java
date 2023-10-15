package System;


/**
 * @author qeeqez
 * @version 1.0
 * @created 21-фев-2023 15:49:53
 */
public abstract class Shape {

	private Point theCenter;
	private Color theColor;

	public Shape(){

	}

	public void finalize() throws Throwable {

	}
	public abstract void draw();

	public Point getLocation(){
		return null;
	}

	/**
	 * 
	 * @param p
	 */
	public abstract void move(Point p);
}//end Shape