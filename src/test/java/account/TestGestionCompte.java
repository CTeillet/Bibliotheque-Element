package account;

import com.teillet.bibliothequeElement.interfaces.account.IManageAccount;
import org.junit.jupiter.api.Test;
import com.teillet.bibliothequeElement.utils.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGestionCompte {
    private final Factory factory = Factory.getFact();

    @Test
    public void TestAjout(){
        IManageAccount gestionCompte = factory.newGestionCompte();
        assertEquals(gestionCompte.size(), 0);
        assertTrue(gestionCompte.addPersonne("teillet.corentin@neuf.fr", "nono", "azerty"));
        assertEquals(gestionCompte.size(), 1);
    }

    @Test
    public void TestSuppression(){
        IManageAccount gestionCompte = factory.newGestionCompte();
        gestionCompte.addPersonne("teillet.corentin@neuf.fr", "nono", "azerty");
        assertTrue(gestionCompte.removePersonne("nono"));
    }

    @Test
    public void TestPassword(){
        IManageAccount gestionCompte = factory.newGestionCompte();
        gestionCompte.addPersonne("teillet.corentin@neuf.fr", "nono", "azerty");
        assertTrue(gestionCompte.isPassword("nono", "azerty"));
    }
}
