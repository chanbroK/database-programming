package database.programming.MyBPlusTree;

import java.util.*;

public class App {

    public static void main(String[] args) {
        for (int x = 0; x < 100000; x++) {
//            System.out.println("Assignment 4: ");
//            SixWayBPlusTree bpTree = new SixWayBPlusTree();
//            int size = 22;
//            for (int i = 1; i < size; i++) {
//                bpTree.add(i);
//            }
//
//            bpTree.getNode(11);
//            System.out.println();
//            bpTree.getNode(22);
//            System.out.println();
//            bpTree.inorderTraverse();
//
//            System.out.println("Assignment 5: ");

            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 100; i++) {
                list.add(r.nextInt(100));
            }
            NavigableSet<Integer> treeSet = new TreeSet<Integer>();
            for (Integer val : list) {
                treeSet.add(val);
            }

            NavigableSet<Integer> yourBTree = new SixWayBPlusTree();
            for (Integer val : list) {
                yourBTree.add(val);
            }
            boolean isPass = true;
            isPass = treeSet.first().equals(yourBTree.first());
            if (!isPass) {
                System.out.println("first test: " + treeSet.first().equals(yourBTree.first()));
                return;
            }
            isPass = treeSet.last().equals(yourBTree.last());
            if (!isPass) {
                System.out.println("last test: " + treeSet.last().equals(yourBTree.last()));
                return;
            }
            Iterator<Integer> treeIterator = treeSet.iterator();
            Iterator<Integer> yourBTreeIterator = yourBTree.iterator();
            isPass = true;
            while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
                Integer a = treeIterator.next();
                Integer b = yourBTreeIterator.next();
                if (!a.equals(b)) {
                    isPass = false;
                    break;
                }
            }
            if (!isPass) {
                System.out.println("iterator test: " + isPass);
                return;
            }
            ArrayList<Integer> removelist = new ArrayList<>();
            for (int i = 0; i < list.size() / 2; i++) {
                treeSet.remove(list.get(i));
                yourBTree.remove(list.get(i));
                removelist.add(list.get(i));
            }
            treeIterator = treeSet.iterator();
            yourBTreeIterator = yourBTree.iterator();
            isPass = true;
            while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
                Integer a = treeIterator.next();
                Integer b = yourBTreeIterator.next();
                if (!a.equals(b)) {
                    isPass = false;
                    break;
                }
            }

            if (!isPass) {
                System.out.println("remove test: " + isPass);
                return;
            }
        }
    }
}
