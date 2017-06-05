//-----------------------------------------------------
// Design Name : Not Testbench
// File Name   : Not_tb.v
// Function    : Testbench for Not gate
// Coder       : Curtis Sppears
//-----------------------------------------------------
module Not_tb;
	/*VARIABLE SETUP */
	reg in; //inputs are registers
	wire out; //outputs are wires, one bit default

	//create chip instance and connect it
	Not n1 (
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
		in = 0;//initial values
		# 1 in = 1;

		# 1 $stop; //stop after last timestep
	end 


	/* RUN TEST */
	//display happens only once
	//monitor runs whenever variables change
	initial begin
		$display("|  in | out |"); 
		$monitor("|  %b  |  %b  |",in,out); 
	end 

endmodule //End of Not_tb
