package com.teillet.bibliothequeElement.account;

import com.teillet.bibliothequeElement.interfaces.account.IManageAccount;
import com.teillet.bibliothequeElement.interfaces.account.IPerson;
import com.teillet.bibliothequeElement.utils.Factory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ManageAccount implements IManageAccount {
    private final Map<String, IPerson> inscrits = new HashMap<>();
    private final Factory factory = Factory.getFact();

    private static final IManageAccount instance = new ManageAccount();

    public static IManageAccount getGestionCompte() {
        return instance;
    }

    @Override
    public boolean addPersonne(String surname,String name, String mail, String username, String password, Date birthDate) {
        if(inscrits.containsKey(username)) return false;
        inscrits.put(username, factory.newPersonne(surname,  name, mail, username, password, birthDate));
        return true;
    }

    @Override
    public boolean addPersonne(String mail, String identifiant, String motDePasse) {
        return this.addPersonne("", "", mail, identifiant, motDePasse, null);
    }

    @Override
    public boolean removePersonne(String identifiant) {
        return inscrits.remove(identifiant)!=null;
    }

    @Override
    public boolean isPassword(String identifiant, String motDePasse) {
        IPerson pers = inscrits.get(identifiant);
        if(pers == null) return false;
        return pers.getPassword().equals(motDePasse);
    }

    public int size(){
        return inscrits.size();
    }

    private ManageAccount(){}
}
