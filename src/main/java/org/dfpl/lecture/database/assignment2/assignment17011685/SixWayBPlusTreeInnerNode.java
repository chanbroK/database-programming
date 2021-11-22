package org.dfpl.lecture.database.assignment2.assignment17011685;

import java.util.ArrayList;

public class SixWayBPlusTreeInnerNode extends SixWayBPlusTreeNode {
    protected ArrayList<SixWayBPlusTreeNode> childList;

    public SixWayBPlusTreeInnerNode() {
        this.childList = new ArrayList<>();
    }

    public int findNewKeyPos(Integer key) {
        int i = 0;
        for (i = 0; i < this.keyList.size(); ++i) {
            if (this.keyList.get(i).equals(key)) {
                return i + 1;
            } else if (this.keyList.get(i) < key) {
                return i;
            }
        }
        return i;
    }

    public void insertAt(int index, Integer key, SixWayBPlusTreeNode leftChild, SixWayBPlusTreeNode rightChild) {
        this.keyList.set(index, key);
        this.childList.set(index, leftChild);
        this.childList.set(index + 1, rightChild);
        // full 처리
        if (this.isOverflow()) {
            this.dealOverflow();
        }
    }

    public void dealOverflow() {
        // TODO midIndex 수정
        int midIndex = this.keyList.size() / 2;
        Integer midKey = this.keyList.get(midIndex);

        // 새로운 노드에 기존 노드 값 ,자식 복사
        SixWayBPlusTreeInnerNode newNode = new SixWayBPlusTreeInnerNode();
        for (int i = midIndex + 1; i < this.keyList.size(); ++i) {
            // key
            Integer key = this.keyList.get(i);
            newNode.keyList.add(key);
            this.keyList.remove(key);
            // child
            SixWayBPlusTreeNode child = this.childList.get(i);
            newNode.childList.set(i - midIndex - 1, child);
            child.setParent(newNode);
            this.childList.remove(child);
        }

        // this 가 root 일때
        if (this.getParent() == null) {
            this.setParent(new SixWayBPlusTreeInnerNode());
        }
        newNode.setParent(this.getParent());

        // 기존 Sibling Node 와 newNode 연결
        // TODO 필요할때마다 Sibling Node 를 구하면 해당 과정 필요 없음
        newNode.setLeftSibling(this);
        newNode.setRightSibling(this.rightSibling);
        if (this.getRightSibling() != null) {
            this.getRightSibling().setLeftSibling(newNode);
        }

        // 위로 올리기
        SixWayBPlusTreeInnerNode parent = (SixWayBPlusTreeInnerNode) this.getParent();
        int pos = parent.keyList.indexOf(midKey);
        parent.insertAt(pos, midKey, this, newNode);
    }
}
