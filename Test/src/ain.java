
public class ain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Method2 m = new Method2("Ackhorne\tAlliance\t7\t+3.1.7.1.7.1.7.1.7.3.7.1.5.1");
		m.initialize();

		int i=0;
		while (i < 1100){
			if(i % 7 == 0){
				System.out.println();
			}
			System.out.print(m.calcNext());
			i++;

		}
		
	}

}
