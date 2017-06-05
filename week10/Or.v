//-----------------------------------------------------
// Design Name : Or
// File Name   : Or.v
// Function    : Or chip (parameterized)
// Coder       : spearscjs
//-----------------------------------------------------
module Or #(parameter WIDTH = 1)
(	
	input [WIDTH-1:0] in1, in2, 
	output [WIDTH-1:0] out
); 

//-------------Code Starts Here---------

	assign out = (in1 | in2);
		
//-------------Code Ends Here---------

endmodule //End Of Module Not
