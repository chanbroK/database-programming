package database.programming.MyBPlusTree;

import java.util.TreeSet;

public class TestCase {

    public static void main(String[] args) {

        //case 1 
        // 132가 지워지지 않음, 순회에는 문제가 없음 -> leafList문제는 아니라는 것 / balancing이 제대로 되지 않는다는 의미
        // 삭제 후 처리가 제대로 되지 않음 !!!!  keyList가 3개인데 childList 5개임
        // 해결 -> innerNode에서 merge 수행시 부모의 키를 같이 merge 하지 않았음
//        int[] arr = {113, 102, 857, 859, 471, 660, 858, 409, 187, 94, 703, 885, 954, 218, 378, 155, 683, 469, 952, 610, 443, 682, 527, 78, 456, 919, 870, 704, 995, 853, 365, 188, 950, 236, 526, 968, 613, 560, 427, 284, 850, 493, 991, 738, 360, 578, 505, 572, 409, 765, 66, 548, 763, 731, 352, 507, 973, 170, 755, 138, 455, 645, 588, 182, 495, 627, 142, 996, 232, 148, 844, 161, 189, 593, 322, 268, 620, 303, 624, 235, 197, 824, 545, 287, 753, 190, 970, 181, 480, 236, 925, 849, 635, 131, 525, 673, 416, 374, 132, 979, 931, 323, 369, 444, 417, 527, 741, 478, 584, 252, 364, 933, 411, 393, 569, 191, 370, 359, 761, 414, 165, 827, 314, 59, 401, 404, 861, 656, 603, 979, 318, 513, 690, 306, 620, 408, 425, 395, 700, 277, 514, 950, 768, 646, 550, 838, 717, 385, 312, 528, 458, 355, 849, 265, 324, 523, 78, 708, 790, 536, 167, 980, 422, 579, 665, 36, 755, 795, 690, 505, 538, 403, 244, 880, 80, 530, 315, 945, 130, 71, 392, 320, 968, 807, 792, 668, 928, 717, 143, 957, 886, 28, 598, 159, 913, 116, 819, 613, 465, 974};
//        int[] remove = {113, 102, 857, 859, 471, 660, 858, 409, 187, 94, 703, 885, 954, 218, 378, 155, 683, 469, 952, 610, 443, 682, 527, 78, 456, 919, 870, 704, 995, 853, 365, 188, 950, 236, 526, 968, 613, 560, 427, 284, 850, 493, 991, 738, 360, 578, 505, 572, 409, 765, 66, 548, 763, 731, 352, 507, 973, 170, 755, 138, 455, 645, 588, 182, 495, 627, 142, 996, 232, 148, 844, 161, 189, 593, 322, 268, 620, 303, 624, 235, 197, 824, 545, 287, 753, 190, 970, 181, 480, 236, 925, 849, 635, 131, 525, 673, 416, 374, 132, 979};

        //case 2
        // root를 포함하여 merge 수행시 root 갱신이 안됨 -> 해결
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
//        int[] remove = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        //case 3
        // root를 포함하여 merge 수행시 무조건 root를 갱신하니 오류 발생
        // 해결-> 조건(root의 childList.size == 1)을 통해 갱신 할 상황에만 갱신
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        int[] remove = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};

        SixWayBPlusTree tree = new SixWayBPlusTree();
        TreeSet<Integer> set = new TreeSet<>();

        for (int val : arr) {
            tree.add(val);
            set.add(val);
        }
        for (int val : remove) {
            tree.remove(val);
            set.remove(val);
        }
        tree.printTree(tree.getRoot(), 0);
        tree.findNode(369);
        tree.getNode(132);
//        boolean isPass = true;
//        Iterator<Integer> myIter = tree.iterator();
//        Iterator<Integer> treeIter = set.iterator();
//        while (myIter.hasNext() && treeIter.hasNext()) {
//            Integer a = myIter.next();
//            Integer b = treeIter.next();
//            System.out.println(a + ":" + b);
//            if (!a.equals(b)) {
//                isPass = false;
//                break;
//            }
//        }
//        if (!isPass) {
//            System.out.println("fail");
//        }
//        for (Integer val : tree.getNode(132).getKeyList()) {
//            System.out.println(val);
//        }
//        tree.inorderTraverse();
//        Set<Integer> set = new TreeSet<>();
//        for (int val : arr) {
//            set.add(val);
//        }
//        System.out.println(set);
//        tree.printTree(tree.getRoot(), 0);
    }
}
