package day0806;

public interface IStack<E> {
	
	void push(E e);
	E pop();
	E peek();
	boolean isEmpty();
	int size();
	
}
