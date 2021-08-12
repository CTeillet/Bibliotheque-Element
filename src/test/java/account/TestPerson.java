package account;

import com.teillet.bibliothequeElement.interfaces.account.IPerson;
import org.junit.jupiter.api.Test;
import com.teillet.bibliothequeElement.utils.Factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("SpellCheckingInspection")
public class TestPerson {
    final Factory fact = Factory.getFact();

    @Test
    void TestNormal() throws ParseException {
        String sDate1="30/07/1999";
        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        IPerson pers = fact.newPersonne("Jean", "Corentin", "teillet.corentin@neuf.fr",
                "nono", "azerty", date);
        assertEquals(pers.getSurname(), "Corentin");
        assertEquals(pers.getName(), "Jean");
        assertEquals(pers.getMail(), "teillet.corentin@neuf.fr");
        assertEquals(pers.getUsername(), "nono");
        assertEquals(pers.getPassword(), "azerty");
    }

    @Test
    void TestSeulementParametresObligatoires() {
        IPerson pers = fact.newPersonne("teillet.corentin@neuf.fr",
                "nono", "azerty");
        assertEquals(pers.getSurname(), "");
        assertEquals(pers.getName(), "");
        assertEquals(pers.getMail(), "teillet.corentin@neuf.fr");
        assertEquals(pers.getUsername(), "nono");
        assertEquals(pers.getPassword(), "azerty");
        assertNull(pers.getBirthDate());
    }


}
