package org.dfpl.lecture.database.assignment2.assignment17011685.mytree;

import java.util.ArrayList;
import java.util.List;

public class MyNode {

    boolean isLeaf;
    private MyNode parent;
    private List<Integer> keyList;
    private List<MyNode> children;

    public MyNode() {
        keyList = new ArrayList<Integer>();
        children = new ArrayList<MyNode>();
        isLeaf = true;
        parent = null;
    }

    public MyNode(int val) {
        keyList = new ArrayList<Integer>();
        children = new ArrayList<MyNode>();
        isLeaf = false;
        parent = null;
        keyList.add(val);
    }

    public MyNode getParent() {
        return parent;
    }

    public void setParent(MyNode parent) {
        this.parent = parent;
    }

    public List<MyNode> getChildren() {
        return children;
    }

    public void setChildren(List<MyNode> children) {
        this.children = children;
    }

    public List<Integer> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Integer> keyList) {
        this.keyList = keyList;
    }
}
