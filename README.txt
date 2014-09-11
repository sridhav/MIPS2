
======================================================================================================================================
#### README FILE #####

Basically the entire program is file oriented.
Every output is displayed in its corresponding file


The command needed to execute the file is 

	java -jar MIPS.jar inp.txt

inp.txt is the file that contains the input the input should be given in the following format
2									#implies the CPI value
10									#implies the Speed up Increment (10% speed up)
A=(B+C)-(D+E); 						#the above two lines are followed by sequence of inputs
if(A>=B) A=B+C; else C=A+D;			#Do remember that the keywords should be in lower case (if & else should be in lower case)
while(A==B) C=A+B+C-D;				#the while is keyword should be in lowercase letters
G=X+A[I];							#use Array base as a single character ie A should be single character 
The keywords if, while, else, goto, loop should be in lower case; 
And a slight problem in array usage. (Not a problem exactly I used a character than a String). Rather than using save[i] use Z[i] 
(rather than using save use Z or any other single letter).


As you can Notice the inp.txt file is located in the folder itself

The output for the input is redirected to the folders in out. out folder has all the output folders 1,2,3 ...etc., the numbers designate the instruction. And the whole output for all
the instructions is present in the all folder. The all2 folder holds the output of the PERFORMANCE ANALYSIS Assigment Values. 
Every folder has 8 output text files except all2 folder
1.	LoadStore.txt
2. 	MemoryMemory.txt
3.	Stack.txt
4.  twoAddress.txt
5.	Accumulator.txt
6.	twoReg.txt

the above are the six different architectures

7.	performance.txt   {here the CPI is Constant which is taken from the file inp.txt}
8. 	performance2.txt	{here the CPI is taken from array which needs to be edited in the source code}

The above two files describe the performance properties for every instruction


The all2 folder which holds the Multi core processor architecture has the following files
1.	LoadStore.txt
2. 	MemoryMemory.txt
3.	Stack.txt
4.  twoAddress.txt
5.	Accumulator.txt
6.	twoReg.txt

the above are the six different architectures
7. performance3.txt  { Performance analysis for the cores}



Here are the list of contents in the handin

1.	inp.txt			#input file to be given
2.	MIPS.jar		#JAR file that needs to be executed
3.	src.zip 			#folder that has all the source files
4.	out.zip				#output files for the given input
5.  Performance Analysis Part 2 #Report for Performance Analysis part2

======================================================================================================================================

### Another way of Execution ####
If want to execute directly from the raw source code. execute the following commands
go to the source folder 
the source folder has an inp.txt file (Edit this file donot edit the file for jar) edit or add instructions if needed
now type the following commands
	
	javac MIPS.java

this compiles my main program. Now enter the following command

	java MIPS inp.txt
	
This is run time execution and inp.txt is given as argument

Entering this commands different folders by name 1,2,3,.... (including all folder) are seen in the src folder. The folders contain the .txt files 
for corresponding instructions and all has all the Instructions output and performance and performance2 has the performance values
 
