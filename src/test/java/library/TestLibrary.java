package library;

import com.teillet.bibliothequeElement.interfaces.library.ILibrary;
import com.teillet.bibliothequeElement.interfaces.library.IElements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.teillet.bibliothequeElement.utils.Factory;

public class TestLibrary {
    private final Factory factory = Factory.getFact();
    private final ILibrary library = factory.newLibrary();

    @Test
    public void TestAjout(){
        IElements el = factory.newFilm("Bonjour", "c:/Bonjour.mp4");
        library.add(el);
        Assertions.assertEquals(library.size(), 1);
    }

    @Test
    public void TestClear(){
        IElements el = factory.newFilm("Bonjour", "c:/Bonjour.mp4");
        library.add(el);
        Assertions.assertEquals(library.size(), 1);
        library.clear();
        Assertions.assertEquals(library.size(), 0);
    }

    @Test
    public void TestEmpty1(){
        Assertions.assertTrue(library.isEmpty());
        Assertions.assertEquals(library.size(), 0);
    }

    @Test
    public void TestEmpty(){
        Assertions.assertTrue(library.isEmpty());
        IElements el = factory.newFilm("Bonjour", "c:/Bonjour.mp4");
        library.add(el);
        Assertions.assertEquals(library.size(), 1);
        library.clear();
        Assertions.assertEquals(library.size(), 0);
    }

    @Test
    public void TestContains(){
        IElements el = factory.newFilm("Bonjour", "c/Bonjour.mp4");
        library.add(el);
        System.out.println("Taille : " + library.size());
        Assertions.assertEquals(1, library.size());
        Assertions.assertTrue(library.contains(el));
    }

}
