//-----------------------------------------------------
// Design Name : Xor16 Testbench
// File Name   : Xor16_tb.v
// Function    : Testbench for Xor16 gate
// Coder       : spearscjs
//-----------------------------------------------------
module Xor16_tb;
	/*VARIABLE SETUP */
	reg	[15:0]	in1, in2; //inputs are registers
	wire[15:0]	out; //outputs are wires, one bit default

	//create chip instance and connect it
	Xor#(16) x1(
		.in1	(in1),
		.in2	(in2),	
		.out	(out)
	);


	/* PULSING CLOCK FOR TEST
	(changes phase after each timestep) */
	reg clk = 0;
	always #1 clk = !clk; 


	/* TEST VALUES 
	# 1 means wait one timestep */
	initial begin
			in1 = 16'b0000_0000_0000_0000;//initial value
			in2 = 16'b0000_0000_0000_0000;
		# 1 in1 = 16'b1111_1111_1111_1111;
		# 1 in2 = 16'b0001_0010_0011_0100;
		# 1 in1 = 16'b1010_1010_1010_1010;
		# 1 in2 = 16'b1111_1111_1111_1111;
		# 1 in1 = 16'b0011_1100_1100_0011;
		# 1 in2 = 16'b1010_1010_1010_1010;
		# 1 in1 = 16'b1111_1110_1111_0110;
		# 1 in2 = 16'b0011_1100_1100_0011;

		# 1 $stop; //stop after last timestep
	end 


	/* RUN TEST */
	//display happens only once
	//monitor runs whenever variables change
	initial begin
		$display("| %16s | %16s | %16s |","in1","in2","out"); 
		$monitor("| %16b | %16b | %16b |",in1,in2,out); 
	end 

endmodule //End of Not16_tb
