import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.String; // Pour String (souvent automatique)
import java.text.SimpleDateFormat; // Pour SimpleDateFormat

public class Commande implements Operation{
    private String NumCmd;
    private String CodeClient;
    private String date;
    private double TotalHT;
    private double TotalTTC;
    
    
    public Commande(String NumCmd,String CodeClient,String date,double TotalHT,double TotalTTC){
        this.NumCmd = NumCmd ;
        this.CodeClient = CodeClient;
        this.date = date ; 
        this.TotalHT = TotalHT;
        this.TotalTTC = TotalTTC;
    }

    public Commande(){
        this.NumCmd = " " ;
        this.CodeClient = " ";
        this.date = null ; 
        this.TotalHT = 0.0;
        this.TotalTTC = 0.0;
    }

    public String getNumCmd(){
        return NumCmd;
    }
    public String getdate(){
        return date;
    }
    public double  getTotalHT(){
        return TotalHT;
    }
    public double  getTotalTTC(){
        return TotalTTC;
    }
    public String getCodeClient(){
        return CodeClient;
    }

    //setters
    public void SetNumCmd(String NumCmd){
        this.NumCmd = NumCmd;
    }
    public void Setdate(String date){
        this.date = date;
    }
    public void SetTotalHT(double TotalHT){
        this.TotalHT = TotalHT;
    }
    public void SetTotalTTC(double TotalTTC){
        this.TotalTTC = TotalTTC;
    }
    public void setCodeCLient(String CodeClient){
        this.CodeClient = CodeClient;
    }

    public void Affiche_Commande(String CodeClient){
        System.out.println("numero de la commande :" + getNumCmd() + "Date : " + getdate() + " Prix TTC : " + getTotalTTC() );
    }
    
    @Override
    public void Commander(String Date_Cd,String CodeArticle,int Qte){
        System.out.println("la commande du " + Date_Cd + " de l'aricle " + CodeArticle + "avec quantité" + Qte);
    }
    @Override
    public void Prix_Commande(double PrixTTC ,String Prixlettre){
        System.out.println("PrixTTC : " + PrixTTC + ", Prix en lettres : " + Prixlettre);
    }
    public static void Affiche_Commandes(String CodeClient) {

        String query = "SELECT numcmd, date, totalht, Totalttc FROM commande WHERE CodeClient = ?";

        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, CodeClient);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String NumCmd = rs.getString("NumCmd");
                String date = rs.getString("date");
                double TotalHT = rs.getDouble("TotalHT");
                double TotalTTC = rs.getDouble("TotalTTC");
                Commande commande = new Commande(NumCmd, CodeClient, date, TotalHT, TotalTTC);
                commande.Affiche_Commande(CodeClient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

