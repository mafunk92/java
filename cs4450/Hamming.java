/**
 * <pre>
 * Author:
 * 		Michael Funk
 * Description:
 * 		Convert string to binary and send through "channel" then converted back to string
 *    1 char sent through channel at a time, with a 50% chance only 1 bit is flipped
 *    Using the Hamming ECC
 * Compiles: yes
 * Works: yes
 *
 * </pre>
 */

 import java.util.*;
 import java.io.*;
 import java.math.*;

 public class Hamming
 {
   //Variables
   private static boolean debug = false;
   private static ArrayList<int[]> iBinary = new ArrayList<int[]>();
   private static ArrayList<int[]> iHamming = new ArrayList<int[]>();
   private static ArrayList<int[]> send = new ArrayList<int[]>();
   private static ArrayList<int[]> oHamming = new ArrayList<int[]>();
   private static ArrayList<int[]> oBinary = new ArrayList<int[]>();

   //Constructor
   public Hamming()
   {
   } // End Constructor

   //Convert String to Binary
   private void convertString(String s)
   {
      int block = 8;
      byte[] bytes = s.getBytes();
      if(debug == true) System.out.println(Arrays.toString(bytes));
      StringBuilder binary = new StringBuilder();

      for(byte b : bytes)
      {
        int val = b;
        for(int i=0;i<block; i++)
        {
          binary.append((val & 128) == 0 ? 0 : 1);
          val <<= 1;
        } // End for
      } // End for

      if(debug == true)
      {
        System.out.println(s + " to binary: " + binary);
        System.out.println("length: " + binary.length() + ", " + binary.length()/block);
      } // End if

      do
      {
        int[] intarray = new int[block];
        for(int i=0;i<block;i++)
        {
          intarray[i] = Integer.parseInt(binary.substring(0,1));
          binary.deleteCharAt(0);
        }
        if(debug == true) System.out.println(Arrays.toString(intarray));
        iBinary.add(intarray);
      } while(binary.length() >0);

      if(debug == true) System.out.println("iBinary size: " + iBinary.size());

      System.out.println("Message converted to binary:(Binary in reverse order) ");

      for(int i=0; i<iBinary.size();i++)
      {
        System.out.println(i + ":\t" + Arrays.toString(iBinary.get(i)));
      }
   } // End convertString

   // Convert Binary to Hamming ECC
   private void convertInHamming()
   {
     for(int i = 0; i<iBinary.size();i++)
     {
       int[] input = iBinary.get(i);
       int[] hamming = new int[13];
       hamming[3] = input[0]; // Data 1
       hamming[5] = input[1]; // Data 2
       hamming[6] = input[2]; // Data 3
       hamming[7] = input[3]; // Data 4
       hamming[9] = input[4]; // Data 5
       hamming[10] = input[5]; // Data 6
       hamming[11] = input[6]; // Data 7
       hamming[12] = input[7]; // Data 8
       hamming[1] = (hamming[3]+hamming[5]+hamming[7]+hamming[9]+hamming[11])%2; //Error 1
       hamming[2] = (hamming[3]+hamming[6]+hamming[7]+hamming[10]+hamming[11])%2; //Error 2
       hamming[4] = (hamming[5]+hamming[6]+hamming[7]+hamming[12])%2; //Error 3
       hamming[8] = (hamming[9]+hamming[10]+hamming[11]+hamming[12])%2; //Error 4
       hamming[0] = (hamming[1]+hamming[2]+hamming[4]+hamming[8])%2; //Error Parady
       iHamming.add(hamming);
    } // End for

     System.out.println("Binary converted to Hamming ECC:");

     for(int i=0; i<iHamming.size();i++)
     {
       System.out.println(i + ":\t" + Arrays.toString(iHamming.get(i)));
     }
   } // End convertInHamming

   // Simulate sending msg though a channel 1 char at a time
   // With a 50% chance for 1 bit to be flipped
   private void send()
   {
     System.out.println("Sending data:");

     for(int i=0;i<iHamming.size();i++)
     {
        int[] s = iHamming.get(i);
        Random rand1 = new Random();
        Random rand2 = new Random();
        int randIx = rand1.nextInt(13);
        int randFlip = rand2.nextInt(2);

        if(randFlip == 1)
        {
          s[randIx] = (s[randIx] + 1) % 2;
          if(randIx != 0) System.out.println("Bit flipped, Char: " + i);
        } // End if

        send.add(s);

       if(debug==true)
       {
         System.out.println(i + " - Location");
         System.out.println(randIx + ", " + randFlip);
         System.out.println(Arrays.toString(s));
       }
     } // End for


     System.out.println("Hamming ECC recieved through channel:");

     for(int i=0; i<send.size();i++)
     {
       System.out.println(i + ":\t" + Arrays.toString(send.get(i)));
     }
   } // End send

   private void errorCorrection()
   {
     System.out.println("Error correction:");

     for(int i =0; i<send.size(); i++)
     {
       int[] s = send.get(i);
       int[] parity1 = new int[4];
       int[] parity2 = new int[4];
       int[] dif = new int[4];
       StringBuilder str = new StringBuilder();

       parity1[0] =s[1]; parity1[1] = s[2]; parity1[2] = s[4]; parity1[3] = s[8];
       parity2[0] = (s[3]+s[5]+s[7]+s[9]+s[11])%2;
       parity2[1] = (s[3]+s[6]+s[7]+s[10]+s[11])%2;
       parity2[2] = (s[5]+s[6]+s[7]+s[12])%2;
       parity2[3] = (s[9]+s[10]+s[11]+s[12])%2;

       for(int j=0; j<4;j++)
       {
         dif[j] = (parity1[j] + parity2[j])%2;
       } // End for

       int[] difR = dif;

       for(int n=0; n<2;n++) // reverse order of dif
       {
         int temp = difR[n];
         difR[n] = difR[difR.length-n-1];
         difR[difR.length-n-1] = temp;
       } // End for

       for(int k=0;k<4;k++)
       {
         str.append(difR[k]);
       } // End for

       String d = str.toString();
       int ix = Integer.parseInt(d,2);

       if(ix !=0)
       {
         System.out.println("Error detected, Char:\t" + i + " Index:\t" + ix);
       } // End if
       if(debug==true)
       {
         System.out.println(i + " - Location");
         System.out.println(Arrays.toString(parity1));
         System.out.println(Arrays.toString(parity2));
         System.out.println(Arrays.toString(dif) + ", " + ix);
       } // End if

       s[ix] =(s[ix] +1)%2;
       oHamming.add(s);
     } // End for
   } // End errorCorrection

   // Convert Hamming ECC to Binary
   private void convertOutHamming()
   {
     for(int i=0;i<oHamming.size();i++)
     {
       int[] output = send.get(i);
       int[] binary = new int[8];
       binary[0] = output[3];
       binary[1] = output[5];
       binary[2] = output[6];
       binary[3] = output[7];
       binary[4] = output[9];
       binary[5] = output[10];
       binary[6] = output[11];
       binary[7] = output[12];
       oBinary.add(binary);
     } // End for

     System.out.println("Hamming ECC converted to binary:(Binary in reverse order) ");

     for(int i=0; i<oBinary.size();i++)
     {
       System.out.println(i + ":\t" + Arrays.toString(oBinary.get(i)));
     }
   } // End convertOutHamming

   //Convert Binary to String
   private void convertBinary()
   {
     StringBuilder s = new StringBuilder();
     StringBuilder out = new StringBuilder();

     for(int i=0;i<oBinary.size();i++)
     {
       for(int j : oBinary.get(i))
       {
         s.append(j);
       }
       String o = s.toString();
       s.setLength(0);
      int op = Integer.parseInt(o,2);
      out.append(Character.toChars(op));
     }
     System.out.println("Binary converted to message:");
     System.out.println(out);
   } // End convertBinary

   public static void main(String[] args)
   {
      Hamming obj = new Hamming();
      String input = new String();
      if(debug == true) System.out.println("Args length: " + args.length);

      if(args.length == 0)
      {
        System.out.println("Please enter what you want to be transmitted:");
        System.out.println();
        Scanner user_input = new Scanner(System.in);
        input = user_input.nextLine();
      } // End if
      else
      {
        for(int i=0;i<args.length;i++)
        {
          input = input + args[i] + " ";
        } // End for
        input = input.substring(0,input.length()-1);
      } // End else

      if(debug == true) System.out.println("Input: " + input + ".");
      obj.convertString(input); // working
      obj.convertInHamming(); // working
      obj.send();
      obj.errorCorrection();
      obj.convertOutHamming(); // working
      obj.convertBinary(); // working
   } // End main
 } // End Hamming
