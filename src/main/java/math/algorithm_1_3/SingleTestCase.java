package math.algorithm_1_3;

import com.algs4.stdlib.StdIn;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lifeng
 * @version 2.0 on 17/1/14.
 */
public class SingleTestCase {

    public boolean parentheses(String s, Stack<String> stack) {
        for (Character c : s.toCharArray()) {
            if (c == 41 || c == 93 || c == 125) {
                String ch = stack.pop();
                if (c == 41 && !"(".equals(ch)) {
                    return false;
                } else if (c == 93 && !"[".equals(ch)) {
                    return false;
                } else if (c == 125 && !"{".equals(ch)) {
                    return false;
                }
            } else {
                stack.push(c.toString());
            }
        }
        return true;
    }


    public static void infixComplete(String expression) {
        expression = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        Stack<String> ops = new Stack<>();
        Stack<String> values = new Stack<>();

        String[] strs = expression.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strs) {
            if ("+".equals(s)
                    || "-".equals(s)
                    || "*".equals(s)) {
                ops.push(s);
            } else if (")".equals(s)) {
                builder.setLength(0);
                String first = values.pop();
                String second = values.pop();
                String op = ops.pop();
                values.push(builder
                        .append("( ")
                        .append(second).append(" ")
                        .append(op).append(" ")
                        .append(first).append(" )").toString()
                );
            } else {
                values.push(s);
            }
        }

        System.out.println(values);
    }

    /**
     * 遍历表达式列表
     * 如果得到数字,无条件加入队列
     * 如果得到开括号,无条件入栈
     * 如果得到闭括号,无限出栈并加入队列,直到遇到开括号
     * 如果得到操作符,入栈时如果优先级小于等于栈顶元素,那么将该元素加入队列,
     * 并与下一个栈顶元素比较.直到大于该元素,加入队列
     * 最后将栈中所有的元素按顺序加入队列
     *
     * @param expression
     * @return
     */
    public static List<String> infixToPostfix(List<String> expression) {
        //定义操作符的优先级,可以将该对象放到外部.
        Map<String, Integer> prior = new HashMap<String, Integer>() {
            {
                put("+", 1);
                put("-", 1);
                put("*", 2);
                put("/", 2);
            }
        };

        //操作数队列
        Stack<String> ops = new Stack<>();
        //后序表达式列表
        List<String> list = new ArrayList<>();
        //正则判断是否为数字
        Pattern pattern = Pattern.compile("-?[0-9]*");
        for (String str : expression) {
            if (pattern.matcher(str).matches()) {
                list.add(str);
            } else {
                //开括号无条件入栈
                if ("(".equals(str)) {
                    ops.push(str);
                    continue;
                }
                //闭括号
                if (")".equals(str)) {
                    while (true) {
                        String o = ops.pop();
                        if (!o.equals("(")) {
                            list.add(o);
                        } else {
                            break;
                        }
                    }
                    continue;
                }
                //当操作符栈不为空,栈顶不为开括号,栈顶符号的优先级大于等于当前正在处理的操作符
                while (!ops.isEmpty()
                        && !ops.peek().equals("(")
                        && prior.get(ops.peek()) >= prior.get(str)) {
                    //弹出栈内的操作符,并输出到表达式内
                    list.add(ops.pop());
                }
                ops.push(str);
            }
        }
        while (!ops.isEmpty()) {
            list.add(ops.pop());
        }
        return list;
    }


    /**
     * 梳理表达式的格式,使其容易使用
     * 优化后表达式中可以有负数:例如
     * 1 + 2 * 3 + ( 4 * -5-6 ) * -7
     * 先根据正则得到表达式中所有的数字,还有他们开始和结束的位置.(start,end)
     * 例如1为(0,1),2为(2,3)...
     * 然后按照顺序把1,2,还有1和2之间的符号+(1,2)加入列表
     * 如果遇到-5-6这种情况,会处理为-5+-6
     *
     * @param expression
     * @return
     */
    private static List<String> processExp(String expression) {


        expression = expression.replaceAll(" ", "");
        List<String> list = new ArrayList<>();
        //正则表达式匹配数字,包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(expression);
        int start = 0;
        String before = "";
        while (matcher.find()) {
            String a = matcher.group(); //匹配得到的数字
            /**
             * //处理数字间隔中的字符
             */
            int end = matcher.start();

            for (int j = start; j < end; j++) {
                before = String.valueOf(expression.charAt(j));
                list.add(before);
            }
            //如果遇到-5-6这种情况,会处理为-5+-6,在中间加上"+"
            if (pattern.matcher(before).matches()) {
                list.add("+");
            }
            //更换下标
            start = matcher.end();
            list.add(a);
            //设置最新添加的字符
            before = a;
        }
        //在数字后可能还有符号,全部添加到末尾
        if (start != expression.length()) {
            for (int i = start; i < expression.length(); i++) {
                list.add(String.valueOf(expression.charAt(i)));
            }
        }
        return list;
    }

    /**
     * 将操作数压入栈中,每当有操作符时,从栈中弹出两个操作数进行计算,然后将结果压回栈中
     * 以此类推,直到完全计算完毕
     *
     * @param expression
     */
    public static void evaluatePostfix(List<String> expression) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Stack<String> vals = new Stack<>();
        for (String s : expression) {
            if (pattern.matcher(s).matches()) {
                vals.push(s);

            } else {
                double num2 = Double.valueOf(vals.pop());
                double num1 = Double.valueOf(vals.pop());
                if ("sqrt".equals(s)) {
                } else if ("+".equals(s)) {
                    vals.push(String.valueOf(num1 + num2));
                } else if ("-".equals(s)) {
                    vals.push(String.valueOf(num1 - num2));
                } else if ("*".equals(s)) {
                    vals.push(String.valueOf(num1 * num2));
                } else if ("/".equals(s)) {
                    vals.push(String.valueOf(num1 / num2));
                }
            }
        }
        System.out.println(vals.peek());
    }

    public static void testCal(String[] args) {
        String s = "服务：美容养护9.00折、钣金9.10折 配件：油品10.00折、防冻液9.90折";
        System.out.println(s.replaceAll("\\.?(0+)?0$", ""));
//        Pattern pattern = Pattern.compile("0?0$");
//        Matcher matcher = pattern.matcher(s);
//        if (matcher.find()){
//            System.out.println(matcher.group());
//            System.out.println(matcher.start());
//            System.out.println(matcher.end());
//        }

//        System.out.println(Arrays.toString(infixToPostfix(processExp("(23+34*45/(5+6+7))")).toArray()));
//        evaluatePostfix(infixToPostfix(processExp("(23+34*45/(5+6+7))")));
//        evaluatePostfix(infixToPostfix(processExp("2*3/(2-1)+3*(4-1)")));
//        toString(infixToPostfix(processExp("2*3/(2-1)+3*(4-1)")));
//        toString(infixToPostfix(processExp("((8*3+2)/13-3-2)*2/4-1000-1")));
//        toString(infixToPostfix(processExp("(23+34*45/(5+6+7))")));
//        System.out.println(Arrays.toString(infixToPostfix(processExp("1 + 2 * 3 + ( 4 * 5 + -6 ) * 7")).toArray()));
//        evaluatePostfix(infixToPostfix(processExp("1 + 2 * 3 + ( 4 * 5 + -6 ) * 7")));
//        evaluatePostfix(infixToPostfix(processExp("((8*3+2)/13-3-2)*2/4-1000-1")));
//        System.out.println(Arrays.toString(processExp("(23+34*45/(5+6+7))").toArray()));
    }

    public static void toString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(" ");
        }
        System.out.println(sb);
    }


    public static void josephus() {
        while (true) {
            String[] s = StdIn.readString().split(",");

            int N = Integer.valueOf(s[0]);
            int M = Integer.valueOf(s[1]);
            ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<>();
            for (int i = 0; i < N; i++) {
                queue.enqueue(i);
            }

            //报数的人将会出列,并得到自己的号,如果与M相等则出列.并重置报数
            //反之,不断地入列直到有人出局
            int i = 1;
            while (!queue.isEmpty()) {
                int index = queue.dequeue();
                if (i == M) {
                    System.out.println(index);
                    i = 1;
                } else {
                    queue.enqueue(index);
                    i++;
                }
            }

        }
    }

    /**
     * 1.3.41 复制队列
     */
    public static void copyQueue() {
        LinkedListDoubleNode<TestItem> q = new LinkedListDoubleNode<>();
        TestItem item1 = new TestItem("test1");
        q.linkLast(item1);
        item1 = new TestItem("test2");
        q.linkLast(item1);
        item1 = new TestItem("test3");
        q.linkLast(item1);
        item1 = new TestItem("test4");
        q.linkLast(item1);

        //复制队列
        LinkedListDoubleNode<TestItem> r = new LinkedListDoubleNode<>(q);


        //测试队列中的对象是否一致
        for (TestItem item : q) {
            System.out.println(r.unlinkFirst() == item);
        }

        r = new LinkedListDoubleNode<>(q);
        TestItem item = q.unlinkFirst();
        item.setName("changed");

        System.out.println(r.unlinkFirst().getName());
    }


    /**
     * 1.3.43 文件列表
     */
    public static void testListFile() {
        File file = new File("/Users/lifeng/Desktop/test");
        LinkedListDoubleNode<String> queue = new LinkedListDoubleNode<>();
        listFile(file, 0, queue);
        for (String s : queue) {
            System.out.println(s);
        }
    }


    public static void listFile(File file, int deep, LinkedListDoubleNode<String> queue) {
        StringBuilder sb = new StringBuilder();
        //根据递归深度缩进
        for (int i = 0; i < deep; i++) {
            sb.append("--");
        }

        //如果是文件直接入列
        if (!file.isDirectory()) {
            sb.append("*").append(file.getName());
            queue.linkLast(sb.toString());
        } else {//如果是文件夹,获取文件夹名称,入列
            sb.append(file.getName());
            queue.linkLast(sb.toString());
            File[] files = file.listFiles();
            deep++;//增加递归深度
            for (File file1 : files) {
                listFile(file1, deep, queue);
            }
        }
    }


    /**
     * 1.3.44 文件编辑器缓冲区
     */
    public static void testBuffer() {
        Buffer buffer = new Buffer();
        buffer.insert('1');
        buffer.insert('2');
        buffer.insert('3');
        buffer.insert('4');
        buffer.insert('5');
        System.out.print("输入5个字符 ");
        System.out.println(buffer);
        buffer.delete();
        System.out.print("删除一个字符 ");
        System.out.println(buffer);
        buffer.left(2);
        System.out.print("光标左移两个位置 ");
        System.out.println(buffer);
        buffer.right(1);
        System.out.print("光标右移一个位置 ");
        System.out.println(buffer);
        buffer.delete();
        buffer.delete();
        buffer.delete();
        buffer.delete();
        System.out.println("全部删除左边 ");
        System.out.println(buffer);
        buffer.right(1);
        System.out.print("光标右移一个位置 ");
        System.out.println(buffer);
    }

    public static Boolean testStack(int[] in, int[] out) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i : in) {
            if (i == out[j]){
                j++;
            }else {
                stack.push(i);
            }
            while (!stack.isEmpty() && stack.peek() == out[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        int[] in = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] out = new int[]{2, 5, 6, 7, 4, 8, 9, 3, 1, 0};
        System.out.println(testStack(in, out));

        out = new int[]{4, 6, 8, 7, 5, 3, 2, 9, 0, 1};
        System.out.println(testStack(in, out));

        out = new int[]{2, 1, 4, 3,6, 5, 8, 7, 9, 0};
        System.out.println(testStack(in, out));

        out = new int[]{0, 4, 6, 5,3, 8, 1, 7, 2, 9};
        System.out.println(testStack(in, out));
    }


}
