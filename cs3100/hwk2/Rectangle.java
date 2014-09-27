/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is a child class Rectangle: It's parent is Geom.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 public class Rectangle extends Geom
 {
	 // Constructor
	 public Rectangle()
	 {
		 base = 0;
		 height = 0;
	 }// End Constructor
	 
	/**
     * Desc: This method will create a Rectangle with base and height.
     * Pre: None
     * Post: Rectangle created.
     */
	 public Rectangle(int b, int h)
	 {
		 base = b;
		 height = h;
	 }// End rectangle
	 
	/**
     * Desc: This method will compute area.
     * Pre: None
     * Post: Return area of rectangle.
     */
	 public double computeArea()
	 {
		 return (base * height);
	 }// End computeArea
	 
	/**
     * Desc: This method will print data.
     * Pre: None
     * Post: Printed data.
     */
	 public void print()
	 {
		 System.out.println( "Rectangle; Base: " + base + ", Height: " + height + ", Area: " + computeArea());
	 }// End print
 
 }// End Square Class
