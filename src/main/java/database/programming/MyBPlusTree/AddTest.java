package database.programming.MyBPlusTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class AddTest {

    public static void main(String[] args) {
        for (int x = 0; x < 10000; x++) {
            SixWayBPlusTree my = new SixWayBPlusTree();
            TreeSet<Integer> tree = new TreeSet<>();
            ArrayList<Integer> list = new ArrayList<>();
            Random r = new Random();
            for (int i = 0; i < 200; i++) {
                list.add(r.nextInt(1000));
            }
            for (Integer val : list) {
                my.add(val);
                tree.add(val);
            }

            boolean isPass = true;
            Iterator<Integer> myIter = my.iterator();
            Iterator<Integer> treeIter = tree.iterator();
            while (myIter.hasNext() && treeIter.hasNext()) {
                Integer a = myIter.next();
                Integer b = treeIter.next();
                if (!a.equals(b)) {
                    isPass = false;
                    break;
                }
            }
            if (!isPass) {
                System.out.println(list);
                return;
            }
        }
    }
}
