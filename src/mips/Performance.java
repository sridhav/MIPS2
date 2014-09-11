/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


/**
 *
 * @author root
 */
public class Performance {
    
    final int OPER_SIZE=8;
    final int REG_SIZE=5;
    final int MEM_SIZE=16;
    double stackCycles=0;
    double accumCycles=0;
    double loadStoreCycles=0;
    double memMemCycles=0;
    double twoRegCycles=0;
    double twoMemCycles=0;
    double speedUpInc;        
    double CPI;
    public Performance(double speedUpVal,double cpival){
        speedUpInc=speedUpVal;
        CPI=cpival;
        System.out.println("Operator Size : "+OPER_SIZE+" Bits");
        System.out.println("Register Size :"+REG_SIZE+" Bits");
        System.out.println("Memory Size :"+MEM_SIZE+" Bits");
        System.out.println("Speed up Increment :"+Math.round((double)(speedUpInc-1)*100)+" %");
        System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)");
        System.out.println("===========================================");
    }
    
    
    public int instructionCount(File f) throws FileNotFoundException, IOException{
        int count=0;
        
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
            if(!sr.equals("")){         
                    count++;
             }
                    
                 
       }
       return count;
    }
    
    
  public void stackPerformance(File f) throws FileNotFoundException, IOException{
        
        int count=instructionCount(f);
        stackCycles=count*CPI;
        int noOfBits=count*OPER_SIZE;
        
        int memoryAccess=0;
        
        System.out.println("No of Bits :"+noOfBits);
        
         FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
                if(sr.indexOf("PUSH")!=-1 || sr.indexOf("POP")!=-1){
                    memoryAccess++;
                }           
        }
        
        System.out.println("Memory Access : "+memoryAccess);
        System.out.println("Clock Cycles: "+stackCycles);
        
    }
    
    public void memoryPerformance(File f) throws FileNotFoundException, IOException{
        
        int count=instructionCount(f);
        memMemCycles=count*CPI;
        int noOfBits=count*((MEM_SIZE*3)+OPER_SIZE);
        
        int memoryAccess=0;
        
        System.out.println("No of Bits :"+noOfBits);
        
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
            if(!sr.equals("")){         
            
                memoryAccess+=3;
            }               
        }
                 System.out.println("Memory Access : "+memoryAccess);
                 System.out.println("Clock Cycles: "+memMemCycles);
    }
    
    public void accumPerformance(File f) throws FileNotFoundException, IOException{
        
        int count=instructionCount(f);
        accumCycles=count*CPI;
        int noOfBits=count*(OPER_SIZE+MEM_SIZE);
        
        int memoryAccess=0;
        
        System.out.println("No of Bits :"+noOfBits);
        
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
                if(!sr.equals("")){         
                
                    memoryAccess+=1;
                }           
        }
         System.out.println("Memory Access : "+memoryAccess);
         System.out.println("Clock Cycles: "+accumCycles);
    }
    
    public void twoAddPerformance(File f) throws FileNotFoundException, IOException{
        
        int count=instructionCount(f);
        twoMemCycles=count*CPI;
        
        int noOfBits=count*(OPER_SIZE+2*MEM_SIZE);
        
        int memoryAccess=0;
        
        System.out.println("No of Bits :"+noOfBits);
        
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
                 if(!sr.equals("")){         
               
                    memoryAccess+=2;
                 }          
        }
       System.out.println("Memory Access : "+memoryAccess);
       System.out.println("Clock Cycles: "+twoMemCycles);
    }
    
    public void twoRegPerformance(File f) throws FileNotFoundException, IOException{
        
        int count=instructionCount(f);
        twoRegCycles=count*CPI;
        int noOfBits=count*(OPER_SIZE+REG_SIZE+MEM_SIZE);
        
        int memoryAccess=0;
        
        System.out.println("No of Bits :"+noOfBits);
        
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
            if(!sr.equals("")){         
                  
                memoryAccess+=1;
            }               
        }
           System.out.println("Memory Access : "+memoryAccess);
           System.out.println("Clock Cycles: "+twoRegCycles);
    }
    
    public void loadStorePerformance(File f) throws FileNotFoundException, IOException{
        
        int count=instructionCount(f);
        loadStoreCycles=count*CPI;
        int memoryAccess=0;
        int noOfBits=0;
        
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
            if(!sr.equals("")){         
                
            if(sr.indexOf("LOAD")!=-1 || sr.indexOf("STORE")!=-1){
                        memoryAccess+=1;
                        noOfBits=noOfBits+OPER_SIZE+REG_SIZE+MEM_SIZE;
                    }
                    else{
                        noOfBits=noOfBits+OPER_SIZE+(3*REG_SIZE);
                    }
            }               
        }
        System.out.println("No of Bits :"+noOfBits);
        System.out.println("Memory Access :"+memoryAccess);
        System.out.println("Clock Cycles: "+loadStoreCycles);
        
    }
    
    public void speedUpVals(File f) throws FileNotFoundException, IOException{
        
         
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        int MemInst=0;
        int BranchInst=0;
        int OperInst=0;
        int JumpInst=0;
        int count=0;
        double speedUpMemInst;
        double speedUpBranchInst;
        double speedUpOperInst;
        double speedUpJumpInst;
        
        while((sr=br.readLine())!=null){
            if(!sr.equals("")){
                if(sr.indexOf("LOAD")!=-1 || sr.indexOf("STORE")!=-1 || sr.indexOf("MOV")!=-1||sr.indexOf("PUSH")!=-1||sr.indexOf("POP")!=-1){
                    MemInst++;
                }
                else if(sr.indexOf("ADD")!=-1 || sr.indexOf("SUB")!=-1 || sr.indexOf("MUL")!=-1 || sr.indexOf("DIV")!=-1){
                    OperInst++;
                }
                else if(sr.indexOf("J ")!=-1){
                    JumpInst++;
                }
                else if(sr.indexOf("BNE")!=-1 || sr.indexOf("BEQ")!=-1|| sr.indexOf("BGE")!=-1|| sr.indexOf("BLE")!=-1|| sr.indexOf("BLT")!=-1|| sr.indexOf("BGT")!=-1){
                    BranchInst++;
                }
                count++;
            }
        }
       
        
        
        speedUpMemInst=speedUp(((double)(MemInst)/(count)),speedUpInc,f.getName());
        speedUpBranchInst=speedUp(((double)(BranchInst)/(count)),speedUpInc,f.getName());
        speedUpJumpInst=speedUp(((double)(JumpInst)/(count)),speedUpInc,f.getName());
        speedUpOperInst=speedUp(((double)(OperInst)/(count)),speedUpInc,f.getName());
        
        System.out.println("Memory Speed Up for "+MemInst+" Instructions :" +speedUpMemInst);
        System.out.println("Branch Speed Up for "+BranchInst+" Instructions :" +speedUpBranchInst);
        System.out.println("Oper Speed Up for "+OperInst+" Instructions :" +speedUpOperInst);
        System.out.println("Jump Speed Up for "+JumpInst+" Instructions :" +speedUpJumpInst);
    }
    
    
    public double speedUp(double factor,double perSpeedUp,String s){
        
        double spUp=0;
        //System.out.println(factor);
        if(s.indexOf("Stack")!=-1){
         spUp=(double)((1/((1-factor)+(factor/perSpeedUp))));
        }
        else if(s.indexOf("MemoryMemory")!=-1){
         spUp=(double)((1/((1-factor)+(factor/perSpeedUp))));
        }   
        else if(s.indexOf("LoadStore")!=-1){
         spUp=(double)((1/((1-factor)+(factor/perSpeedUp))));
        }
        else if(s.indexOf("Accumulator")!=-1){
         spUp=(double)((1/((1-factor)+(factor/perSpeedUp))));
        }
        else if(s.indexOf("twoAddress")!=-1){
         spUp=(double)((1/((1-factor)+(factor/perSpeedUp))));
        }
        else if(s.indexOf("twoReg")!=-1){
         spUp=(double)((1/((1-factor)+(factor/perSpeedUp))));
        }
            return spUp;
        
        
    }
    
    public double getClockCycles(int IC){
        return IC*CPI;
    }
    
    public static void redirect(String file) throws FileNotFoundException{
        
		File fil = new File(file);
		FileOutputStream fos = new FileOutputStream(fil);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
	//	System.out.println("This goes to out.txt");        
                
    }
    
    
}
