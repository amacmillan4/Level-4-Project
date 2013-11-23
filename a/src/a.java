

public class a {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Method method = new Method("Aberafan", "&-36-14-58-16-34-58-56-58,12", 100, 8);
		method.swapRound();
		
		int i = 0;
		while (i < 200){
			
			
			if (i % 8 == 0){
				System.out.println();
			}
			
			if (i == 48){
				method.swapRound();
			}
			
			if( i == 96)
				method.swapRound();
			
			if( i == 144)
				method.swapRound();
			
			System.out.print(method.calcNext());

			i++;
		}
	}

}
