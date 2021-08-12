package com.teillet.bibliothequeElement.library;

import com.teillet.bibliothequeElement.interfaces.library.ILibrary;
import com.teillet.bibliothequeElement.interfaces.library.IElements;
import com.teillet.bibliothequeElement.interfaces.library.IManageLibrary;
import com.teillet.bibliothequeElement.utils.Factory;

public class ManageLibrary implements IManageLibrary {
    private final ILibrary library;
    private static final IManageLibrary instance = new ManageLibrary();


    public ManageLibrary(ILibrary library) {
        this.library = library;
    }

    @Override
    public int size() {
        return library.size();
    }

    public boolean isEmpty() {
        return library.isEmpty();
    }

    public boolean contains(IElements e) {
        return library.contains(e);
    }

    public boolean add(IElements elements) {
        return library.add(elements);
    }

    public boolean remove(IElements e) {
        return library.remove(e);
    }

    public void clear() {
        library.clear();
    }

    @Override
    public IElements get(int index) {
        return null;
    }

    private ManageLibrary(){
        Factory fact = Factory.getFact();
        library = fact.newLibrary();
    }

    public static IManageLibrary getInstance() {
        return instance;
    }
}
