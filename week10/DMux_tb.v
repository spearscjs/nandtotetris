//-----------------------------------------------------
// Design Name : Not Testbench
// File Name   : Not_tb.v
// Function    : Testbench for Not gate
// Coder       : spearscjs
//-----------------------------------------------------
module DMux_tb;
	/*VARIABLE SETUP */
	reg in, sel; //inputs are registers
	wire out1, out2; //outputs are wires, one bit default

	//create chip instance and connect it
	DMux d1 (
		.in 	(in),
		.sel	(sel),
		.out1	(out1),
		.out2	(out2)
	);


	/* PULSING CLOCK FOR TEST
	(changes phase after each timestep) */
	reg clk = 0;
	always #1 clk = !clk; 


	/* TEST VALUES 
	# 1 means wait one timestep */
	initial begin
		in = 0;//initial values
		sel = 0;
		# 1  in  = 1;
		# 1  sel = 1;
		# 2  in = 0;
		# 1 $stop;
		
	end 


	/* RUN TEST */
	//display happens only once
	//monitor runs whenever variables change
	initial begin
		$display("| in  |   sel |  out1|  out2 |"); 
		$monitor("|  %b  |   %b   |  %b   |   %b   |",in, sel,out1,out2); 
	end 

endmodule //End of And_tb
