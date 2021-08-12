package library;

import com.teillet.bibliothequeElement.interfaces.library.IElements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.teillet.bibliothequeElement.utils.Factory;

public class TestElements {
    private final Factory fact = Factory.getFact();

    @Test
    public void TestElementsCreation(){
        IElements elements = fact.newFilm("Bonjour", "c:/bonjour.mp4");
        Assertions.assertEquals(elements.getTitle(), "Bonjour");
        Assertions.assertEquals(elements.getPath(), "c:/bonjour.mp4");
    }

}
