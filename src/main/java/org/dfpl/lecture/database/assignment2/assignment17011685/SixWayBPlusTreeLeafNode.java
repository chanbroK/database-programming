package org.dfpl.lecture.database.assignment2.assignment17011685;

public class SixWayBPlusTreeLeafNode extends SixWayBPlusTreeNode {


    public void insertKey(Integer key) {
        // 위치 찾기
        int index = 0;
        while (index < this.keyList.size() && this.keyList.get(index) < key) {
            ++index;
        }
        this.insertAt(index, key);
    }

    public void insertAt(int index, Integer key) {
        this.keyList.set(index, key);
        if (this.isOverflow()) {
            this.dealOverflow();
            // TODO dealOverflow 를 밖으로 빼야함. parent 갑승ㄹ 갱신시켜줘야 함
        }
    }

    // KeyList 가 가득 찼을때 처리(split 과정)
    public void dealOverflow() {
        // TODO midIndex 수정
        int midIndex = this.keyList.size() / 2;

        // 새로운 노드에 기존 노드 값 복사
        SixWayBPlusTreeNode newNode = new SixWayBPlusTreeLeafNode();
        for (int i = midIndex; i < this.keyList.size(); ++i) {
            Integer key = this.keyList.get(i);
            newNode.keyList.add(key);
            this.keyList.remove(key);
        }

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
    }
}
