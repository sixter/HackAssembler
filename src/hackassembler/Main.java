package hackassembler;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

public class Main {
	public static void main(String Args[]) throws IOException{
		hackassembler();
	}
	
	public static void hackassembler() throws IOException{
		
		String fileName = new String("PongL");
		List<String> input = Files.readAllLines(Paths.get("C:/Users/User/Documents/Nand2Tetris/nand2tetris/projects/06/" + fileName.toLowerCase() + "/" + fileName + ".asm"));
		List<String> output = new ArrayList<String> ();
		char firstChar = 'a';
		
		// Convert symbol to numbers
		SymbolTable.convertSymbolsToAddresses(input);
		
		// Convert symbol-less program to binary
		for (String line : input) 
		{ 
			firstChar = line.charAt(0);
			if (firstChar == '@'){	// A command
				output.add(getACommand(line));
			}
			else {	// C command
				output.add(getCCommand(line));
			}	
		}
		
		FileWriter writer = new FileWriter(fileName + ".hack"); 
		for(String str: output) {
		  writer.write(str + "\n");
		}
		writer.close();
	}
	
	public static String getACommand(String input){
		int address;
		String command = new String();
		
//		System.out.println(input);
		address = Parser.parseA(input);
//		System.out.println(address);
		command = Code.codeA(address);
//		System.out.println(command);
		return command;
	}
	
	public static String getCCommand(String input){
		String command = new String();
		
		String[] address = Parser.parseC(input);
		command = Code.codeC(address);
//		System.out.println("dest: " + address[0]);	// Dest
//		System.out.println("comp: " + address[1]);	// Comp
//		System.out.println("jump: " + address[2]);	// Jump
//		System.out.println(command);
		return command;
	}
}
