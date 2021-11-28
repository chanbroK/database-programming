package org.dfpl.lecture.database.assignment2.assignment17011685.mytree;

import java.util.*;

@SuppressWarnings("unused")
public class MyTree implements NavigableSet<Integer> {

    // Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
    final int m = 6;
    final int max_children = m;
    final int max_keys = m - 1;
    final int min_keys = (int) (Math.ceil(m / 2.)) - 1;
    // TODO implement leafList
    private final LinkedList<MyNode> leafList;
    private MyNode root;
    private int size;

    // 생성자
    public MyTree() {
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
    public MyNode getNode(Integer key) {
        MyNode node = root;
        while (true) {
            int pos;
            for (pos = 0; pos < node.getKeyList().size(); pos++) {
                if (node.getKeyList().get(pos).equals(key) && node.isLeaf) {
                    // 리프노드일때 종료
                    System.out.println(key + " found");
                    return node;
                } else if (node.getKeyList().get(pos) > key && !node.isLeaf) {
                    // 왼쪽 자식 노드로 이동
                    System.out.println("less than to " + node.getKeyList().get(pos));
                    node = node.getChildren().get(pos);
                }
                // pos 이동
            }
            if (node.isLeaf) {
                // 찾지 못함
                System.out.println(key + " not found");
                return null;
            }
            // 오른쪽 자식 노드로 이동
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

    // #recursiveInorderTraverse
    public void recursiveInorderTraverse(MyNode node) {
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
    public void updateParent(MyNode node, Integer key) {
        if (node == null) {
            //TODO
        }
    }

    // #deleteInNode
    public void deleteKey(MyNode node, Integer key) {
        if (node.isLeaf) {
            // leaf
            node.getKeyList().remove(key);
            if (node.getKeyList().size() < min_keys) { //min key 규칙 위반
                rebalancingTree(node, pos)

            }
        } else {
            // not leaf

        }
    }

    // #findNode
    public MyNode findNode(Integer key) {
        MyNode node = root;
        if (root == null) {
            // 빈 트리
            return null;
        }
        while (true) {
            int pos;
            for (pos = 0; pos < node.getKeyList().size(); pos++) {
                if (node.getKeyList().get(pos).equals(key) && node.isLeaf) {
                    // 리프노드일때 종료
                    return node;
                } else if (node.getKeyList().get(pos) > key && !node.isLeaf) {
                    // 왼쪽 자식 노드로 이동
                    node = node.getChildren().get(pos);
                }
                // pos 이동
            }
            if (node.isLeaf) {
                // 찾지 못함
                return null;
            }
            // 오른쪽 자식 노드로 이동
            node = node.getChildren().get(pos);
        }
    }

    public MyNode getRoot() {
        return root;
    }

    public List<MyNode> getLeafList() {
        return leafList;
    }

    // # insertNode
    public MyNode insertNode(
            int parent_pos,
            int val,
            MyNode node,
            MyNode parent
    ) {
        // search pos
        int pos;
        for (pos = 0; pos < node.getKeyList().size(); pos++) { // 해당 KeyList에서 val 보다 높은 값 위치 찾기
            if (val == node.getKeyList().get(pos)) {
                // 중복 허용 X
                size--;
                return node;
            } else if (val < node.getKeyList().get(pos)) { // val가 큰 값을 만났을때 정지
                break;
            }
        } // for 문 종료시 해당 노드의 KeyList에서 pos의 val이 제일 큼
        if (!node.isLeaf) { // leaf가 아니면 오른쪽 자식으로 이동(재귀)
            node
                    .getChildren()
                    .set(pos, insertNode(pos, val, node.getChildren().get(pos), node)); // 재귀를 통해 함수에서 반환되는 노드를 자식 노드로 연결
            // 자식 노드가 추가되었을 수 있음
            if (node.getKeyList().size() == max_keys + 1) { // 최대키 규칙 위반
                // node split
                node = splitNode(parent_pos, node, parent);
            }
        } else { // leaf면 삽입
            node.getKeyList().add(pos, val);
            if (node.getKeyList().size() == max_keys + 1) { // 최대키 규칙 위반
                // node split
                node = splitNode(parent_pos, node, parent);
            }
        }
        return node; // 재귀를 하며 자식을 연결하기 위해 현재 노드 반환
    }

    // #splitNode
    public MyNode splitNode(
            int pos,
            MyNode node,
            MyNode parent
    ) {
        // TODO split시 middle index 수식 확인
        int middle = (int) Math.ceil((this.m - 1) / 2.);
        MyNode newNode = new MyNode(); // 새로운 자식노드
        newNode.isLeaf = node.isLeaf; // 새로운 노드 isLeaf는 기존 노드와 동일

        // TODO 반복문 수정 (밑의 로직 까지)
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
            MyNode newParent = new MyNode(node.getKeyList().get(middle)); // middle -> root
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
            parent.getChildren().add(pos + 1, newNode); // 오른쪽만 부모노드에 연결
            newNode.setParent(parent);
        }
        return node; // 재귀를 위해
    }

    // #printTree
    public void printTree(MyNode node, int level) {
        if (node == null) {
            System.out.println("Empty");
        } else {
            System.out.printf("Level %d ", level);
            for (int i = 0; i < node.getKeyList().size(); i++) {
                System.out.printf("|%d|", node.getKeyList().get(i));
            }
            System.out.printf("\n");
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
        MyNode node = root;
        while (!node.isLeaf) {
            node = node.getChildren().get(0);
        }
        return node.getKeyList().get(0);
    }

    @Override
    public Integer last() {
        MyNode node = root;
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
            root = new MyNode(e);
            root.isLeaf = true;
            leafList.add(root);
        } else {
            root = insertNode(0, e, root, root);
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        MyNode target = findNode((Integer) o);
        if (target != null) {
            // 빈 트리가 아니고 삭제할 노드를 찾았을 때
            deleteKey((Integer) o);
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
        MyNode curNode;
        int keyIdx;
        int listIdx;

        public BPlusTreeIterator() { // 시작점 : 최소값
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
