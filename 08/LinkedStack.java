package day0806;

import java.util.EmptyStackException;

public class LinkedStack<E> implements IStack<E> {

	private Node<E> top; // top은 마지막 노드를 나타냄(head로 사용)
	
	@Override
	public void push(E e) { //첫 노드로 삽입
		top = new Node<>(e,top);
	}

	@Override
	public E pop() { // 첫 노드 삭제
		if(isEmpty()) throw new EmptyStackException();
		Node<E> node = top;
		top = node.link;
		node.link = null;
		return node.data;
	}

	@Override
	public E peek() {
		if(isEmpty()) throw new EmptyStackException();
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		return top==null;
	}

	@Override
	public int size() { // 연결리스트 전체 탐색하는 버전
		int size = 0;
		for (Node<E> temp = top;  temp !=null; temp = temp.link) {
			size++;
		}
		return size;
	}
	
}
