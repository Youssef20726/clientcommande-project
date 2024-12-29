import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleDb {

    public static int createArticle(Article article) throws SQLException {
        String sql = "INSERT INTO Article (CodeArticle, Libelle, Qte, PrixHT) VALUES (?, ?, ?, ?)";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, article.getCodeArticle());
            stmt.setString(2, article.getLibelle());
            stmt.setInt(3, article.getQte());
            stmt.setDouble(4, article.getPrixHT());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error creating article: " + e.getMessage(), e); 
        }
                return 0;
    }

    public void updateArticle(Article article) throws SQLException {
        String sql = "UPDATE Article SET Libelle = ?, Qte = ?, PrixHT = ? WHERE CodeArticle = ?";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, article.getLibelle());
            stmt.setInt(2, article.getQte());
            stmt.setDouble(3, article.getPrixHT());
            stmt.setString(4, article.getCodeArticle());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating article: " + e.getMessage(), e);
        }
    }

    public void deleteArticle(String codeArticle) throws SQLException {
        String sql = "DELETE FROM Article WHERE CodeArticle = ?";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codeArticle);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error deleting article: " + e.getMessage(), e);
        }
    }

    public Article getArticle(String codeArticle) throws SQLException {
        String sql = "SELECT * FROM Article WHERE CodeArticle = ?";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codeArticle);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Article(
                            rs.getString("CodeArticle"),
                            rs.getString("Libelle"),
                            rs.getInt("Qte"),
                            rs.getDouble("PrixHT")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error retrieving article: " + e.getMessage(), e);
        }
        return null; 
    }
}