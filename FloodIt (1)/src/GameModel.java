import java.util.Random;
class GameModel {


/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the followiung information:
 * - the state of all the ``dots'' on the board (color, captured or not)
 * - the size of the board
 * - the number of steps since the last reset
 * - the current color of selection
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
	Random generator = new Random();
    public final int COLOR_0 = 0;
    public final int COLOR_1  = 1;
    public final int COLOR_2 = 2;
    public final int COLOR_3 = 3;
    public final int COLOR_4  = 4;
    public final int COLOR_5 = 5;
    public final int NUMBER_OF_COLORS  = 6;

    static int NUMBER_OF_STEPS;

    static int CURRENT_SELECTED_COLOR = 0;

    public static DotInfo[] GameModel;

    private int sizeO = 10;
    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param size
     *            the size of the board
     */
    
    public GameModel(int size) {
        NUMBER_OF_STEPS = 0;
    	sizeO = size;
        GameModel = new DotInfo[size];
        for(int i =0; i<GameModel.length; i++){
                GameModel[i] = new DotInfo(i,i,generator.nextInt(6));
           
        	
        }


    }


    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){
        GameModel = new DotInfo[sizeO];
        for(int i =0; i<GameModel.length; i++){
            GameModel[i] = new DotInfo(i,i,generator.nextInt(6));
        }
        NUMBER_OF_STEPS = 0;
    }


    /**
     * Getter method for the size of the game
     * 
     * @return the value of the attribute sizeOfGame
     */   
    public int getSize(){
        return GameModel.length;

    }

    /**
     * returns the current color  of a given dot in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    
    public static int getColor(int i, int j){
        for(int z = 0; z<GameModel.length; z++){
            if(GameModel[z].getX() == i && GameModel[z].getY() == j)
                return GameModel[z].color;
        }
        return GameModel[0].color;
    }

    /**
     * returns true is the dot is captured, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCaptured(int i, int j){
        for(int z = 0; z<GameModel.length; z++){
            if(GameModel[z].getX() == i && GameModel[z].getY() == j){
                return GameModel[z].isCaptured();
            }
        }
        return GameModel[0].isCaptured();
    }

    /**
     * Sets the status of the dot at coordinate (i,j) to captured
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void capture(int i, int j){
        for(int z = 0; z<GameModel.length; z++){
            if(GameModel[z].getX() == i && GameModel[z].getY() == j && GameModel[z].isCaptured() == false){
                GameModel[z].setCaptured(true);
            }
        }        
        }



    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        return NUMBER_OF_STEPS;
    }

    /**
     * Setter method for currentSelectedColor
     * 
     * @param val
     *            the new value for currentSelectedColor
    */   
    public void setCurrentSelectedColor(int val) {
        CURRENT_SELECTED_COLOR = val;
    }

    /**
     * Getter method for currentSelectedColor
     * 
     * @return currentSelectedColor
     */   
    public int getCurrentSelectedColor() {
        return CURRENT_SELECTED_COLOR;

    }


    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
            for(int z = 0; z<GameModel.length; z++){
            if(GameModel[z].getX() == i && GameModel[z].getY() == j){
                return GameModel[z];
            }
        }
        System.out.println("The dot which you are looking for has not been found!");
        return GameModel[0];
    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new color.
     */
     public void step(){
        NUMBER_OF_STEPS = NUMBER_OF_STEPS+ 1;
    }
 
   /**
     * The method <b>isFinished</b> returns true iff the game is finished, that
     * is, all the dats are captured.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
            for(int i = 0; i<GameModel.length; i++){
            if(GameModel[i].isCaptured() == false){
                return false;
            }
        } 
        return true;
    }

    public int returnIndex(int x, int y){
    	for(int i =0; i<GameModel.length; i++){
    		if(GameModel[i].getX() == x && GameModel[i].getY() == y){
                return i;
            }
    	}
    	return 0;
    }





    //This is an extra method here 
     public void printModel(){
    	for(int i =0; i<GameModel.length; i++){
    		System.out.println(GameModel[i]);
    	}
    }

   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */


    public String toString(){
        return "NUMBER_OF_STEPS:" + Integer.toString(NUMBER_OF_STEPS);
   }





}


