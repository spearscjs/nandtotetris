module Or8Way (
	input [7:0] in,
	output out 
);


or(o1,in[0],in[1]);
or(o2,in[2],in[3]);
or(o3,in[4],in[5]);
or(o4,in[6],in[7]);
or(o12,o1,o2);
or(o34,o3,o4);
or(out,o12,o34);



endmodule
