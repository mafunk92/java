/**
 * <pre>
 * 
 * Author:
 * 		Michael Funk
 *  	
 * Description: 
 * 		Encrypts and Decrypts text using the playfair cipher
 * 
 * Compiles: yes
 * Works: yes 
 * 
 * </pre>
 */

 import java.util.*;
 import java.io.*;
 
 public class Playfair
 {
	 //Variables
	 private static boolean debug = false; // debug flag
	 private static char key[][] = new char[5][5];
	 private static String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	 private static String encM = new String();		//cipher text
	 private static	String decM = new String();		//clear text
  
	 //Constructors
	 public Playfair()
	 {
	 } // End Constructor
	 
	 //Methods	 
	 public void createKey(String k)
	 {
		 String combined = new String();
		 combined = k + alphabet;
		 	 
         String noDupes=""; //string with no duplicates
         HashMap<Integer,Character> tc = new HashMap<Integer,Character>();//create a hashmap to store the char's
         char [] charArray = combined.toCharArray();
         for (Character c : charArray)//for each char
         {
             if (!tc.containsValue(c))//if the char is not already in the hashmap
                 {
                     noDupes=noDupes + c.toString();   //add the char to the output string
                     tc.put(c.hashCode(),c); //and add the char to the hashmap
                 }
         }
         
         int matrixCount = 0;
		 for(int m=0; m<=4; m++) // to create matrix, row
		 {
			for(int n=0; n<=4; n++)
			{
				key[m][n] = noDupes.charAt(matrixCount++);
			}
		 } // end for
		  
		 if(debug == true)
		 {
			 System.out.println("Entering createKey()");
			 System.out.println("Key in createKey: " + k);
			 System.out.println("");
			 System.out.println(combined);
			 System.out.println(noDupes);
			 System.out.println("");
			 System.out.println("- - Key - -");
			 
			 for(int m=0; m<5; m++)
			 {
				 for(int n=0; n<5; n++)
				 {
					System.out.print(" " + key[m][n]);
				}
				System.out.println("");
			 }

			System.out.println("");
			System.out.println("End createKey()");
		 } // End debug if
	 } // End createKey; creates key matrix
	 
	 public int[] findIndex(char c)
	 {
		 int pair[] = {-1,-1}; // row,column
		 
		 for(int m=0; m<=4; m++) // row
		 {
			for(int n=0; n<=4; n++) // column
			{
				if(key[m][n] == c)
				{
					pair[0] = m;
					pair[1] = n;
				} // End if; records index
			}	
		 } // end for; searches matrix for char index
		 
		 if(debug == true) System.out.println("In findIndex, " + c + " : " + pair[0] + " , " + pair[1]);
		 return pair;
	 } // End findIndex
	 
	 public void transcribe(char ins,String m)
	 {
		 String converted = new String(); // final converted msg/cipher
		 ArrayList<Character> msg = new ArrayList<Character>();
		 int fIndex[] = new int[2];
		 int sIndex[] = new int[2];
		  
		 if(m.length() %2 == 1)
		 {
			 m = m + "X";
		 } // End if; checks if string is odd; if true adds 'X' to end
		 
		 m = m.replaceAll("J","I");
		 
		 for(char c : m.toCharArray())
		 {
			 msg.add(c);
		 } // End for; puts string into ArrayList for easier handling of pairs
		  
		 if( ins == 'e')
		 {
			do
			{
				 fIndex = findIndex(msg.get(0));
				 sIndex = findIndex(msg.get(1));
				 
				 if(fIndex[0] == sIndex[0]) // same row
				 {
					converted = converted + key[fIndex[0]][(fIndex[1]+1)%5];
					converted = converted + key[sIndex[0]][(sIndex[1]+1)%5];
					msg.remove(0);
					msg.remove(0);
				 }
				 else if(fIndex[1] == sIndex[1]) // same column
				 {
					converted = converted + key[(fIndex[0]+1)%5][fIndex[1]];
					converted = converted + key[(sIndex[0]+1)%5][sIndex[1]];
					msg.remove(0);
					msg.remove(0);
				 }
				 else // box
				 {
					converted = converted + key[sIndex[0]][fIndex[1]];
					converted = converted + key[fIndex[0]][sIndex[1]];
					msg.remove(0);
					msg.remove(0);
				 }
			 }while(!msg.isEmpty());
			 
			 encM = converted;		 
		 }
		 else if( ins == 'd')
		 {
			 do
			{
				 fIndex = findIndex(msg.get(0));
				 sIndex = findIndex(msg.get(1));
				 
				 if(fIndex[0] == sIndex[0]) // same row
				 {
					converted = converted + key[fIndex[0]][(fIndex[1]+4)%5];
					converted = converted + key[sIndex[0]][(sIndex[1]+4)%5];
					msg.remove(0);
					msg.remove(0);
				 }
				 else if(fIndex[1] == sIndex[1]) // same column
				 {
					converted = converted + key[(fIndex[0]+4)%5][fIndex[1]];
					converted = converted + key[(sIndex[0]+4)%5][sIndex[1]];
					msg.remove(0);
					msg.remove(0);
				 }
				 else // box
				 {
					converted = converted + key[sIndex[0]][fIndex[1]];
					converted = converted + key[fIndex[0]][sIndex[1]];
					msg.remove(0);
					msg.remove(0);
				 }
			 }while(!msg.isEmpty());
			 
			 decM = converted;
		 } // end if
		 
		 if(debug == true)
		 {
			System.out.println("Entered transcribe");
			System.out.println(m);
			System.out.println(msg);
			System.out.println(converted);
			System.out.println("End transcribe");
			System.out.println("");
		 } // End debug if
	 } // End transcribe; converts cleartext/ciphertext
	 
	 public void output(char ins)
	 {
		 ArrayList<String> out = new ArrayList<String>();
		 
		 if(ins == 'e')
		 {
			 if(debug == true) System.out.println(encM);
			 
			 for(int i=0;i<encM.length();i=i+4)
			 {
				out.add(encM.substring(i,i+4) + " ");
			 } // End for; adds blocks of 4 char
			 
			 if(debug == true) System.out.println(out);
				 
			 try
			 {
					 PrintWriter o = new PrintWriter(new FileWriter("cipout.txt"));
					 for(int i=0;i<out.size();i++)
					 {
						 if(i%8 == 7)
						 {
							 o.print(out.get(i));
							 o.println("");
						 }
						 else
						 {
							 o.print(out.get(i));
						 }
					 }
					 o.close();
			 }
			 catch(IOException e)
			 {
				 	System.out.println(e);
			 } // End catch
		 }
		 else if(ins == 'd')
		 {
			 if(debug == true) System.out.println(decM);
			 
			 for(int i=0;i<decM.length();i=i+4)
			 {
				out.add(decM.substring(i,i+4) + " ");
			 } // End for; adds blocks of 4 char
			 
			 if(debug == true) System.out.println(out);
			 
			 try
			{
				PrintWriter o = new PrintWriter(new FileWriter("msgout.txt"));
			
				for(int i=0;i<out.size();i++)
					 {
						 if(i%8 == 7)
						 {
							 o.print(out.get(i));
							 o.println("");
						 }
						 else
						 {
							 o.print(out.get(i));
						 }
					 }
				
				o.close();
		    }
			catch(IOException e)
			{
				 System.out.println(e);
			} // End catch
		 } // End if; prints converted msg to desired file
	 } // End output; writes to .txt file
	  
	 public void encrypt(String k, String m)
	 {
		 createKey(k);
		 transcribe('e',m);
		 output('e');
	 } // End enc
	 
	 public void decrypt(String k, String c)
	 {
		 createKey(k);
		 transcribe('d',c);
		 output('d');
	 } // End dec
	 
	 //Main
	 public static void main(String[] args)
	 {
		Playfair obj = new Playfair();
		ArrayList<String> file = new ArrayList<String>();
		String key = new String();
		String msg = new String();
		
		try
		{
			Scanner reader = new Scanner(new FileInputStream(args[1]));
			
			while(reader.hasNextLine())
			{
				file.add(reader.nextLine());
			} // End while		
		} // End try
		catch(IOException e)
		{
			System.out.println(e);
		} // End catch
		
		key = file.get(0);
		for(int i=1; i<file.size(); i++)
		{
			msg = msg + file.get(i);
		} // End for; putting file msg into string
		
		key = key.replaceAll("\\s",""); // deletes white spaces
		msg = msg.replaceAll("\\s","");
			
		if(debug == true)
		{
			System.out.println("");
			System.out.println("File Contents:");
			for(int i =0; i<file.size();i++)
			{
				System.out.println(file.get(i));
			} // End for; display infile contents
			
			System.out.println("");
			System.out.println("Key: " + key);
			System.out.println("");
			System.out.println("Msg: " + msg);
			System.out.println("");
		} // End if; debuging text
			
		if(args[0].equals("enc"))
		{
			if(debug == true) System.out.println("Entering Enc()");
			obj.encrypt(key,msg);
		}
		else if(args[0].equals("dec"))
		{
			if(debug == true) System.out.println("Entering dec()");
			obj.decrypt(key,msg);
		}
		else
		{
			System.out.println("Error");
		} // End if
	 } // End main
 } // End Playfair
