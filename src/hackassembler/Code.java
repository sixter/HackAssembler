package hackassembler;

import java.util.HashMap;
import java.util.Map;

public class Code {
	
	public static int[] binaryTable = {1, 2, 4, 8, 16, 32, // 2^5
		64, 128, 256, 512, 1024, // 2^10
		2048, 4096, 8192, 16384, 32768}; // 2^15
	
	public static String codeA(int address){
		String commandA = new String("0");
		commandA = "0" + convertAddressToBinary(address);
		return commandA;
	}
	
	public static String convertAddressToBinary(int address){
		String binary = new String("");
		for (int i = 14; i>=0; i--){
			if (address >= binaryTable[i]){
				binary = binary + "1";
				address = address - binaryTable[i];
			}
			else {
				binary = binary + "0";
			}
		}
		return binary;
	}
	
	
	public static String codeC(String[] destCompJump){
		String commandC = new String("111");
		
//		if (destCompJump[0] == null && destCompJump[1] != null){
//			destCompJump[0] = destCompJump[1];
//			destCompJump[1] = null;
//		}
		
		// convert comp to binary
		String comp = new String("0");
		if (destCompJump[1].contains("M")){
			comp = "1";
			destCompJump[1] = destCompJump[1].replace("M", "A");
		}
		comp = comp + compTable.get(destCompJump[1]);
		
		// convert dest to binary
		String dest = new String("000");
		if (!(destCompJump[0] == null)){
			dest = destTable.get(destCompJump[0]);
		}
		
		// convert jump to binary
		String jump = new String("000");
		if (!(destCompJump[2] == null)){
			jump = jumpTable.get(destCompJump[2]);
		}
		
		commandC = commandC + comp + dest + jump;
		return commandC;
	}
	
	public static Map< String,String> compTable =  new HashMap< String,String>(); 
	static {
		compTable.put("0", "101010");
		compTable.put("1","111111");
		compTable.put("-1","111010");
		compTable.put("D","001100");
		compTable.put("A","110000");
		compTable.put("!D","001101");
		compTable.put("!A","110001");
		compTable.put("-D","001111");
		compTable.put("-A","110011");
		compTable.put("D+1","011111");
		compTable.put("A+1","110111");
		compTable.put("D-1","001110");
		compTable.put("A-1","110010");
		compTable.put("D+A","000010");
		compTable.put("D-A","010011");
		compTable.put("A-D","000111");
		compTable.put("D&A","000000");
		compTable.put("D|A","010101");
		// Flipped equations
		compTable.put("1+D","011111");
		compTable.put("1+A","110111");
		compTable.put("-1+D","001110");
		compTable.put("-1+D","110010");
		compTable.put("A+D","000010");
		compTable.put("-A+D","010011");
		compTable.put("-D+A","000111");
		compTable.put("A&D","000000");
		compTable.put("A|D","010101");
	}
	
	public static Map< String,String> destTable =  new HashMap< String,String>(); 
	static {
		destTable.put("M", "001");
		destTable.put("D", "010");
		destTable.put("A", "100");
		destTable.put("MD", "011");
		destTable.put("AM", "101");
		destTable.put("AD", "110");
		destTable.put("AMD", "111");
		// Flipped equations
		destTable.put("DM", "011");
		destTable.put("MA", "101");
		destTable.put("DA", "110");
		destTable.put("ADM", "111");
		destTable.put("DAM", "111");
		destTable.put("DMA", "111");
		destTable.put("MAD", "111");
		destTable.put("MDA", "111");
	}
	
	public static Map< String,String> jumpTable =  new HashMap< String,String>(); 
	static {
		jumpTable.put("JGT", "001");
		jumpTable.put("JEQ", "010");
		jumpTable.put("JGE", "011");
		jumpTable.put("JLT", "100");
		jumpTable.put("JNE", "101");
		jumpTable.put("JLE", "110");
		jumpTable.put("JMP", "111");
	}
}
