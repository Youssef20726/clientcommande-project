import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeDb {

    public void EnregistrerCommande(Commande commande) throws SQLException {
        String query = "INSERT INTO commande (numcmd, codeclient, date, totalHT, totalTTC) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, commande.getNumCmd());
            stmt.setString(2, commande.getCodeClient());
            stmt.setString(3, commande.getdate());
            stmt.setDouble(4, commande.getTotalHT());
            stmt.setDouble(5, commande.getTotalTTC());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error creating commande: " + e.getMessage(), e);
        }
    }
    public Commande getCommande(String codeClient) throws SQLException {
        String sql = "SELECT * FROM commande WHERE codeclient = ?";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codeClient);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Commande(
                            rs.getString("numcmd"),
                            rs.getString("codeclient"),
                            rs.getString("date"), // Convert to LocalDate
                            rs.getDouble("totalht"),
                            rs.getDouble("totalttc")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving commande: " + e.getMessage(), e);
        }

        return null;
    }
    
    public void deleteCommande(String numcmd) throws SQLException {
        String sql = "DELETE FROM commande  WHERE numcmd = ?";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numcmd);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error deleting article: " + e.getMessage(), e);
        }
    }
   
    
}