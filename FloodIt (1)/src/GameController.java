import java.lang.Math.*;
import java.util.Scanner;
import java.awt.event.*;
import java.awt.Graphics.*;
import javax.swing.*;
import java.awt.*;


class GameController implements ActionListener{
 int counter;
 GameModel i;
 ArrayStack t;
 static int f;
 int sizeO;
 Scanner scan = new Scanner(System.in);
 GameView y;


	public GameController(int size){
		sizeO = size;
		i = new GameModel(size);
		t = new ArrayStack(sizeO*sizeO);
		f = (int) Math.sqrt(size);
		preliminaryCheck();
		y = new GameView(i,this);
	}

	public void reset(){
		i = new GameModel(sizeO);
	}


	
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("Reset")){
    		i.reset();
    		preliminaryCheck();
    		i.printModel();
    		
    		y.one.setText("Number of steps: " + i.getNumberOfSteps());
    		y.update();
    		
    		System.out.println(i.getNumberOfSteps());
    	}if(e.getActionCommand().equals("Grey")){
    		flood(0);
    		i.step();
    		
    		y.one.setText("Number of steps: " + i.getNumberOfSteps());
    		y.update();
    		i.printModel();
    		System.out.println("Grey");
    		System.out.println(i.getNumberOfSteps());
    	}if(e.getActionCommand().equals("Yellow")){
    		flood(1);
    		i.step();
    		
    		y.one.setText("Number of steps: " + i.getNumberOfSteps());
    		y.update();
    		i.printModel();
    		System.out.println("Yellow");
    		
    		System.out.println(i.getNumberOfSteps());
    	}if(e.getActionCommand().equals("Blue")){
    		flood(2);
    		i.step();
    		
    		y.one.setText("Number of steps: " + i.getNumberOfSteps());
    		y.update();
    		i.printModel();
    		System.out.println("Blue");
    		
    		System.out.println(i.getNumberOfSteps());
    	}if(e.getActionCommand().equals("Green")){
    		flood(3);
    		i.step();
    		
    		y.one.setText("Number of steps: " + i.getNumberOfSteps());
    		y.update();
    		i.printModel();
    		System.out.println("Green");
    		
    		System.out.println(i.getNumberOfSteps());
    	}if(e.getActionCommand().equals("Purple")){
    		flood(4);
    		i.step();
    		
    		y.one.setText("Number of steps: " + i.getNumberOfSteps());
    		y.update();
    		i.printModel();
    		System.out.println("Purple");
    		
    		System.out.println(i.getNumberOfSteps());
    	}if(e.getActionCommand().equals("Red")){
    		flood(5);
    		i.step();
    		
    		i.printModel();
    		y.one.setText("Number of steps: " + i.getNumberOfSteps());
    		y.update();
    		System.out.println("Red");
    		
    		System.out.println(i.getNumberOfSteps());
    	}
    	/*if(i.isFinished()){
    		JPanel finish = new JPanel();
    		y.add(finish);
    	}*/
    }



	public void importIntoStack(DotInfo k){
		if(k.isCaptured() == true){
			t.push(k);
		}
	}


	public void updateToMainColor(int newColor){
		for(int h = 0; h<i.GameModel.length; h++){
			if (i.GameModel[h].isCaptured() == true){
				i.GameModel[h].setColor(newColor);
			}
	}
}

	public void importAllIntoStack(){
		for(int z = 0; z<i.GameModel.length; z++){
			if(i.GameModel[z].isCaptured() == true){
				t.push(i.GameModel[z]);
			}
		}
	}


	public void preliminaryCheck(){
		int g = 0;
		i.GameModel[g].setCaptured(true);
		importIntoStack(i.GameModel[g]);
		int newColors = i.GameModel[g].getColor();
		
		while(t.isEmpty() == false){

			DotInfo d = t.pop();
			int x = d.getX();
			int y = d.getY();
			g = i.returnIndex(x,y);

				

			if( ( (g+1)<=(sizeO-1) )  &&  (i.GameModel[g+1].getColor() == newColors ) ){
				i.GameModel[g+1].setCaptured(true);
				importIntoStack(i.GameModel[g+1]);
			}
				

			if( ((g+f)<=(sizeO-1)) &&  (i.GameModel[g+f].getColor() == newColors ) ){
				i.GameModel[g+f].setCaptured(true);
				importIntoStack(i.GameModel[g+f]);
					
			}
			
		}

		for (int o =0 ; o<i.GameModel.length ; o++){
			if(i.GameModel[o].isCaptured()){
				System.out.println(i.GameModel[o]);
			}
			
		}
	}

	public void flood(int newColor){
			
			updateToMainColor(newColor);
			importAllIntoStack();
			

				while(t.isEmpty() == false){
					
					DotInfo d = t.pop();
					int x = d.getX();
			
					int y = d.getY();
					int g = i.returnIndex(x,y);
					System.out.println(g);

					
					if( ( (g-f) >= 0) &&  (i.GameModel[g-f].getColor() == newColor) && i.GameModel[g-f].isCaptured() == false ){
						i.GameModel[g-f].setCaptured(true);
						
						importIntoStack(i.GameModel[g-f]);
					}


					if(( ( (g+1)<=(sizeO-1) && ((g)%f != (f-1)) ))  &&  (i.GameModel[g+1].getColor() == newColor) && i.GameModel[g+1].isCaptured() == false ){
						i.GameModel[g+1].setCaptured(true);
						
						importIntoStack(i.GameModel[g+1]);
					}
					

					if( ((g+f)<=(sizeO-1)) &&  (i.GameModel[g+f].getColor() == newColor) && i.GameModel[g+f].isCaptured() == false ){
						i.GameModel[g+f].setCaptured(true);
					
						importIntoStack(i.GameModel[g+f]);
					}

					if(( ((g-1) >= 0 && ((g-1)%f != (f-1))) ) &&  (i.GameModel[g-1].getColor() == newColor) && i.GameModel[g-1].isCaptured() == false ){
						i.GameModel[g-1].setCaptured(true);
						importIntoStack(i.GameModel[g-1]);
					}



					
				}
				if(i.isFinished()){
					PopupWin win= new PopupWin(this,y);

				}


		}
	 public class PopupWin extends JFrame implements ActionListener
    {	GameView y;
        JButton quit= new JButton("quit");
        JButton reset=new JButton("reset");
        GameController model;
        JLabel congrats=new JLabel("Congradulations you won, Would you like to reset or quit");
        public PopupWin(GameController model, GameView y)
        {  	
        	this.y = y;      
        	this.model=model;
            JPanel panel= new JPanel();
            JPanel panel1= new JPanel();
            panel1.setPreferredSize(new Dimension(35,90));
            panel.setPreferredSize(new Dimension(35,90));
            add(panel,BorderLayout.PAGE_START);
            add(panel1, BorderLayout.PAGE_START);
            panel1.add(quit);
            panel1.add(reset);
            panel.add(congrats);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            quit.addActionListener(this);
            reset.addActionListener(this);
            setSize(500, 500);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent e)
        {
        	if(e.getSource()==quit)
        	{
        		System.exit(0);
        		//System.exit(0);

        	}
        	if(e.getSource()==reset)
        	{
        		
        		this.model.reset();
        		//this.model.
        		//System.exit(0);
        		this.y.update();

        		this.y.one.setText("Number of steps: 0");
        		int x= model.i.getSize();
        		this.y.setVisible(false);
        		this.setVisible(false);

        		model = new GameController(x);
        	}
            
        }
    }


}
