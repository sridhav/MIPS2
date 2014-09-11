/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import static mips.MIPS.baseFile;

/**
 *
 * @author Sridhar
 */
public class MIPSCore {

    /**
     * @param args the command line arguments
     */
    public static double noOfCores=4;
    public static double CPI=1.3;
    public static double speedUpInc=1.1;
    
    static String baseFile = "./out/";
    static int m = 0;
    static String[] z = {"LoadStore.txt", "MemoryMemory.txt", "Stack.txt", "Accumulator.txt", "twoAddress.txt", "twoReg.txt"};
   
     static String[] z2 = {"LoadStoreCores.txt", "MemoryMemoryCores.txt", "StackCores.txt", "AccumulatorCores.txt", "twoAddressCores.txt", "twoRegCores.txt"};
   
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File files2[]=new File[z.length];
        for(int i=0;i<z.length;i++){
            files2[i] = new File(baseFile + "" + "all2" + "/" + z2[i]);
                      if (!files2[i].exists()) {
                            File temp=new File(baseFile+""+"all2"+"/");
                            temp.mkdirs();
                            files2[i].createNewFile();
                      }
                      
        }
        
        MIPS.helloWorld(args[0]);
        String view[]=new String[(int)noOfCores];
       
        for(int i=0;i<z.length;i++){
                    
                     for(int k=0;k<view.length;k++){
                            view[k]="";
                    }
                      File files = new File(baseFile + "" + "all" + "/" + z[i]);
                      
                      FileReader xx=new FileReader(files);
                      BufferedReader red=new BufferedReader(xx);
                      String s=null;
                      int j=0;
                      while((s=red.readLine())!=null){   
                          if(!s.equals("")){
                             view[j%(int)noOfCores]=view[j%(int)noOfCores]+s+"\n";
                             j++;
                          }  
                        }
                      
                      FileWriter ff=new FileWriter(files2[i],false);
                       BufferedWriter bw = new BufferedWriter(ff);
                      
                      for(int k=0;k<view.length;k++){
                          
                         bw.write("\n\n#"+k+"#************ Core "+k +"***********\n");
                         bw.write(view[k]);
                         bw.write("***************************\n");
                         
                    }
                      bw.close();
        }
        

    }
}
