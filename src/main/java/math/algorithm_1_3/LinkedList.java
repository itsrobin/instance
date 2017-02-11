package math.algorithm_1_3;

/**
 * @author lifeng
 * @version 2.0 on 17/1/10.
 */
public class LinkedList<Item> {

    Node first = new Node();

    public static class Node<Item> {
        Item item;
        Node<Item> next;

        Node(Item s){
            item = s;
        }

        Node(){
        }
    }


    public void removeAfter(Node n) {
        if (n != null && n.next != null) {
            n.next = null;
        }
    }

    public void insertAfter(Node m, Node n) {
        if (m != null) {
            m.next = n;
        }
    }

    public void remove(LinkedList list, String key) {
        //first节点默认为没有实际意义的结点
        Node before = list.first;
        Node node = before.next;
        while (node != null) {
            Node next = node.next;
            if (node.item.equals(key)) {
                //如果该节点应该深处,那么前置结点直接与后置结点相连
                //前置节点不变
                before.next = next;
            }else {
                //否则前置结点应该转移到下一个结点
                before = node;
            }
            //遍历的结点转为后置结点
            node = next;
        }
    }

    public Integer max(Node<Integer> first) {
        Integer value =0;
        for ( Node<Integer> node= first; node!=null ; node = node.next) {
            if (value < node.item) {
                value = node.item;
            }
        }
        return value;
    }

    public  Integer maxRec(Node<Integer> first,int max) {
        if (first == null) return max;
        if (max < first.item){
            max = first.item;
        }
         return maxRec(first.next,max);
    }

    public static Node reverse(Node first) {
        if (first == null) return null;
        if (first.next == null) return first;
        Node second = first.next;
        Node rest = reverse(second);
        second.next = first;
        first.next = null;
        return  rest;

    }



    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        Node<Integer> node = new Node<Integer>(1);
        Node<Integer> node2 = new Node<Integer>(2);
        Node<Integer> node3 = new Node<Integer>(3);
        Node<Integer> node5 = new Node<Integer>(4);
        Node<Integer> node6 = new Node<Integer>(5);
        Node<Integer> node4 = new Node<Integer>(6);
        list.first.next = node;
        node.next = node2;
        node2.next = node3;
        node3.next = node5;
        node5.next = node6;
        node6.next = node4;
//        System.out.println(maxRec(node,0);
//        remove(list,"123");

        reverse(node);

    }
}
