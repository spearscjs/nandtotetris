//-----------------------------------------------------
// Design Name : Not16 Testbench
// File Name   : Not16_tb.v
// Function    : Testbench for Not16 gate
// Coder       : <YOUR NAME HERE>
//-----------------------------------------------------
module Not16_tb;
	/*VARIABLE SETUP */
	reg	[15:0]	in; //inputs are registers
	wire[15:0]	out; //outputs are wires, one bit default

	//create chip instance and connect it
	Not#(16) n1(
		.in		(in),
		.out	(out)
	);


	/* PULSING CLOCK FOR TEST
	(changes phase after each timestep) */
	reg clk = 0;
	always #1 clk = !clk; 


	/* TEST VALUES 
	# 1 means wait one timestep */
	initial begin
			in = 16'b0000_0000_0000_0000;//initial value
		# 1 in = 16'b1111_1111_1111_1111;
		# 1 in = 16'b1010_1010_1010_1010;
		# 1 in = 16'b0011_1100_1100_0011;
		# 1 in = 16'b0001_0010_0011_0100;
		# 1 $stop; //stop after last timestep
	end 


	/* RUN TEST */
	//display happens only once
	//monitor runs whenever variables change
	initial begin
		$display("| %16s | %16s |","in","out"); 
		$monitor("| %16b | %16b |",in,out); 
	end 

endmodule //End of Not16_tb