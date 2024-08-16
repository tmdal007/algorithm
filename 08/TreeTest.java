package day0808;

public class TreeTest {

	public static void main(String[] args) {
		String[] names = {"A","B","C","D","E","F"};
		CompleteBinaryTree<String> tree = new CompleteBinaryTree<>(names.length);
		for(String name:names) {
			tree.add(name);
		}
		System.out.println("=======bfs=======");
		tree.bfs2();
		System.out.println();
		System.out.println("=======dfs=======");
		tree.dfsByPreOrder(1);
	}

}
