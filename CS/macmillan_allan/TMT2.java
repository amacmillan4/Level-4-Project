import java.io.IOException;
import java.util.HashMap;

import FormatIO.EofX;
import FormatIO.FileIn;


public class TMT2 {

	public static void main(String args[]) throws IOException{

		HashMap<Integer, Integer> table = new HashMap<Integer,Integer>();
		int knownPlainText = Hex16.convert("0x4974");

		int T = 65536;
		int L = 3;

		FileIn tableIn = new FileIn("TMTable.txt");

		try{
			String word = tableIn.readWord();

			while(word != null){
				table.put(Integer.parseInt(word.split(",")[0]), Integer.parseInt(word.split(",")[1]));
				word = tableIn.readWord();
			}
		}
		catch(EofX e){}

		FileIn cipherIn = new FileIn("TMTCipher.txt");
		FileIn decipher = new FileIn("TMTCipher.txt");

		String alltext = "";

		try{
			int hexC = Hex16.convert(cipherIn.readWord());
			int times = 0;

			cipherIn.close();

			while(!table.containsKey(hexC) && times < L){
				hexC = Coder.encrypt(hexC, knownPlainText);
				times++;
			}

			if (times >= L){
				System.out.println("Number not in table");
			}
			else
			{
				int number = table.get(hexC);

				for(int i = 0; i < (L-times-1); i++){
					number = Coder.encrypt(number, knownPlainText);
				}

				System.out.println("Key = " + String.format("0x%04X", number));

				String word = decipher.readWord();

				while(word != null){

					int	p = Coder.decrypt(number, Hex16.convert(word));

					int	c0 = p / 256;
					int	c1 = p % 256;

					alltext += (char)c0;

					if (c1 != 0)
						alltext += (char)c1;

					word = decipher.readWord();
				}
				

			}


		}
		catch(EofX e){}
		System.out.println("Text = " + alltext);

		tableIn.close();
		decipher.close();
	}

}