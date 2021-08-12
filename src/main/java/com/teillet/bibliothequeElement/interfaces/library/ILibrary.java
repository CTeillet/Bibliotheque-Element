package com.teillet.bibliothequeElement.interfaces.library;

public interface ILibrary {
    int size();
    boolean contains(IElements e);
    boolean add(IElements elements);
    boolean remove(IElements e);
    void clear();
    boolean isEmpty();
}
