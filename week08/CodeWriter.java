/**********************************************************************
* AUTHOR: spearscjs
* LAST MODIFIED: 04/08/17 1:00AM
***********************************************************************
* TITLE:				CodeWriter 
* PROGRAM DESCRIPTION:  writes asm code 
***********************************************************************/

//Imports ****************************************************************************************************
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class CodeWriter {
	
	//Constants **************************************************************************************
	//INSTANCE VARIABLES ***********************************************************
	private HashMap<String, String> commandMap = new HashMap<String, String>();
	private PrintWriter out;
	private String fileName;
	
	//Constructors *****************************************************************
	/** CodeWriter: default constructor (fills all current assembly codes with translation in map) 
	 * @param String fileName - name of file to be written to (out)
	 * @return n/a
	 */
	public CodeWriter(String fileName) {
		//fill command map
		//pus/pop
		this.commandMap.put("push", "@SP\n" +
									"AM=M+1\n" +
									"A=A-1\n" +
									"M=D");

		this.commandMap.put("pop",  "@R13\n" +
									"M=D\n" +
									"@SP\n" +
									"AM=M-1\n" +
									"D=M\n" +
									"@R13\n" +
									"A=M\n" +
									"M=D\n");
		
		//arithmetic
		this.commandMap.put("or",   "@SP\n" +
								    "AM=M-1\n" +
								    "D=M\n" +
								    "A=A-1\n" +
								    "M=D|M\n");
		
		this.commandMap.put("not",  "@SP\n" +
								    "A=M-1\n" +
								    "M=!M\n");
		
		this.commandMap.put("and",  "@SP\n" +
									"AM=M-1\n" +
									"D=M\n" +
									"A=A-1\n" +
									"M=D&M\n");
		
		this.commandMap.put("add",  "@SP\n" +
									"AM=M-1\n" +
									"D=M\n" +
									"A=A-1\n" +
									"M=D+M\n");
		
		this.commandMap.put("sub",  "@SP\n" +
									"AM=M-1\n" +
									"D=M\n" +
									"A=A-1\n" +
									"M=M-D\n");
		
		this.commandMap.put("neg",  "@SP\n" +
									"A=M-1\n" +
									"M=-M\n");		
		
		//output
		try {
			this.fileName = fileName;
			out = new PrintWriter(fileName);
		}
		catch(FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			System.out.println("ERROR: file not found ... EXITING");
			System.exit(0);
		}
	}
	
	//WRITE ************************************************************************
	/** writeArithmetic: writes arithmetic command to file 
	 * @param command
	 */
	public void writeArithmetic(String command) {
		String asm = this.commandMap.get(command);
		if(asm == null) {
			System.out.println("ERROR: invalid command:: " + command + " ... EXITING");
			System.exit(0);
		}
		out.println(asm + "\n");
	}
	
	/** writePush: writes push command to file 
	 * @param command
	 * @param arg2
	 */
	public void writePush(String command, int arg2) {
		//load value register, push
		String asm = "@"  + arg2 + "\nD=A\n" + this.commandMap.get(command);
		out.println(asm + "\n");
	}
	
	/** writePush: writes pop command to file 
	 * @param command
	 * @param arg1 //type 
	 * @param arg2 //numerical val
	 */
	public void writePop(String command, String arg1, int arg2) {
		String asm = "";
		
		    switch (arg1) {
		      case "local": 
		        asm +=
		          "@LCL\n" +
		          "D=M\n" +
		          "@" + arg2 + "\n" +
		          "D=D+A\n" +
		          this.commandMap.get("pop");
		          break;
		      case "argument": 
		        asm +=
		          "@ARG\n" +
		          "D=M\n" +
		          "@" + arg2 + "\n" +
		          "D=D+A\n" +
		          this.commandMap.get("pop");
		          break;
		      case "this": 
		        asm +=
		          "@THIS\n" +
		          "D=M\n" +
		          "@" + arg2 + "\n" +
		          "D=D+A\n" +
		          this.commandMap.get("pop");
		          break;
		      case "that": 
		        asm +=
		          "@THAT\n" +
		          "D=M\n" +
		          "@" + arg2 + "\n" +
		          "D=D+A\n" +
		          this.commandMap.get("pop");
		          break;
		      case "pointer": 
		        if (arg2 == 0) {
		          asm +=
		            "@THIS\n" +
		            "D=A\n" +
		            this.commandMap.get("pop");
		        }
		        else {
		          asm +=
		            "@THAT\n" +
		            "D=A\n" +
		            this.commandMap.get("pop");
		        }
		        	break;
		      case "static": 
		        asm +=
		          "@" + fileName.substring(0,fileName.length()-3) + arg2 + "\n" + //add fileName without extension
		          "D=A\n" +
		          this.commandMap.get("pop");
		          break;
		      case "temp": 
		        asm +=
		          "@R5\n" +
		          "D=A\n" +
		          "@" + arg2 + "\n" +
		          "D=D+A\n" +
		          this.commandMap.get("pop");
		          break;
		      default: 	
		    	  System.out.println("ERROR: invalid command ... EXITING");
		    	  System.exit(0);
		    }
		
		out.write(asm + "\n"); //output
	}
	
	/** close : closes stream
	 * 
	 */
	public void close() {
		this.out.close();
	}
	
	//Mutators *********************************************************************
	/** setFileName: sets file name for class
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
		//this.parser = new Parser(fileName);
	}
	
}
