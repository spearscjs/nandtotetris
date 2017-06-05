/**********************************************************************
* AUTHOR: Curtis Spears
* LAST MODIFIED: 04/08/17 1:00AM
***********************************************************************
* TITLE:				Parser
* PROGRAM DESCRIPTION:  Encapsulates access to the input code. Reads a vm language command, parses
* 						it, and provides convenient access to the command's components).
* 						In addition, removes all white space and comments.
***********************************************************************/
//Imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

	//Constants *****************************************************************************
	//Command Types 
	public static final char C_ARITHMETIC = 'A';
	public static final char C_PUSH = 'P';
	public static final char C_POP = 'O';
	public static final char NO_COMMAND = 'N';
	//not currently implemented 
	public static final char C_IF = 'I';
	public static final char C_FUNCTION = 'F';
	public static final char C_RETURN ='R';
	public static final char C_CALL = 'C';	
	public static final char C_LABEL = 'L';
	//Command VM Syntax
	public static final String[] ARITHMETIC_COMMANDS = { "eq", "gt", "lt", "or", "add", "sub", "neg", "and", "not" };
	public static final String[] ARG1 = { "constant", "argument", "local", "static", "this", "that", "pointer" } ;
	
	//Instance Variables ********************************************************************
	//file & debugging
	private Scanner inputFile;
	private int lineNumber;
	//parsed command parts
	private String rawLine;
	private String[] components;
	private char commandType;
	private String arg1;
	private int arg2;
	
	//Drivers *******************************************************************************
	/** Parser: default constructor (fills all current assembly codes with translation in map) 
	 * @param String fileName - name of file to be written to (out)
	 * @return n/a
	 */
	public Parser(String inFileName) {
		try {
			this.inputFile = new Scanner(new FileInputStream(inFileName));
		}
		catch(FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			System.out.println("ERROR: File Not Found ... Exiting Program.");
			System.exit(0);
		}
	}
	
	/**  hasMoreCommands: returns boolean if more commands left, closes stream if not
	 * Precondition: file steam is open
	 * Postcondition: true if more commands, else closes stream and false
	 * @return boolean hasMoreCommands
	 */
	public boolean hasMoreCommands() {
		if(this.inputFile.hasNextLine()) {
			return true;
		}
		inputFile.close();
		return false;
	}

	/** advance: reads next line from file
	 * Precondition: file stream is open, called only if hasMoreCommands 
	 * Postcondition: current instruction parts put into instance variables
	 * @param n/a
	 * @return void
	 */
	public void advance() {
		//retrieve
		if(this.inputFile.hasNextLine()) { //check manually anyway to avoid crashes
			//get line
			this.rawLine = this.inputFile.nextLine();
			//update
			this.lineNumber++;
			//this.parse();
			this.components();
			this.parseCommandType();
			this.arg1();
			this.arg2();
		}
	}
	
	
	//Parsing Helpers ********************************************************************************** 	
	/** parseCommandType: determines command type from components 
	 * Precondition: components is not null
	 * Postcondition: sets commandType
	 * @return void
	 */
	private void parseCommandType() {
		if(this.components == null) { 
			this.commandType = NO_COMMAND;
		}
		//Pop & Push Command
		if(this.components.length == 3) {
			if(this.components[0].equals("pop")) {
				this.commandType = C_POP;
				return;
			}
			if(this.components[0].equals("push")) {
				this.commandType = C_PUSH;
				return;
			}
		}
			
		//Arithmetic Command
		for(String command : ARITHMETIC_COMMANDS) {
			if(components[0].equals(command)) {
				this.commandType = C_ARITHMETIC;
				this.arg1 = command;
				return;
			}
		}
		this.commandType = NO_COMMAND;
		
	}
	
	/** arg1: Returns the first arg of the current command.
		In the case of C_ARITHMETIC, the command itself
		(add, sub, etc.) is returned.
	* Precondition: the current command is valid and NOT a C_RETURN type.
	* Postcondition: arg1 parsed into instance variable
	* @return String arg1
	*/
	private String arg1() {
	
		// arithmetic command done in parseCommand
		
		// push command
		if(this.commandType == C_PUSH || this.commandType == C_POP) {
			// traverse command list 
			for(int i=0; i<ARG1.length; i++) {
				if(components[1].equals(ARG1[i])) {
					this.arg1 = this.components[1];
					return arg1;
				}
			}
			//not valid command
			System.out.println("ERROR: invalid " + commandType + " command on line " + lineNumber);
		}
		return null;
	}
	
	/** arg2: Returns the second argument of the current
		command. 
	* Precondition: the current command is  C_PUSH, C_POP, C_FUNCTION, or
					C_CALL. 
	* Postcondition: arg2 parsed into instance variable
	* @return String arg2
	*/
	private int arg2() {
		this.arg2 = 0; //reset
		if(this.commandType == C_PUSH || this.commandType == C_POP) {
			// traverse command list 
			try {
				this.arg2 = Integer.parseInt(this.components[2]);
			}
			catch(NumberFormatException nfE) {
				System.out.println("ERROR: invalid " + commandType + " command at line " + lineNumber);
				nfE.printStackTrace();
			}
		}
		//not valid command
		return arg2;
	}
		
	/**components: gets component of command
	 * 
	 */
	private void components() {
		if(this.rawLine != null) {
			this.components = rawLine.split(" ");
			
		}
	}
	// Accessors/Getters *********************************************************************************
	/** getCommandType: accesses commandType instance variable 
	 * @return char commandType
	 */
	public char getCommandType() {
		return this.commandType;
	}
	
	 // Debugging Getters **********************************************************************************
	/** getCommandTypeString: accesses commandType instance variable as String
	 * @return String commandType
	 */
	public String getCommandTypeString() {
		return Character.toString(this.getCommandType());
	}
	
	/** getRawLine: accesses rawLine instance variable 
	 * @return String rawLine
	 */
	public String getRawLine() {
		return this.rawLine;
	}
	
	/** getLineNumber: accesses lineNumber instance variable 
	 * @return int lineNumber
	 */
	public int getLineNumber() {
		return this.lineNumber;
	}
	
	/** getArg1: obtains instance var
	 * @return String arg1
	 */
	public String getArg1() {
		return this.arg1;
	}
	
	/** getArg2: obtains instance var
	 * @return String arg2
	 */
	public int getArg2() {
		return this.arg2;
	}

}
