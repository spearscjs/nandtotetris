//-----------------------------------------------------
// Design Name : Not Testbench
// File Name   : Not_tb.v
// Function    : Testbench for Not gate
// Coder       : Curtis Sppears
//-----------------------------------------------------
module Or_tb;
	/*VARIABLE SETUP */
	reg in1, in2; //inputs are registers
	wire out; //outputs are wires, one bit default

	//create chip instance and connect it
	Or o1 (
		.in1		(in1),
		.in2		(in2),
		.out	(out)
	);


	/* PULSING CLOCK FOR TEST
	(changes phase after each timestep) */
	reg clk = 0;
	always #1 clk = !clk; 


	/* TEST VALUES 
	# 1 means wait one timestep */
	initial begin
		in1 = 0;//initial values
		in2 = 0;
		# 1 in1 = 1;
		# 1 in2 = 1;
		# 1 $stop; //stop after last timestep
	end 


	/* RUN TEST */
	//display happens only once
	//monitor runs whenever variables change
	initial begin
		$display("|  in1 |  in2   |  out |"); 
		$monitor("|  %b  |  %b    |  %b  |",in1,in2,out); 
	end 

endmodule //End of And_tb
