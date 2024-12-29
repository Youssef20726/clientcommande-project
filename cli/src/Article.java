import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Article {
    private String CodeArticle;
    private String Libelle;
    private int Qte;
    private double PrixHT;

    public Article(String CodeArticle,String Libelle,int Qte,double PrixHT){
        this.CodeArticle = CodeArticle;
        this.Libelle = Libelle;
        this.Qte = Qte;
        this.PrixHT = PrixHT;
    }

    public Article(){
        this.CodeArticle = "";
        this.Libelle = "";
        this.Qte = 0;
        this.PrixHT = 0.0;
    }

    //getters
    public String getCodeArticle(){
        return CodeArticle;
    }
    public String getLibelle(){
        return Libelle;
    }
    public int getQte(){
        return Qte;
    }
    public double getPrixHT(){
        return PrixHT;
    }

    //setters
    public void SetCodeArticle(String CodeArticle){
        this.CodeArticle = CodeArticle;
    }
    public void SetLibelle(String Libelle){
        this.Libelle = Libelle;
    }
    public void SetQte(int Qte){
        this.Qte = Qte;
    }
    public void SetPrixHT(double PrixHT){
        this.PrixHT = PrixHT;
    }
    
    public void afficheArticle(String codeClient, String date) {
        System.out.println("Article: " + getLibelle() + ", Qte: " + getQte() + ", Total HT: " + (getQte() * getPrixHT()));
    }
    public void Affiche_Article(String codeClient, String date) {

        String query = "SELECT a.Libelle, c.Qte, (c.Qte * a.PrixHT) AS TotalHT " +
                       "FROM Commande cmd " +
                       "JOIN Contenir c ON cmd.NumCmd = c.NumCmd " +
                       "JOIN Articles a ON c.CodeArticle = a.CodeArticle " +
                       "WHERE cmd.CodeClient = ? AND cmd.DateCmd = ?";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, codeClient);
            stmt.setString(2, date);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String libelle = rs.getString("Libelle");
                    int quantite = rs.getInt("Qte");
                    double totalHT = rs.getDouble("TotalHT");

                    System.out.println("Article: " + libelle + ", Qte: " + quantite + ", Total HT: " + totalHT);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}