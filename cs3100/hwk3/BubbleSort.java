/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This is BubbleSort. This program will intake a txt file and
 * 			sort the elements using the bubble sort method.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
 import java.util.*;
 import java.io.*;
 
 public class BubbleSort
 {
	private int[] arr;
	private int counter=0;
	private int top=0;
	
	public BubbleSort()
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
     * Desc: This method will print contents of array
     * Pre: None
     * Post: Array printed on single line
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
	
	/**
     * Desc: This method will sort array using bubble sort
     * Pre: None
     * Post: Array will ordered
     */
	public void bubble()
	{
		int temp;
		
		for( int i =0; i< top; i++)
		{
			for( int j=0; j<top-1; j++)
			{
				if( arr[j] > arr[j+1])
				{
					temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1] = temp;
					counter++;
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		BubbleSort  list = new BubbleSort();
		String file = "data2.txt" ;
		int inp;
			
		list.add(file);
		
		Scanner inputReader = new Scanner(System.in);
				
		System.out.print("Unsorted list:");
		list.print();
		System.out.println("");
		
		list.bubble();
		System.out.print("Sorted list:");
		list.print();
		System.out.println("");
		list.getCount();
		
	} // End main
 
 } // End BubbleSort
