// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.
//Set R2 to 0
//R2
@R2
D=M
M=0

//CHECK FOR 0's (avoids unnecessary looping)
//R1
@R1 //A=R1
D=M //D=RAM[A]
@END //Jump if D=0 (RAM[A]=0)
D;JEQ //Jump if D=0 (RAM[A]=0)
//R0 
@R0 //A=R0
D=M //D=RAM[A]
@END //A=(END)
D;JEQ //Jump if D=0 (RAM[A]=0) 

//Loop through, recusively add R0+=R0 and decrement R1 until R1==0
(LOOP)
        @R0 //A=R0
        D=M //D=RAM[A]
	@R2 //A=R2
	M=M+D //RAM[A]+=D
        @R1 //A=R1
        M=M-1 //RAM[A]-+1
        D=M //D=Ram[A]
        @LOOP //A=LOOP
        D;JGT //if d>0 jump to A

//END infinite loop
(END)
	@END
	0;JMP

