package day0806;

public class Node<T> {
	
	public Node<T> link;
	public T data;
	

	public Node() { // data, link는 null
		super();
	}
	
	public Node(T data) { // link는 null
		super();
		this.data = data;
	}	
	
	public Node(T data, Node<T> link) {
		super();
		this.data = data;
		this.link = link;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", link=" + link + "]";
	}
	
}
