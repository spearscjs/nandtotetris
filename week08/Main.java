
/**********************************************************************
* AUTHOR: Curtis Spears
* LAST MODIFIED: 04/08/17 1:00AM
***********************************************************************
* TITLE:				Main
* PROGRAM DESCRIPTION:  Driver
***********************************************************************/
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		//Initialization
		Scanner keyboard = new Scanner(System.in); //keyboard
		String fileName; //user inputted
		String outFile;
		Parser parser;
		CodeWriter writer;
		boolean found;
		
		//File Name & Initialize dependent variables
		System.out.print("Please enter the name of the .vm file (don't forget file name): ");
		fileName = keyboard.nextLine();
		parser = new Parser(fileName);
		outFile = fileName.substring(0, fileName.length() - 3) + ".asm";
		writer = new CodeWriter(outFile);
		
		//File Traversal
		while(parser.hasMoreCommands()) {
			parser.advance(); //next line / command
			if(parser.getCommandType() == parser.C_ARITHMETIC) {
				writer.writeArithmetic(parser.getArg1());	
			}
			if(parser.getCommandType() == parser.C_PUSH) {
				writer.writePush("push", parser.getArg2());	
			}
			if(parser.getCommandType() == parser.C_POP) {
	
				writer.writePop("pop", parser.getArg1(), parser.getArg2());
			}
			if(parser.getCommandType() != parser.NO_COMMAND) {
				System.out.printf("%-20s		type: %-10c		arg1: %-10s		arg2: %-10d \n",
						parser.getRawLine(), parser.getCommandType(),parser.getArg1(), parser.getArg2());
			}
		}
	//close stream 
		writer.close();
		System.out.println("Translation Successful.			Out File:  " + outFile);
	}
	
}
