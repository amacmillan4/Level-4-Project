import java.io.IOException;
import java.util.ArrayList;

import FormatIO.EofX;
import FormatIO.FileIn;


public class KPT {

	public static int possibleKeys = 65536;
	public static String knownPlainText = "0x416c";
	
	public static void main(String args[]) throws IOException{

		ArrayList<Integer> cipherBlocks = new ArrayList<Integer>();
		String key_string;
		String alltext = "";
		
		//Read file contents into ArrayList - One line per item
		FileIn fin  = new FileIn("KPT.txt");
		try
		{
			for (;;)
			{
				String	s = fin.readWord();
				int	i = Hex16.convert(s);
				cipherBlocks.add(i);

			}
		}
		catch (EofX x){}

		//For all possible keys
		for (int q = 0; q < possibleKeys; q++){

			//Convert into correct format
			key_string = String.format("0x%04X", q);
			int key = Hex16.convert(key_string);

			//Decrpyt using key
			int	p = Coder.decrypt(key, cipherBlocks.get(0));
			String out = String.format("0x%04x", p);

			//If plaintext is matched
			if (out.contains(knownPlainText)){
				System.out.println("Key = " + key_string);

				for (Integer block: cipherBlocks){
					
					//Decrypt each block
					p = Coder.decrypt(key, block);
					out = String.format("0x%04x", p);

					int	c0 = p / 256;
					int	c1 = p % 256;

					alltext += (char)c0;

					if (c1 != 0)
						alltext += (char)c1;
				}	
				break;
			}
		}
		System.out.println("Text = " + alltext);
	}




}
