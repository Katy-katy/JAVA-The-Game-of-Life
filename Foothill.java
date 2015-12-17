/*  Source program for Assignment_3 for CS 1B
 *  Written by Ekaterina Tcareva, 01/27/2014
 */
import java.util.Scanner;

public class Foothill
{
   public static void main(String[] args)
   {
      int rule, k;
      String strUserIn;
      
      Scanner inputStream = new Scanner(System.in);
      Automaton aut;

      // get rule from user
      do
      {
         System.out.print("Enter Rule (0 - 255): ");
         // get the answer in the form of a string:
         strUserIn = inputStream.nextLine();
         // and convert it to a number so we can compute:
         rule = Integer.parseInt(strUserIn);

      } while (rule < 0 || rule > 255);

      // create automaton with this rule and single central dot
      aut = new Automaton(rule);

      // now show it
      System.out.println("   start");
      for (k = 0; k < 100; k++)
      {
         System.out.println(aut.toStringCurrentGen() );
         aut.propagateNewGeneration();
      }
      System.out.println("   end");      
      inputStream.close();
   }
}

class Automaton
{
   // class constants
   public final static int MAX_DISPLAY_WIDTH = 121;
   
   // private members
   private boolean rules[];  // allocate rules[8] in constructor!
   private String thisGen;   // same here
   String extremeBit;// bit, "*" or " ", implied everywhere "outside"
   int displayWidth;// an odd number so it can be perfectly centered
  
   
   // public constructors, mutators, etc. (need to be written)
   public Automaton(int new_rule)
   {
      thisGen = " * ";
      extremeBit = " ";
      setDisplayWidth(79);      
      rules = new boolean[8];
      SetRule(new_rule);
   }

   public void resetFirstGen()
   {
      thisGen = " * ";
   }
   public boolean SetRule(int new_rule)
   {
      boolean valid;
      if (new_rule < 0 || new_rule > 255)
         valid = false;
      else
      {
         String rule;
         rule = Integer.toBinaryString(new_rule);
         if (rule.length() < 8)
         {   
            int addChar = 8 - rule.length();
            for (int k = 0; k < addChar; k++)
               rule = "0" + rule; 
         }         
         for (int k = 0; k < rules.length; k++)
         {
            if ( rule.charAt(k) == '0')
               rules[rules.length - k - 1] = false;
            else
               rules[rules.length - k - 1] = true;
         }          
         valid = true;
      }
      return valid;
   }
   
   public boolean setDisplayWidth(int width)
   {
      boolean valid;
      if ((width <= 0 || width > MAX_DISPLAY_WIDTH ) || (width % 2 == 0))
         valid = false;
      else
      {
         displayWidth = width;         
         valid = true;
      }
      return valid;
   }
   
   public String toStringCurrentGen()
   {      
      String gen = "";
      if (thisGen.length() == displayWidth)
         gen = thisGen;
      
      else if (thisGen.length() < displayWidth)
      {         
         int addBits = (displayWidth - thisGen.length()) / 2;
         for (int k = 0; k < addBits; k++)
            gen = gen + extremeBit;
         
         gen = gen + thisGen;
         
         for (int k = 0; k < addBits; k++)
            gen = gen + extremeBit;         
      }
      else // (thisGen.length() > displayWidth) 
      {
         int substructBits = (thisGen.length() - displayWidth) / 2;
         gen = thisGen.substring(substructBits, (substructBits + displayWidth));         
      }      
      return gen;
   }   
   
   public void propagateNewGeneration()
   {      
      String nextGen ="";      
      thisGen = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;
      
      thisGen = thisGen.replace('*','1');
      thisGen = thisGen.replace(' ','0');
      
      int number;
      String numberString;
      for(int k = 0; k < thisGen.length() - 2; k++)
      {   
          numberString = thisGen.substring (k, k+3);
          
          number = Integer.parseInt(numberString, 2);
          
          if (rules[number])
             nextGen = nextGen + "*";
          else
             nextGen = nextGen + " ";          
      }
      thisGen = nextGen;
            
      if (extremeBit == " " && rules[0] == true)
         extremeBit = "*";
      if (extremeBit == "*" && rules[7] == false)
         extremeBit = " ";
   }
}
/*------------------run 1--------------------------------------
Enter Rule (0 - 255): 300
Enter Rule (0 - 255): -9
Enter Rule (0 - 255): 4
   start
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
                                       *                                       
   end
--------------------run 2--------------------------------------
Enter Rule (0 - 255): 126
   start
                                       *                                       
                                      ***                                      
                                     ** **                                     
                                    *******                                    
                                   **     **                                   
                                  ****   ****                                  
                                 **  ** **  **                                 
                                ***************                                
                               **             **                               
                              ****           ****                              
                             **  **         **  **                             
                            ********       ********                            
                           **      **     **      **                           
                          ****    ****   ****    ****                          
                         **  **  **  ** **  **  **  **                         
                        *******************************                        
                       **                             **                       
                      ****                           ****                      
                     **  **                         **  **                     
                    ********                       ********                    
                   **      **                     **      **                   
                  ****    ****                   ****    ****                  
                 **  **  **  **                 **  **  **  **                 
                ****************               ****************                
               **              **             **              **               
              ****            ****           ****            ****              
             **  **          **  **         **  **          **  **             
            ********        ********       ********        ********            
           **      **      **      **     **      **      **      **           
          ****    ****    ****    ****   ****    ****    ****    ****          
         **  **  **  **  **  **  **  ** **  **  **  **  **  **  **  **         
        ***************************************************************        
       **                                                             **       
      ****                                                           ****      
     **  **                                                         **  **     
    ********                                                       ********    
   **      **                                                     **      **   
  ****    ****                                                   ****    ****  
 **  **  **  **                                                 **  **  **  ** 
****************                                               ****************
*              **                                             **              *
**            ****                                           ****            **
 **          **  **                                         **  **          ** 
****        ********                                       ********        ****
   **      **      **                                     **      **      **   
  ****    ****    ****                                   ****    ****    ****  
 **  **  **  **  **  **                                 **  **  **  **  **  ** 
************************                               ************************
                       **                             **                       
                      ****                           ****                      
                     **  **                         **  **                     
                    ********                       ********                    
                   **      **                     **      **                   
                  ****    ****                   ****    ****                  
                 **  **  **  **                 **  **  **  **                 
                ****************               ****************                
*              **              **             **              **              *
**            ****            ****           ****            ****            **
 **          **  **          **  **         **  **          **  **          ** 
****        ********        ********       ********        ********        ****
   **      **      **      **      **     **      **      **      **      **   
  ****    ****    ****    ****    ****   ****    ****    ****    ****    ****  
 **  **  **  **  **  **  **  **  **  ** **  **  **  **  **  **  **  **  **  ** 
*******************************************************************************
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
*                                                                             *
**                                                                           **
 **                                                                         ** 
****                                                                       ****
   **                                                                     **   
  ****                                                                   ****  
 **  **                                                                 **  ** 
********                                                               ********
       **                                                             **       
      ****                                                           ****      
     **  **                                                         **  **     
    ********                                                       ********    
   end

--------------------run 3--------------------------------------
Enter Rule (0 - 255): 130
   start
                                       *                                       
                                      *                                        
                                     *                                         
                                    *                                          
                                   *                                           
                                  *                                            
                                 *                                             
                                *                                              
                               *                                               
                              *                                                
                             *                                                 
                            *                                                  
                           *                                                   
                          *                                                    
                         *                                                     
                        *                                                      
                       *                                                       
                      *                                                        
                     *                                                         
                    *                                                          
                   *                                                           
                  *                                                            
                 *                                                             
                *                                                              
               *                                                               
              *                                                                
             *                                                                 
            *                                                                  
           *                                                                   
          *                                                                    
         *                                                                     
        *                                                                      
       *                                                                       
      *                                                                        
     *                                                                         
    *                                                                          
   *                                                                           
  *                                                                            
 *                                                                             
*                                                                              
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
                                                                               
   end
--------------------run 4--------------------------------------

-------------------------------------------------------------*/