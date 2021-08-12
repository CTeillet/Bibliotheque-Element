package com.teillet.bibliothequeElement.interfaces.connection;

public interface IManageConnection {
    boolean connect(String identifiant, String motDePasse);
    boolean isConnected(String identifiant);
    boolean disconnect(String identifiant);
    void setTimeBeforeDisconnect(int timeBeforeDisconnect);
}