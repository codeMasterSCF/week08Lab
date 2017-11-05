package domainmodel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 463849
 */
public class Note implements Serializable {
    
    private int noteId;
    private java.util.Date dateCreated;
    private String contents;

    public Note() {
    }

    public Note(int NoteId, Date dateCreated, String contents) {
        this.noteId = NoteId;
        this.dateCreated = dateCreated;
        this.contents = contents;
    }

    public Note(String contents)
    {
        this.dateCreated = new Date();
        this.contents = contents;
    }
    
    public Note(int noteId, String contents)
    {
        this.noteId = noteId;
        this.dateCreated = new Date();
        this.contents = contents;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int NoteId) {
        this.noteId = NoteId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
  
}
