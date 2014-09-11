/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mips;
import java.util.Stack;
/**
 *
 * @author Sridhar
 */
public class InfixToPostfix{
     
    public String expre;
    //public String InToPost(String expr)
    //}
    
    public String IgnoreSpaces(String expr){
       
        String newExpr="";
        for(int i=0;i<expr.length();i++){    
            if(!(expr.charAt(i)==' ')){
                newExpr=newExpr+expr.charAt(i);
            }
        }
        return newExpr;
       
    }
    
    public String IgnoreSpacesColons(String expr){
       
        String newExpr="";
        for(int i=0;i<expr.length();i++){    
            if(!(expr.charAt(i)==' ') && !(expr.charAt(i)==';')){
                newExpr=newExpr+expr.charAt(i);
            }
        }
       // System.out.println(newExpr);
        return newExpr;
       
    }
    
    public char getResultVariable(String str){
        
        char x=str.charAt(0);
     
        return x;
    }
    
    public String getEvalExpr(String str){
        
        String m=str.substring(2, str.length());
        return m;
      
    }
    
    public String getPostfixExpr(String str){
        char[] stack=new char[str.length()];
        int top=-1;
        char checkChar;
        //char temp;
       // int top2;
        //char[] ops={'+','-','*','/'};
        String post="";
        for(int i=0;i<str.length();i++){
            checkChar=str.charAt(i);
            if(checkChar=='('){
                top=top+1;
                stack[top]=checkChar;
            }
            else if(checkChar==')'){
                    try{
                     while(true){
                        if(stack[top]=='('){
                           // stack[top]=' ';
                            top=top-1;
                            break;
                        }
                         else{
                            post=post+stack[top];
                              top=top-1;
                        }
                     }
                     
                      
                     
                
                    }catch(Exception e){
                
                        
                   
                }
                
            }
            
            else if(checkChar=='+' || checkChar=='-' || checkChar=='*' || checkChar=='/'){
                int val,val2;
                    top=top+1;
                    stack[top]=checkChar;                
                
             while((top-1)>=0){
                if(stack[top]=='*'||stack[top]=='/'){
                    val=2;
                }
                else{
                    val=1;
                }
//                while(top2>=0){
                
                if((stack[top-1]=='*'||stack[top-1]=='/')){
                     val2=2;
                }
                else{
                      val2=1;
                 }

                if(val2>=val){      
                      post=post+stack[top-1];
                      
                      top=top-1; 
                      stack[top]=checkChar;
                }
                 else{
                      break;
                 }
                
                 //top2=top2-1;
             }
              display(stack,top);
           }
  //          }
            else {
                post=post+checkChar;
            }
           
            
        }
      //  System.out.println(top);
        while(top>=0){
            post=post+stack[top];
            top=top-1;
        }
        return post;
    }
    
    
    public void display(char arr[], int top){
        System.out.println("array");
        while(top>=0){
            System.out.println(arr[top]);
            top=top-1;
        }
        
    }
    public void display2(Stack s){
      //  System.out.println("array");
        while(!s.empty()){
            System.out.println("display\t"+s.pop());
        }
        
    }
    
    
    public String getPostfixString2(String str){
        
        Stack oper=new Stack();
        String post="";
        char checkChar;
        String temp;
         String topVar,topVarl;
        int val=0, val2=0;
        for(int i=0;i<str.length();i++){
            checkChar=str.charAt(i);
            if(checkChar=='('){
                oper.push(""+checkChar);
            }
            else if(checkChar==')'){
                try{
                temp="";
                do{
                  //  System.out.println("temp"+temp);
                    temp=(String)oper.pop();
                    post=post+temp;
                }while(!temp.equals("("));
                
                }catch(Exception e){
                    
                }
            }
            else if(checkChar=='+' || checkChar=='-' || checkChar=='*' || checkChar=='/'){
            
             oper.push(""+checkChar);
              try{
                  while(!oper.empty()){  
                    
                    topVar=(String)oper.pop();    
                    
                    if(!oper.empty()){
                        val=0;
                        val2=0;
                        if(topVar.equals("*") || topVar.equals("/")){
                            val=2;
                          //  System.out.println("AM Here");
                        }
                    
                        if(topVar.equals("+")||topVar.equals("-")) {
                           val=1;
                        }
                        
                        topVarl=(String)oper.pop();
                        
                        if(topVarl.equals("(")){
                            //oper.push(topVarl);
                            oper.push(topVar);
                            break;
                        }
                        //System.out.println("low oper"+topVarl);
                        
                        if(topVarl.equals("*") || topVarl.equals("/")){
                            val2=2;
                        }
                        
                        if(topVarl.equals("+")||topVarl.equals("-")) {
                            val2=1;
                        }
                        
                        if(val2>=val){
                            post=post+topVarl;
                            oper.push(topVar);
                        }
                 else{
                     oper.push(topVarl);
                     oper.push(topVar);
                     break;
                 }
                 
                }
                else{
                     oper.push(topVar);
                     break;
                }
                 }
                 
                 }catch(Exception e){
                     
                 }
             
             }
           
             else{
                post=post+checkChar;
            }
           //  display2(oper);     
            }
        while(!oper.empty()){
            post=post+(String)oper.pop();           
        }
       
        return post;
    }
}
