/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is BinSearch. It will recursively search an array that can
 * 			hold up to 20 elements. It will return the index of item
 * 			being searched for.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 import java.util.*;
 import java.io.*;
 
 public class BinSearch
 {
	private int[] arr;
	private int counter=0;
	private int top=0;
	
	public BinSearch()
	{
		arr = new int[20];
	} // End Constructor
	
	/**
     * Desc: This method will add integers from txt file to arr.
     * Pre: None
     * Post: Text file elements added to array
     */
	public void add(String inp)
	{
		int line;
		
		try
		{
			Scanner scan = new Scanner(new FileInputStream(inp));
			
			while (scan.hasNextInt())
			{
				line = scan.nextInt();
				arr[top] = line;
				top++;
			}
			
			
		}
		
		catch (Exception e)
		{//Catch exception if any

		System.err.println("Error: " + e.getMessage());
			
		}
		
	} // End add
	

	/**
     * Desc: This method will search for element recursivly in Binary
     * 		search
     * Pre: None
     * Post: Return index if in array, else return -1
     */
	public int searchRec2(int low, int high, int dx, int[] a)
	{
		int mid = 0;
		
		if (low > high)
		{
			counter++;
			return(-1);
		}
		
		mid = ((high+low)/2);
		
		if(dx == a[mid])
		{
			return mid;
		}
		if(dx < a[mid])
		{
			counter++;
			return(searchRec2(low,mid-1,dx,a));
		}
		
		if(dx > a[mid])
		{
			counter++;
			return(searchRec2(mid+1,high,dx,a));
		}	
		
		
		return(-1);
	}
	
	/**
     * Desc: Initiates recursive binary search.
     * Pre: None
     * Post: Prints index of found element.
     */
	public void searchRec1(int dx)
	{
		int low=0;
		int high=top-1;
		int index=( searchRec2(low,high,dx,arr));
		
		if(index == -1)
		{
			System.out.println("Value not found");
		}
		else
		{
		System.out.println("The index for the value is: " + index);
		}
	}
	
	/**
     * Desc: This method will print elements of array
     * Pre: None
     * Post: Prints out array elements on single line
     */
	public void print()
	{
		for(int i=0;i<arr.length;i++)
		{
			if( arr[i] != 0)
			{
				System.out.print("  " + arr[i]);
			}
		}
	} // End print
	
	/**
     * Desc: This method will print count of compares
     * Pre: None
     * Post: Prints count
     */
	public void getCount()
	{
		System.out.println( "Number of Compares: "+counter);
	}

	public static void main(String[] args)
	{
		BinSearch  list = new BinSearch();
		String file = "data.txt" ;
		int val;
			
		list.add(file);
		
		Scanner inputReader = new Scanner(System.in);
				

		list.print();
		System.out.println("");
		
		System.out.print("Enter value to search for in array: ");
		val = inputReader.nextInt();
		list.searchRec1(val);
		list.getCount();
		

	} // End Main
 } // End BinSearch
