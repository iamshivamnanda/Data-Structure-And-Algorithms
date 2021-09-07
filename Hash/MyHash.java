package Hash;

import java.util.ArrayList;
import java.util.Objects;

class Node<K, V> {
    K key;
    V value;
    final int hashCode;
    Node<K, V> next;

    Node(K k, V val, int hashCode) {
        this.key = k;
        this.value = val;
        this.hashCode = hashCode;
    }
}

public class MyHash<K, V> {

    ArrayList<Node<K, V>> bucket;
    int bucketsize;
    int size;

    MyHash(int n) {
        this.bucket = new ArrayList<Node<K, V>>();
        this.bucketsize = n;
        this.size = 0;

        for (int i = 0; i < n; i++) {
            bucket.add(null);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % this.bucketsize;
        index = index < 0 ? index * -1 : index;
        return index;
    }

    public void add(K key, V value) {
        int BucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        Node<K, V> head = bucket.get(BucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        this.size++;
        head = bucket.get(BucketIndex);
        Node<K, V> newNode = new Node<K, V>(key, value, hashCode);
        newNode.next = head;
        bucket.set(BucketIndex, newNode);
    }

    public V get(K key) {
        int BucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        Node<K, V> node = bucket.get(BucketIndex);

        while (node != null) {
            if (node.key.equals(key) && node.hashCode == hashCode) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int BucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        Node<K, V> node = bucket.get(BucketIndex);

        Node<K, V> prevNode = null;

        while (node != null) {
            if (node.key.equals(key) && hashCode == node.hashCode) {
                break;
            }
            prevNode = node;
            node = node.next;
        }
        if (node == null) {
            return null;
        }
        size--;
        if (prevNode != null) {
            prevNode.next = node.next;
        } else {
            bucket.set(BucketIndex, node.next);
        }
        return node.value;
    }

    public static void main(String[] args) {
        MyHash<Integer, String> mhash = new MyHash<Integer, String>(100);
        mhash.add(5, "shivam");
        mhash.add(7, "Nanda");

        System.out.println(mhash.size());
        System.out.println(mhash.get(5));
        System.out.println(
                mhash.remove(5)
        );

        System.out.println(mhash.size());
        System.out.println(mhash.get(5));

    }
}
