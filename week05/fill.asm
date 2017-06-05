// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//RAM[24576] - 16 bit code when keyboard key pressed, 0 otherwise
//@SCREEN - access screen pixels 

//Set Position
@24576 //A=24576 (starting pixel address)
D=A 
@position //A=position
M=D //RAM[A]=D 

(LOOP)
	//Read Keyboard Input (check if pressed)
	@24576 //A=24576
	D=M //D=RAM[A]
	@WHITE //A=(DRAW)
	D,JEQ //jump to WHITE if D=0 

	//DRAW BLACK
	@16384 //A=smallest address for pixel
	D=A 
	@position //A=position
	D=D-M //D=RAM[position]
	@LOOP //A=(RESET)
	D,JEQ //reset when position has reached max 
	@position //A=position
	M=M-1 //Decrement position M=RAM[A]
	D=M //D=RAM[A] - dereference current position
	A=D 
	M=-1 //set next 16 pixels to 1's (white)
	@LOOP //A=(LOOP)
	0,JEQ //unconitional jump to (LOOP)

	//DRAW WHITE
	(WHITE)
	@24576
	D=A
	@position //A=position
	D=D-M //D=RAM[position]
	@LOOP //A=(RESET)
	D,JEQ //reset when position has reached max 
	@position //A=position
	D=M //D=RAM[A] - dereference current position
	A=D
	M=0 //set 16 pixels to 0's (white)
	@position
	M=M+1 //Increment position RAM[A] += 1
	@LOOP //A=(LOOP)
	0,JEQ //unconditional jump to (LOOP)


