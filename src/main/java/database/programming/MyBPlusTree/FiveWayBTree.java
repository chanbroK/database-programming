package database.programming.MyBPlusTree;

import java.util.*;

// Merge 함수를 따로 만들자
// 1. Key Child 이동 + parnet 까지 고려

public class FiveWayBTree implements NavigableSet<Integer> {

    final int m = 5;
    final int max_children = m;
    final int max_keys = m - 1;
    final int min_keys = (int) (Math.ceil((double) m / 2)) - 1; // 최소 #키 구하는 식

    private FiveWayBTreeNode root;
    private int size;

    FiveWayBTree() {
        root = null;
        size = 0;
    }

    // 내가 만든 함수

    public FiveWayBTreeNode getRoot() {
        return root;
    }

    public boolean isExist(int val) {
        FiveWayBTreeNode node = searchNode(val);
        if (node != null) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                if (val == node.getKeyList().get(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public FiveWayBTreeNode searchNode(int val) {
        // @param 찾고자 하는 값
        FiveWayBTreeNode cur = root;
        while (true) {
            int i;
            for (i = 0; i < cur.getKeyList().size(); i++) {
                if (val == cur.getKeyList().get(i)) {
                    // find Node
                    return cur;
                } else if (val < cur.getKeyList().get(i)) {
                    break;
                }
            }
            if (cur.isLeaf) {
                break;
            } else {
                cur = cur.getChildren().get(i);
            }
        }
        // Can not find Node
        return null;
    }

    public FiveWayBTreeNode createNode(int val) {
        //추후 생성자로 넘기자
        FiveWayBTreeNode newNode = new FiveWayBTreeNode();
        newNode.isLeaf = false;
        newNode.getKeyList().add(val);
        return newNode;
    }

    public FiveWayBTreeNode splitNode(
            int pos,
            FiveWayBTreeNode node,
            FiveWayBTreeNode parent
    ) {
        int middle = node.getKeyList().size() / 2; // 2
        FiveWayBTreeNode newNode = new FiveWayBTreeNode(); // 새로운 자식노드
        newNode.isLeaf = node.isLeaf; // 새로운 노드의 리프 여부는 기존 노드와 동일?

        // add , clear
        for (int i = middle + 1; i < node.getKeyList().size(); i++) { // 분리할 노드에 Key 담기
            newNode.getKeyList().add(node.getKeyList().get(i));
            // newNode.getKeyList().set(i - middle + 1, node.getKeyList().get(i));
            node.getKeyList().remove(i);
            i--;
        }
        if (!node.isLeaf) { // 분리할 노드에 Chile 담기
            for (int i = middle + 1; i < node.getChildren().size(); i++) {
                // newNode.getChildren().add(i - middle + 1, node.getChildren().get(i));
                newNode.getChildren().add(node.getChildren().get(i));
                node.getChildren().get(i).setParent(newNode);
                node.getChildren().remove(i);
                i--;
            }
        }

        // 부모노드 처리
        if (node == root) { // 새로운 부모 생성
            FiveWayBTreeNode newParent = createNode(node.getKeyList().get(middle)); // middle -> root
            node.getKeyList().remove(middle);
            newParent.getChildren().add(node);
            node.setParent(newParent);
            newParent.getChildren().add(newNode);
            newNode.setParent(newParent);
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

    public FiveWayBTreeNode insertNode(
            int parent_pos,
            int val,
            FiveWayBTreeNode node,
            FiveWayBTreeNode parent
    ) {
        int pos;
        for (pos = 0; pos < node.getKeyList().size(); pos++) { // 해당 KeyList에서 val 보다 높은 값 위치 찾기
            if (val == node.getKeyList().get(pos)) {
                // 중복 허용 X
                size--;
                return node;
            } else if (val < node.getKeyList().get(pos)) { // val가 큰 값을 만났을때 정지
                break;
            }
        } // for 문 종료시 해당 노드에서 val이 제일 큼
        if (!node.isLeaf) { // leaf가 아니면 오른쪽 자식으로
            node
                    .getChildren()
                    .set(pos, insertNode(pos, val, node.getChildren().get(pos), node)); // 자식 노드 연결
            if (node.getKeyList().size() == max_keys + 1) { // 최대키 규칙 위반
                node = splitNode(parent_pos, node, parent);
            }
        } else { // leaf면 삽입
            node.getKeyList().add(pos, val);
            if (node.getKeyList().size() == max_keys + 1) { // 최대키 규칙 위반
                node = splitNode(parent_pos, node, parent);
            }
        }
        return node; // 재귀를 위해
    }

    public void printTree(FiveWayBTreeNode node, int level) {
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

    public Integer getPLV(FiveWayBTreeNode node) {
        int pIdx = node.getParent().getChildren().indexOf(node) - 1;
        if (pIdx < 0) {
            return null;
        }
        return node.getParent().getKeyList().get(pIdx);
    }

    public FiveWayBTreeNode getLS(FiveWayBTreeNode node) {
        if (getPLV(node) == null) {
            return null;
        }
        int pIdx = node.getParent().getChildren().indexOf(node) - 1;
        return node.getParent().getChildren().get(pIdx);
    }

    public Integer getLV(FiveWayBTreeNode node) {
        if (getLS(node) == null) {
            return null;
        }
        int pIdx = node.getParent().getChildren().indexOf(node) - 1;
        int size = node.getParent().getChildren().get(pIdx).getKeyList().size();
        return node.getParent().getChildren().get(pIdx).getKeyList().get(size - 1);
    }

    public Integer getPRV(FiveWayBTreeNode node) {
        int pIdx = node.getParent().getChildren().indexOf(node);
        if (pIdx == node.getParent().getKeyList().size()) {
            return null;
        }
        return node.getParent().getKeyList().get(pIdx);
    }

    public FiveWayBTreeNode getRS(FiveWayBTreeNode node) {
        if (getPRV(node) == null) {
            return null;
        }
        int pIdx = node.getParent().getChildren().indexOf(node) + 1;
        return node.getParent().getChildren().get(pIdx);
    }

    public Integer getRV(FiveWayBTreeNode node) {
        if (getRS(node) == null) {
            return null;
        }
        int pIdx = node.getParent().getChildren().indexOf(node) + 1;
        return node.getParent().getChildren().get(pIdx).getKeyList().get(0);
    }

    public FiveWayBTreeNode getLC(FiveWayBTreeNode node, int idx) {
        FiveWayBTreeNode t = node.getChildren().get(idx);
        while (!t.isLeaf) {
            t = t.getChildren().get(t.getChildren().size() - 1);
        }
        return t;
    }

    public FiveWayBTreeNode getRC(FiveWayBTreeNode node, int idx) {
        FiveWayBTreeNode t = node.getChildren().get(idx + 1);
        while (!t.isLeaf) {
            t = t.getChildren().get(0);
        }
        return t;
    }

    // 내장 오버라이딩 함수
    @Override
    public Comparator<? super Integer> comparator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer first() {
        //최소 값은 계속 왼쪽으로 이동
        FiveWayBTreeNode t = root;
        while (!t.isLeaf) {
            t = t.getChildren().get(0);
        }
        return t.getKeyList().get(0);
    }

    @Override
    public Integer last() {
        //최대 값은 계속 오른쪽으로 이동
        FiveWayBTreeNode t = root;
        while (!t.isLeaf) {
            t = t.getChildren().get(t.getChildren().size() - 1);
        }
        return t.getKeyList().get(t.getKeyList().size() - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        if (root == null) { // 첫 삽입
            root = createNode(e);
            root.isLeaf = true; // leaf 인 root
        } else {
            root = insertNode(0, e, root, root);
        }
        return false;
    }

    public void mergeNode(FiveWayBTreeNode node, int rpos, int lpos) {
        //무조건 왼쪽 노드 기준으로 머지
        node.getChildren().get(lpos).getKeyList().add(node.getKeyList().get(lpos)); // add PV
        node
                .getChildren()
                .get(lpos)
                .getKeyList()
                .addAll(node.getChildren().get(rpos).getKeyList()); // add RS Key
        node
                .getChildren()
                .get(lpos)
                .getChildren()
                .addAll(node.getChildren().get(rpos).getChildren()); // add RS children
        for (
                int i = 0;
                i < node.getChildren().get(rpos).getChildren().size();
                i++
        ) {
            node
                    .getChildren()
                    .get(rpos)
                    .getChildren()
                    .get(i)
                    .setParent(node.getChildren().get(lpos));
        }
        // clear
        node.getChildren().remove(rpos);
        node.getKeyList().remove(lpos);
    }

    public void borrowFromLeft(FiveWayBTreeNode node, int pos) {
        // add key
        node
                .getChildren()
                .get(pos)
                .getKeyList()
                .add(0, node.getKeyList().get(pos - 1)); // PLV -> T
        int size = node.getChildren().get(pos - 1).getKeyList().size();
        node
                .getKeyList()
                .set(pos - 1, node.getChildren().get(pos - 1).getKeyList().get(size - 1)); // LV -> PLV
        node.getChildren().get(pos - 1).getKeyList().remove(size - 1);

        if (node.getChildren().get(pos - 1).getChildren().size() > 0) { // LV에 child가 있다면
            //add ,clear child
            size = node.getChildren().get(pos - 1).getChildren().size();
            node
                    .getChildren()
                    .get(pos)
                    .getChildren()
                    .add(0, node.getChildren().get(pos - 1).getChildren().get(size - 1));
            node
                    .getChildren()
                    .get(pos - 1)
                    .getChildren()
                    .get(size - 1)
                    .setParent(node.getChildren().get(pos));
            node.getChildren().get(pos - 1).getChildren().remove(size - 1);
        }
    }

    public void borrowFromRight(FiveWayBTreeNode node, int pos) {
        // add key
        node.getChildren().get(pos).getKeyList().add(node.getKeyList().get(pos)); // PRV -> T
        node
                .getKeyList()
                .set(pos, node.getChildren().get(pos + 1).getKeyList().get(0)); //  RV -> PRV
        node.getChildren().get(pos + 1).getKeyList().remove(0);
        if (node.getChildren().get(pos + 1).getChildren().size() > 0) { // RV에 child가 있다면
            // add , clear child
            node
                    .getChildren()
                    .get(pos)
                    .getChildren()
                    .add(node.getChildren().get(pos + 1).getChildren().get(0));
            node
                    .getChildren()
                    .get(pos + 1)
                    .getChildren()
                    .get(0)
                    .setParent(node.getChildren().get(pos));
            node.getChildren().get(pos + 1).getChildren().remove(0);
        }
    }

    public void balancing(FiveWayBTreeNode node, int pos) { // borrow or merge
        if (pos == 0) { // 무조건 오른쪽
            if (node.getChildren().get(pos + 1).getKeyList().size() > min_keys) { // min key 규칙 준수
                borrowFromRight(node, pos);
            } else { // min key 규칙 위반
                mergeNode(node, pos + 1, pos); // 부모노드(현재노드)와 자신 위치랑 자기 형제 위치를 같이 넘겨줌.
            }
            return;
        } else if (pos == node.getKeyList().size()) { // 자식노드 키 위치가 맨 오른쪽일 때는 왼쪽 부모, 형제 봐야 함.
            if (node.getChildren().get(pos - 1).getKeyList().size() > min_keys) { // 자식노드 기준, 왼쪽 형제의 키개수가 최소숫자 범위 안 부서질 때
                borrowFromLeft(node, pos);
            } else { // 최소숫자 범위 부서질 때
                mergeNode(node, pos, pos - 1); // 부모노드(현재노드)와 지우는 노드랑 병합대상 노드 위치를 같이 넘겨줌.
            }
            return;
        } else { // 맨 왼쪽,맨 오른쪽 말고 그 이외
            if (node.getChildren().get(pos - 1).getKeyList().size() > min_keys) {
                borrowFromLeft(node, pos);
            } else if (
                    node.getChildren().get(pos + 1).getKeyList().size() > min_keys
            ) {
                borrowFromRight(node, pos);
            } else {
                mergeNode(node, pos, pos - 1); // 극단에 있는 자식 말고 그 외 지역에 위치한 노드들이 병합할 때
            }
            return;
        }
    }

    int findLV(FiveWayBTreeNode node) {
        //무조건 오른쪽 이동
        if (node.isLeaf) {
            // System.out.println(
            //   "find LV" + node.getKeyList().get(node.getKeyList().size() - 1)
            // );
            return node.getKeyList().get(node.getKeyList().size() - 1);
        }
        return findLV(node.getChildren().get(node.getChildren().size() - 1));
    }

    int findRV(FiveWayBTreeNode node) {
        //뮤조건 왼쪽 이동
        if (node.isLeaf) {
            // System.out.println("find RV" + node.getKeyList().get(0));
            return node.getKeyList().get(0);
        }
        return findRV(node.getChildren().get(0));
    }

    public void mergeChildNode(FiveWayBTreeNode node, int pos) {
        // 왼쪽 자식로 Merge
        // 바로 자식노드끼리 합치면
        // RV의 오른자식과 LV의 왼쪽 자식 중 하나가 사라짐
        // 일단 PV의 값을 복사해서 위의 두 자식을 살린채 합치자
        // add P
        int val = node.getKeyList().get(pos);
        node.getChildren().get(pos).getKeyList().add(val);
        // add R
        node
                .getChildren()
                .get(pos)
                .getKeyList()
                .addAll(node.getChildren().get(pos + 1).getKeyList());
        node
                .getChildren()
                .get(pos)
                .getChildren()
                .addAll(node.getChildren().get(pos + 1).getChildren());
        for (
                int i = 0;
                i < node.getChildren().get(pos + 1).getChildren().size();
                i++
        ) {
            node
                    .getChildren()
                    .get(pos + 1)
                    .getChildren()
                    .get(i)
                    .setParent(node.getChildren().get(pos));
        }
        //clear
        node.getChildren().remove(node.getChildren().get(pos + 1));
        node.getKeyList().remove((Object) val);
        delVal(node.getChildren().get(pos), val); // 부모노드에서 내렸던 값을 지우기
    }

    public void delNotLeaf(FiveWayBTreeNode node, int pos) {
        // System.out.println(
        //   "delNotLeaf" +
        //   pos +
        //   node.getChildren().get(pos).getKeyList().size() +
        //   node.getChildren().get(pos + 1).getKeyList().size()
        // );
        // if (
        //   node.getChildren().get(pos).getKeyList().size() >=
        //   node.getChildren().get(pos + 1).getKeyList().size()
        // ) {
        //   if (node.getChildren().get(pos).getKeyList().size() > min_keys) { // LV 찾기
        //     int LV = findLV(node.getChildren().get(pos));
        //     node.getKeyList().set(pos, LV); // val 을 LV로 대체
        //     delVal(node.getChildren().get(pos), LV); //LV의 값만 가져와서 대체 했기에 기존 LV는 삭제가 필요 , 삭제 시 balance 고려
        //   } else {
        //     mergeChildNode(node, pos); // 자식들을 머지
        //   }
        // } else {
        //   if (node.getChildren().get(pos + 1).getKeyList().size() > min_keys) { // RV 찾기
        //     int RV = findRV(node.getChildren().get(pos + 1));
        //     node.getKeyList().set(pos, RV); // val -> RV
        //     delVal(node.getChildren().get(pos + 1), RV); // RV의 값만 가져와서 대체 했기에 기존 RV는 삭제가 필요 , 삭제 시 balance 고려
        //   } else {
        //     mergeChildNode(node, pos);
        //   }
        // }
        if (node.getChildren().get(pos).getKeyList().size() > min_keys) { // LV 찾기
            int LV = findLV(node.getChildren().get(pos));
            node.getKeyList().set(pos, LV); // val 을 LV로 대체
            delVal(node.getChildren().get(pos), LV); //LV의 값만 가져와서 대체 했기에 기존 LV는 삭제가 필요 , 삭제 시 balance 고려
        } else if (node.getChildren().get(pos + 1).getKeyList().size() > min_keys) { // RV 찾기
            int RV = findRV(node.getChildren().get(pos + 1));
            node.getKeyList().set(pos, RV); // val -> RV
            delVal(node.getChildren().get(pos + 1), RV); // RV의 값만 가져와서 대체 했기에 기존 RV는 삭제가 필요 , 삭제 시 balance 고려
        } else {
            mergeChildNode(node, pos); // 자식들을 머지
        }
    }

    public boolean delVal(FiveWayBTreeNode node, int val) {
        boolean flag = false; // val 존재 유무
        int pos;

        // 위치 탐색
        for (pos = 0; pos < node.getKeyList().size(); pos++) {
            if (val == node.getKeyList().get(pos)) {
                flag = true; // 찾음
                break;
            } else if (val < node.getKeyList().get(pos)) { // 자식으로 이동을 위해 멈춤
                break;
            }
        } // pos 는 val의 위치 혹은 keyList.size() - 1

        if (flag) { // val 찾음
            if (node.isLeaf) {
                node.getKeyList().remove((Object) val);
            } else {
                delNotLeaf(node, pos);
            }
            return flag;
        } else { // 현재 노드에 val 가 없음
            if (node.isLeaf) { //leaf 노드이면 Not found
                return flag;
            } else { // leaf가 아니면 자식으로
                flag = delVal(node.getChildren().get(pos), val);
            }
        }
        if (node.getChildren().get(pos).getKeyList().size() < min_keys) { // min key 규칙 위반
            balancing(node, pos); // min key 규칙 준수를 위한 조정
        }
        return flag;
    }

    public void delete(FiveWayBTreeNode node, int val) {
        if (node == null) {
            // cur node is Empty;
            return;
        }
        if (delVal(node, val) == false) {
            // System.out.println("Not found value :" + val + "[in delete]");
            return;
        }
        if (node.getKeyList().size() == 0) { // empty node
            node = node.getChildren().get(0); // 노드가 가진 왼쪽 자식을 대입
        }
        root = node;
    }

    @Override
    public boolean remove(Object o) {
        delete(root, (int) o);
        return true;
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
        // e 보다 작은 최대값
        Integer max = null;
        Iterator<Integer> iter = iterator();
        while (iter.hasNext()) {
            int t = iter.next();
            if (t <= e) {
                if (max == null) {
                    max = t;
                } else {
                    if (max <= t) {
                        max = t;
                    }
                }
            }
        }
        return max;
    }

    @Override
    public Integer ceiling(Integer e) {
        // e 보다 큰 최소값
        Integer min = null;
        Iterator<Integer> iter = iterator();
        while (iter.hasNext()) {
            int t = iter.next();
            if (t >= e) {
                if (min == null) {
                    min = t;
                } else {
                    if (min >= t) {
                        min = t;
                    }
                }
            }
        }
        return min;
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
        return new treeIterator();
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
    public NavigableSet<Integer> subSet(
            Integer fromElement,
            boolean fromInclusive,
            Integer toElement,
            boolean toInclusive
    ) {
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
        // 작거나 같은 트리
        Iterator<Integer> iter = iterator();
        NavigableSet<Integer> result = new TreeSet<Integer>();
        while (iter.hasNext()) {
            Integer x = iter.next();
            if (x <= toElement) {
                result.add(x);
            }
        }
        return result;
    }

    @Override
    public SortedSet<Integer> tailSet(Integer fromElement) {
        // 크거나 같은 트리
        Iterator<Integer> iter = iterator();
        NavigableSet<Integer> result = new TreeSet<Integer>();
        while (iter.hasNext()) {
            Integer x = iter.next();
            if (x >= fromElement) {
                result.add(x);
            }
        }
        return result;
    }

    class treeIterator implements Iterator<Integer> {

        FiveWayBTreeNode curNode;
        int idx;

        public treeIterator() { // 시작점 : 최소값
            curNode = root;
            while (!curNode.isLeaf) {
                curNode = curNode.getChildren().get(0);
                idx = 0;
            }
        }

        public boolean hasNext() {
            return curNode != null && size != 0;
        }

        public void movePointer() {
            if (!curNode.isLeaf && curNode.getChildren().size() > idx) {
                // 자식 이동
                curNode = curNode.getChildren().get(idx);
                idx = 0;
                if (!curNode.isLeaf) {
                    movePointer();
                }
            } else if (curNode.getKeyList().size() <= idx) {
                // KeyList의 마지막이므로 부모 이동
                if (curNode == root) {
                    // 현재 root일때
                    curNode = curNode.getParent();
                    return;
                } else {
                    //부모 이동
                    idx = curNode.getParent().getChildren().indexOf(curNode); // 현재 자식의 위치
                    curNode = curNode.getParent();
                    if (curNode.getKeyList().size() <= idx) {
                        //다음 key로 이동
                        idx++;
                        movePointer();
                    }
                }
            }
        }

        public Integer next() {
            Integer result = null;
            result = curNode.getKeyList().get(idx);
            idx++;
            movePointer();
            return result;
        }
    }
}
