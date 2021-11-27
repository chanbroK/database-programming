package org.dfpl.lecture.database.assignment2.assignment17011685.mytree;

public class App {
    public static void main(String[] args) {
        MyTree tree = new MyTree();
        int[] arr = {0, 1, 2, 3, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
        for (int v : arr) {
            tree.add(v);
        }
        tree.printTree(tree.getRoot(), 0);
    }
}
