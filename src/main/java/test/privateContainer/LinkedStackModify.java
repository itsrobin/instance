package test.privateContainer;

/**
 * Created by lifeng on 16/5/28.
 * 自定义内部链式存储类
 */
public class LinkedStackModify<T> {
    //定义内部节点
    private static class Node<U> {
        //节点内容
        U item;
        //尾部节点
        Node<U> next;
        //节点构造器,生成空节点
        Node() {
            item = null;
            next = null;
        }
        //生成带内容和尾节点的节点
        Node(U item, Node<U> next) {
            this.item = item;
            this.next = next;
        }

        //判断该节点是否围为最后一个节点
         boolean end() {
            return item==null && next == null;
        }
    }

    //初始化顶部的节点
    private Node<T> top = new Node<T>();

    //压入方法
    public void push(T item) {
        top = new Node<T>(item,top);
    }

    //弹出方法
    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStackModify<String> linkedStack = new LinkedStackModify();
        for (String s : "rich man a am i".split(" ")) {
            linkedStack.push(s);
        }
        String s;
        while ((s = linkedStack.pop()) != null) {
            System.out.println(s);
        }
    }



}
