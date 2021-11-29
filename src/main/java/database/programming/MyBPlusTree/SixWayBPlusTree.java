package database.programming.MyBPlusTree;


import java.util.*;

// TODO 주석 최대한 영어로 수정하기 add 부분
public class SixWayBPlusTree implements NavigableSet<Integer> {

    // Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
    final int m = 6;
    final int max_children = m;
    final int max_keys = m - 1;
    final int min_keys = (int) (Math.ceil(m / 2.)) - 1;
    private final LinkedList<SixWayBPlusTreeNode> leafList;
    private SixWayBPlusTreeNode root;
    private int size;

    // Constructor
    public SixWayBPlusTree() {
        root = null;
        size = 0;
        leafList = new LinkedList<>();
    }

    /**
     * 과제 Assignment4를 위한 메소드:
     * <p>
     * key로 검색하면 root부터 시작하여, key를 포함할 수 있는 leaf node를 찾고 key가 실제로 존재하면 해당 Node를
     * 반환하고, 그렇지 않다면 null을 반환한다. 중간과정을 System.out.println(String) 으로 출력해야 함. 6 way
     * B+ tree에서 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21 이 순서대로
     * add되었다고 했을 때,
     * <p>
     * 예: getNode(11)을 수행하였을 때
     * > less than 13
     * > larger than or equal to 10
     * > 11 found
     * 위의 3 문장을
     * 콘솔에 출력하고 11을 포함한 SixWayBPlusTreeNode를 반환함
     * <p>
     * 예: getNode(22)를 수행하였을 때
     * > larger than or equal to 13
     * > larger than or equal to 19
     * > 22 not found
     * 위의 3
     * 문장을 콘솔에 출력하고 null을 반환함.
     *
     * @param key
     * @return
     */
    public SixWayBPlusTreeNode getNode(Integer key) {
        SixWayBPlusTreeNode node = root;
        while (true) {
            int pos;
            for (pos = 0; pos < node.getKeyList().size(); pos++) {
                if (node.getKeyList().get(pos).equals(key) && node.isLeaf) {
                    // return in leaf node
                    System.out.println(key + " found");
                    return node;
                } else if (node.getKeyList().get(pos) > key && !node.isLeaf) {
                    // move to left child node
                    System.out.println("less than to " + node.getKeyList().get(pos));
                    node = node.getChildren().get(pos);
                    pos = -1;
                }
                // move pos
            }
            if (node.isLeaf) {
                // not found
                System.out.println(key + " not found");
                return null;
            }
            // move to right child node
            System.out.println("larger than or equal to " + node.getKeyList().get(pos - 1));
            node = node.getChildren().get(pos);
        }
    }

    /**
     * 과제 Assignment4를 위한 메소드:
     * <p>
     * inorder traversal을 수행하여, 값을 오름차순으로 출력한다.
     * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21 이 순서대로
     * add되었다고 했을 때,
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     * 10
     * 11
     * 12
     * 13
     * 14
     * 15
     * 16
     * 17
     * 18
     * 19
     * 20
     * 21
     * 위와 같이 출력되어야 함.
     */
    public void inorderTraverse() {
        recursiveInorderTraverse(root);
    }

    /////////////////////////
    /////////#helper/////////
    /////////////////////////
    // #checkMinKey
    public boolean checkMinKey(SixWayBPlusTreeNode node) {
        return node.getKeyList().size() > min_keys;
    }

    // #recursiveInorderTraverse
    public void recursiveInorderTraverse(SixWayBPlusTreeNode node) {
        if (node == null) {
            System.out.println("Empty");
        } else {
            for (int i = 0; i < node.getChildren().size(); i++) {
                recursiveInorderTraverse(node.getChildren().get(i));
            }
            if (node.isLeaf) {
                for (int i = 0; i < node.getKeyList().size(); i++) {
                    System.out.println(node.getKeyList().get(i));
                }
            }
        }
    }

    // #updateParent
    public void updateParent(SixWayBPlusTreeNode node, Integer oldKey, Integer newKey) {
        if (node != null) {
            if (node.getKeyList().contains(oldKey)) {
                node.getKeyList().set(node.getKeyList().indexOf(oldKey), newKey);
                return;
            }
            updateParent(node.getParent(), oldKey, newKey);
        }
    }

    // #borrowFromLeft
    public void borrowFromLeft(SixWayBPlusTreeNode node, int pos) {
        Integer PLV = node.getKeyList().get(pos - 1);
        int size = node.getChildren().get(pos - 1).getKeyList().size(); // left sibling key size
        Integer LV = node.getChildren().get(pos - 1).getKeyList().get(size - 1);
        // move key
        node.getChildren().get(pos).getKeyList().add(0, PLV); // PLV -> T
        node.getKeyList().set(pos - 1, LV); // LV -> T
        node.getChildren().get(pos - 1).getKeyList().remove(LV);
        if (node.getChildren().get(pos).isLeaf) {
            node.getChildren().get(pos).getKeyList().set(0, LV);
        } else {
            //move child
            size = node.getChildren().get(pos - 1).getChildren().size();
            SixWayBPlusTreeNode LC = node.getChildren().get(pos - 1).getChildren().get(size - 1);
            node.getChildren().get(pos).getChildren().add(0, LC);
            LC.setParent(node.getChildren().get(pos));
            node.getChildren().get(pos - 1).getChildren().remove(LC);
        }
    }

    // #borrowFromRight
    public void borrowFromRight(SixWayBPlusTreeNode node, int pos) {
        // move key
        Integer PRV = node.getKeyList().get(pos);
        Integer RV = node.getChildren().get(pos + 1).getKeyList().get(0);
        node.getChildren().get(pos).getKeyList().add(PRV); // PRV -> T
        node.getKeyList().set(pos, RV); // RV -> PRV
        node.getChildren().get(pos + 1).getKeyList().remove(RV);
        // parent key update
        if (node.getChildren().get(pos).isLeaf) {
            updateParent(node, RV, node.getChildren().get(pos + 1).getKeyList().get(0));
        } else {
            //move child
            SixWayBPlusTreeNode RC = node.getChildren().get(pos + 1).getChildren().get(0);
            node.getChildren().get(pos).getChildren().add(RC);
            RC.setParent(node.getChildren().get(pos));
            node.getChildren().get(pos + 1).getChildren().remove(RC);
        }
    }

    // #mergeNode
    public void mergeNode(SixWayBPlusTreeNode node, int rightPos, int leftPos) {
        // merging at left child
        SixWayBPlusTreeNode RC = node.getChildren().get(rightPos);
        SixWayBPlusTreeNode LC = node.getChildren().get(leftPos);
        if (!RC.isLeaf) {
            // merge in no leaf
            LC.getKeyList().add(node.getKeyList().get(leftPos));
        }
        LC.getKeyList().addAll(RC.getKeyList()); // move RC key list to LC
        LC.getChildren().addAll(RC.getChildren()); // move RC child list to LC
        for (SixWayBPlusTreeNode child : RC.getChildren()) {
            child.setParent(LC);
        }
        //clear
        node.getChildren().remove(RC);
        node.getKeyList().remove(leftPos);
        leafList.remove(RC);
        if (root == node && root.getChildren().size() == 1) {
            root = LC;
        }
    }

    // #balancing
    public void balancing(SixWayBPlusTreeNode node, int childPos) {// borrow or merge
        if (childPos == 0) { // not exist left sibling
            if (checkMinKey(node.getChildren().get(childPos + 1))) { // comply min key rule
                borrowFromRight(node, childPos);

            } else { // violate min key rule
                mergeNode(node, childPos + 1, childPos);
            }
        } else if (childPos == node.getKeyList().size()) { // not exist right sibling
            if (checkMinKey(node.getChildren().get(childPos - 1))) {// comply min key rule
                borrowFromLeft(node, childPos);
            } else { //violate
                mergeNode(node, childPos, childPos - 1);
            }
        } else { // exist right, left sibling
            if (checkMinKey(node.getChildren().get(childPos - 1))) {// use left sibling
                borrowFromLeft(node, childPos);
            } else if (checkMinKey(node.getChildren().get(childPos + 1))) { // use right sibling
                borrowFromRight(node, childPos);
            } else {
                // violate, merge with left sibling
                mergeNode(node, childPos, childPos - 1);
            }
        }
    }

    // #deleteInNode
    public void deleteKey(SixWayBPlusTreeNode node, Integer key) {
        // leaf
        if (node.getKeyList().indexOf(key) == 0) { // update parent node key
            updateParent(node.getParent(), key, node.getKeyList().get(1));
        }
        node.getKeyList().remove(key);
        while (node.getParent() != null) {
            if (node.getKeyList().size() < min_keys) { //violate min key rule
                balancing(node.getParent(), node.getParent().getChildren().indexOf(node));
            }
            node = node.getParent();
        }
    }

    // #findNode
    public SixWayBPlusTreeNode findNode(Integer key) {
        SixWayBPlusTreeNode node = root;
        if (root == null) {
            // empty
            return null;
        }
        while (true) {
            int pos;
            for (pos = 0; pos < node.getKeyList().size(); pos++) {
                if (node.getKeyList().get(pos).equals(key) && node.isLeaf) {
                    // return in leaf node
                    return node;
                } else if (node.getKeyList().get(pos) > key && !node.isLeaf) {
                    // move to left child node
                    node = node.getChildren().get(pos);
                    pos = -1;
                }
                // move pos
            }
            if (node.isLeaf) {
                // not found
                return null;
            }
            // move to right child node
            node = node.getChildren().get(pos);
        }
    }

    public SixWayBPlusTreeNode getRoot() {
        return root;
    }

    // # insertNode
    public SixWayBPlusTreeNode insertNode(
            int parent_pos,
            int val,
            SixWayBPlusTreeNode node,
            SixWayBPlusTreeNode parent
    ) {
        // search pos
        int pos;
        for (pos = 0; pos < node.getKeyList().size(); pos++) { // 해당 KeyList에서 val 보다 높은 값 위치 찾기
            if (val == node.getKeyList().get(pos)) {
                // no duplicate key
                size--;
                return node;
            } else if (val < node.getKeyList().get(pos)) { // val가 큰 값을 만났을때 정지
                break;
            }
        } // for 문 종료시 해당 노드의 KeyList에서 pos의 val이 제일 큼
        if (!node.isLeaf) { // leaf가 아니면 오른쪽 자식으로 이동(재귀)
            node
                    .getChildren()
                    .set(pos, insertNode(pos, val, node.getChildren().get(pos), node));
            // 재귀를 통해 함수에서 반환되는 노드를 자식 노드로 연결
            // 자식 노드가 추가되었을 수 있음
            if (node.getKeyList().size() == max_keys + 1) { // violate max key rule
                // node split
                node = splitNode(parent_pos, node, parent);
            }
        } else { // insert if leaf
            node.getKeyList().add(pos, val);
            if (node.getKeyList().size() == max_keys + 1) { // violate max key rule
                // node split
                node = splitNode(parent_pos, node, parent);
            }
        }
        return node; // return current node to connect child node by recursive
    }

    // #splitNode
    public SixWayBPlusTreeNode splitNode(
            int pos,
            SixWayBPlusTreeNode node,
            SixWayBPlusTreeNode parent
    ) {
        int middle = (int) Math.ceil((this.m - 1) / 2.);
        SixWayBPlusTreeNode newNode = new SixWayBPlusTreeNode(); // 새로운 자식노드
        newNode.isLeaf = node.isLeaf; // 새로운 노드 isLeaf는 기존 노드와 동일

        // i는 계속 일정한 숫자이고 size가 줄어드는 방식 이용
        if (node.isLeaf) {
            // middle 포함해서 right, left로 분리
            newNode.getKeyList().add(node.getKeyList().get(middle));
            for (int i = middle + 1; i < node.getKeyList().size(); i++) { // 분리할 노드에 Key 담기
                newNode.getKeyList().add(node.getKeyList().get(i));
                // newNode.getKeyList().set(i - middle + 1, node.getKeyList().get(i));
                node.getKeyList().remove(i);
                i--;
            }
            // leafList 갱신(newNode 추가)
            if (leafList.indexOf(node) == leafList.size() - 1) {
                leafList.add(newNode);
            } else {
                leafList.add(leafList.indexOf(node) + 1, newNode);
            }
        } else {
            // middle 포함하지 않고 right, left로 분리
            for (int i = middle + 1; i < node.getKeyList().size(); i++) { // 분리할 노드에 Key 담기
                newNode.getKeyList().add(node.getKeyList().get(i));
                // newNode.getKeyList().set(i - middle + 1, node.getKeyList().get(i));
                node.getKeyList().remove(i);
                i--;
            }
            if (!node.isLeaf) { // 분리할 노드에 Child 담기
                for (int i = middle + 1; i < node.getChildren().size(); i++) {
                    // newNode.getChildren().add(i - middle + 1, node.getChildren().get(i));
                    newNode.getChildren().add(node.getChildren().get(i));
                    node.getChildren().get(i).setParent(newNode);
                    node.getChildren().remove(i);
                    i--;
                }
            }
        }

        // 부모노드 처리
        if (node == root) { // 새로운 부모 생성
            SixWayBPlusTreeNode newParent = new SixWayBPlusTreeNode(node.getKeyList().get(middle)); // middle -> root
            node.getKeyList().remove(middle);
            newParent.getChildren().add(node);
            node.setParent(newParent);
            newParent.getChildren().add(newNode);
            newNode.setParent(newParent);
            // leafList 갱신 필요 X  기존 root가 이미 들어가 있음
            return newParent;
        } else { // 기존 부모 이용
            int size = parent.getKeyList().size();
            //add ,clear
            for (int i = size; i > pos; i--) { // add key, child
                parent.getKeyList().add(i, parent.getKeyList().get(i - 1));
                parent.getChildren().add(i + 1, parent.getChildren().get(i));
                parent.getChildren().remove(i);
                parent.getKeyList().remove(i - 1);
            }
            parent.getKeyList().add(pos, node.getKeyList().get(middle));
            node.getKeyList().remove(middle);
            parent.getChildren().add(pos + 1, newNode); // right node connect to parent node
            newNode.setParent(parent);
        }
        return node; // to recursive
    }

    // #printTree
    public void printTree(SixWayBPlusTreeNode node, int level) {
        if (node == null) {
            System.out.println("Empty");
        } else {
            System.out.printf("Level %d ", level);
            for (int i = 0; i < node.getKeyList().size(); i++) {
                System.out.printf("|%d|", node.getKeyList().get(i));
            }
            System.out.print("\n");
            level++;
            for (int i = 0; i < node.getChildren().size(); i++) {
                printTree(node.getChildren().get(i), level);
            }
        }
    }

    // #Override
    @Override
    public Comparator<? super Integer> comparator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer first() {
        SixWayBPlusTreeNode node = root;
        while (!node.isLeaf) {
            node = node.getChildren().get(0);
        }
        return node.getKeyList().get(0);
    }

    @Override
    public Integer last() {
        SixWayBPlusTreeNode node = root;
        while (!node.isLeaf) {
            node = node.getChildren().get(node.getChildren().size() - 1);
        }
        return node.getKeyList().get(node.getKeyList().size() - 1);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean add(Integer e) {
        size++;
        if (root == null) {
            root = new SixWayBPlusTreeNode(e);
            root.isLeaf = true;
            leafList.add(root);
        } else {
            root = insertNode(0, e, root, root);
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        SixWayBPlusTreeNode target = findNode((Integer) o);
        if (target != null) {
            // not empty tree and find target node
            deleteKey(target, (Integer) o);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer lower(Integer e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer floor(Integer e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer ceiling(Integer e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer higher(Integer e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer pollFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer pollLast() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BPlusTreeIterator();
    }


    @Override
    public NavigableSet<Integer> descendingSet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Integer> descendingIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
                                        boolean toInclusive) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SortedSet<Integer> headSet(Integer toElement) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SortedSet<Integer> tailSet(Integer fromElement) {
        // TODO Auto-generated method stub
        return null;
    }

    class BPlusTreeIterator implements Iterator<Integer> {
        SixWayBPlusTreeNode curNode;
        int keyIdx;
        int listIdx;

        public BPlusTreeIterator() { // start in minimum key(first)
            keyIdx = 0;
            listIdx = 0;
            curNode = leafList.get(listIdx);
        }

        @Override
        public boolean hasNext() {
            return curNode != null && size != 0;
        }


        @Override
        public Integer next() {
            Integer result = null;
            result = curNode.getKeyList().get(keyIdx);
            keyIdx++;
            if (keyIdx == curNode.getKeyList().size()) {
                if (listIdx + 1 == leafList.size()) {
                    curNode = null;
                } else {
                    curNode = leafList.get(++listIdx);
                    keyIdx = 0;
                }
            }
            return result;
        }
    }
}
