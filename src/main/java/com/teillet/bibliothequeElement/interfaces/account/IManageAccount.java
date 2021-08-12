package com.teillet.bibliothequeElement.interfaces.account;

import java.util.Date;

public interface IManageAccount {
    boolean addPersonne(String surname, String name, String mail, String identifiant, String password, Date birthDate);
    boolean addPersonne(String mail, String username, String password);
    boolean removePersonne(String username);
    boolean isPassword(String username, String password);
    int size();

}