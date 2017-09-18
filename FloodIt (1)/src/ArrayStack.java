public class ArrayStack implements Stack<DotInfo>{

    DotInfo[] s;

    int bottom = 0;
    static int top;
   
     /**
     * Tests if this Stack is empty.
     *	@param int represents the size of the created stack
     * @return none
     */
    public ArrayStack(int size) {
        s = new DotInfo[size];
        top = 0;
    }

     /**
     * Tests if this Stack is empty.
     *
     * @return true if this Stack is empty; and false otherwise.
     */
    public boolean isEmpty(){
        if (top==0){
            return true;
        }
        return false;
    }
        
    /**
     * Returns a reference to the top element; does not change
     * the state of this Stack.
     *
     * @return The top element of this stack without removing it.
     */

    public DotInfo peek(){
        return s[top];
    }
        

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return The top element of this stack.
     */

    public DotInfo pop(){
        if(top > 0){
            top = top - 1;
        }

        DotInfo temp = s[top];
        s[top]= null;
        
        return temp;
    }
        
    /**
     * Puts an element onto the top of this stack.
     *
     * @param element the element be put onto the top of this stack.
     */

    public  void push( DotInfo element ){
        if(top < s.length){
            s[top] = element;
            top++;
        }else{
            System.out.println("Stack OVERFLOW!!!");
        }
    }

    public void printStack(){
       for(int i = 0; i<s.length; i++){
            System.out.println(s[i]);
       }
    }

}