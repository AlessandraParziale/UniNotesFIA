package Storage.Utente;

import Application.util.Observer;
import Storage.ConPool;
import Storage.Libretto.LibrettoBean;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe implementata per le operazioni sul database.
 */
public class UtenteDao {
    /**
     * Metodo per salvare un nuovo Utente (UtenteBean).
     * @param utente Nuovo utente da inserire new DataBase.
     * @return true se è stato salvato, false altrimenti.
     * @throws SQLException
     */
   public UtenteBean doSave(UtenteBean utente) throws SQLException {
       try(Connection con = ConPool.getConnection()) {
           PreparedStatement ps = con.prepareStatement(
                   "INSERT INTO Utente(nome, cognome, cf, email, dataDiNascita, password, tipo, idLibretto) VALUES(?,?,?,?,?,?,?,?,?)",
                   Statement.RETURN_GENERATED_KEYS);

           ps.setString(1, utente.getNome());
           ps.setString(2, utente.getCognome());
           ps.setString(3, utente.getCf());
           ps.setString(4, utente.getEmail());
           ps.setObject(5, utente.getDdn());
           ps.setString(6, utente.getPassword());
           ps.setBoolean(7, utente.isTipo());
           ps.setInt(8, utente.getLibretto().getIdLibretto());

           if (ps.executeUpdate() != 1) {
               throw new RuntimeException("INSERT error.");
           }
           ResultSet rs = ps.getGeneratedKeys();
           rs.next();
           int id = rs.getInt(1);
           utente.setIdUtente(id);
           return utente;

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

    /**
     * Metodo per salvare la previsione di una magistrale nel DB
     * @param utente
     * @return un utente che si è fatto predire una futura magistrale
     * @throws SQLException
     */

    public UtenteBean salvaMagistrale(UtenteBean utente) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente(magistrale) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, utente.getMagistrale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            utente.setIdUtente(id);
            return utente;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ritirna tutti gli utenti presenti nel Database.
     * @return Lista di tutti gli utenti presenti nel Datobase.
     */

   public ArrayList<UtenteBean> doRetriveAll(){
       ArrayList<UtenteBean> utenti = new ArrayList<>();
       try (Connection con = ConPool.getConnection()) {
           PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente u");
           ResultSet rs = ps.executeQuery();
           UtenteExtractor ue = new UtenteExtractor();

           while (rs.next()) {
               UtenteBean u = ue.extract(rs);
               utenti.add(u);
           }
           return utenti;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

    /**
     * Questo metodo ritorna un Utente che ha id(id).
     * @param id id dell'utente da ritornare.
     * @return il Utente con id(id).
     */
       public UtenteBean doRetriveById ( int id){
           try (Connection con = ConPool.getConnection()) {
               PreparedStatement ps =
                       con.prepareStatement("SELECT * FROM Utente u WHERE u.id = ?");

               ps.setInt(1, id);

               ResultSet rs = ps.executeQuery();
               UtenteBean u = new UtenteBean();
               UtenteExtractor ue = new UtenteExtractor();
               if (rs.next()) {

                   u = ue.extract(rs);

               }
               return u;
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

       }

    /**
     * Elimina un Utente con id(id).
     * @param id id dell'utente da eliminare.
     */

    public void doDelate(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Utente  WHERE id = ?",

                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, id);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doUpdate(UtenteBean u) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Utente SET nome = ?, cognome = ?, cf = ?, email = ?, dataDiNascita= ?, tipo = ? WHERE id = " + u.getIdUtente());

            ps.setString(1,u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getCf());
            ps.setString(4, u.getEmail());
            ps.setObject(5, u.getDdn());
            ps.setBoolean(6, u.isTipo());


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Modifica un utente presente sul database.
     * @param u l'utente con i parametri modificati.
     * @return true se è stato modificato, false altrimenti.
     */
    public boolean doUpdateAdmin(UtenteBean u) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Utente SET tipo = "+!u.isTipo()+" WHERE id ="+ u.getIdUtente());


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UtenteBean findAccount(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente u WHERE u.email = ? AND u.password = ?");

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            UtenteExtractor ue = new UtenteExtractor();
            UtenteBean u = null;

            if (rs.next()) {
                u = ue.extract(rs);
            }
            if(u!=null){
                u.setLibretto(new LibrettoBean(rs.getInt("u.idLibretto"),null));
                u.getLibretto().setNunEsami(0);
            }
            if(u==null){
                return null;
            }
            con.close();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
