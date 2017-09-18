public class DotInfo{


/**
 * The class <b>DotInfo</b> is a simple helper class to store the initial color and state
 * (captured or not) at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */




    int x;
    int y;
   	int color;
    boolean captured;



    /**
     * Constructor 
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param color
     *            the initial color
     */
    public DotInfo(){
    	this.x = 0;
    	this.y =0;
    	this.color = 0;
    	captured= false;
    }
    public DotInfo(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        captured = false;
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){
        return x;

    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){
        return y;

    }
    
 
    /**
     * Setter for captured
     * @param captured
     *            the new value for captured
     */
    public void setCaptured(boolean captured) {
        this.captured = captured;

    }

    /**
     * Get for captured
     *
     * @return captured
     */
    public boolean isCaptured(){
        if (captured == true){
            return true;
        }
        return false;
    }

    /**
     * Get for color
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

    public void setColor(int color){
    	this.color = color;
    }

    public String toString(){
    	return "Point (" + Integer.toString(getX()) + ", " +Integer.toString(getY()) + ", " + Integer.toString(getColor()) +isCaptured()+ ")" ;
    }



 }