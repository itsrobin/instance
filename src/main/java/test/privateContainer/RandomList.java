package test.privateContainer;

import collection.Lists;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lifeng on 16/5/28.
 * 随机列表
 */
public class RandomList<T> {
    private ArrayList<T> storage = Lists.newArrayList();
    private Random rand = new Random();

    public void add(T item) {
        storage.add(item);
    }

    public T get() {
        return storage.get(rand.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<String>();
        for(String s : "I am a lonely man".split(" ")) {
            rs.add(s);
        }

        for (int i = 0; i < 11 ; i++) {
            System.out.print(rs.get() + " ");
        }
    }
}
