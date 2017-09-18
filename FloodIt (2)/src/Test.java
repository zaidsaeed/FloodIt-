public class Test{

	


	public static void main(String args[]){
		GameModel one = new GameModel(2);
		System.out.println(one);
		GameModel p = (GameModel) one.clone();
		System.out.println(p);



	}

}