package dataaccess;

import domainmodel.Note;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 463849
 */
public class NoteDB {
    
    public int insert(Note note) throws NotesDBException {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            
            trans.begin();
            em.persist(note);
            trans.commit();
            return 1;
            
        } catch (Exception ex) {
            
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new NotesDBException("Error inserting note");
            
        } finally {
            
            em.close();
            
        }
    
    }
    
    public int update(Note note) throws NotesDBException {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
            
        } catch (Exception ex) {
            
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error updating note");
            
        } finally {
            
            em.close();
            
        }
      
    }
    
    public List<Note> getAll() throws NotesDBException {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            
            List<Note> note = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return note;
            
        } catch (Exception ex) {
            
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting notes");
            
        } finally {
            
            em.close();
            
        }
        
        
    }
    
    public Note getNote(int noteId) throws NotesDBException {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            
            Note note = em.find(Note.class, noteId);
            return note;
            
        } catch (Exception ex) {
            
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting notes");
            
        } finally {
            
            em.close();
            
        }
       
    }
    
    public int delete(Note note) throws NotesDBException {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
            return 1;
            
        } catch (Exception ex) {
            
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting note");
            
        } finally {
            
            em.close();
            
        }
    
    }
    
    private static java.sql.Date convertDateToSql(java.util.Date date) {

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        return sqlDate;

    }
    
}
