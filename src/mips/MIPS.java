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
import java.io.InputStreamReader;

/**
 *
 * @author root
 */
public class MIPS {

    /**
     * @param args the command line arguments
     */
    static int noOfIns = 0;
    static int load = 0;
    static double CPI = 1.3;
    static double speedUpInc = 1.1;
    
    static String baseFile = "./out/";
    static int m = 0;
    static String[] z = {"LoadStore.txt", "MemoryMemory.txt", "Stack.txt", "Accumulator.txt", "twoAddress.txt", "twoReg.txt"};
    static String[] z2 = {"LoadStoreCores.txt", "MemoryMemoryCores.txt", "StackCores.txt", "AccumulatorCores.txt", "twoAddressCores.txt", "twoRegCores.txt"};
   
    public static void helloWorld(String arg) throws IOException {
        // TODO code application logic here
        InfixToPostfix x = new InfixToPostfix();
        PostEval y = new PostEval();
        //FILE EMPTYING
        for (int j = 0; j < 6; j++) {
                File files = new File(baseFile + "" + "all" + "/" + z[j]);
                if (!files.exists()) {
                    File temp=new File(baseFile+""+"all"+"/");
                    temp.mkdirs();
                    files.createNewFile();
                }
                FileWriter temp = new FileWriter(baseFile + "" + "all" + "/" + z[j], false);
                BufferedWriter bufferWriter = new BufferedWriter(temp);
                bufferWriter.write("");
                bufferWriter.close();

            }

        
  
        BufferedReader in = new BufferedReader(new FileReader(arg));
        String inp;
        int n=0;
        while ((inp = in.readLine())!=null) {
            n++;
            
            if(n==1){
                CPI=Double.parseDouble(inp);
                MIPSCore.CPI=CPI;
                
            }
            else if(n==2){
                speedUpInc=1.0+(double)(Double.parseDouble(inp)/100);
                MIPSCore.speedUpInc=speedUpInc;
            }
            else if(n==3){
                
                MIPSCore.noOfCores=Double.parseDouble(inp);
                
            }
            
            else{
            m++;
           // System.out.println(inp);
            //System.out.println(baseFile+""+m+"/"+z[0]);
            for (int j = 0; j < 6; j++) {
                File files = new File(baseFile + "" + m + "/" + z[j]);
                if (!files.exists()) {
                    File temp=new File(baseFile+""+m+"/");
                    temp.mkdirs();
                    files.createNewFile();
                }
                FileWriter temp = new FileWriter(baseFile + "" + m + "/" + z[j], false);
                BufferedWriter bufferWriter = new BufferedWriter(temp);
                bufferWriter.write("");
                bufferWriter.close();

            }
            String expr2;
            if (inp.indexOf("loop") != -1) {
              expr2 = x.IgnoreSpaces(inp);
            } else {
                expr2 = x.IgnoreSpacesColons(inp);
            }
            //expr2=expr2.toUpperCase();
            String post = "";
            String[] evalIfExp;
            if (expr2.indexOf("if") != -1 && expr2.indexOf("loop") == -1) {

                evalIfExp = y.getIfString(expr2);
                display(evalIfExp);

            } else if (expr2.indexOf("while") != -1) {
                evalIfExp = y.getLoop(expr2);
                display(evalIfExp);
            } else if (expr2.indexOf("loop") != -1) {

                y.getLoop2(expr2);
                //      display(evalIfExp);


            } else {
                String evalExpr[] = {"", "", "", "", "", "", ""};
                if (expr2.indexOf("[") != -1) {
                    String index = expr2.substring(expr2.indexOf("[") + 1, expr2.indexOf("]"));
                    String base, other;
                    int i;
                    for (i = expr2.indexOf("[") - 1; i > 0; i--) {

                        if (expr2.charAt(i) == '+' || expr2.charAt(i) == '-' || expr2.charAt(i) == '*' || expr2.charAt(i) == '(' || expr2.charAt(i) == '/') {
                            break;
                        }
                    }
                    base = expr2.substring(i + 1, expr2.indexOf("["));
                  //  System.out.println(base);
                    String[] arr = y.getArray(base, index, "z");
                    for (i = 0; i < arr.length; i++) {
                        evalExpr[i] = evalExpr[i] + arr[i];
                    }
                    other = expr2.substring(0, i - 3) + "z";
                  //  System.out.println(other);
                    String evalExpr2, evalRes2;

                    evalExpr2 = x.getEvalExpr(other);
                    evalRes2 = "" + x.getResultVariable(other);

                    evalExpr2 = x.getPostfixString2(evalExpr2);

                    evalExpr[0] = evalExpr[0] + y.getLoadStore(evalExpr2, evalRes2);
                    evalExpr[1] = evalExpr[1] + y.getMemtoMem(evalExpr2, evalRes2);
                    evalExpr[2] = evalExpr[2] + y.getStack(evalExpr2, evalRes2);
                    evalExpr[3] = evalExpr[3] + y.getAccum(evalExpr2, evalRes2);
                    evalExpr[4] = evalExpr[4] + y.getTwoAddress(evalExpr2, evalRes2);
                    evalExpr[5] = evalExpr[5] + y.getTwoReg(evalExpr2, evalRes2);

                } else {
                    String evalExpr2, evalRes2;

                    evalExpr2 = x.getEvalExpr(expr2);
                    evalRes2 = "" + x.getResultVariable(expr2);
                    evalExpr2 = x.getPostfixString2(evalExpr2);
                    evalExpr[0] = evalExpr[0] + y.getLoadStore(evalExpr2, evalRes2);
                    evalExpr[1] = evalExpr[1] + y.getMemtoMem(evalExpr2, evalRes2);
                    evalExpr[2] = evalExpr[2] + y.getStack(evalExpr2, evalRes2);
                    evalExpr[3] = evalExpr[3] + y.getAccum(evalExpr2, evalRes2);
                    evalExpr[4] = evalExpr[4] + y.getTwoAddress(evalExpr2, evalRes2);
                    evalExpr[5] = evalExpr[5] + y.getTwoReg(evalExpr2, evalRes2);

                }
                display(evalExpr);
            }
            }
        }


    }

    public static void display(String arr[]) throws IOException {
        for (int j = 0; j < 6; j++) {
            File files = new File(baseFile+""+m+"/"+z[j]);
            File files2=new File(baseFile+"all/"+z[j]);
            if (!files.exists() || !files.exists()) {
               File temp=new File(baseFile+""+m+"/");
               temp.mkdirs();
               File temp2=new File(baseFile+""+"all"+"/");
               temp2.mkdirs();

                files.createNewFile();
                files2.createNewFile();
            }
            FileWriter temp = new FileWriter(baseFile+""+m+"/"+z[j], true);
            FileWriter temp2 = new FileWriter(baseFile+""+"all/"+z[j], true);
            
            BufferedWriter bufferWriter = new BufferedWriter(temp);
            BufferedWriter bufferWriter2 = new BufferedWriter(temp2);
            bufferWriter.write(arr[j]);
            bufferWriter2.write(arr[j]);
            
            bufferWriter.close();
            bufferWriter2.close();

        }

        /*  for(int i=0;i<4;i++){
         System.out.println(z[i]);
         System.out.println(arr[i]+"\n");
         }*/

        Performance.redirect(baseFile+""+m+"/performance.txt");
        Performance per = new Performance(speedUpInc, CPI);
        //per.redirect("/root/Desktop/performance.txt");
        System.out.println("Load Store Performance");
        per.loadStorePerformance(new File(baseFile+""+m+"/"+z[0]));
        per.speedUpVals(new File(baseFile+""+m+"/"+z[0]));

        System.out.println("\n");

        System.out.println("Memory Memory Performance");
        per.memoryPerformance(new File(baseFile+""+m+"/"+z[1]));
        per.speedUpVals(new File(baseFile+""+m+"/"+z[1]));

        System.out.println("\n");

        System.out.println("Stack Performance");
        per.stackPerformance(new File(baseFile+""+m+"/"+z[2]));
        per.speedUpVals(new File(baseFile+""+m+"/"+z[2]));

        System.out.println("\n");

        System.out.println("Accumulator Performance");
        per.accumPerformance(new File(baseFile+""+m+"/"+z[3]));
        per.speedUpVals(new File(baseFile+""+m+"/"+z[3]));

        System.out.println("\n");

        System.out.println("Two Address Performance");
        per.twoAddPerformance(new File(baseFile+""+m+"/"+z[4]));
        per.speedUpVals(new File(baseFile+""+m+"/"+z[4]));

        System.out.println("\n");

        System.out.println("GPR 1 Performance");
        per.twoRegPerformance(new File(baseFile+""+m+"/"+z[5]));
        per.speedUpVals(new File(baseFile+""+m+"/"+z[5]));
        
        
        
        
        Performance.redirect(baseFile+""+"all"+"/performance.txt");
        per = new Performance(speedUpInc, CPI);
        String temp=baseFile+""+"all/";
        //per.redirect("/root/Desktop/performance.txt");
        System.out.println("Load Store Performance");
        per.loadStorePerformance(new File(temp+""+z[0]));
        per.speedUpVals(new File(temp+""+z[0]));

        System.out.println("\n");

        System.out.println("Memory Memory Performance");
        per.memoryPerformance(new File(temp+""+z[1]));
        per.speedUpVals(new File(temp+""+z[1]));

        System.out.println("\n");

        System.out.println("Stack Performance");
        per.stackPerformance(new File(temp+""+z[2]));
        per.speedUpVals(new File(temp+""+z[2]));

        System.out.println("\n");

        System.out.println("Accumulator Performance");
        per.accumPerformance(new File(temp+""+z[3]));
        per.speedUpVals(new File(temp+""+z[3]));

        System.out.println("\n");

        System.out.println("Two Address Performance");
        per.twoAddPerformance(new File(temp+""+z[4]));
        per.speedUpVals(new File(temp+""+z[4]));

        System.out.println("\n");

        System.out.println("GPR 1 Performance");
        per.twoRegPerformance(new File(temp+""+z[5]));
        per.speedUpVals(new File(temp+""+z[5]));
        
        
        
        
        
        
        
        
        
        
        
        
        Performance2.redirect(baseFile+""+m+"/performance2.txt");
        Performance2 per2 = new Performance2(speedUpInc, CPI);
        //per.redirect("/root/Desktop/performance.txt");
        
        System.out.println("Load Store Performance");
        per2.loadStorePerformance(new File(baseFile+""+m+"/"+z[0]));
        per2.speedUpVals(new File(baseFile+""+m+"/"+z[0]));

        System.out.println("\n");

        System.out.println("Memory Memory Performance");
        per2.memoryPerformance(new File(baseFile+""+m+"/"+z[1]));
        per2.speedUpVals(new File(baseFile+""+m+"/"+z[1]));

        System.out.println("\n");

        System.out.println("Stack Performance");
        per2.stackPerformance(new File(baseFile+""+m+"/"+z[2]));
        per2.speedUpVals(new File(baseFile+""+m+"/"+z[2]));

        System.out.println("\n");

        System.out.println("Accumulator Performance");
        per2.accumPerformance(new File(baseFile+""+m+"/"+z[3]));
        per2.speedUpVals(new File(baseFile+""+m+"/"+z[3]));

        System.out.println("\n");

        System.out.println("Two Address Performance");
        per2.twoAddPerformance(new File(baseFile+""+m+"/"+z[4]));
        per2.speedUpVals(new File(baseFile+""+m+"/"+z[4]));

        System.out.println("\n");

        System.out.println("GPR 1 Performance");
        
        
        Performance2.redirect(baseFile+""+"all"+"/performance2.txt");
        per2 = new Performance2(speedUpInc, CPI);
        temp=baseFile+""+"all/";
        //per.redirect("/root/Desktop/performance.txt");
        System.out.println("Load Store Performance");
        per2.loadStorePerformance(new File(temp+""+z[0]));
        per2.speedUpVals(new File(temp+""+z[0]));

        System.out.println("\n");

        System.out.println("Memory Memory Performance");
        per2.memoryPerformance(new File(temp+""+z[1]));
        per2.speedUpVals(new File(temp+""+z[1]));

        System.out.println("\n");

        System.out.println("Stack Performance");
        per2.stackPerformance(new File(temp+""+z[2]));
        per2.speedUpVals(new File(temp+""+z[2]));

        System.out.println("\n");

        System.out.println("Accumulator Performance");
        per2.accumPerformance(new File(temp+""+z[3]));
        per2.speedUpVals(new File(temp+""+z[3]));

        System.out.println("\n");

        System.out.println("Two Address Performance");
        per2.twoAddPerformance(new File(temp+""+z[4]));
        per2.speedUpVals(new File(temp+""+z[4]));

        System.out.println("\n");

        System.out.println("GPR 1 Performance");
        per2.twoRegPerformance(new File(temp+""+z[5]));
        per2.speedUpVals(new File(temp+""+z[5]));

        
        
        
        
      
        Performance3.redirect(baseFile+"all2/performance3.txt");
        Performance3 per3 = new Performance3(speedUpInc, CPI);
        //per.redirect("/root/Desktop/performance.txt");
        
        System.out.println("Load Store Performance");
        per3.loadStorePerformance(new File(baseFile+"all2/"+z2[0]));
        per3.speedUpVals(new File(baseFile+"all2/"+z2[0]));

        System.out.println("\n");

        System.out.println("Memory Memory Performance");
        per3.memoryPerformance(new File(baseFile+"all2/"+z2[1]));
        per3.speedUpVals(new File(baseFile+"all2/"+z2[1]));

        System.out.println("\n");

        System.out.println("Stack Performance");
        per3.stackPerformance(new File(baseFile+"all2/"+z2[2]));
        per3.speedUpVals(new File(baseFile+"all2/"+z2[2]));

        System.out.println("\n");

        System.out.println("Accumulator Performance");
        per3.accumPerformance(new File(baseFile+"all2/"+z2[3]));
        per3.speedUpVals(new File(baseFile+"all2/"+z2[3]));

        System.out.println("\n");

        System.out.println("Two Address Performance");
        per3.twoAddPerformance(new File(baseFile+"all2/"+z2[4]));
        per3.speedUpVals(new File(baseFile+"all2/"+z2[4]));

        System.out.println("\n");

        System.out.println("GPR 1 Performance");
        per3.twoRegPerformance(new File(baseFile+"all2/"+z2[5]));
        per3.speedUpVals(new File(baseFile+"all2/"+z2[5]));

        
        
    }
}
