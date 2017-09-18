import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.Checkbox;
import java.awt.*;
import java.lang.*;


import javax.swing.JOptionPane; 
import javax.swing.*;
import java.util.*;
import java.io.*;


/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, Serializable {

    
    /**
     * Reference to the view of the board
     */
    private GameView gameView;
    /**
     * Reference to the model of the game
     */
    private GameModel gameModel;


    private JRadioButton plane = new JRadioButton("Plane", false);
    private JRadioButton torus = new JRadioButton("Torus", true);;
    private JRadioButton orthagonal = new JRadioButton("Orthagonal", true);
    private JRadioButton diagonal = new JRadioButton("Diagonal", false);
 
    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */
    

    public void setGameModel(GameModel o){
        gameModel = o;
    }
    public GameController(int size) {
        gameModel = new GameModel(size);
        gameView = new GameView(gameModel, this);
        /*try{
            System.out.println("An element has been pushed into the undo/redo stack");
            GameModel o = (GameModel) gameModel.clone();
            gameModel.undoStack.push(o);   
            }
        catch(CloneNotSupportedException zz){
            System.out.println("E");
        }*/

        //flood();
        //gameView.update();
    }

    /**
     * resets the game
     */
    public void reset(){
        
        //flood(); //excercise erasing this line of code to save efficiencey 
        while(!gameModel.undoStack.isEmpty()){
            GameModel t = (GameModel) gameModel.undoStack.pop();
            t = null; //simply emptying out the undo redo stack when the reset button is called
        }
        gameModel.reset();
        gameView.setModel(gameModel);
        gameView.update();
        gameView.setScoreLabel();
    }

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public GameView getGameView(){
        return gameView;
    }

    public GameModel getGameModel(){
        return gameModel;
    }

    public void createSettingsWindow(){
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(plane);
        buttonGroup.add(torus);
                 
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(orthagonal);
        buttonGroup1.add(diagonal);

        JDialog dialog = new JDialog(gameView);

        JPanel contents = new JPanel(new GridLayout(2, 1,0,25));


        JPanel contents1 = new JPanel();

        JLabel p_or_t = new JLabel("Plane or Torus");
        contents1.add(p_or_t);
        contents1.add(plane);
        contents1.add(torus);
        contents.add(contents1);
                

        JPanel contents2 = new JPanel();
        JLabel p_or_t1 = new JLabel("Diagonal Moves:");
        contents2.add(p_or_t1);
        contents2.add(orthagonal);
        contents2.add(diagonal);
        contents.add(contents2);

                
               
        dialog.setContentPane(contents);
        dialog.setSize(new Dimension(100,100));
        dialog.pack();
        dialog.setLocationRelativeTo(gameView);
        dialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() instanceof DotButton && gameView.gameModelNothingSelected() ) {
            try{
                System.out.println("An element has been pushed into the undo stack!");
                GameModel o = (GameModel) gameModel.clone();
                gameModel.undoStack.push(o);
                gameView.undo.setEnabled(true);

                System.out.println(o.nothingSelected());  
            }
            catch(CloneNotSupportedException zz){
                System.out.println("An element has NOT been pushed into the stack!");
            }
            int row = ((DotButton)(e.getSource() ) ) . getRow();
            int column = ((DotButton)(e.getSource())).getColumn() ;
            gameModel.capture(  row , column  );
   

            selectColor(  ((DotButton)(e.getSource())).getColor() );
        }  
       
        
        else if (e.getSource() instanceof DotButton) {
            
            try{
                System.out.println("An element has been pushed into the undo stack");
                GameModel o = (GameModel) gameModel.clone();
                gameModel.undoStack.push(o); 
                gameView.undo.setEnabled(true);
                System.out.println(o.nothingSelected()); 
            }
            catch(CloneNotSupportedException zz){
                System.out.println("An element has NOT been pushed into the stack!");

            }

            //int row = ((DotButton)(e.getSource() ) ) . getRow();
            //int column = ((DotButton)(e.getSource())).getColumn();
            int color = ((DotButton)(e.getSource())).getColor();
            selectColor(color);
        }


        else if (e.getSource() instanceof JButton) {
            
            JButton clicked = (JButton)(e.getSource());
            if(clicked.getText().equals("Settings")){
                createSettingsWindow();
            }

            if(clicked.getText().equals("Undo")){
                gameModel.redoStack.push(gameModel);
                GameModel t = (GameModel) gameModel.undoStack.pop();
                //gameModel.redoStack.push(t);
                t.undoStack = gameModel.undoStack;
                t.redoStack = gameModel.redoStack;
                gameModel = t;

                if( gameModel.getNumberOfSteps() == 0 && !gameModel.nothingSelected()){
                    System.out.println("Your last  error is here man");
                }

                if( gameModel.getNumberOfSteps() == 0 && !gameModel.nothingSelected()){
                    gameModel.deSelectEverything();
                }
                gameView.redo.setEnabled(true);

                
                //System.out.println(t.nothingSelected());
                gameView.setModel(t);
                if(gameModel.undoStack.isEmpty())
                {
                    clicked.setEnabled(false);
                }
                gameView.update();
                
            }

            if(clicked.getText().equals("Redo")){
                GameModel t = (GameModel) gameModel.redoStack.pop();
                gameModel.undoStack.push(gameModel);
                t.undoStack = gameModel.undoStack;
                t.redoStack = gameModel.redoStack;
                gameModel =t;

                gameView.setModel(t);
                if(gameModel.redoStack.isEmpty())
                {
                    gameView.redo.setEnabled(false);
                }
                gameView.undo.setEnabled(true);
                gameView.update();
            }


            if (clicked.getText().equals("Quit")) {
                try{
                    FileOutputStream fout=new FileOutputStream("savedGame.ser");  
                    ObjectOutputStream out=new ObjectOutputStream(fout); 
                    out.writeObject(gameModel);
                    out.close();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    out = new ObjectOutputStream(bos);
                    out.writeObject(gameModel);
                    out.close();


                    byte[] buf = bos.toByteArray();

                    //ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    //out = new ObjectOutputStream(bos);
                    //out.writeObject(gameModel);
                    //out.close();


                    //byte[] buf = bos.toByteArray();
                    System.exit(0);
                }
                catch(IOException p){
                    p.printStackTrace();
                }
                

                /*OutputStream out = new ObjectOutputStream(new FileOutputStream("savedGame.ser"));
                out.writeObject(this);
                out.close();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                out = new ObjectOutputStream(bos);
                out.writeObject(gameModel);
                out.close();


                byte[] buf = bos.toByteArray();*/
            }
            if (clicked.getText().equals("Reset")){
                reset();
            }
        }
        } 
    


 
    /** @param none
    @return none
    this method creates the dialog of the setting window 
    from which the user can change the settings of his/her game

    **/


       /**
     * <b>selectColor</b> is the method called when the user selects a new color.
     * If that color is not the currently selected one, then it applies the logic
     * of the game to capture possible locations. It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param color
     *            the newly selected color
     */

    public void selectColor(int color){
        if(color != gameModel.getCurrentSelectedColor()) {

            gameModel.setCurrentSelectedColor(color);
            //Push every other model into the stack

            

            if(orthagonal.isSelected() && plane.isSelected()){
             flood();   
            }else if(orthagonal.isSelected() && torus.isSelected()){
             flood1();
            }else if(diagonal.isSelected() && plane.isSelected()){
             flood2();
            }else if(diagonal.isSelected() && torus.isSelected()){
             flood3();
            }


            /*//Push every other model into the stack
            try{
                System.out.println("An element has been pushed into the undo/redo stack");
                GameModel o = (GameModel) gameModel.clone();
                gameModel.undoStack.push(o);   
            }
            catch(CloneNotSupportedException zz){
                System.out.println("E");
            }*/
            
            gameModel.step();
            gameView.update();

            if(gameModel.isFinished()) {
                      Object[] options = {"Play Again",
                                "Quit"};
                        int n = JOptionPane.showOptionDialog(gameView,
                                "Congratulations, you won in " + gameModel.getNumberOfSteps() 
                                    +" steps!\n Would you like to play again?",
                                "Won",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if(n == 0){
                            reset();
                        } else{
                            System.exit(0);
                        }  
            }            
            }        
    }

   /**
     * <b>flood</b> is the method that computes which new dots should be ``captured'' 
     * when a new color has been selected. The Model is updated accordingly. This is for PLANE/ORTHAGONAL combination
     */
     private void flood() {

        Stack<DotInfo> stack = new GenericLinkedStack<DotInfo>();
        for(int i =0; i < gameModel.getSize(); i++) {
           for(int j =0; j < gameModel.getSize(); j++) {
                if(gameModel.isCaptured(i,j)) {
                    stack.push(gameModel.get(i,j));
                }
           }
        }

        while(!stack.isEmpty()){
            DotInfo DotInfo = stack.pop();
            if((DotInfo.getX() > 0) && shouldBeCaptured (DotInfo.getX()-1, DotInfo.getY())) {
                gameModel.capture(DotInfo.getX()-1, DotInfo.getY());
                stack.push(gameModel.get(DotInfo.getX()-1, DotInfo.getY()));
            }  
            if((DotInfo.getX() < gameModel.getSize()-1) && shouldBeCaptured (DotInfo.getX()+1, DotInfo.getY())) {
                gameModel.capture(DotInfo.getX()+1, DotInfo.getY());
                stack.push(gameModel.get(DotInfo.getX()+1, DotInfo.getY()));
            }
            if((DotInfo.getY() > 0) && shouldBeCaptured (DotInfo.getX(), DotInfo.getY()-1)) {
                gameModel.capture(DotInfo.getX(), DotInfo.getY()-1);
                stack.push(gameModel.get(DotInfo.getX(), DotInfo.getY()-1));
            }  
            if((DotInfo.getY() < gameModel.getSize()-1) && shouldBeCaptured (DotInfo.getX(), DotInfo.getY()+1)) {
                gameModel.capture(DotInfo.getX(), DotInfo.getY()+1);
                stack.push(gameModel.get(DotInfo.getX(), DotInfo.getY()+1));
            }
        }
    }

    /**
     * <b>flood</b> is the method that computes which new dots should be ``captured'' 
     * when a new color has been selected. The Model is updated accordingly. This is for TORUS/ORTHAGONAL combination
     */
    
    private void flood1(){
        flood();
        Stack<DotInfo> stack = new GenericLinkedStack<DotInfo>();
        for(int i =0; i < gameModel.getSize(); i++) {
           for(int j =0; j < gameModel.getSize(); j++) {
                if(gameModel.isCaptured(i,j)) {
                    stack.push(gameModel.get(i,j));
                }
           }
        }

        while(!stack.isEmpty()){
            DotInfo DotInfo = stack.pop();
            //checking upon torus criteria
            int GAMESIZE = gameModel.getSize();

            //This if-statement compares the top row to the bottom row
            //(First part of the if statement tests to see if dots considered are in top most row and compares them to those of the top most row)
            System.out.println("Flood1 is running");
            if( (DotInfo.getX() == 0) && shouldBeCaptured (DotInfo.getX()+ (GAMESIZE - 1), DotInfo.getY())   ){
                gameModel.capture(DotInfo.getX() + (GAMESIZE-1), (DotInfo.getY()));
                stack.push(gameModel.get(DotInfo.getX() + (GAMESIZE-1), (DotInfo.getY())));
            }
                        
            //This if-statement checks for dots on the right most side of the board and compares them to the dots on the leftmost side of the gameView (one under as well)
            if(DotInfo.getX() != GAMESIZE-1 && (DotInfo.getY() == (GAMESIZE-1)) && shouldBeCaptured (DotInfo.getX() + (1), (0)) ) {
                gameModel.capture(DotInfo.getX()+1, 0);
                stack.push(gameModel.get(DotInfo.getX()+1, 0));
            }

            //This If-Statement checks the dot on the left-most of the board and compares it to the right most DotInfo on the row above
            if( DotInfo.getX() != 0 && (DotInfo.getY() == 0 ) && shouldBeCaptured( DotInfo.getX() - 1,(GAMESIZE-1) ) ) {
                System.out.println("We're checking condition number 3 m8!");
                gameModel.capture( DotInfo.getX() - 1,(GAMESIZE-1) );
                stack.push(gameModel.get(  DotInfo.getX() - 1,(GAMESIZE-1))  );
            } 

            //This if-statement compares the lower most DotInfo's to the top most DotInfos
             if( (DotInfo.getX() == GAMESIZE-1 ) && shouldBeCaptured( 0, DotInfo.getY() ) ) {
                System.out.println("We're checking condition number 3 m8!");
                gameModel.capture( 0, DotInfo.getY() );
                stack.push(gameModel.get(  0, DotInfo.getY()  ));
            } 
        }
    }

    /*This function is run when the plane and diagonal options are selected */
    private void flood2(){
        flood();
        int GAMESIZE = gameModel.getSize();
        Stack<DotInfo> stack = new GenericLinkedStack<DotInfo>();
            for(int i =0; i < gameModel.getSize(); i++) {
               for(int j =0; j < gameModel.getSize(); j++) {
                    if(gameModel.isCaptured(i,j)) {
                        stack.push(gameModel.get(i,j));
                    }
               }
            }

        while(!stack.isEmpty()){ 
            DotInfo DotInfo = stack.pop();

            /* This checks the dot to the top right of the chosen dot */
            if( DotInfo.getX() -1 >= 0 && DotInfo.getY()+1 < GAMESIZE  && shouldBeCaptured(DotInfo.getX() -1, DotInfo.getY() +1 )   ){
                gameModel.capture(DotInfo.getX() -1, DotInfo.getY() +1 );
                stack.push(gameModel.get(DotInfo.getX() -1, DotInfo.getY() +1 ));
            }
            /*This if-statement checks the DotInfo to the bottom right of selected DotInfo */
            if( DotInfo.getX() + 1 < GAMESIZE && DotInfo.getY()+ 1 < GAMESIZE && shouldBeCaptured(DotInfo.getX()+1, DotInfo.getY()+1)     ){
                gameModel.capture(DotInfo.getX()+1, DotInfo.getY()+1);
                stack.push(gameModel.get(DotInfo.getX()+1, DotInfo.getY()+1));
            }
            /* This if-statement checks the DotInfo to the bottom left of the "SELECTED" DotInfo */ 
            if(DotInfo.getX() + 1 < GAMESIZE && DotInfo.getY() - 1 >=0 && shouldBeCaptured(DotInfo.getX() +1  , DotInfo.getY() -1 )){
                gameModel.capture(DotInfo.getX() +1  , DotInfo.getY() -1);
                stack.push(gameModel.get(DotInfo.getX() +1  , DotInfo.getY() -1));
            }
            /* This if-statement checks the DotInfo the top left of the selected DotInfo */
            if(DotInfo.getX() - 1 >= 0 && DotInfo.getY() -1 >=0 && shouldBeCaptured(DotInfo.getX()-1 , DotInfo.getY()-1) ){
                gameModel.capture(DotInfo.getX()-1 , DotInfo.getY()-1);
                stack.push(gameModel.get(DotInfo.getX()-1 , DotInfo.getY()-1));
            }
        }
    }

    /*This function is run when one selects torus and diagonal */
    public void flood3(){
        flood1();
        flood2();
        int GAMESIZE = gameModel.getSize();
        Stack<DotInfo> stack = new GenericLinkedStack<DotInfo>();
        for(int i =0; i < gameModel.getSize(); i++) {
            for(int j =0; j < gameModel.getSize(); j++) {
                if(gameModel.isCaptured(i,j)) {
                    stack.push(gameModel.get(i,j));
                }
            }
        }

        while(!stack.isEmpty()){ 
            DotInfo DotInfo = stack.pop();

            //TORUS CRITERIA IS BEING CHECKED HERE
            //TOP LEFT CRITERIA
            //Criteria #1 (AT POINT ZERO, ZERO)
            if(DotInfo.getX() == 0 && DotInfo.getY() == 0 && shouldBeCaptured(GAMESIZE-1,GAMESIZE-1)){
                gameModel.capture(GAMESIZE-1,GAMESIZE-1);
                stack.push(gameModel.get(GAMESIZE-1,GAMESIZE-1));
            }
            //Criteria #2 (AT ALL POINTS ON THE TOP MOST ROW)
            if(DotInfo.getX() == 0 && DotInfo.getY() != 0 && shouldBeCaptured( GAMESIZE-1,  DotInfo.getY() - 1 ) ){
                gameModel.capture(GAMESIZE-1,  DotInfo.getY() - 1);
                stack.push(gameModel.get(GAMESIZE-1,  DotInfo.getY() - 1));
            }

            //TOP RIGHT 
            //Criteria One
            if(DotInfo.getX() == 0 && DotInfo.getY() == GAMESIZE-1 && shouldBeCaptured(GAMESIZE-1, 0)){
                gameModel.capture(GAMESIZE-1, 0);
                stack.push(gameModel.get(GAMESIZE-1, 0));
            }
            if(DotInfo.getX() == 0 && DotInfo.getY() != GAMESIZE-1 && shouldBeCaptured(GAMESIZE-1, DotInfo.getY()+1 ) ){
                gameModel.capture(GAMESIZE-1, DotInfo.getY()+1);
                stack.push(gameModel.get(GAMESIZE-1, DotInfo.getY()+1));
            }

            //BOTTOM RIGHT
            //CRITERIA ONE
            if(DotInfo.getX() == GAMESIZE-1 && DotInfo.getY() == GAMESIZE-1 && shouldBeCaptured(0,0)){
                gameModel.capture(0,0);
                stack.push(gameModel.get(0,0));
            }
            //CRITERIA TWO
            if(DotInfo.getX() == GAMESIZE-1 && DotInfo.getY() != GAMESIZE-1 && shouldBeCaptured(0,DotInfo.getY()+1)){
                gameModel.capture(0,DotInfo.getY()+1);
                stack.push(gameModel.get(0,DotInfo.getY()+1));
            }


            //Bottom LEFT CRITERIA
            //IF I DON"T HAVE A BOTTOM
            //Criteria #1
            if(DotInfo.getX() == GAMESIZE-1 && DotInfo.getY() == 0 && shouldBeCaptured(0, 0)){
                gameModel.capture(0, 0);
                stack.push(gameModel.get(0, 0));
            }
            //Criteria #2
            if(DotInfo.getX() == GAMESIZE-1 && DotInfo.getY() != 0 && shouldBeCaptured(0, DotInfo.getY()-1)){
                gameModel.capture(0, DotInfo.getY()-1);
                stack.push(gameModel.get(0, DotInfo.getY()-1));
            }
        }



    }


    /**
     * <b>shouldBeCaptured</b> is a helper method that decides if the dot
     * located at position (i,j), which is next to a captured dot, should
     * itself be captured
     * @param i
     *            row of the dot
     * @param j
     *            column of the dot
     */
    
   private boolean shouldBeCaptured(int i, int j) {
        if(!gameModel.isCaptured(i, j) &&
           (gameModel.getColor(i,j) == gameModel.getCurrentSelectedColor())) {
            return true;
        } else {
            return false;
        }
    }






}


