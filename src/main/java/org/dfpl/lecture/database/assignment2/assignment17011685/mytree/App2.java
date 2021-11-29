package org.dfpl.lecture.database.assignment2.assignment17011685.mytree;

import java.util.Set;
import java.util.TreeSet;

public class App2 {

    public static void main(String[] args) {

        //case 1
        int[] arr = {0, 1, 1, 3, 6, 6, 8, 9, 9, 12, 12, 12, 13, 14, 14, 15, 16, 19, 19, 20, 22, 22, 23, 24, 25, 26, 26, 28, 30, 30, 30, 32, 32, 32, 34, 34, 36, 36, 37, 39, 40, 42, 44, 45, 45, 46, 46, 46, 48, 51, 52, 52, 52, 53, 53, 54, 54, 55, 58, 59, 59, 60, 61, 61, 63, 65, 65, 66, 67, 68, 68, 68, 68, 70, 70, 71, 71, 71, 73, 73, 74, 75, 75, 76, 77, 77, 80, 80, 82, 84, 87, 87, 87, 87, 88, 88, 91, 94, 95, 96};
        int[] remove = {66, 60, 58, 36, 96, 34, 30, 19, 22, 94, 30, 59, 32, 61, 84, 16, 45, 68, 88, 8, 20, 55, 68, 51, 9, 42, 14, 23, 15, 45, 75, 32, 25, 53, 48, 80, 13, 82, 28, 68, 30, 52, 68, 91, 46, 71, 44, 87, 32, 67};
        MyTree tree = new MyTree();
        for (int val : arr) {
            tree.add(val);
        }
        for (int val : remove) {
            tree.remove(val);
        }
        tree.inorderTraverse();
        Set<Integer> set = new TreeSet<>();
        for (int val : arr) {
            set.add(val);
        }
        System.out.println(set);
        tree.printTree(tree.getRoot(), 0);
    }
}
