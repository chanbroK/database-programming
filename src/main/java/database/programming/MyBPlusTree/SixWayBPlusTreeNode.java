package database.programming.MyBPlusTree;

import java.util.ArrayList;
import java.util.List;

public class SixWayBPlusTreeNode {

    boolean isLeaf;
    private SixWayBPlusTreeNode parent;
    private List<Integer> keyList;
    private List<SixWayBPlusTreeNode> children;

    public SixWayBPlusTreeNode() {
        keyList = new ArrayList<Integer>();
        children = new ArrayList<SixWayBPlusTreeNode>();
        isLeaf = true;
        parent = null;
    }

    public SixWayBPlusTreeNode(int val) {
        keyList = new ArrayList<Integer>();
        children = new ArrayList<SixWayBPlusTreeNode>();
        isLeaf = false;
        parent = null;
        keyList.add(val);
    }

    public SixWayBPlusTreeNode getParent() {
        return parent;
    }

    public void setParent(SixWayBPlusTreeNode parent) {
        this.parent = parent;
    }

    public List<SixWayBPlusTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<SixWayBPlusTreeNode> children) {
        this.children = children;
    }

    public List<Integer> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Integer> keyList) {
        this.keyList = keyList;
    }
}
