package hackassembler;
import java.util.*; 

public class SymbolTable {
	
	public static Map< String,String> SymbolTable = new HashMap< String,String>();
	static {
		SymbolTable.put("SP", "0");
		SymbolTable.put("LCL", "1");
		SymbolTable.put("ARG", "2");
		SymbolTable.put("THIS", "3");
		SymbolTable.put("THAT", "4");
		SymbolTable.put("R0", "0");
		SymbolTable.put("R1", "1");
		SymbolTable.put("R2", "2");
		SymbolTable.put("R3", "3");
		SymbolTable.put("R4", "4");
		SymbolTable.put("R5", "5");
		SymbolTable.put("R6", "6");
		SymbolTable.put("R7", "7");
		SymbolTable.put("R8", "8");
		SymbolTable.put("R9", "9");
		SymbolTable.put("R10", "10");
		SymbolTable.put("R11", "11");
		SymbolTable.put("R12", "12");
		SymbolTable.put("R13", "13");
		SymbolTable.put("R14", "14");
		SymbolTable.put("R15", "15");
		SymbolTable.put("SCREEN", "16384");
		SymbolTable.put("KBD", "24576");
	}
	
	public static List<String> convertSymbolsToAddresses(List<String> input){
		List<String> output = new ArrayList<String>();
		int j = 16;
		String symbol = new String();
		List<String> pointersUsed = new ArrayList<String>();
		
		for (int k = 0; k<input.size(); k++){
			if (input.get(k).isEmpty() || input.get(k).charAt(0)== '/'){
				input.remove(k);
				k--;
				continue;
			}
			if (input.get(k).charAt(0) == '('){
				symbol = input.get(k).substring(1,input.get(k).length()-1);
				if (!SymbolTable.containsKey(symbol)){
					SymbolTable.put(symbol, Integer.toString(k));
				}
				pointersUsed.add(Integer.toString(k));
				System.out.println(k);
				input.remove(k);
				k--;
			}
		}
		
		for (int i = 0; i<input.size(); i++) 
		{	
			if (input.get(i).charAt(0) == '@'){
				if (input.get(i).charAt(1) == '0'
					 || input.get(i).charAt(1) == '1' || input.get(i).charAt(1) == '2'
					 || input.get(i).charAt(1) == '3' || input.get(i).charAt(1) == '4'
					 || input.get(i).charAt(1) == '5' || input.get(i).charAt(1) == '6'
					 || input.get(i).charAt(1) == '7' || input.get(i).charAt(1) == '8'
					 || input.get(i).charAt(1) == '9')
					continue;
				
				symbol = input.get(i).substring(1);
				if (!SymbolTable.containsKey(symbol)){
					SymbolTable.put(symbol, Integer.toString(j));
					j++;
					while (pointersUsed.contains(Integer.toString(j+1))){
						j++;
					}
				}
				input.set(i, "@" + SymbolTable.get(symbol));
				continue;
			}
		}
		return output;
	}
	
}
