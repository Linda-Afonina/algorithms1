package org.example;

import org.example.exceptions.ArrayIsFullException;
import org.example.exceptions.ElementNotFoundException;
import org.example.exceptions.NullStringException;
import org.example.exceptions.WrongIndexException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private final String[] stringList;
    private int size;


    public StringListImpl() {
        stringList = new String[10];
    }

    public StringListImpl(int size) {
        stringList = new String[size];
    }

    public void checkItem(String item) {
        if (item == null) {
            throw new NullStringException("Пустая строка");
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= stringList.length) {
            throw new WrongIndexException("Неверный индекс");
        }
    }

    public void checkSize() {
        if (size == stringList.length) {
            throw new ArrayIsFullException("Массив переполнен");
        }
    }

    @Override
    public String add(String item) {
        checkSize();
        checkItem(item);
        stringList[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkIndex(index);
        checkItem(item);
        checkSize();
        if (index == size) {
            stringList[size++] = item;
            return item;
        }
        System.arraycopy(stringList, index, stringList, index + 1, size - index);
        stringList[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndex(index);
        checkItem(item);
        stringList[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Элемент массива не найден");
        }
        if (size != index) {
            System.arraycopy(stringList, index + 1, stringList, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String item = stringList[index];
        if (index != size) {
            System.arraycopy(stringList, index + 1, stringList, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i > 0; i--) {
            if (stringList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return stringList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(stringList, size);
    }
}
