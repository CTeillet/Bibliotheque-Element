package com.teillet.bibliothequeElement.utils;

import com.teillet.bibliothequeElement.interfaces.library.IElements;

import java.lang.reflect.Constructor;

public class Utils {
    public static IElements Object2Elements(String type, String path, String title) {
        try {
            @SuppressWarnings("unchecked") Class<IElements> cls = (Class<IElements>) Class.forName("com.teillet.bibliothequeElement.library."
                    + type);
            @SuppressWarnings("rawtypes") Class[] partypes = new Class[2];
            partypes[0] = String.class;
            partypes[1] = String.class;
            Constructor<IElements> ct
                    = cls.getConstructor(partypes);
            Object[] arglist = new Object[2];
            arglist[0] = title;
            arglist[1] = path;
            return ct.newInstance(arglist);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
