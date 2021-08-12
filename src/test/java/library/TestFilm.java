package library;

import com.teillet.bibliothequeElement.interfaces.library.IFilm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.teillet.bibliothequeElement.utils.Factory;

import java.util.ArrayList;
import java.util.List;

public class TestFilm {

    @Test
    public void TestFilmCreation(){
        List<String> acteurs = new ArrayList<>();
        Factory factory = Factory.getFact();
        IFilm film = factory.newFilm("Bonjour", "c:/bonjour.mp4", "Spielberg", "1:01", acteurs);

        Assertions.assertEquals(film.getTitle(), "Bonjour");
        Assertions.assertEquals(film.getPath(), "c:/bonjour.mp4");
        Assertions.assertEquals(film.getDirector(), "Spielberg");
        Assertions.assertEquals(film.getDuration(), "1:01");
        Assertions.assertEquals(film.getActors().size(), 0);
    }

}
