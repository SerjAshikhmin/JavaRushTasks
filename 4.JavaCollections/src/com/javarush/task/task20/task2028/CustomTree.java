package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry root;
    int size = 0;

    public CustomTree() {
        this.root = new Entry<String>("0");
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }

    @Override
    public boolean add(String s) {
        Entry<String> newNode = new Entry<>(s);
        Entry<String> top = root;
        Queue<Entry<String>> queue=new LinkedList<>();
        do {
            if (top.leftChild != null) {
                queue.add(top.leftChild);
            } else {
                if (top.availableToAddLeftChildren) {
                    top.leftChild = newNode;
                    size++;
                    top.availableToAddLeftChildren = false;
                    return true;
                }
            }
            if (top.rightChild != null) {
                queue.add(top.rightChild);
            } else {
                if (top.availableToAddRightChildren) {
                    top.rightChild = newNode;
                    size++;
                    top.availableToAddRightChildren = false;
                    return true;
                }
            }
            if (!queue.isEmpty()) top = queue.poll();
        } while (!queue.isEmpty());
        //не нашли, куда добавить новый элемент - восстанавливаем эту возможность
        top = root;
        do {
            if (top.leftChild != null) {
                queue.add(top.leftChild);
            } else {
                top.availableToAddLeftChildren = true;
            }
            if (top.rightChild != null) {
                queue.add(top.rightChild);
            } else {
                top.availableToAddRightChildren = true;
            }
            if (!queue.isEmpty()) top = queue.poll();
        } while (!queue.isEmpty());
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public String getParent(String s) {
        Entry<String> top = root;
        Queue<Entry<String>> queue=new LinkedList<>();
        do {
            if (top.leftChild != null) {
                queue.add(top.leftChild);
                if (top.leftChild.elementName.equals(s)) {
                    return top.elementName;
                }
            }
            if (top.rightChild != null) {
                queue.add(top.rightChild);
                if (top.rightChild.elementName.equals(s)) {
                    return top.elementName;
                }
            }
            if (!queue.isEmpty()) top = queue.poll();
        } while (!queue.isEmpty());
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        String s = (String) o;
        Entry<String> top = root;
        Queue<Entry<String>> queue=new LinkedList<>();
        do {
            if (top.leftChild != null) {
                if (top.leftChild.elementName.equals(s)) {
                    int sizeToDel = countSizeToDel(top.leftChild);
                    size -= sizeToDel;
                    top.leftChild = null;
                    if (top.rightChild == null) {
                        top.availableToAddLeftChildren = true;
                    }
                    return true;
                } else queue.add(top.leftChild);
            }
            if (top.rightChild != null) {
                if (top.rightChild.elementName.equals(s)) {
                    int sizeToDel = countSizeToDel(top.rightChild);
                    size -= sizeToDel;
                    top.rightChild = null;
                    if (top.leftChild == null) {
                        top.availableToAddRightChildren = true;
                    }
                    return true;
                } else queue.add(top.rightChild);
            }
            if (!queue.isEmpty()) top = queue.poll();
        } while (!queue.isEmpty());
        return false;
    }

    public int countSizeToDel(Entry<String> delEntry) {
        int sizeToDel = 1;
        Entry<String> top = delEntry;
        Queue<Entry<String>> queue=new LinkedList<>();
        do {
            if (top.leftChild != null) {
                queue.add(top.leftChild);
                sizeToDel++;
            }
            if (top.rightChild != null) {
                queue.add(top.rightChild);
                sizeToDel++;
            }
            if (!queue.isEmpty()) top = queue.poll();
        } while (!queue.isEmpty());
        return sizeToDel;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

}
