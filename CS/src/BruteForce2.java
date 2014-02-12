import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import FormatIO.EofX;
import FormatIO.FileIn;
import FormatIO.FileOut;


public class BruteForce2 {

	public static void main(String args[]) throws IOException{

		ArrayList<Integer> a = new ArrayList<Integer>();
		String key_string;

		// open files
		FileIn	fin  = new FileIn("aaaa.txt");

		try{
			for (;;){
				String	s = fin.readWord();
				a.add(Hex16.convert(s));
			}
		}
		catch (EofX x){}


		for (int q = 0; q < 65536; q++){

			System.out.println("Trying key : " + q);
			key_string = String.format("0x%04X", q);

			FileOut fout = new FileOut("dec_aaaa.txt");

			int key = Hex16.convert(key_string);

			// read blocks, encrypt and output blocks

			for (Integer x: a){
				int	p = Coder.decrypt(key, x);
				String	out = String.format("0x%04x", p);
				fout.println(out);
			}

			fout.close();

			fin = new FileIn("dec_aaaa.txt");
			fout = new FileOut("check.txt");

			// read blocks and output chars
			try
			{
				for (;;)
				{
					String	s = fin.readWord();
					int i = Hex16.convert(s);
					int	c0 = i / 256;
					int	c1 = i % 256;
					fout.print((char)c0);
					if (c1 != 0)
						fout.print((char)c1);
				}
			}
			catch (EofX x)
			{
			}
			fout.close();

			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader("check.txt"));

				String line = br.readLine();
				if (line.contains("allan")){
					System.out.println("FOUND - KEY IS: " + String.format("0x%04x", q));
					br.close();
					break;
				}
				br.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}		
	}

}
