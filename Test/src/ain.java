
public class ain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Method2 m = new Method2("Birmingham Bob","Surprise","&-1D-3D.12.5D.14.5D.14.5D.14.5D.14.5D.14.5D,1D","16");
		m.initialize(16, Composition.PLAIN_COURSE);
		
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
