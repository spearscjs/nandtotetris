//-----------------------------------------------------
// Design Name : Not
// File Name   : Not.v
// Function    : Not chip (parameterized)
// Coder       : spearscjs
//-----------------------------------------------------
module Not #(parameter WIDTH = 1) 
(	
	input [WIDTH-1:0] in, 
	output [WIDTH-1:0] out
); 

//-------------Code Starts Here---------

	assign out[WIDTH-1:0] = ~in[WIDTH-1:0];
		
//-------------Code Ends Here---------

endmodule //End Of Module Not
