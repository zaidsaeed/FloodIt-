import java.awt.*;
import javax.swing.*;
import java.util.Random;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out the actual game and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {
Random generator = new Random();
DotButton[] l;
GameModel model;
JPanel panel2;
JLabel one;


    /**
     * Constructor used for initializing the Frame
     * 
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */


    JPanel panel1;

    public GameView(GameModel gamemodel, GameController gameController) {
        this.model = gamemodel;
        int size = gameController.sizeO;
        l = new DotButton[size]; 
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(35,90));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        add(panel, BorderLayout.PAGE_START);


        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        add(panel1, BorderLayout.CENTER);
        panel1.setPreferredSize(new Dimension(10,90));
        int p = decideOnSize();
        if(GameController.f !=0){
            panel1.setLayout (new GridLayout(GameController.f, GameController.f));
        }
        
        if(GameController.f != 0){
            for(int i =0; i<model.GameModel.length; i++){
                DotInfo x = model.GameModel[i];
                l[i] = new DotButton(x.getX(),x.getY(),x.getColor(),p);
                panel1.add(l[i]);
            }  
        }




        panel2 = new JPanel();
        //JLabel is added
        one = new JLabel("Number of steps:" + gamemodel.getNumberOfSteps(), SwingConstants.LEFT);
        panel2.add(one, FlowLayout.LEFT);

        
        //adding the five JButtons 
        //reset attributes
        JButton reset = new JButton("Reset");
        reset.addActionListener(gameController);


        //grey attributes
        JButton grey = new JButton("Grey");
        grey.addActionListener(gameController);
        grey.setIcon(new ImageIcon("data//M//ball-0.png"));
        grey.setOpaque(false);
        grey.setContentAreaFilled(false);
        grey.setBorderPainted(false);

        //yellow attributes
        JButton yellow = new JButton("Yellow");
        yellow.addActionListener(gameController);
        yellow.setIcon(new ImageIcon("data//M//ball-1.png"));
        yellow.setOpaque(false);
        yellow.setContentAreaFilled(false);
        yellow.setBorderPainted(false);

        //blue attributes
        JButton blue = new JButton("Blue");
        blue.addActionListener(gameController);
        blue.setIcon(new ImageIcon("data//M//ball-2.png"));
        blue.setOpaque(false);
        blue.setContentAreaFilled(false);
        blue.setBorderPainted(false);

        //green attributes
        JButton green = new JButton("Green");
        green.addActionListener(gameController);
        green.setIcon(new ImageIcon("data//M//ball-3.png"));
        green.setOpaque(false);
        green.setContentAreaFilled(false);
        green.setBorderPainted(false);

        //purple attributes
        JButton purple = new JButton("Purple");
        purple.addActionListener(gameController);
        purple.setIcon(new ImageIcon("data//M//ball-4.png"));
        purple.setOpaque(false);
        purple.setContentAreaFilled(false);
        purple.setBorderPainted(false);

        //red attributes
        JButton red = new JButton("Red");
        red.addActionListener(gameController);
        red.setIcon(new ImageIcon("data//M//ball-5.png"));
        red.setOpaque(false);
        red.setContentAreaFilled(false);
        red.setBorderPainted(false);


        panel2.add(reset);
        panel2.add(grey);
        panel2.add(yellow);
        panel2.add(blue);
        panel2.add(green);
        panel2.add(purple);
        panel2.add(red);

        panel2.setPreferredSize(new Dimension(35,90));
        panel2.setBorder(BorderFactory.createLineBorder(Color.RED,3));
        add(panel2, BorderLayout.PAGE_END);
        



        //display the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);
    }

        public int decideOnSize(){
            if(model.GameModel.length<10){
                return 0;
            }else if(model.GameModel.length > 10 && model.GameModel.length < 25){
                return 1;
            }else{
                return 2;
            }
    }

    /*public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        //
        g.drawString("This is my custom Panel!",10,20);
    }*/



    public static void main(String args[]){
        GameModel z =  new GameModel(9);
        GameController p = new GameController(9);
        GameView v = new GameView(z,p);
    }

    /**
     * update the status of the board's DotButton instances based on the current game model
     */

    public void update(){

        /*if(GameController.f != 0){
            for(int i =0; i<model.GameModel.length; i++){
                l[i].setColor(model.GameModel[i].getColor());
            }   
        }*/
        l = new DotButton[GameController.f*GameController.f];
        if(GameController.f != 0){
            int p = decideOnSize();
            panel1.removeAll();
            panel2.remove(one);
        if(GameController.f !=0){
            panel1.setLayout (new GridLayout(GameController.f, GameController.f));
        }
            for(int i =0; i<model.GameModel.length; i++){
                DotInfo x = model.GameModel[i];
                l[i] = new DotButton(x.getX(),x.getY(),x.getColor(),p);
                panel1.add(l[i]);
            } 
                invalidate();
                validate();
                repaint();
                setVisible(true);
        }
        panel2.add(one, FlowLayout.LEFT); 

    }



}