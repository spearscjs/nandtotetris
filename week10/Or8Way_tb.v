//-----------------------------------------------------
// Design Name : Xor16 Testbench
// File Name   : Xor16_tb.v
// Function    : Testbench for Xor16 gate
// Coder       : spearscjs
//-----------------------------------------------------
module Or8Way_tb;
	/*VARIABLE SETUP */
	reg	[7:0]	in; //inputs are registers
	wire out; //outputs are wires, one bit default

	//create chip instance and connect it
	Or8Way o1(
		.in	 (in),
		.out (out)
	);


	/* PULSING CLOCK FOR TEST
	(changes phase after each timestep) */
	reg clk = 0;
	always #1 clk = !clk; 


	/* TEST VALUES 
	# 1 means wait one timestep */
	initial begin
			in = 8'b0000_0000;//initial value
		# 1 in = 8'b1111_1111;
		# 1 in = 8'b1010_1010;
		# 1 in = 8'b0000_0001;
		# 1 $stop; //stop after last timestep
	end 


	/* RUN TEST */
	//display happens only once
	//monitor runs whenever variables change
	initial begin
		$display("| %8s | %16s |","in","out"); 
		$monitor("| %8b | %16b |",in ,out); 
	end 

endmodule //End of Not16_tb

