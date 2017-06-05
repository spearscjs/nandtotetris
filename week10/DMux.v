module DMux (
	input in, sel,
	output out1, out2
);
	//assign out1
	assign out1 = (sel == 1'b0) ? in :
				  (sel == 1'b1) ? 1'b0 : 1'bx;
	//assign out2
	assign out2 = (sel == 1'b0) ? 1'b0 :
				  (sel == 1'b1) ? in : 1'bx;

endmodule
