package com.teillet.bibliothequeElement.connection;

import com.teillet.bibliothequeElement.interfaces.account.IManageAccount;
import com.teillet.bibliothequeElement.interfaces.connection.IManageConnection;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ManageConnection implements IManageConnection {
    private int timeBeforeDisconnect = 10;
    private final Map<String, Date> connected = new HashMap<>();
    private final IManageAccount gestionCompte;

    public ManageConnection(IManageAccount gestionCompte){
        this.gestionCompte = gestionCompte;
    }

    @Override
    public boolean connect(String identifiant, String motDePasse) {
        if(gestionCompte.isPassword(identifiant, motDePasse)){
            connected.put(identifiant, new Date());
            return true;
        }else return false;
    }

    @Override
    public boolean isConnected(String identifiant) {
        Date temp = connected.get(identifiant);
        if( temp != null){
            Date now = new Date();

            if(TimeUnit.MILLISECONDS.toMinutes(now.getTime()-temp.getTime())<timeBeforeDisconnect) {
                connected.put(identifiant, now);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean disconnect(String identifiant) {
        Object temp = connected.remove(identifiant);
        return temp!=null;
    }

    @Override
    public void setTimeBeforeDisconnect(int timeBeforeDisconnect) {
        this.timeBeforeDisconnect = timeBeforeDisconnect;
    }
}
