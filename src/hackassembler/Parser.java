package hackassembler;

public class Parser {
	
	public static int parseA (String line){
		String addressS;
		int address;
		
		addressS = line.substring(1);
		address = Integer.parseInt(addressS);
		return address;
	}
	
	public static String[] parseC (String line){
		int index;
		String[] parseCommand = new String[3];
		
		// Parsing dest
		index = line.indexOf("=");
		if (index == -1)
			parseCommand[0] = null;
		else{
			parseCommand[0] = line.substring(0, index);
			line = line.substring(index+1);
		}
		
		// Parsing comp
		index = line.indexOf(";");
		if (index == -1){
			parseCommand[1] = line;
			line = "";
		}
		else{
			parseCommand[1] = line.substring(0, index);
			line = line.substring(index+1);
		}
		
		// Parsing jump
		if (line.length() == 0)
			parseCommand[2] = null;
		else
			parseCommand[2] = line;
		
		return parseCommand;
	}
}
