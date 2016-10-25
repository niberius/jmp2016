import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mongodb.morphia.Datastore;
import org.zoltor.db.impl.NotesDaoImpl;
import org.zoltor.pojo.Note;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Pavel_Ardenka on 25/10/2016.
 * TODO WIP
 */
@RunWith(MockitoJUnitRunner.class)
public class NotesDaoImplTest {

    @Mock
    private NotesDaoImpl notesDao;

    @Before
    public void setUp() {
        Note note = new Note();
        note.setNoteText("aaa");
        Note note2 = new Note();
        note2.setNoteText("bbb");
        List<Note> notes = new ArrayList<>();
        notes.add(note);
        notes.add(note2);
        when(notesDao.getByTag("ba")).thenReturn(notes);
    }

    @Test
    public void testBlaba() throws Exception {
        System.out.println(notesDao.getByTag("ba"));
        System.out.println(notesDao.getByTag("aaba"));
    }

}
