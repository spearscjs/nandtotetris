//-----------------------------------------------------
// Design Name : And
// File Name   : And.v
// Function    : And chip (parameterized)
// Coder       : spearscjs
//-----------------------------------------------------
module And #(parameter WIDTH = 1)
(	
	input [WIDTH-1:0] in1, in2, 
	output [WIDTH-1:0] out
); 

//-------------Code Starts Here---------

	assign out = (in1 & in2);
		
//-------------Code Ends Here---------

endmodule //End Of Module Not
