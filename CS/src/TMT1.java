import java.io.IOException;

import FormatIO.FileOut;


public class TMT1 {

	public static void main(String args[]) throws IOException{
				
		int knownPlainText = Hex16.convert("0x4974");
		int T = 65536;
		int L = 3;
		int N = (T)/L;
		
		FileOut fout = new FileOut("TMTable.txt");

		for (int q = 0; q < N; q++){
			
			int random_int = (int) ((Math.random() * T) + 1);
			int copy = random_int;
			
			for(int i = 0; i < L; i++){
				random_int = Coder.encrypt(random_int, knownPlainText);	
			}

			fout.println(random_int+","+copy);
		}
		System.out.println("Success");

		fout.close();
	}
	
}