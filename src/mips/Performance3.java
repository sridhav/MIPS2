/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;


/**
 *
 * @author root
 */
public class Performance3 {
    
    final int OPER_SIZE=8;
    final int REG_SIZE=5;
    final int MEM_SIZE=16;
    double stackCycles[];
    double accumCycles[];
    double loadStoreCycles[];
    double memMemCycles[];
    double twoRegCycles[];
    double twoMemCycles[];
    double speedUpInc;        
    double CPI;
    double arr[]={1.0,2.5,1.0,2.0,1.5,1.0};
    double noOfCores;
    File filesExcel=new File("./excelSheet.txt");
    FileWriter fe=new FileWriter(filesExcel);
    BufferedWriter be=new BufferedWriter(fe);
    
    public Performance3(double speedUpVal,double cpival) throws IOException{
        speedUpInc=speedUpVal;
        noOfCores=MIPSCore.noOfCores;
        stackCycles=new double[(int)noOfCores];
        accumCycles=new double[(int)noOfCores];
        loadStoreCycles=new double[(int)noOfCores];
        memMemCycles=new double[(int)noOfCores];
        twoRegCycles=new double[(int)noOfCores];
        twoMemCycles=new double[(int)noOfCores];
        CPI=cpival;
        
        
        System.out.println("Operator Size : "+OPER_SIZE+" Bits");
        System.out.println("Register Size :"+REG_SIZE+" Bits");
        System.out.println("Memory Size :"+MEM_SIZE+" Bits");
        System.out.println("Speed up Increment :"+Math.round((double)(speedUpInc-1)*100)+" %");
        //System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)");
        System.out.println("===========================================");
    }
    
    
    public int[] instructionCount(File f) throws FileNotFoundException, IOException{
        int cores=(int)noOfCores;
        int count[]=new int[cores];  
        for(int i=0;i<noOfCores;i++){
        count[i]=0; 
        }
        int i=0;
        FileReader x=new FileReader(f);
         
         BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
            if(!sr.equals("") && !(sr.indexOf("#")!=-1)){         
                    if(sr.charAt(0)!='*'){
                        count[i]++;
                    }
                    else{
                        i++;
                        
                    }
                    
             }            
         }
       
       return count;
    }
    public void writeExcel(){
      //  be.append(null);
    }
    
  public void stackPerformance(File f) throws FileNotFoundException, IOException{
        int count[]=instructionCount(f);
        int[] memoryAccess=new int[(int)noOfCores];
        for(int i=0;i<(int)noOfCores;i++){
            memoryAccess[i]=0;
        }
        int j=0;
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
               if(!sr.equals("") && !(sr.indexOf("#")!=-1)){         
                    if(sr.charAt(0)!='*'){
                        if(sr.indexOf("PUSH")!=-1 || sr.indexOf("POP")!=-1){
                            memoryAccess[j]++;
                        }
                    }
                        else{
                            j++;
                        }
                    
               }
        }
        for(int i=0;i<(int)noOfCores;i++){
            System.out.println("*****Core*****"+i);
            System.out.println("Instruction Count "+count[i]);
                  stackCycles[i]=count[i]*CPI;
            int noOfBits=count[i]*OPER_SIZE;
          //  System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)"); 
            System.out.println("No of Bits for core"+": "+noOfBits);
            System.out.println("Memory Access : "+memoryAccess[i]);
            System.out.println("Clock Cycles: "+stackCycles[i]);
        }
        
        
        
          
    }
    
    public void memoryPerformance(File f) throws FileNotFoundException, IOException{
        int count[]=instructionCount(f);
        int memoryAccess[]=new int[(int)noOfCores];
        for(int i=0;i<(int)noOfCores;i++){
        System.out.println("*****Core*****"+i);
        System.out.println("Instruction Count "+count[i]);
        
        memMemCycles[i]=count[i]*CPI;
        memoryAccess[i]=count[i]*3;
        int noOfBits=count[i]*((MEM_SIZE*3)+OPER_SIZE);
        
        System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)"); 
        System.out.println("No of Bits :"+noOfBits);
        
    /*    FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
       while((sr=br.readLine())!=null){
            if(!sr.equals("")){         
                 if(!(sr.indexOf("#")!=-1)){         
                    if(sr.charAt(0)!='*'){
                        memoryAccess[i]+=3;
                    }
                    else{
                        
                    }
                 }
        }*/
            System.out.println("Memory Access : "+memoryAccess[i]);
            System.out.println("Clock Cycles: "+memMemCycles[i]);
            }
        }
    
                 
    
    public void accumPerformance(File f) throws FileNotFoundException, IOException{
        int count[]=instructionCount(f);
        int memoryAccess[]=new int[(int)noOfCores];
        for(int i=0;i<noOfCores;i++){
        System.out.println("*****Core*****"+i);
        System.out.println("Instruction Count "+count[i]);
        
        accumCycles[i]=count[i]*CPI;
        int noOfBits=count[i]*(OPER_SIZE+MEM_SIZE);
        memoryAccess[i]=count[i];
        
        System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)"); 
        System.out.println("No of Bits :"+noOfBits);
        
        /*FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
                if(!sr.equals("")){                
                    memoryAccess[i]+=1;
                }           
        }*/
         System.out.println("Memory Access : "+memoryAccess[i]);
         System.out.println("Clock Cycles: "+accumCycles[i]);
        }
      }
    
    public void twoAddPerformance(File f) throws FileNotFoundException, IOException{
        
        int count[]=instructionCount(f);
        int memoryAccess[]=new int[(int)noOfCores];
        
        for(int i=0;i<noOfCores;i++){
        
            System.out.println("*****Core*****"+i);
        System.out.println("Instruction Count "+count[i]);
        
            twoMemCycles[i]=count[i]*CPI;
            memoryAccess[i]=count[i]*2;
            System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)"); 
        int noOfBits=count[i]*(OPER_SIZE+2*MEM_SIZE);
        
        
        System.out.println("No of Bits :"+noOfBits);
        
        /*FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
                 if(!sr.equals("")){         
                    memoryAccess[i]+=2;
                 }          
        }*/
       System.out.println("Memory Access : "+memoryAccess[i]);
       System.out.println("Clock Cycles: "+twoMemCycles[i]);
        }
    }
        
    
    public void twoRegPerformance(File f) throws FileNotFoundException, IOException{
        
        int count[]=instructionCount(f);
        int memoryAccess[]=new int[(int)noOfCores];
        for(int i=0;i<noOfCores;i++){
        System.out.println("*****Core*****"+i);
        System.out.println("Instruction Count "+count[i]);
        
        twoRegCycles[i]=count[i]*CPI;
        int noOfBits=count[i]*(OPER_SIZE+REG_SIZE+MEM_SIZE);
        memoryAccess[i]=count[i];
        System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)"); 
        System.out.println("No of Bits :"+noOfBits);
        
        /*FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
            if(!sr.equals("")){         
                  
                memoryAccess[i]+=1;
            }               
        }*/
           System.out.println("Memory Access : "+memoryAccess[i]);
           System.out.println("Clock Cycles: "+twoRegCycles[i]);
        }
        }
    
    public void loadStorePerformance(File f) throws FileNotFoundException, IOException{
        
        int count[]=instructionCount(f);
        int memoryAccess[]=new int[(int)(noOfCores)];
        for(int i=0;i<noOfCores;i++){
        System.out.println("*****Core*****"+i);
        System.out.println("Instruction Count "+count[i]);
        
        loadStoreCycles[i]=count[i]*CPI;
        int noOfBits=0;
        int j=0;
        System.out.println("CPI value :"+CPI+ "(Clock cycles per Instruction)"); 
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        while((sr=br.readLine())!=null){
                  
            if(!sr.equals("") && !(sr.indexOf("#")!=-1)){         
                    if(sr.charAt(0)!='*'){
            if(sr.indexOf("LOAD")!=-1 || sr.indexOf("STORE")!=-1){
                        memoryAccess[j]+=1;
                        noOfBits=noOfBits+OPER_SIZE+REG_SIZE+MEM_SIZE;
                    }
                    else{
                        noOfBits=noOfBits+OPER_SIZE+(3*REG_SIZE);
                    }
              }
            else{
                        j++;    
             }
            }               
        }
        System.out.println("No of Bits :"+count[i]*32);
        System.out.println("Memory Access :"+memoryAccess[i]);
        System.out.println("Clock Cycles: "+loadStoreCycles[i]);
        }
    }
    
    public void speedUpVals(File f) throws FileNotFoundException, IOException{
        
         
        FileReader x=new FileReader(f);
        BufferedReader br=new BufferedReader(x);
        String sr;
        int MemInst[]=new int[(int)noOfCores];
        int BranchInst[]=new int[(int)noOfCores];
        int OperInst[]=new int[(int)noOfCores];
        int JumpInst[]=new int[(int)noOfCores];
       int j=0;
        double speedUpMemInst;
        double speedUpBranchInst;
        double speedUpOperInst;
        double speedUpJumpInst;
        int count[]=instructionCount(f);
        while((sr=br.readLine())!=null){
            if(!sr.equals("")&& !(sr.indexOf("#")!=-1)){
                if(sr.charAt(0)!='*'){
                if(sr.indexOf("LOAD")!=-1 || sr.indexOf("STORE")!=-1 || sr.indexOf("MOV")!=-1||sr.indexOf("PUSH")!=-1||sr.indexOf("POP")!=-1){
                    MemInst[j]++;
                }
                else if(sr.indexOf("ADD")!=-1 || sr.indexOf("SUB")!=-1 || sr.indexOf("MUL")!=-1 || sr.indexOf("DIV")!=-1){
                    OperInst[j]++;
                }
                else if(sr.indexOf("J ")!=-1){
                    JumpInst[j]++;
                }
                else if(sr.indexOf("BNE")!=-1 || sr.indexOf("BEQ")!=-1|| sr.indexOf("BGE")!=-1|| sr.indexOf("BLE")!=-1|| sr.indexOf("BLT")!=-1|| sr.indexOf("BGT")!=-1){
                    BranchInst[j]++;
                }
                //count[j]++;
            }
                else{
                    j++;
                }
        }
        }
        
        System.out.println("\nSpeed Up Vals");
        for(int i=0;i<(int)noOfCores;i++){
            System.out.println("******* Core "+i+" *******");
            speedUpMemInst=speedUp(((double)(MemInst[i])/(count[i])),speedUpInc,f.getName());
            speedUpBranchInst=speedUp(((double)(BranchInst[i])/(count[i])),speedUpInc,f.getName());
            speedUpJumpInst=speedUp(((double)(JumpInst[i])/(count[i])),speedUpInc,f.getName());
            speedUpOperInst=speedUp(((double)(OperInst[i])/(count[i])),speedUpInc,f.getName());

            System.out.println("Memory Speed Up for "+MemInst[i]+" Instructions :" +speedUpMemInst);
            System.out.println("Branch Speed Up for "+BranchInst[i]+" Instructions :" +speedUpBranchInst);
            System.out.println("Oper Speed Up for "+OperInst[i]+" Instructions :" +speedUpOperInst);
            System.out.println("Jump Speed Up for "+JumpInst[i]+" Instructions :" +speedUpJumpInst);
        }
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
