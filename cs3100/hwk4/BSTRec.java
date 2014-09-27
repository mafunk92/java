/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is BST. Uses the Node class. Methods are :
 * 			insertR1, deleteR1, countTreeR1, printLDRR1, & addR1.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 import java.util.*;
 import java.io.*;
 
 public class BSTRec
 {
	private Node root;

	public BSTRec()
	{
		root = null;
	} // End Constructor
	
	/**
     * Desc: This method will initiate recursive function insertR2
     * Pre: None
     * Post: Initiates insertR2
     */
	public void insertR1( int val)
	{
		root = insertR2(root,val);
	}
	
	/**
     * Desc: This method will insert node into tree
     * Pre: None
     * Post: Value added to tree.
     */
	public Node insertR2(Node hp, int dx)
	{
		Node nxp;
		
		if( hp == null)
		{
			nxp = new Node(dx);
			return nxp;
		}
		
		if( dx == hp.getData())
		{
			System.out.println("Duplicate not added.");
			return hp;
		}
		
		if( dx < hp.getData())
		{
			hp.setLp( insertR2(hp.getLp(),dx));
			return hp;
		}
		
		if( dx > hp.getData())
		{
			hp.setRp( insertR2(hp.getRp(),dx));
			return hp;
		}
		return null;
	}
	
	/**
     * Desc: This method wil initiate recursive method countTreeR2
     * Pre: None
     * Post: countTreeR2 inititiated.
     */
	public void countTreeR1()
	{
		System.out.println("Tree count: " + countTreeR2(root));
	}
	
	/**
     * Desc: This method will count the nodes in the tree.
     * Pre: None
     * Post: Return count of nodes in tree.
     */
	public int countTreeR2(Node hp)
	{
		if( hp == null)
		{
			return 0;
		}
		
		return (1 + countTreeR2(hp.getLp()) + countTreeR2(hp.getRp()));
	}
	
	/**
     * Desc: This method will initiate recursive method deleteR2.
     * Pre: None
     * Post: deleteR2 initiated.
     */
	public void deleteR1(int val)
	{
		root = deleteR2(root,val);
	}
	
	/**
     * Desc: This method will delete node with value.
     * Pre: None
     * Post: Node removed from tree.
     */
	public Node deleteR2(Node hp, int dx)
	{
		Node maxN;
		
		if( hp == null)
		{
			System.out.println("Not found");
			return null;
		}
		
		if( dx < hp.getData())
		{
			hp.setLp( deleteR2(hp.getLp(), dx));
			return hp;
		}
		
		if ( dx > hp.getData())
		{
			hp.setRp( deleteR2(hp.getRp(), dx));
			return hp;
		}
		
		//dx == hp.getData
		if( (hp.getLp() == null) && (hp.getRp() == null) )
		{
			return null;
		}
		
		if( (hp.getLp() == null) && (hp.getRp() != null) )
		{
			return hp.getRp();
		}
		
		if( (hp.getLp() != null) && (hp.getRp() == null) )
		{
			return hp.getLp();
		}
		
		if( (hp.getLp() != null) && (hp.getRp() != null) )
		{
			maxN = findMax( hp.getLp());
			hp.setData(maxN.getData());
			hp.setLp( deleteR2( hp.getLp(), maxN.getData()));
			return hp;
		}
		return null;
	}
	
	/**
     * Desc: This method will find max Node
     * Pre: None
     * Post: Will return max node.
     */
	public Node findMax(Node hp)
	{
		if( hp == null)
		{
			return null;
		}
		
		if( hp.getRp() == null)
		{
			return hp;
		}
		
		return( findMax(hp.getRp()));
		
	}
	
	/**
     * Desc: This method will initiate recursive method printLDRR2.
     * Pre: None
     * Post: printLDRR2 initiated.
     */
	public void printLDRR1()
	{
		printLDRR2(root);
	}
	
	/**
     * Desc: This method will print nodes in order.
     * Pre: None
     * Post: Values of nodes printed.
     */
	public void printLDRR2(Node hp)
	{
		if( hp == null)
		{
			return;
		}
		
		printLDRR2(hp.getLp());
		hp.print();
		printLDRR2(hp.getRp());
	}
	
	/**
     * Desc: This method will dinitiate recursive method addR2.
     * Pre: None
     * Post: addR2 initiated.
     */
	public void addR1()
	{
		addR2(root);
	}
	
	/**
     * Desc: This method will add 1 to every node value.
     * Pre: None
     * Post: Every node value incremented by 1.
     */
	public void addR2(Node hp)
	{
		if( hp == null)
		{
			return;
		}
		
		hp.setData(hp.getData() + 1);
		addR2(hp.getLp());
		addR2(hp.getRp());
	}
	

	public static void main(String[] args)
	{
		BSTRec tree = new BSTRec();
		String file = "data.txt";
		
		String line;
		
		try
		{
			Scanner reader = new Scanner(new FileInputStream(file));
			
			while( reader.hasNext())
			{
				line = reader.next();
				//System.out.println(line);
				
				if( line.equals("ins"))
				{
					tree.insertR1(reader.nextInt());
				}
				
				if( line.equals("del"))
				{
					tree.deleteR1(reader.nextInt());
				}
				
				if( line.equals("printLDR"))
				{
					tree.printLDRR1();
					System.out.println("");
				}
				
				if( line.equals("add"))
				{
					tree.addR1();
				}
				
				if( line.equals("countNodes"))
				{
					tree.countTreeR1();
					tree.printLDRR1();
					System.out.println("");
				}
			}
			
			reader.close();
		}
		catch (Exception e)
		{//Catch exception if any

		System.err.println("Error: " + e.getMessage());
			
		}
	
		
	} // End main
 
 } // End BSTRec
