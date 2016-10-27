import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FieldEnd;
import org.mongodb.morphia.query.Query;
import org.zoltor.db.MongoServer;
import org.zoltor.db.impl.NotesDaoImpl;
import org.zoltor.pojo.Note;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Pavel_Ardenka on 25/10/2016.
 * TODO Use PowerMock to moc MongoServer to prevent MongoDB initialization?
 */
@RunWith(MockitoJUnitRunner.class)
public class NotesDaoImplTest {

    @InjectMocks
    private NotesDaoImpl notesDao = new NotesDaoImpl();

    @Mock
    private Datastore dataStore;

    @Mock
    private Query<Note> query;

    @Mock
    private FieldEnd fieldEnd;

    // Text arguments for notes
    private static final String NOTE_TAG_FOR_SEARCH = "tag1";
    private static final String NOTE_TEXT_FOR_SEARCH = "note2";
    private static final String NOTE_TEXT_OR_TAG_FOR_SEARCH = "tag";

    // DB field names
    private static final String TAG_FIELD = "tag";
    private static final String NOTE_TEXT_FIELD = "noteText";
    private static final Date CURRENT_TIMESTAMP = new Date();

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        // Mock create query for each DAO method
        when(dataStore.createQuery(Note.class)).thenReturn(query);
        when(fieldEnd.containsIgnoreCase(anyString())).thenReturn(query);
        when(query.search(anyString())).thenReturn(query);

        // Mock field() method for each query
        when(query.field(anyString())).thenReturn(fieldEnd);
    }

    @Test
    public void testGetByTag() throws Exception {
        when(query.field(TAG_FIELD).containsIgnoreCase(NOTE_TAG_FOR_SEARCH).asList())
                .thenReturn(Collections.singletonList(getTestNote1()));
        List<Note> noteFoundByTag = notesDao.getByTag(NOTE_TAG_FOR_SEARCH);
        List<Note> expectedNotes = Collections.singletonList(getTestNote1());
        assertEquals(expectedNotes, noteFoundByTag);
    }

    @Test
    public void testGetByText() throws Exception {
        when(query.field(NOTE_TEXT_FIELD).containsIgnoreCase(NOTE_TEXT_FOR_SEARCH).asList())
                .thenReturn(Collections.singletonList(getTestNote2()));
        List<Note> noteFoundByText = notesDao.getByText(NOTE_TEXT_FOR_SEARCH);
        List<Note> expectedNotes = Collections.singletonList(getTestNote2());
        assertEquals(expectedNotes, noteFoundByText);
    }

    @Test
    public void testGetByTagOrText() throws Exception {
        List<Note> mockedNotes = Arrays.asList(getTestNote1(), getTestNote2(), getTestNote3());
        when(query.search(NOTE_TEXT_OR_TAG_FOR_SEARCH).asList())
                .thenReturn(mockedNotes);
        List<Note> noteFoundByTagOrText = notesDao.getByTagOrText(NOTE_TEXT_OR_TAG_FOR_SEARCH);
        List<Note> expectedNotes = Arrays.asList(getTestNote1(), getTestNote2(), getTestNote3());
        assertEquals(expectedNotes, noteFoundByTagOrText);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Note> mockedNotes = Arrays.asList(getTestNote1(), getTestNote2(), getTestNote3());
        when(dataStore.createQuery(Note.class).asList())
                .thenReturn(mockedNotes);
        List<Note> allNotes = notesDao.getAll();
        List<Note> expectedNotes = Arrays.asList(getTestNote1(), getTestNote2(), getTestNote3());
        assertEquals(expectedNotes, allNotes);
    }

    @Test
    public void testGetById() throws Exception {
        ObjectId noteId = new ObjectId();
        Note mockedNote = getTestNote1();
        mockedNote.setId(noteId);
        when(dataStore.get(Note.class, noteId))
                .thenReturn(mockedNote);
        Note noteFoundByid = notesDao.getById(noteId);
        Note expectedNote = getTestNote1();
        expectedNote.setId(noteId);
        assertEquals(expectedNote, noteFoundByid);
    }

    @Test
    public void testSave() throws Exception {
        final List<Note> mockedNotes = new ArrayList<>();
        mockedNotes.add(getTestNote1());
        final Note mockedNoteToSave = getTestNote2();
        when(dataStore.save(mockedNoteToSave)).then(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                mockedNotes.add(mockedNoteToSave);
                return null;
            }
        });
        notesDao.save(mockedNoteToSave);
        List<Note> expectedNotes = Arrays.asList(getTestNote1(), getTestNote2());
        assertEquals(expectedNotes, mockedNotes);
    }

    @Test
    public void testDelete() throws Exception {
        final List<Note> mockedNotes = new ArrayList<>();
        mockedNotes.add(getTestNote1());
        mockedNotes.add(getTestNote2());
        final Note mockedNoteToDelete = getTestNote2();
        when(dataStore.delete(mockedNoteToDelete)).then(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                mockedNotes.remove(mockedNoteToDelete);
                return null;
            }
        });
        notesDao.delete(mockedNoteToDelete);
        List<Note> expectedNotes = Collections.singletonList(getTestNote1());
        assertEquals(expectedNotes, mockedNotes);
    }

    private Note getTestNote1() {
        return getTestNote("tag1", "Test Note1");
    }

    private Note getTestNote2() {
        return getTestNote("tag2", "Test Note2");
    }

    private Note getTestNote3() {
        return getTestNote("bla", "Test Note tag3");
    }

    private Note getTestNote(String noteTag, String noteText) {
        Note note = new Note();
        note.setTag(noteTag);
        note.setNoteText(noteText);
        note.setTimeStamp(CURRENT_TIMESTAMP);
        return note;
    }

}
