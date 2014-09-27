/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is a parent class : Geom. It will be the parent to Triangle and Rectangle
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 public abstract class Geom
 {
	 protected int base;
	 protected int height;
	 
	 // Constructor
	 public Geom()
	 {
		 base = 0;
		 height = 0;
	 }// End Constructor
	 
	/**
     * Desc: This method will get base.
     * Pre: None
     * Post: Return ase.
     */
	 public int getBase()
	 {
		 return base;
	 }// End getBase
	 
	/**
     * Desc: This method will print data.
     * Pre: None
     * Post: Printed data.
     */
	 public abstract void print();
	 
	/**
     * Desc: This method will compute area.
     * Pre: None
     * Post: Printed area.
     */
	 public abstract double computeArea();
 
 }// End Geom Class
