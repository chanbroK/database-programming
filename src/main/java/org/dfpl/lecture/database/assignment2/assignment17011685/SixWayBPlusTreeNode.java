package org.dfpl.lecture.database.assignment2.assignment17011685;

import java.util.ArrayList;
import java.util.List;

public class SixWayBPlusTreeNode {

    final protected int maxKey = 6;
    // TODO leftSibling 과 rightSibling 을 필요할때 구해서 사용하자
    protected SixWayBPlusTreeNode leftSibling;
    protected SixWayBPlusTreeNode rightSibling;
    // Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
    protected SixWayBPlusTreeNode parent;
    protected List<Integer> keyList;

    public SixWayBPlusTreeNode() {
        this.keyList = new ArrayList<>();
        this.parent = null;
        this.leftSibling = null;
        this.rightSibling = null;
    }

    // Getters and Setters
    public SixWayBPlusTreeNode getParent() {
        return parent;
    }

    public void setParent(SixWayBPlusTreeNode parent) {
        this.parent = parent;
    }

    public List<Integer> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Integer> keyList) {
        this.keyList = keyList;
    }

    public SixWayBPlusTreeNode getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(SixWayBPlusTreeNode leftSibling) {
        this.leftSibling = leftSibling;
    }

    public SixWayBPlusTreeNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(SixWayBPlusTreeNode rightSibling) {
        this.rightSibling = rightSibling;
    }

    // KeyList 가 가득 찼는지 확인
    public boolean isOverflow() {
        // TODO isOverflow -> isFull , 조건문 수정
        return this.keyList.size() == this.maxKey;
    }
}
