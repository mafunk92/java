/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is ArrayDrawer. It will create Geoms like Triangle and Rectangle and store them into an array.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 import java.util.*;
 import java.io.*;
 
 public class ArrayDrawer
 {
	private Geom[] geoms;
	
	//Constructor
	public ArrayDrawer()
	{
		geoms = new Geom[10];
	}// End Constructor
	
	/**
     * Desc: This method add a Geom to the array.
     * Pre: None
     * Post: Geom added to array.
     */
	public void addGeom(Geom g)
	{
		for (int i = 0; i < geoms.length; i++)
		{
            if (geoms[i] == null)
            {
                 geoms[i] = g;
                 return;
            }  
      	}
      	System.out.println("ArrayDrawer is full");
      	
	}// End addGeom
	
	/**
     * Desc: This method will delete Geom with designated base.
     * Pre: None
     * Post: Geom removed from array.
     */
	public void deleteGeom(int basex)
	{
		for(int i = 0; i< geoms.length;i++)
		{
			if(geoms[i].getBase() == basex)
			{
				geoms[i] = null;
				return;
			}
		}
		System.out.println("Not Found");
	}// End deleteGeom
	
	/**
     * Desc: This method will print contents of array.
     * Pre: None
     * Post: Printed array elements.
     */
	public void printAll()
	{
		System.out.println("Start of Array");
		
		for(int i=0; i< geoms.length;i++)
		{
			if( geoms[i] != null)
			{
				geoms[i].print();
			}
		}
		
		System.out.println("End of Array");
	}// End printAll
	
	/**
     * Desc: This method will compute area for everything in array.
     * Pre: None
     * Post: Area of whole array printed.
     */
	public void compAreaAll()
	{
		double area = 0;
		
		for(int i=0; i < geoms.length; i++)
		{
			if( geoms[i] != null)
			{
				area = area + geoms[i].computeArea();
			}
		}
		
		System.out.println("Area of everything is: " + area);
	}// End compAreaAll
	
	public static void main(String[] args)
	{
		ArrayDrawer g1 = new ArrayDrawer();
		String line = null;
		String input = "pics.txt";
		int par1 = 0;
		int par2= 0;
		try
		{
			Scanner reader = new Scanner(new FileInputStream(input));
		  
			while (reader.hasNext())
			{
				line = reader.next();
			//	System.out.println(line);
			
				if(line.equals("add"))
				{
					line = reader.next();
					if(line.equals("rec"))
					{
						par1 = reader.nextInt();
						par2 = reader.nextInt();
						
						g1.addGeom(new Rectangle(par1,par2));
					}
					
					if(line.equals("tri"))
					{
						par1 = reader.nextInt();
						par2 = reader.nextInt();
						
						g1.addGeom(new Triangle(par1,par2));
					}
					
				}
				
				if(line.equals("compAreaAll"))
				{
					g1.compAreaAll();
				}
				
				if(line.equals("del"))
				{
					par1 = reader.nextInt();
					g1.deleteGeom(par1);
				}
				
				if(line.equals("printAll"))
				{
					g1.printAll();
				}

			}
		}

		catch (Exception e)
		{//Catch exception if any

		System.err.println("Error: " + e.getMessage());
			
		}	
	
	}// End main
 }// End ArrayDrawer
