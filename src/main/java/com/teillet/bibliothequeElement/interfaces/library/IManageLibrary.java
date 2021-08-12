package com.teillet.bibliothequeElement.interfaces.library;

public interface IManageLibrary {
    int size();
    boolean isEmpty();
    boolean contains(IElements e);
    boolean add(IElements e);
    boolean remove(IElements e);
    void clear();
    IElements get(int index);

}
