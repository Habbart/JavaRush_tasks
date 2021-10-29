package com.javarush.task.task33.task3310.strategy;

import java.io.File;
import java.util.Arrays;

public class FileStorageStrategy implements StorageStrategy{

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000l;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    int size = 0;
    long maxBucketSize;

    public FileStorageStrategy() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    int hash(Long k){
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int)(k ^ (k >>> 7) ^ (k >>> 4));
    }

    int indexFor(int hash, int length){
        return hash & (length-1);
    }

    Entry getEntry(Long key){ // переделать
        int hash = (key == null) ? 0 : hash((long)key.hashCode());
        for (FileBucket f:
             table) {
            Entry e;
            while((e = f.getEntry()) != null){
                Object k;
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            }
        }
        return null;
    }



    void resize(int newCapacity){
        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        FileBucket[] newTable = new FileBucket[newCapacity];
        for (FileBucket f: newTable){
            f = new FileBucket();
        }
        transfer(newTable);
        table = newTable;
        //threshold = (int)(newCapacity * loadFactor); НЕПРАВИЛЬНО
    }
    void transfer(FileBucket[] newTable){
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            if(src[j].getEntry() == null) continue;
            Entry e = src[j].getEntry(); //ПЕРЕДЕЛАТЬ!!!!
            if (e != null) {                // или же все энтри по очереди
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                    e = next;
                } while (e != null);
            }
            src[j].remove();
            src[j] = null;
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex){
        createEntry(hash, key, value, bucketIndex);
        if (table[bucketIndex].getFileSize()  >= bucketSizeLimit)
            resize(2 * table.length);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        for (FileBucket f:
             table) {
            if(f.getFileSize() == 0) continue;
            int temp = size;
            while(temp > 0) {
                Entry e = f.getEntry();
                if (e.key.equals(key)) return true;
                temp--;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for (FileBucket f:
                table) {
            if(f.getFileSize() == 0) continue;
            int temp = size;
            while(temp > 0) {
                Entry e = f.getEntry();
                if (e.value.equals(value)) return true;
                temp--;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "FileStorageStrategy{" +
                "table=" + Arrays.toString(table) +
                ", bucketSizeLimit=" + bucketSizeLimit +
                ", size=" + size +
                ", maxBucketSize=" + maxBucketSize +
                '}';
    }

    @Override
    public void put(Long key, String value) {
        if (key == null) return;
        int hash = hash((long)key.hashCode());
        int i = indexFor(hash, table.length);
        for (Entry e = table[i].getEntry(); e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                String oldValue = e.value;
                e.value = value;
            }
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        Long result = null;
        for (FileBucket f:
                table) {
            if(f.getFileSize() == 0) continue;
            int temp = size;
            while(temp> 0){
                Entry e = f.getEntry();
                if(e.value.equals(value))  return e.key;
                temp--;
            }
        }
        return result;
    }

    @Override
    public String getValue(Long key) {
        String result = null;
        for (FileBucket f:
                table) {
            if(f.getFileSize() == 0) continue;
            int temp = size;
            while (temp > 0) {
                Entry e = f.getEntry();

                if (e.key.equals(key)) return e.value;
                temp--;
            }
        }
        return result;
    }
}
