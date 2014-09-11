/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mips;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 *
 * @author root
 */
public class PostEval {
   Stack chars=new Stack();
   Stack regs=new Stack();
     InfixToPostfix post=new InfixToPostfix();
     String regType=""; 
       String regVars[]={"$R1","$R2","$R3","$R4","$R5","$R6","$R7","$R8","$R9","$R10","$R11","$R12","$R13","$R14","$R15","$R16","$R17","$R18"};
     int regInd=0;
   String tempVars[]={"M1","M2","M3","M4","M5","M6","M7","M8","M9","M10","M11","M12","M13","M14","M15","M16"};
     int tempind=0;
   
     public String Evaluation(String x, char res){
     //Stack chars=new Stack();
     char temp;
     
     //String lx="";
     //String re="";
     try{
     for(int i=0;i<x.length();i++){
         temp=x.charAt(i);
         
         if(temp=='+'||temp=='*'||temp=='-'||temp=='/'){
             String a=(String)chars.pop();
             String b=(String)chars.pop();
             
             String c=(String)regs.pop();
             String d=(String)regs.pop();
             
             String result;
             String result2;
             //System.out.println(a+""+b);
             if(i==(x.length()-1)){
                result=res+"";
                result2=regVars[regInd];
                regInd++;
             }
             else{
                 result=tempVars[tempind];
                 tempind++;
                 result2=regVars[regInd];
                 regInd++;
             }
             if(temp=='+'){
                 memAdd(a,b,result);
                 regAdd(c,d,result2);
             }
             if(temp=='*'){
                 memMul(a,b,result);
                 regMul(c,d,result2);
             }
             if(temp=='-'){
                 memSub(a,b,result);
                 regSub(c,d,result2);
             }
             if(temp=='/'){
                 memDiv(a,b,result);
                 regDiv(c,d,result2);
             } 
         }
         else{
             //System.out.println(temp);
             chars.push(temp+"");
             regs.push(temp+"");
         }
             
         }
     }catch(Exception e){
         
     }        
     
     //System.out.println(regType);
     return "";   
    }
    
    public String  memAdd(String a, String b,String res){
        return "ADD "+res+", "+a+", "+b+"\n";
      //  chars.push(res);
    }
    public String memSub(String a, String b,String res){
        return "SUB "+res+", "+a+", "+b+"\n";
        //chars.push(res);
    }
    public String memMul(String a, String b,String res){
        return "MUL "+res+", "+a+", "+b+"\n";
        //chars.push(res);
    }
    public String memDiv(String a, String b,String res){
        return "DIV "+res+", "+a+", "+b+"\n";
     //chars.push(res);
    }
    public void regAdd(String a, String b, String res){
        String regVar1=regVars[regInd];
        regInd=regInd++;
        regType=regType+"LOAD "+a+", "+regVar1+"\n";
        String regVar2=regVars[regInd];
        regInd=regInd+1;
        regType=regType+"LOAD "+b+", "+regVar2+"\n";     
        regType=regType+"ADD "+res+", "+regVar1+", "+regVar2+"\n";
    //    chars.push(res);
    }
    public void regSub(String a, String b, String res){
        String regVar1=regVars[regInd];
        regInd=regInd+1;
        regType=regType+"LOAD "+a+", "+regVar1+"\n";
        String regVar2=regVars[regInd];
        regInd=regInd+1;
        regType=regType+"LOAD "+b+", "+regVar2+"\n";     
        regType=regType+"SUB "+res+", "+regVar1+", "+regVar2+"\n";
        //chars.push(res);
    }
     public void regMul(String a, String b, String res){
        String regVar1=regVars[regInd];
        regInd=regInd+1;
        regType=regType+"LOAD "+a+", "+regVar1+"\n";
        String regVar2=regVars[regInd];
        regInd=regInd+1;
        regType=regType+"LOAD "+b+", "+regVar2+"\n";     
        regType=regType+"MUL "+res+", "+regVar1+", "+regVar2+"\n";
      //  chars.push(res);
    }
     public void regDiv(String a, String b, String res){
        String regVar1=regVars[regInd];
        regInd=regInd+1;
        regType=regType+"LOAD "+a+", "+regVar1+"\n";
        String regVar2=regVars[regInd];
        regInd=regInd+1;
        regType=regType+"LOAD "+b+", "+regVar2+"\n";     
        regType=regType+"DIV "+res+", "+regVar1+", "+regVar2+"\n";
      //  chars.push(res);
    }
     
    public String getMemtoMem(String val,String res){
        String memType="";
        Stack x=new Stack();
        tempind=0;
        regInd=0;
        for(int i=0;i<val.length();i++){
            char temp=val.charAt(i);
            if(temp=='+' || temp=='-' || temp=='*' || temp=='/'){
                String a=(String)x.pop();
                String b=(String)x.pop();
            
                String result;
             if(i==(val.length()-1)){
                result=res+"";
              }
             else{
                 result=tempVars[tempind];
                 tempind++;
             }
                switch(temp){
                    
                    case '+': memType=memType+memAdd(b,a,result); 
                              x.push(result);
                              break;
                    case '-': memType=memType+memSub(b,a,result); 
                              x.push(result);
                              break;
                    case '*': memType=memType+memMul(b,a,result); 
                              x.push(result);
                              break;
                    case '/': memType=memType+memDiv(b,a,result); 
                              x.push(result);
                              break;
                }
                
            }
            else{
                x.push(temp+"");
          
            }            
            
        }
        return memType;
    }
    
    public String getRegtoReg(String str){
        String regType="";       
        Stack y=new Stack();
        char temp;
        for(int i=0;i<str.length();i++){
            temp=str.charAt(i);
            switch(temp){
                case '+':  
            
            
            }
            
            
            
            
        }
        
        
        return regType;
        
        
        
    }
    
    public String getStack(String val,String res){
        Stack x=new Stack();
        String stackStr="";
        tempind=0;
        regInd=0;
        char temp;
        for(int i=0;i<val.length();i++){
            temp=val.charAt(i);
            
            if(temp=='+'||temp=='-'||temp=='*'||temp=='/'){
            
                switch(temp){
                    case '+':stackStr=stackStr+"ADD\n";
                             break;
                    case '-':stackStr=stackStr+"SUB\n";
                             break;
                    case '*':stackStr=stackStr+"MUL\n";
                             break;
                    case '/':stackStr=stackStr+"DIV\n";
                             break;
                }
            
            }
            else{
                stackStr=stackStr+"PUSH "+temp+"\n";
            }
            
        }
        
        stackStr=stackStr+"POP "+res+"\n";
        
        return stackStr;
    
    }
    
    public String getAccum(String val,String res){
        String accumStr="";
         tempind=0;
        regInd=0;
        char temp;
        int count=0;
        Stack x=new Stack();
        boolean first=true;
        for(int i=0;i<val.length();i++){
            temp=val.charAt(i);
              if(temp=='+'||temp=='-'||temp=='*'||temp=='/'){
                count=0;
                switch(temp){
                    case '+':accumStr=accumStr+"ADD "+(String)x.pop()+"\n";
                             break;
                    case '-':accumStr=accumStr+"SUB "+(String)x.pop()+"\n";
                             accumStr=accumStr+"NEG"+"\n";
                             break;
                    case '*':accumStr=accumStr+"MUL "+(String)x.pop()+"\n";
                             break;
                    case '/':accumStr=accumStr+"DIV "+(String)x.pop()+"\n";
                             break;
                }
               
              }
              else{
                  x.push(temp+"");
                  count++;                      
                  if(count>=2){
                      String tempv=tempVars[tempind];
                      tempind++;
                      if(!first){
                        accumStr=accumStr+"STORE "+tempv+"\n"; 
                      }
                      first=false;
               //       String k=(String)x.pop();
                      accumStr=accumStr+"LOAD "+(String)x.pop()+"\n";
                 //     x.push(k);
                      
                      String tempw=(String)x.pop();
                      x.push(tempv);
                      x.push(tempw);
                      count=count-2;          
                  }
              }
          
        }
        accumStr=accumStr+"STORE "+res+"\n";
        
        return accumStr;
    }
    
    public String getLoadStore(String val,String res){
        String regStr="";
         tempind=0;
        regInd=0;
        Stack x=new Stack();
        Stack reg=new Stack();
        char temp;
        String regVal;
        
        for(int i=0;i<val.length();i++){
            temp=val.charAt(i);
            
            if(temp=='+'||temp=='-'||temp=='*'||temp=='/'){
                
                switch(temp){
                    case '+':regVal=regVars[regInd];
                             regInd++;
                             regStr=regStr+"ADD "+regVal+" "+(String)reg.pop()+" "+(String)reg.pop()+"\n";
                             reg.push(regVal);
                             break;
                    case '-':regVal=regVars[regInd];
                             regInd++;
                             String temp2;
                                     temp2= (String)reg.pop();
                             regStr=regStr+"SUB "+regVal+" "+(String)reg.pop()+" "+(String)temp2+"\n";
                             reg.push(regVal);
                             
                             break;
                    case '*':regVal=regVars[regInd];
                             regInd++;
                             regStr=regStr+"MUL "+regVal+" "+(String)reg.pop()+" "+(String)reg.pop()+"\n";
                             reg.push(regVal);
                             break;
                    case '/':regVal=regVars[regInd];
                             regInd++;
                             regStr=regStr+"DIV "+regVal+" "+(String)reg.pop()+" "+(String)reg.pop()+"\n";
                             reg.push(regVal);
                             break;
                }
                
            }
            else{
                x.push(temp+"");
                regVal=regVars[regInd];
                regInd++;
                regStr=regStr+"LOAD "+regVal+" "+temp+"\n";
                reg.push(regVal);
            }
            
            
        }
        regStr=regStr+"STORE "+res+" "+(String)reg.pop()+"\n";
        return regStr;
        
        
    }
    
    public String[] getIfString(String m){
        tempind=0;
        regInd=0;
        
        String[] ifRes={"","","","","",""};
        String cond="";
        String validation=m.substring(m.indexOf('(')+1,m.indexOf(')'));
        int val=0,val2=0;
        
        if((validation.indexOf("=="))!=-1){
            val=validation.indexOf("==");
            val2=val+2;
            cond="==";
        }     
        else if((validation.indexOf("!="))!=-1){
            val=validation.indexOf("!=");
            val2=val+2;
            cond="!=";
        }
        
        else if((validation.indexOf(">="))!=-1){
            val=validation.indexOf(">=");
            val2=val+2;
            cond=">=";
        }
        else if((validation.indexOf("<="))!=-1){
            val=validation.indexOf("<=");
            val2=val+2;
            cond="<=";
        }
        else if((validation.indexOf("<"))!=-1){
            val=validation.indexOf("<");
            val2=val+1;
            cond="<";
        }
        else if((validation.indexOf(">"))!=-1){
            val=validation.indexOf(">");
            val2=val+1;
            cond=">";
        }
        
        String leftCond=validation.substring(0,val);
        String rightCond=validation.substring(val2,validation.length());
        String[] allTrueVal={"","","","","",""};
        String[] allFalseVal={"","","","","",""};
        String trueVal="";
        String falseVal="";
        String tempVar,tempVar2;
        String tempPost;
        String falseRes;
        String trueRes;
        
        if(m.indexOf("else")!=-1){
            trueVal=m.substring(m.indexOf(")")+1,m.indexOf("else"));
            falseVal=m.substring(m.indexOf("else")+4);
            
            /*if(trueVal.indexOf("goto")!=-1){
                
                
                for(int i=0;i<4;i++){
                    allTrueVal[i]=allTrueVal[i]+getGoto(trueVal);
                }
                
            }
            else if (falseVal.indexOf("goto")!=-1){
                
                for(int i=0;i<4;i++){
                    allFalseVal[i]=allTrueVal[i]+getGoto(falseVal);
                
                }
                
            }*/
            //else{
            
                trueVal=post.IgnoreSpaces(trueVal);
                trueRes=""+post.getResultVariable(trueVal);
                trueVal=post.getEvalExpr(trueVal);
                trueVal=post.getPostfixString2(trueVal);

                falseVal=post.IgnoreSpaces(falseVal);
                falseRes=""+post.getResultVariable(falseVal);
                falseVal=post.getEvalExpr(falseVal);
                falseVal=post.getPostfixString2(falseVal);
               // System.out.println(falseVal+" "+falseRes+" "+trueVal+" "+trueRes);

                allTrueVal[0]=getLoadStore(trueVal,trueRes);
                allFalseVal[0]=getLoadStore(falseVal,falseRes);

                allTrueVal[1]=getMemtoMem(trueVal,trueRes);
                allFalseVal[1]=getMemtoMem(falseVal,falseRes);

                allTrueVal[2]=getStack(trueVal,trueRes);
                allFalseVal[2]=getStack(falseVal,falseRes);

                allTrueVal[3]=getAccum(trueVal,trueRes);
                allFalseVal[3]=getAccum(falseVal,falseRes);

                 allTrueVal[4]=getTwoAddress(trueVal,trueRes);
                allFalseVal[4]=getTwoAddress(falseVal,falseRes);
                
                 allTrueVal[5]=getTwoReg(trueVal,trueRes);
                allFalseVal[5]=getTwoReg(falseVal,falseRes);
        
                
              //  }
            }
   
        else{
            if(m.indexOf("goto")!=-1){
                for(int i=0;i<6;i++){
                    String temp=m.substring(m.indexOf("goto")+4,m.length());
                    allTrueVal[i]=allTrueVal[i]+getGoto(temp);
                }
            }
            else{
            trueVal=m.substring(m.indexOf(")")+1,m.length());
            trueRes=""+post.getResultVariable(trueVal);
            trueVal=post.getEvalExpr(trueVal);
            trueVal=post.getPostfixString2(trueVal);
            allTrueVal[0]=getLoadStore(trueVal,trueRes);
            //allFalseVal[0]=getLoadStore(falseVal,falseRes);
            
            allTrueVal[1]=getMemtoMem(trueVal,trueRes);
            //allFalseVal[1]=getMemtoMem(falseVal,falseRes);
            
            allTrueVal[2]=getStack(trueVal,trueRes);
            //allFalseVal[2]=getStack(falseVal,falseRes);
            
            allTrueVal[3]=getAccum(trueVal,trueRes);
           // allFalseVal[3]=getAccum(falseVal,falseRes);
            allTrueVal[4]=getTwoAddress(trueVal,trueRes);
          
            allTrueVal[5]=getTwoReg(trueVal,trueRes);
          
            falseVal="";
            }
        }
        
        if(leftCond.length()>1 && rightCond.length()>1){
            
            tempVar=tempVars[tempind];
            tempind++;
            tempPost=post.getPostfixString2(leftCond);
            ifRes[0]=ifRes[0]+getLoadStore(tempPost,tempVar);
            ifRes[1]=ifRes[1]+getMemtoMem(tempPost,tempVar);
            ifRes[2]=ifRes[2]+getStack(tempPost,tempVar);
            ifRes[3]=ifRes[3]+getAccum(tempPost,tempVar);
            ifRes[4]=ifRes[4]+getTwoAddress(tempPost,tempVar);
            ifRes[5]=ifRes[5]+getTwoReg(tempPost,tempVar);
          
            tempVar2=tempVars[tempind];
            tempind++;
            String tempPost2=post.getPostfixString2(rightCond);
            ifRes[0]=ifRes[0]+getLoadStore(tempPost2,tempVar2);
            ifRes[1]=ifRes[1]+getMemtoMem(tempPost2,tempVar2);
            ifRes[2]=ifRes[2]+getStack(tempPost2,tempVar2);
            ifRes[3]=ifRes[3]+getAccum(tempPost2,tempVar2);
            ifRes[4]=ifRes[4]+getTwoAddress(tempPost2,tempVar2);
            ifRes[5]=ifRes[5]+getTwoReg(tempPost2,tempVar2);
          
            
            
                ifRes[0]=ifRes[0]+conditionCode(tempVar,tempVar2,allTrueVal[0],allFalseVal[0],cond);
                ifRes[1]=ifRes[1]+conditionCodeMem(tempVar,tempVar2,allTrueVal[1],allFalseVal[1],cond);
                ifRes[2]=ifRes[2]+conditionCodeStack(tempVar,tempVar2,allTrueVal[2],allFalseVal[2],cond);
                ifRes[3]=ifRes[3]+conditionCodeAccum(tempVar,tempVar2,allTrueVal[3],allFalseVal[3],cond);
                ifRes[4]=ifRes[4]+conditionCodeTwoMem(tempVar,tempVar2,allTrueVal[4],allFalseVal[4],cond);
                ifRes[5]=ifRes[5]+conditionCodeReg(tempVar,tempVar2,allTrueVal[5],allFalseVal[5],cond);            
            
        }
        else if(rightCond.length()>1 && leftCond.length()<=1){
            tempVar2=tempVars[tempind];
            tempind++;
            tempPost=post.getPostfixString2(rightCond);
            
            ifRes[0]=ifRes[0]+getLoadStore(tempPost,tempVar2);
            ifRes[1]=ifRes[1]+getMemtoMem(tempPost,tempVar2);
            ifRes[2]=ifRes[2]+getStack(tempPost,tempVar2);
            ifRes[3]=ifRes[3]+getAccum(tempPost,tempVar2);
            ifRes[4]=ifRes[4]+getTwoAddress(tempPost,tempVar2);
            ifRes[5]=ifRes[5]+getTwoReg(tempPost,tempVar2);
           
            
            
           //for(int i=0;i<6;i++) {
                ifRes[0]=ifRes[0]+conditionCode(leftCond,tempVar2,allTrueVal[0],allFalseVal[0],cond);
                ifRes[1]=ifRes[1]+conditionCodeMem(leftCond,tempVar2,allTrueVal[1],allFalseVal[1],cond);
                ifRes[2]=ifRes[2]+conditionCodeStack(leftCond,tempVar2,allTrueVal[2],allFalseVal[2],cond);
                ifRes[3]=ifRes[3]+conditionCodeAccum(leftCond,tempVar2,allTrueVal[3],allFalseVal[3],cond);
                ifRes[4]=ifRes[4]+conditionCodeTwoMem(leftCond,tempVar2,allTrueVal[4],allFalseVal[4],cond);
                ifRes[5]=ifRes[5]+conditionCodeReg(leftCond,tempVar2,allTrueVal[5],allFalseVal[5],cond);
           // }
        }
        else if(rightCond.length()<=1 && leftCond.length()>1){
            tempVar=tempVars[tempind];
            tempind++;
            tempPost=post.getPostfixString2(leftCond);
            
            ifRes[0]=ifRes[0]+getLoadStore(tempPost,tempVar);
            ifRes[1]=ifRes[1]+getMemtoMem(tempPost,tempVar);
            ifRes[2]=ifRes[2]+getStack(tempPost,tempVar);
            ifRes[3]=ifRes[3]+getAccum(tempPost,tempVar);
            ifRes[4]=ifRes[4]+getTwoAddress(tempPost,tempVar);
            ifRes[5]=ifRes[5]+getTwoReg(tempPost,tempVar);
           
            
            
                ifRes[0]=ifRes[0]+conditionCode(tempVar,rightCond,allTrueVal[0],allFalseVal[0],cond);
                ifRes[1]=ifRes[1]+conditionCodeMem(tempVar,rightCond,allTrueVal[1],allFalseVal[1],cond);
                ifRes[2]=ifRes[2]+conditionCodeStack(tempVar,rightCond,allTrueVal[2],allFalseVal[2],cond);
                ifRes[3]=ifRes[3]+conditionCodeAccum(tempVar,rightCond,allTrueVal[3],allFalseVal[3],cond);
                ifRes[4]=ifRes[4]+conditionCodeTwoMem(tempVar,rightCond,allTrueVal[4],allFalseVal[4],cond);
                ifRes[5]=ifRes[5]+conditionCodeReg(tempVar,rightCond,allTrueVal[5],allFalseVal[5],cond);
            
        }
        else if(rightCond.length()<=1 ){
            ifRes[0]=ifRes[0]+conditionCode(leftCond,rightCond,allTrueVal[0],allFalseVal[0],cond);
                ifRes[1]=ifRes[1]+conditionCodeMem(leftCond,rightCond,allTrueVal[1],allFalseVal[1],cond);
                ifRes[2]=ifRes[2]+conditionCodeStack(leftCond,rightCond,allTrueVal[2],allFalseVal[2],cond);
                ifRes[3]=ifRes[3]+conditionCodeAccum(leftCond,rightCond,allTrueVal[3],allFalseVal[3],cond);
                ifRes[4]=ifRes[4]+conditionCodeTwoMem(leftCond,rightCond,allTrueVal[4],allFalseVal[4],cond);
                ifRes[5]=ifRes[5]+conditionCodeReg(leftCond,rightCond,allTrueVal[5],allFalseVal[5],cond);
        }
        
        
        return ifRes;
    }
    
    public String conditionCode(String left,String right,String trueV,String falseV,String cond){
        String tot="";
        String reg1,reg2,reg3;
        regInd=0;
        reg1=regVars[regInd];
            regInd++;
            reg2=regVars[regInd];
            regInd++;
            reg3=regVars[regInd];
            regInd++;
        if(cond.equals("==")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"BEQ "+reg1+" "+reg2+" True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"BNE "+reg1+" "+reg2+" True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"BGE "+reg1+" "+reg2+" "+"True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"BLE "+reg1+" "+reg2+" "+"True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"BLT "+reg1+" "+reg2+" "+"True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        else if(cond.equals(">")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"BGT "+reg1+" "+reg2+" "+"True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        return tot;
    }
    
    public String[] getLoop(String m){
        tempind=0;
        regInd=0;
        String[] whileRes={"","","","","",""};
        String cond="";
        String validation=m.substring(m.indexOf('(')+1,m.indexOf(')'));
        int val=0,val2=0;
        
        if((validation.indexOf("=="))!=-1){
            val=validation.indexOf("==");
            val2=val+2;
            cond="==";
        }
        else if((validation.indexOf("!="))!=-1){
            val=validation.indexOf("!=");
            val2=val+2;
            cond="!=";
        }
        else if((validation.indexOf(">="))!=-1){
            val=validation.indexOf(">=");
            val2=val+2;
            cond=">=";
        }
        else if((validation.indexOf("<="))!=-1){
            val=validation.indexOf("<=");
            val2=val+2;
            cond="<=";
        }
        else if((validation.indexOf("<"))!=-1){
            val=validation.indexOf("<");
            val2=val+1;
            cond="<";
        }
        else if((validation.indexOf(">"))!=-1){
            val=validation.indexOf(">");
            val2=val+1;
            cond=">";
        }
        
        String leftCond=validation.substring(0,val);
        String rightCond=validation.substring(val2,validation.length());
        String[] allTrueVal={"","","","","",""};
        String tempVar,tempVar2;
        String tempPost;
        String trueRes;
       String trueVal=m.substring(m.indexOf(")")+1,m.length());
        trueVal=post.IgnoreSpaces(trueVal);
            trueRes=""+post.getResultVariable(trueVal);
            trueVal=post.getEvalExpr(trueVal);
            trueVal=post.getPostfixString2(trueVal);
            allTrueVal[0]=getLoadStore(trueVal,trueRes);
            //allFalseVal[0]=getLoadStore(falseVal,falseRes);
            
            allTrueVal[1]=getMemtoMem(trueVal,trueRes);
            //allFalseVal[1]=getMemtoMem(falseVal,falseRes);
            
            allTrueVal[2]=getStack(trueVal,trueRes);
            //allFalseVal[2]=getStack(falseVal,falseRes);
            
            allTrueVal[3]=getAccum(trueVal,trueRes);
           // allFalseVal[3]=getAccum(falseVal,falseRes);
            allTrueVal[4]=getTwoAddress(trueVal,trueRes);
            allTrueVal[5]=getTwoReg(trueVal,trueRes);
           
        
        
        
        
        if(leftCond.length()>1 && rightCond.length()>1){
            
            tempVar=tempVars[tempind];
            tempind++;
            tempVar2=tempVars[tempind];
            tempind++;
            if(leftCond.indexOf("[")!=-1){   
                String temp[]=getArrayExpr(leftCond);
                for(int k=0;k<temp.length;k++){
                    whileRes[k]=whileRes[k]+temp[k];
                }
                tempVar="z";
            }
            else{
            tempPost=post.getPostfixString2(leftCond);
            whileRes[0]=whileRes[0]+getLoadStore(tempPost,tempVar);
            whileRes[1]=whileRes[1]+getMemtoMem(tempPost,tempVar);
            whileRes[2]=whileRes[2]+getStack(tempPost,tempVar);
            whileRes[3]=whileRes[3]+getAccum(tempPost,tempVar);
            whileRes[4]=whileRes[4]+getTwoAddress(tempPost,tempVar);
            whileRes[5]=whileRes[5]+getTwoReg(tempPost,tempVar);
           
            }
            if(rightCond.indexOf("[")!=-1){
                String temp[]=getArrayExpr(leftCond);
                for(int k=0;k<temp.length;k++){
                    whileRes[k]=whileRes[k]+temp[k];
                }
                tempVar2="z1";
            }
            else{
            tempPost=post.getPostfixString2(rightCond);
            whileRes[0]=whileRes[0]+getLoadStore(tempPost,tempVar2);
            whileRes[1]=whileRes[1]+getMemtoMem(tempPost,tempVar2);
            whileRes[2]=whileRes[2]+getStack(tempPost,tempVar2);
            whileRes[3]=whileRes[3]+getAccum(tempPost,tempVar2);
            whileRes[4]=whileRes[4]+getTwoAddress(tempPost,tempVar2);
            whileRes[5]=whileRes[5]+getTwoReg(tempPost,tempVar2);
            }
            
            
           
           whileRes[0]=whileRes[0]+conditionCode2(tempVar,tempVar2,allTrueVal[0],cond);
           whileRes[1]=whileRes[1]+conditionCode2Mem(tempVar,tempVar2,allTrueVal[1],cond);
           whileRes[2]=whileRes[2]+conditionCode2Stack(tempVar,tempVar2,allTrueVal[2],cond);
           whileRes[3]=whileRes[3]+conditionCode2Accum(tempVar,tempVar2,allTrueVal[3],cond);
           whileRes[4]=whileRes[4]+conditionCode2TwoAdd(tempVar,tempVar2,allTrueVal[4],cond);
           whileRes[5]=whileRes[5]+conditionCode2TwoReg(tempVar,tempVar2,allTrueVal[5],cond);
             
            
            
            
        }
        else if(rightCond.length()>1 && leftCond.length()<=1){
        
            tempVar2=tempVars[tempind];
            tempind++;
            tempPost=post.getPostfixString2(rightCond);
             if(rightCond.indexOf("[")!=-1){
                String temp[]=getArrayExpr(leftCond);
                for(int k=0;k<temp.length;k++){
                    whileRes[k]=whileRes[k]+temp[k];
                }
                tempPost="z";
            }
            else{
                whileRes[0]=whileRes[0]+getLoadStore(tempPost,tempVar2);
                whileRes[1]=whileRes[1]+getMemtoMem(tempPost,tempVar2);
                whileRes[2]=whileRes[2]+getStack(tempPost,tempVar2);
                whileRes[3]=whileRes[3]+getAccum(tempPost,tempVar2);
                whileRes[4]=whileRes[4]+getTwoAddress(tempPost,tempVar2);
                whileRes[5]=whileRes[5]+getTwoReg(tempPost,tempVar2);
             }
            
            
           whileRes[0]=whileRes[0]+conditionCode2(leftCond,tempVar2,allTrueVal[0],cond);
           whileRes[1]=whileRes[1]+conditionCode2Mem(leftCond,tempVar2,allTrueVal[1],cond);
           whileRes[2]=whileRes[2]+conditionCode2Stack(leftCond,tempVar2,allTrueVal[2],cond);
           whileRes[3]=whileRes[3]+conditionCode2Accum(leftCond,tempVar2,allTrueVal[3],cond);
           whileRes[4]=whileRes[4]+conditionCode2TwoAdd(leftCond,tempVar2,allTrueVal[4],cond);
           whileRes[5]=whileRes[5]+conditionCode2TwoReg(leftCond,tempVar2,allTrueVal[5],cond);
        }
        
        else if(rightCond.length()<=1 && leftCond.length()>1){
            tempVar=tempVars[tempind];
            tempind++;
            tempPost=post.getPostfixString2(leftCond);
            if(leftCond.indexOf("[")!=-1){   
                String temp[]=getArrayExpr(leftCond);
                for(int k=0;k<temp.length;k++){
                    whileRes[k]=whileRes[k]+temp[k];
                }
                tempPost="z";
            }
            else{
                whileRes[0]=whileRes[0]+getLoadStore(tempPost,tempVar);
                whileRes[1]=whileRes[1]+getMemtoMem(tempPost,tempVar);
                whileRes[2]=whileRes[2]+getStack(tempPost,tempVar);
                whileRes[3]=whileRes[3]+getAccum(tempPost,tempVar);
                whileRes[4]=whileRes[4]+getTwoAddress(tempPost,tempVar);
                whileRes[5]=whileRes[5]+getTwoReg(tempPost,tempVar);
             } 
            
           whileRes[0]=whileRes[0]+conditionCode2(tempVar,rightCond,allTrueVal[0],cond);
           whileRes[1]=whileRes[1]+conditionCode2Mem(tempVar,rightCond,allTrueVal[1],cond);
           whileRes[2]=whileRes[2]+conditionCode2Stack(tempVar,rightCond,allTrueVal[2],cond);
           whileRes[3]=whileRes[3]+conditionCode2Accum(tempVar,rightCond,allTrueVal[3],cond);
           whileRes[4]=whileRes[4]+conditionCode2TwoAdd(tempVar,rightCond,allTrueVal[4],cond);
           whileRes[5]=whileRes[5]+conditionCode2TwoReg(tempVar,rightCond,allTrueVal[5],cond);
            
        }
        else if(rightCond.length()<=1 && leftCond.length()<=1){
           whileRes[0]=whileRes[0]+conditionCode2(leftCond,rightCond,allTrueVal[0],cond);
           whileRes[1]=whileRes[1]+conditionCode2Mem(leftCond,rightCond,allTrueVal[1],cond);
           whileRes[2]=whileRes[2]+conditionCode2Stack(leftCond,rightCond,allTrueVal[2],cond);
           whileRes[3]=whileRes[3]+conditionCode2Accum(leftCond,rightCond,allTrueVal[3],cond);
           whileRes[4]=whileRes[4]+conditionCode2TwoAdd(leftCond,rightCond,allTrueVal[4],cond);
           whileRes[5]=whileRes[5]+conditionCode2TwoReg(leftCond,rightCond,allTrueVal[5],cond);
        }
        
        return whileRes;
    }
    
    public String[] getArrayExpr(String expr){
     //   String newExpr="";
        String[] evalExpr={"","","","","",""};
        String index=expr.substring(expr.indexOf("[")+1,expr.indexOf("]"));
                String base,other;
                int i;
                for(i=expr.indexOf("[")-1;i>=0;i--){
                    
                    if(expr.charAt(i)=='+' || expr.charAt(i)=='-' || expr.charAt(i)=='*' || expr.charAt(i)=='(' || expr.charAt(i)=='/'){
                        break;
                    }
                }
                base=expr.substring(i+1,expr.indexOf("["));
               // System.out.println(base);
                String[] arr=getArray(base,index,"z");
                for(int k=0;k<arr.length;k++){
                    evalExpr[k]=evalExpr[k]+arr[k];
                }
                
        
        return evalExpr;
    }
    
    
    
    
    
    
    
    public String conditionCode2(String left,String right,String trueV,String cond){
        
        String tot="";
        String reg1,reg2,reg3;
        regInd=0;
        reg1=regVars[regInd];
            regInd++;
            reg2=regVars[regInd];
            regInd++;
            reg3=regVars[regInd];
            regInd++;
        if(cond.equals("==")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"Loop : BNE "+reg1+" "+reg2+" Exit\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"Loop : BEQ "+reg1+" "+reg2+" Exit\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"Exit :\n";
            
        }
        else if(cond.equals(">=")){
             tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"Loop : BLE "+reg1+" "+reg2+" Exit\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"Exit :\n";
           
        }
        else if(cond.equals("<=")){
             tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"Loop : BGE "+reg1+" "+reg2+" Exit\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"Exit :\n";
           
        }
        else if(cond.equals("<")){ 
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"Loop : BGT "+reg1+" "+reg2+" Exit\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals(">")){
             tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"LOAD "+reg2+" "+right+"\n";
            tot=tot+"Loop : BLT "+reg1+" "+reg2+" Exit\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"Exit :\n";
        }
        return tot;       
    }
    
    
    public String[] getArray(String base,String index,String res){
        String str[]={"","","","","",""};
       
        String expr=base+"+("+4+"*"+index+")";
        // System.out.println(expr);
        expr=post.getPostfixString2(expr);
        //System.out.println(expr);
        str[0]=str[0]+getLoadStore(expr, res);
         str[1]=str[1]+getMemtoMem(expr, res);
         str[2]=str[2]+getStack(expr, res);
        str[3]=str[3]+getAccum(expr, res);
         str[4]=str[4]+getTwoAddress(expr, res);
        str[5]=str[5]+getTwoReg(expr, res);
       
        
        return str;
    }
    
    public String getGoto(String trueV){
        String tr="";
        
        
        tr=tr+"J "+trueV;
        return tr;
    }
    
    public void getLoop2(String m) throws IOException{
        String sk="";
        regInd=0;
        tempind=0;
        String x=m.substring(m.indexOf("loop")+4,m.lastIndexOf("loop"));
        
        String[] exprs={"","","","","","","","","","",};
        int k=0;
        StringTokenizer str2=new StringTokenizer(m,";");
        
        while(str2.hasMoreElements()){
            exprs[k]=(String) str2.nextElement();
            k++;
            
        }
        
        exprs[0]=exprs[0].substring(exprs[0].indexOf(":")+1,exprs[0].length());
        
        for(int j=0;j<k;j++){
            String[] evalIfExp;
            String expr2=exprs[j];
           
            expr2=post.IgnoreSpaces(expr2);
            // System.out.println(expr2);
           
            if(expr2.indexOf("if")!=-1){        
           
                evalIfExp=getIfString(expr2);
                for(int l=0;l<evalIfExp.length;l++){
                    evalIfExp[l]="loop :"+evalIfExp[l];
                }
                MIPS.display(evalIfExp);
           
        }
        else if(expr2.indexOf("while")!=-1){
            evalIfExp=getLoop(expr2);
            for(int l=0;l<evalIfExp.length;l++){
                    evalIfExp[l]="loop :"+evalIfExp[l];
            }
            MIPS.display(evalIfExp);
        }
        
        else{
            String evalExpr[]={"","","","","","",""};
            if(expr2.indexOf("[")!=-1){
                String index=expr2.substring(expr2.indexOf("[")+1,expr2.indexOf("]"));
                String base,other;
                int i;
                for(i=expr2.indexOf("[")-1;i>0;i--){
                    
                    if(expr2.charAt(i)=='+' || expr2.charAt(i)=='-' || expr2.charAt(i)=='*' || expr2.charAt(i)=='(' || expr2.charAt(i)=='/'){
                        break;
                    }
                }
                base=expr2.substring(i+1,expr2.indexOf("["));
                String[] arr=getArray(base,index,"z");
                for(i=0;i<arr.length;i++){
                    evalExpr[i]=evalExpr[i]+arr[i];
                }
                other=expr2.substring(0,i-3)+"z";
               // System.out.println(other);
                String evalExpr2,evalRes2;
                
                evalExpr2=post.getEvalExpr(other);
                evalRes2=""+post.getResultVariable(other);
                evalExpr2=post.getPostfixString2(evalExpr2);
                
                evalExpr[0]=evalExpr[0]+getLoadStore(evalExpr2, evalRes2);
                evalExpr[1]=evalExpr[1]+getMemtoMem(evalExpr2, evalRes2);
                evalExpr[2]=evalExpr[2]+getStack(evalExpr2, evalRes2);
                evalExpr[3]=evalExpr[3]+getAccum(evalExpr2, evalRes2);
                evalExpr[4]=evalExpr[4]+getTwoAddress(evalExpr2, evalRes2);
                evalExpr[5]=evalExpr[5]+getTwoReg(evalExpr2, evalRes2);
        
                for(int l=0;l<evalExpr.length;l++){
                    evalExpr[l]="loop :"+evalExpr[l];
                }
                
            }
            else{
                String evalExpr2,evalRes2;
                evalExpr2=post.getEvalExpr(expr2);
                evalRes2=""+post.getResultVariable(expr2);
                evalExpr2=post.getPostfixString2(evalExpr2);
                evalExpr[0]=evalExpr[0]+getLoadStore(evalExpr2, evalRes2);
                evalExpr[1]=evalExpr[1]+getMemtoMem(evalExpr2, evalRes2);
                evalExpr[2]=evalExpr[2]+getStack(evalExpr2, evalRes2);
                evalExpr[3]=evalExpr[3]+getAccum(evalExpr2, evalRes2);
                evalExpr[4]=evalExpr[4]+getTwoAddress(evalExpr2, evalRes2);
                evalExpr[5]=evalExpr[5]+getTwoReg(evalExpr2, evalRes2);
                
                for(int l=0;l<evalExpr.length;l++){
                    evalExpr[l]="loop :"+evalExpr[l];
                }
             }
            MIPS.display(evalExpr);
        
    }
            
            
        }
        
      //  return sk;
        
        
        
    }
    
    
    public String getTwoAddress(String m,String res){
        String newStr="";
        Stack k=new Stack();
       // char temp;
        
        char temp2;
        int count=0;
        for(int i=0;i<m.length();i++){
            
            temp2=m.charAt(i);
            
            if(temp2=='+'||temp2=='-'||temp2=='*'||temp2=='/'){
                count=0;
                String lx;
                String ly;
                switch(temp2){
                    case '+': lx=(String)k.pop();
                            newStr=newStr+"ADD "+lx+" "+k.pop()+"\n";
                             k.push(lx); 
                             break;
                    case '-':lx=(String) k.pop(); 
                             ly=(String) k.pop();
                             newStr=newStr+"SUB "+ly+" "+lx+"\n";
                             k.push(ly); 
                             break;
                    case '*': lx=(String) k.pop();
                             newStr=newStr+"MUL "+lx+" "+k.pop()+"\n";
                             k.push(lx); 
                             break;
                    case '/':  lx=(String) k.pop();
                             newStr=newStr+"DIV "+lx+" "+k.pop()+"\n";
                             k.push(lx); 
                             break;
                    
                    
                }
                
            }
            else{
                k.push(temp2+"");
                count++;
                //System.out.println(count);
                if(count==2){
                     String tempVar=tempVars[tempind];
                     tempind++;
                     String lx,ly;
                     
                     newStr=newStr+"MOV "+tempVar+" "+k.pop()+"\n";
                     k.push(tempVar);
                     
                }
                
                        
                
            }
            
            
            
            
        }
         newStr=newStr+"MOV "+res+" "+k.pop()+"\n";
            
        
        return newStr;
        
        
    }
    
     public String getTwoReg(String m,String res){
        String newStr="";
        Stack k=new Stack();
       // char temp;
        Stack regStack=new Stack();
        regInd=0;
        char temp2;
        int count=0;
        boolean tru=false;
        for(int i=0;i<m.length();i++){
            
            temp2=m.charAt(i);
            
            if(temp2=='+'||temp2=='-'||temp2=='*'||temp2=='/'){
                count=0;
                String lx,ly;
                if(k.empty() && tru){
                    lx=(String) regStack.pop();
                    ly=(String) regStack.pop();
                }
                else{
                    lx=(String) regStack.pop();
                    ly=(String) k.pop();
                    
                }
                switch(temp2){
                    case '+':
                            newStr=newStr+"ADD "+lx+" "+ly+"\n";
                             regStack.push(lx); 
                             break;
                    case '-':newStr=newStr+"SUB "+lx+" "+ly+"\n";
                             regStack.push(lx); 
                             break;
                    case '*':
                             newStr=newStr+"MUL "+lx+" "+ly+"\n";
                             regStack.push(lx); 
                             break;
                    case '/': newStr=newStr+"DIV "+lx+" "+ly+"\n";
                             regStack.push(lx); 
                             break;
                    
                    
                }
                
            }
            else{
                k.push(temp2+"");
                count++;
                tru=true;
                //System.out.println(count);
                if(count==2){
                     String tempVar=regVars[regInd];
                     regInd++;
                     
                     newStr=newStr+"LOAD "+tempVar+" "+k.pop()+"\n";
                     regStack.push(tempVar);
                     
                }
                
                        
                
            }
            
        }
         newStr=newStr+"STORE "+res+" "+regStack.pop()+"\n";
            
        
        return newStr;
        
        
    }
    
        public String conditionCodeStack(String left,String right,String trueV,String falseV,String cond){
        String tot="";
        
        
        if(cond.equals("==")){
            tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"BEQ "+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("!=")){
             tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"BNE "+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
            
        }
        else if(cond.equals(">=")){
             tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"BGE "+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<=")){
             tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"BLE "+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<")){
             tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"BLT "+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        else if(cond.equals(">")){
             tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"BGT "+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        return tot;
    }
    
    public String conditionCodeAccum(String left,String right,String trueV,String falseV,String cond){
        String tot="";
        if(cond.equals("==")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"BEQ "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"BNE "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
            
        }
        else if(cond.equals(">=")){
             tot=tot+"LOAD "+left+"\n";
            tot=tot+"BGE "+right+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<=")){
             tot=tot+"LOAD "+left+"\n";
            tot=tot+"BLE "+right+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"BLT "+right+"\n";
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        else if(cond.equals(">")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"BGT "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        return tot;
    }
    
    public String conditionCodeMem(String left,String right,String trueV,String falseV,String cond){
        String tot="";
        if(cond.equals("==")){
            tot=tot+"BEQ "+left+" "+right+" True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"BNE "+left+" "+right+" True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"BGT "+left+" "+right+" True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<=")){
            tot=tot+"BLE "+left+" "+right+" True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
           
        }
        else if(cond.equals("<")){
           tot=tot+"BLT "+left+" "+right+" True\n"; 
           tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        else if(cond.equals(">")){
            tot=tot+"BGT "+left+" "+right+" True\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        return tot;
    }
    public String conditionCodeReg(String left,String right,String trueV,String falseV,String cond){
        String tot="";
         String reg1,reg2,reg3;
        regInd=0;
        reg1=regVars[regInd];
            regInd++;
            reg2=regVars[regInd];
            regInd++;
       
        if(cond.equals("==")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BEQ "+reg2+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BNE "+reg2+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BGE "+reg2+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("<=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BLE "+reg2+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("<")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BLT "+reg2+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        else if(cond.equals(">")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BGT "+reg2+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        return tot;
    }
    
    public String conditionCodeTwoMem(String left,String right,String trueV,String falseV,String cond){
        String tot="";
       
       
        if(cond.equals("==")){
           
            tot=tot+"BEQ "+left+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"BNE "+left+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"BGE "+left+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("<=")){
            tot=tot+"BLE "+left+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit :\n";
        }
        else if(cond.equals("<")){
            tot=tot+"BLT "+left+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        else if(cond.equals(">")){
            tot=tot+"BGT "+left+" "+right+"\n";
            tot=tot+falseV+"\n";
            tot=tot+"J Exit\n";
            tot=tot+"True : "+trueV+"\n";
            tot=tot+"Exit:\n";
        }
        return tot;
    }
    
     public String conditionCode2Stack(String left,String right,String trueV,String cond){
        
        String tot="";
        if(cond.equals("==")){
            tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"Loop : BNE "+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"Loop : BEQ "+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"Loop : BLT "+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<=")){
            tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"Loop : BGT "+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<")){ 
            tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"Loop : BGE "+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals(">")){
            tot=tot+"PUSH "+left+"\n";
            tot=tot+"PUSH "+right+"\n";
            tot=tot+"Loop : BLE "+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        return tot;       
    }
    
    public String conditionCode2Accum(String left,String right,String trueV,String cond){
        
        String tot="";
        if(cond.equals("==")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"Loop : BNE "+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals("!=")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"Loop : BEQ "+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"Loop : BLT "+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<=")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"Loop : BGT "+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<")){ 
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"Loop : BGE "+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals(">")){
            tot=tot+"LOAD "+left+"\n";
            tot=tot+"Loop : BLE "+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        return tot;       
    } 
     
    public String conditionCode2Mem(String left,String right,String trueV,String cond){
        
        String tot="";
        if(cond.equals("==")){
            tot=tot+"Loop : BNE "+left+" "+right+" True\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals("!=")){
           tot=tot+"Loop : BEQ "+left+" "+right+" True\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"Loop : BLT "+left+" "+right+" True\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<=")){
            tot=tot+"Loop : BGT "+left+" "+right+" True\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<")){ 
            tot=tot+"Loop : BGE "+left+" "+right+" True\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals(">")){
            tot=tot+"Loop : BLE "+left+" "+right+" True\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        return tot;       
    }  
    
    public String conditionCode2TwoAdd(String left,String right,String trueV,String cond){
        
        String tot="";
        if(cond.equals("==")){
            tot=tot+"Loop : BNE "+left+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals("!=")){
           tot=tot+"Loop : BEQ "+left+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"Loop : BLT "+left+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<=")){
            tot=tot+"Loop : BGT "+left+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<")){ 
            tot=tot+"Loop : BGE "+left+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals(">")){
            tot=tot+"Loop : BLE "+left+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        return tot;       
    }  
    
    public String conditionCode2TwoReg(String left,String right,String trueV,String cond){
        
        String tot="";
         String reg1,reg2;
        regInd=0;
        reg1=regVars[regInd];
            regInd++;
            reg2=regVars[regInd];
            regInd++;
        if(cond.equals("==")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BNE "+reg2+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals("!=")){
           tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BEQ "+reg2+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
            
        }
        else if(cond.equals(">=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BLT "+reg2+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<=")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BGT "+reg2+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
           
        }
        else if(cond.equals("<")){ 
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BGE "+reg2+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        else if(cond.equals(">")){
            tot=tot+"LOAD "+reg1+" "+left+"\n";
            tot=tot+"BLE "+reg2+" "+right+"\n";
            tot=tot+trueV+"\n";
            tot=tot+"J Loop\n";
            tot=tot+"True :\n";
        }
        return tot;       
    }  
}
