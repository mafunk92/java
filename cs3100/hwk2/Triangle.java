/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is a child class : Triangle. It's parent is Geom.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 public class Triangle extends Geom
 {
	 // Constructor
	 public Triangle()
	 {
		 base = 0;
		 height = 0;
	 }// End Constructor
	 
	/**
     * Desc: This method will create a Triangle with base and height.
     * Pre: None
     * Post: Triangle created.
     */
	 public Triangle( int b, int h)
	 {
		 base = b;
		 height = h;
	 }// End Triangle
	 
	/**
     * Desc: This method will compute area.
     * Pre: None
     * Post: return area.
     */
	 public double computeArea()
	 {
		 return (base * height *.5);
	 }// End computeArea
	 
	/**
     * Desc: This method will print data.
     * Pre: None
     * Post: Printed data.
     */
	 public void print()
	 {
		 System.out.println( "Triangle; Base: " + base + ", Height: " + height + ", Area: " + computeArea());
	 }// End print
 
 }// End Triangle Class
