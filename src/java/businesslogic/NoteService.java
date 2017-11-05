package businesslogic;

import dataaccess.NoteDB;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.util.List;

/**
 *
 * @author 463849
 */
public class NoteService {
    
    private NoteDB noteDB;
    
    public NoteService() {
        noteDB = new NoteDB();
    }
    
    public Note get(int noteId) throws NotesDBException {
    
        return noteDB.getNote(noteId);
    
    }
    
    public List<Note> getAll() throws NotesDBException {
    
        return noteDB.getAll();
    
    }
    
    public int update(int noteId, String contents) throws NotesDBException {
    
        Note note = new Note(noteId, contents);
        
        return noteDB.update(note);
    
    }
    
    public int delete(int noteId) throws NotesDBException {
    
        Note note = noteDB.getNote(noteId);
        
        return noteDB.delete(note);
    
    }
    
    public int insert(String contents) throws NotesDBException {
    
        Note note = new Note(contents);
        
        return noteDB.insert(note);
        
    }
    
}
