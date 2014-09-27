/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This program uses an array to store location and name of child on a bus with 10
 * 			available seats total. Methods available are: getOn, findFirstEmpty, print,
 * 			getOff, and swap.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
public class Bus 
{
	//Data
	private String names[];
	
	//Constructor
	public Bus()
	{
		names =  new String[10];
	}
	
	//Work Methods
    /**
     * Desc: This method will add child to array in first empty index in arary.
     * Pre: None
     * Post: Child will be located within array.
     */
    public void getOn(String name)
    {
        int x = findFirstEmpty();

        if (x >= 0)
        {
            names[x] = name;
        }
        else
        {
            System.out.println("There was an error.");
        }
    } // End getOn
         
    /**
     * Desc: This method will find the first empty position in array
     * Pre: None
     * Post: If array is full, return -1; If array is not full
     * 			return first null index in array.
     */
    public int findFirstEmpty()
    {  
        for (int i = 0; i <= names.length; i++)
		{
            if (names[i] == null)
            {
                return i; 
            }  
      	}

        return -1;   
    } // End findFirstEmtpy
 
    /**
     * Desc: This method will print the array indexes that are not null.
     * Pre: None
     * Post: Child and seat position printed.
     */
    public void print()
    {
        //Print out the array and the position of the item in the array.    
        for (int i = 0; i < names.length; i++)
        {
           if (names[i] != null)
           {
               System.out.println(i + " , " + names[i]);
           }
       }     
    } // End Print
    
    
    /**
     * Desc: This method will remove child from array
     * Pre: None
     * Post: The array index where the child was is null.
     */
    public void getOff(String name)
    {
       for(int i=0;i < names.length;i++)
       { 
         if (name.equals(names[i]))
         {
              //Removes person from array    
               names[i] = null;
         }
       }
    } // End getOff
       
    /**
     * Desc: This method will swap two children's seating arrangement
     * Pre: None
     * Post: The children being swapped will be located in different seats.
     */
    public void swap(String name1, String name2)
    {
        int x = -1; //holder of index of name1
        int y = -1; //holder of index of name2
 
        for(int i = 0;i < names.length;i++)
       { 
		   if (name1.equals(names[i]))
           {
			 x = i;
		   }
	   }
	   
	   for(int j = 0;j< names.length;j++)
       { 
		   if (name2.equals(names[j]))
           {
			 y = j;
		   }
	   }
		names[x] = name2;
		names[y] = name1;
    } // End swap
    
    public static void main(String[] args) 
    {
		//Create new Bus array
        Bus b1 = new Bus();
        System.out.println("The bus is ready to be loaded.");
       
		//Add children to bus
        b1.getOn("joe");
        b1.getOn("jeff");
        b1.getOn("erica");
        b1.getOn("bob");
	
		//Print
        System.out.println("After loading kids.");
        b1.print();
        System.out.println();
        
		//Swap jeff and bob
        b1.swap("jeff", "bob");

		//Print
        System.out.println("After swapping jeff and bob.");
        b1.print();
        System.out.println();
        
		// Erica and bob get off
        b1.getOff("erica");
        b1.getOff("bob");
        
		//Print
        System.out.println("After bob left.");
        b1.print();
        System.out.println();
        
		//Nancy gets on
        b1.getOn("nancy");

		//Print
        System.out.println("After nancy came on.");
        b1.print();
        System.out.println();
    } // End main
} // End Bus
