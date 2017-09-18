import java.io.*;
import java.lang.*;
import java.awt.*;
public class GenericLinkedStack<T> implements Stack<T>, Serializable{

	/*public static void main(String args[]){
		GenericLinkedStack<Object> one = new GenericLinkedStack<Object>();
		one.push("HI");
		one.push("HI");
		System.out.println(one.pop());
		System.out.println(one.pop());
		System.out.println(one.pop());
	}*/

	/*public Object clone() throws CloneNotSupportedException{
    	GenericLinkedStack<T> cloned;
        try{
            cloned = (GenericLinkedStack<T>) super.clone();
            cloned = new GenericLinkedStack<T>();
            while(top != null){
            	cloned.push

            }
        }catch(CloneNotSupportedException e){
            throw new Error();
        }
        return cloned;   
    }*/
	public Elem<T> top;

	public GenericLinkedStack(){
		top = null;
	}

	public void push(T newValue) throws EmptyStackException{
		if (newValue == null){
			throw new EmptyStackException();
		}

		top = new Elem<T>(newValue, top);
	}

	public T pop() throws EmptyStackException{
		if(top == null){
			throw new EmptyStackException();
		}
		T tmp = top.payload;
		top = top.reference;
		return tmp;
	}

	public boolean isEmpty(){
		return top==null;
	}

	public T peek(){
		return top.payload;
	}

}


class Elem<E> implements Serializable{
	E payload;
	Elem<E> reference;
	public Elem(E payload, Elem<E> reference){
		this.payload = payload;
		this.reference = reference;
	}

	public String toString(){
		return "Payload of node: " + payload + "/n"+
		"Reference of node" + reference;

	}


	/*public Object clone() throws CloneNotSupportedException{
    	Elem<E> cloned;
        try{
            cloned = Elem<E> super.clone();
            cloned.paylod = payload;
            cloned.reference = reference; 
        }catch(CloneNotSupportedException e){
            throw new Error();
        }
        return cloned;   
    }*/



}

class EmptyStackException extends NullPointerException implements Serializable{
    public EmptyStackException(){
        super("You are dealing with an empty stack.");
    }
}