package com.teillet.bibliothequeElement.utils;

import com.teillet.bibliothequeElement.library.Library;
import com.teillet.bibliothequeElement.library.Elements;
import com.teillet.bibliothequeElement.library.Film;
import com.teillet.bibliothequeElement.library.ManageLibrary;
import com.teillet.bibliothequeElement.account.ManageAccount;
import com.teillet.bibliothequeElement.account.Person;
import com.teillet.bibliothequeElement.connection.ManageConnection;
import com.teillet.bibliothequeElement.interfaces.library.ILibrary;
import com.teillet.bibliothequeElement.interfaces.library.IElements;
import com.teillet.bibliothequeElement.interfaces.library.IFilm;
import com.teillet.bibliothequeElement.interfaces.library.IManageLibrary;
import com.teillet.bibliothequeElement.interfaces.account.IManageAccount;
import com.teillet.bibliothequeElement.interfaces.account.IPerson;
import com.teillet.bibliothequeElement.interfaces.connection.IManageConnection;

import java.util.Date;
import java.util.List;

public class Factory {
    private static final Factory fact = new Factory();
    public static Factory getFact() {
        return fact;
    }

    public IPerson newPersonne(String name, String surname, String mail, String username, String password, Date birthDate){
        return new Person(name, surname, mail, username, password, birthDate);
    }
    public IPerson newPersonne(String mail, String username, String motDePasse){
        return new Person(mail, username, motDePasse);
    }
    public IManageAccount newGestionCompte(){
        return ManageAccount.getGestionCompte();
    }

    public IManageConnection newGestionConnexion(IManageAccount gestionCompte){
        return new ManageConnection(gestionCompte);
    }

    public IFilm newFilm(String title, String path, String director, String duration, List<String> actors) {
        return new Film(title, path, director, duration, actors);
    }

    public ILibrary newLibrary() {
        return new Library();
    }

    public IManageLibrary newGestionLibrary(){
        return ManageLibrary.getInstance();
    }

    public IElements newFilm(String title, String path){
        return new Film(title, path);
    }


}
