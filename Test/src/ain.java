
public class ain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Method2 m = new Method2("Birmingham Bob\t\t16\t&-1D-3D.12.5D.14.5D.14.5D.14.5D.14.5D.14.5D,1D");
		m.initialize();

		
		int i=0;
		while (i < 1100){
			if(i % 16 == 0){
				System.out.println();
			}
			System.out.print(m.calcNext());
			i++;

		}
		
	}

}
