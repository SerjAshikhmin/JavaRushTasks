package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>(Math.max((int)((float) collection.size() / 0.75F) + 1, 16));
        this.addAll(collection);
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public Object clone() throws InternalError{
        try {
            AmigoSet<E> newSet = (AmigoSet) super.clone();
            newSet.map = (HashMap)this.map.clone();
            return newSet;
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeInt(HashMapReflectionHelper.callHiddenMethod(this.map, "capacity"));
        ous.writeFloat(HashMapReflectionHelper.callHiddenMethod(this.map, "loadFactor"));
        ous.writeInt(this.map.keySet().size());

        Iterator iterator = iterator();
        while (iterator.hasNext()) {
            E e = (E) iterator.next();
            ous.writeObject(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        return this.map.equals(((AmigoSet) o).map);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        int size = ois.readInt();
        this.map = new HashMap<>(capacity, loadFactor);

        for (int i = 0; i < size; i++) {
            this.map.put((E) ois.readObject(), PRESENT);
        }
    }

}
