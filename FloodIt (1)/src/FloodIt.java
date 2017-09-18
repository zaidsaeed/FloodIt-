

/**
 * The class <b>FloodIt</b> launches the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class FloodIt {
   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If a game size (<12) is passed as parameter, it is 
     * used as the board size. Otherwise, a default value is passed
     * 
     * @param args
     *            command line parameters
     */

     public static void main(String[] args) {
        StudentInfo info =new StudentInfo();
        info.display();
        Integer x=12;
        try
        {
             x = Integer.parseInt(args[0]);
        }
        catch(ArrayIndexOutOfBoundsException ie)
        {
            x=12;
        
        }
        if(x<10){
                x= 12;
            }   
        

        GameController p = new GameController(x*x);

   }


}