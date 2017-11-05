package dataaccess;

import domainmodel.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 463849
 */
public class NoteDB {
    
    public int insert(Note note) throws NotesDBException {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        try {
            
            String preparedInsert = "INSERT INTO Notes "
                    + "(noteId, dateCreated, contents) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedInsert);
            ps.setInt(1, note.getNoteId());
            ps.setDate(2, convertDateToSql(note.getDateCreated()));
            ps.setString(3, note.getContents());
            int insertedRows = ps.executeUpdate();
            
            return insertedRows;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotesDBException("Could not insert note.");
            
        } finally {
            
            pool.freeConnection(connection);
            
        }
    
    }
    
    public int update(Note note) throws NotesDBException {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        String preparedUpdate = "UPDATE Notes SET " 
                + "contents = ? " 
                + "WHERE noteId = ?";
        
        try {
            
            PreparedStatement ps = connection.prepareStatement(preparedUpdate);
            ps.setString(1, note.getContents()); 
            ps.setInt(2, note.getNoteId());
            int updatedRows = ps.executeUpdate();
            
            return updatedRows;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotesDBException("Could not update note.");
            
        } finally {
            
            pool.freeConnection(connection);
            
        }
      
    }
    
    public List<Note> getAll() throws NotesDBException {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = connection.prepareStatement("SELECT * FROM Notes;");
            rs = ps.executeQuery();
            List<Note> notes = new ArrayList<>();
            
            while (rs.next()) {
                
                notes.add(new Note(rs.getInt("noteId"), rs.getDate("dateCreated"), rs.getString("contents")));
                
            }
            
            pool.freeConnection(connection);
            
            return notes;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Could not get notes.");
            
        } finally {
            
            try {
                
                rs.close();
                ps.close();
                
            } catch (SQLException ex) {
            }
            
            pool.freeConnection(connection);
        }
        
        
    }
    
    public Note getNote(int noteId) throws NotesDBException {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
            
        String preparedSingleSelect = "SELECT * FROM Notes WHERE noteId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = connection.prepareStatement(preparedSingleSelect);
            ps.setInt(1, noteId);
            rs = ps.executeQuery();

            Note note = null;
            while (rs.next()) {

                note = new Note(rs.getInt("noteId"), rs.getDate("dateCreated"), rs.getString("contents"));

            }

            return note;

        } catch (SQLException ex) {

            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Could not get note.");

        } finally {

            try {

                rs.close();
                ps.close();

            } catch (SQLException ex) {
            }

            pool.freeConnection(connection);
        }
       
    }
    
    public int delete(Note note) throws NotesDBException {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
    
        try {
            
            String preparedDelete = "DELETE FROM Notes " 
                + "WHERE noteId = ?";
            PreparedStatement ps = connection.prepareStatement(preparedDelete);
            ps.setInt(1, note.getNoteId());
            int rows = ps.executeUpdate();
            
            return rows;
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotesDBException("Could not delete note.");
            
        } finally {
        
            pool.freeConnection(connection);
            
        }
    
    }
    
    private static java.sql.Date convertDateToSql(java.util.Date date) {

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        return sqlDate;

    }
    
}
