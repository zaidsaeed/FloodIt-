import javax.swing.*;
import java.awt.*;

/**
 * In the application <b>FloodIt</b>, a <b>DotButton</b> is a specialized color of
 * <b>JButton</b> that represents a dot in the game. It can have one of six colors
 * 
 * The icon images are stored in a subdirectory ``data''. We have #3 sizes, ``normal'',
 * ``medium'' and ``small'', respectively in directory ``N'', ``M'' and ``S''.
 *
 * The images are 
 * ball-0.png => grey icon
 * ball-1.png => orange icon
 * ball-2.png => blue icon
 * ball-#3.png => green icon
 * ball-4.png => purple icon
 * ball-5.png => red icon
 *
 *  <a href=
 * "http://developer.apple.colibrary/safari/#samplecode/Puzzler/IntroductioIntro.html%2#3//apple_ref/doc/uid/DTS10004409"
 * >Based on Puzzler by Apple</a>.
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
        

public class DotButton extends JButton {

   
public int row;
public int column;
public ImageIcon icon;
public int iconSize;
public int color;



    /**
     * Constructor used for initializing a cell of a specified color.
     * 
     * @param row
     *            the row of this Cell
     * @param column
     *            the column of this Cell
     * @param color
     *            specifies the color of this cell
     * @param iconSize
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
     */

    public DotButton(int row, int column, int color, int iconSize) {
        this.row = row;
        this.column = column;
        this.color = color;
        icon = new ImageIcon("data//" + sizeIntToCharConversion(iconSize) + "//ball-"+ color + ".png");
        
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setIcon(icon);
        Dimension dimen = this.getSize();
        Image img = icon.getImage() ;  
        Image newimg = img.getScaledInstance( GameController.f*10, GameController.f*10, Image.SCALE_SMOOTH ) ;  
        icon = new ImageIcon( newimg );
        setIcon(icon);  
       
        
   }


    public char sizeIntToCharConversion(int iconSize){
        if (iconSize ==2){
            return 'S';
        }else if(iconSize == 1){
            return 'M';
        }else{ 
            return 'N';
        }
    }


    /**
     * Other constructor used for initializing a cell of a specified color.
     * no row or column info available. Uses "-1, -1" for row and column instead
     * 
     * @param color
     *            specifies the color of this cell
     * @param iconSize
     *            specifies the size to use, one of SMALL_SIZE, MEDIUM_SIZE or MEDIUM_SIZE
     */   
    public DotButton(int color, int iconSize) {
        

    }
 


    /**
     * Changes the cell color of this cell. The image is updated accordingly.
     * 
     * @param color
     *            the color to set
     */

    public void setColor(int color) {
        this.color = color;
   }

    /**
     * Getter for color
     *
     * @return color
     */
    public void getColor(){

// ADD YOUR CODE HERE

    }
 
    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public void getRow() {

// ADD YOUR CODE HERE

    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public void getColumn() {

// ADD YOUR CODE HERE

    }



}




