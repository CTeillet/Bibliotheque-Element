package com.teillet.bibliothequeElement.account;

import com.teillet.bibliothequeElement.interfaces.account.IPerson;

import java.util.Date;

public class Person implements IPerson {
    private final String name;
    private final String surname;
    private final String mail;
    private final String username;
    private final String password;
    private final Date birthDate;


    public Person(String name, String surname, String mail, String username, String password, Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
    }

    public Person(String mail, String username, String password) {
        this("", "", mail, username, password, null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }
}
