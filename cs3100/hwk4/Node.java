/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is Node class for BST. Each node has LP, Data, and RP.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 import java.util.*;
 import java.io.*;
 
 public class Node
 {
	private int data;
	private Node rp;
	private Node lp;
	
	public Node()
	{
		data = 0;
		rp = null;
		lp = null;
	} // End Constructor
	
	public Node( int dx)
	{
		data = dx;
		rp = null;
		lp = null;
	} // End Constructor
	
	 /**
     * Desc: This method set data of node.
     * Pre: None
     * Post: Set data to dx.
     */
	public void setData(int dx)
	{
		data = dx;
	}
	
	/**
     * Desc: This method set rp of node.
     * Pre: None
     * Post: Set rp to rpx.
     */
	public void setRp(Node rpx)
	{
		rp = rpx;
	}
	
	/**
     * Desc: This method set lp of node.
     * Pre: None
     * Post: Set lp to lpx.
     */
	public void setLp(Node lpx)
	{
		lp = lpx;
	}
	
	/**
     * Desc: This method will get data.
     * Pre: None
     * Post: return data.
     */
	public int getData()
	{
		return data;
	}
	
	/**
     * Desc: This method will get rp.
     * Pre: None
     * Post: return rp.
     */
	public Node getRp()
	{
		return rp;
	}
	
	/**
     * Desc: This method will get lp.
     * Pre: None
     * Post: return lp.
     */
	public Node getLp()
	{
		return lp;
	}
	
	/**
     * Desc: This method will print data.
     * Pre: None
     * Post: Print data to screen.
     */
	public void print()
	{
		System.out.print( "  " + data);
	}
	
	public static void main(String[] args)
	{
		
	} // End main
 
 } // End Node
