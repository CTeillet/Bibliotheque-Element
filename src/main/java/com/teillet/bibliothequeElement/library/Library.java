package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.database.BDElements;
import com.teillet.bibliothequeElement.interfaces.library.ILibrary;
import com.teillet.bibliothequeElement.interfaces.library.IElements;

public class Library implements ILibrary {

    public int size() {
        return BDElements.size();
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public boolean contains(IElements elements) {
        return BDElements.containsElements(elements);
    }

    public boolean add(IElements elements) {
        return BDElements.addElements(elements);
    }

    public boolean remove(IElements elements) {
        return BDElements.removeElements(elements);
    }

    public void clear() {
        BDElements.removeAllElements();
    }

    public IElements getTitle(String title) {
        return BDElements.getElementByTitle(title);
    }

    public IElements getPath(String path){
        return BDElements.getElementByPath(path);
    }
}
