package org.dfpl.lecture.database.assignment2.assignment17011685;

import java.util.ArrayList;
import java.util.List;

public class SixWayBPlusTreeNode {

    boolean isLeaf;
    // Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
    private SixWayBPlusTreeNode parent;
    private List<Integer> keyList;
    private List<SixWayBPlusTreeNode> children;

    public SixWayBPlusTreeNode() {
        keyList = new ArrayList<>();
        children = new ArrayList<>();
        isLeaf = false;
        parent = null;
    }

    public SixWayBPlusTreeNode(int val) {
        keyList = new ArrayList<>();
        keyList.add(val);
        children = new ArrayList<>();
        isLeaf = false;
        parent = null;
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

    public List<SixWayBPlusTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<SixWayBPlusTreeNode> children) {
        this.children = children;
    }
}
