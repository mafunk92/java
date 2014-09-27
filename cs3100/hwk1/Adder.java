/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		This program sums from 1 to user's integer input.
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */
 
import java.util.Scanner;

public class Adder
{

    public static void main(String[] args) 
    {
		// Integer for users input
        int inp = 0;
        //Integer to sum up to users input
        int sum = 0;
        
        System.out.println("Welcome to Adder. \n");
        System.out.println("Please enter an integer that you would like to add up to : ");
        
        Scanner inputReader = new Scanner(System.in);
        
       inp = inputReader.nextInt();
       
       for (int i=0;i<=inp; i++)
       {
		   sum+=i;
	   }
	   
	   System.out.println("The sum from 1 to " + inp + " is " + sum + ".");
    }

}
