package database.programming.MyBPlusTree;

import java.util.Random;

public class RemoveTest2 {

    public static void main(String[] args) {
        SixWayBPlusTree tree = new SixWayBPlusTree();
        Random r = new Random();
        for (int i = 0; i < 30; i++) {
            tree.add(i + 1);
        }


        int[] remove = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        // 14까지는 되고 15부터 안됨
        for (int val : remove) {
            tree.remove(val);
        }

        tree.printTree(tree.getRoot(), 0);

        tree.remove(24);

        tree.printTree(tree.getRoot(), 0);
    }
}
