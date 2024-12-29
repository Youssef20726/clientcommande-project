import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class Test {
    public static void main(String[] args) {
        try {
            // Test the database connection
            Connection conn = DB.connect();
            System.out.println("Database connection successful!");
            
            // Étape 1 : Création d'un client
            Client client = new Client("C001", "RS Client 1", "123 Rue Principale", "12345678");
            System.out.println("Test Client: " + client.getCodeClient() + ", " + client.getRs());

            //Étape 2 : Création d'une commande
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Commande commande = new Commande("CMD001","C001","2024-12-20", 100.0, 118.0);

            // Étape 3 : Création d'un article
            Article article = new Article("A001", "Ordinateur", 2, 920.0);
            article.afficheArticle(client.getCodeClient(), "2024-12-20");
            Article article2 = new Article("B007", "Souris", 1, 30.0);
            article.afficheArticle(client.getCodeClient(), "2024-12-20");
            Article article3 = new Article("C001", "Ecran", 2, 700.0);
            article.afficheArticle(client.getCodeClient(), "2024-12-20");



            // Étape 4 : Appel des méthodes de l'interface Operation
            commande.Commander("29-12-2024", "A001", 3);
            commande.Prix_Commande(118.0, "Cent dix-huit Dinars");

            // Étape 5 : Création de l'article dans la base de données
            ArticleDb articleDb = new ArticleDb();
            ArticleDb articleDb2 = new ArticleDb();
            ArticleDb articleDb3 = new ArticleDb();
            articleDb.createArticle(article);
            articleDb.createArticle(article2);
            articleDb.createArticle(article3);
            //articleDb.deleteArticle("C001");
            //articleDb.deleteArticle("B007");
            //articleDb.deleteArticle("A001");
            //System.out.println("Article supprimer avec succès!");
            //System.out.println("Article créé avec succès !");
            Commande commande1 = new Commande("C001","CL001", "2023-10-01", 1000.00, 1200.00);
        Commande commande2 = new Commande("C002","CL002","2023-10-02", 700.00, 840.00);

        // Display commandes
        System.out.println("\nCommandes:");
        commande1.Affiche_Commande("C001");
        commande2.Affiche_Commande("C002");

        CommandeDb commandeDb1 = new CommandeDb();
        CommandeDb commandeDb2 = new CommandeDb();
        try {
            commandeDb1.EnregistrerCommande(commande1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            commandeDb2.EnregistrerCommande(commande2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fetch and display commandes for a specific client from the database
        System.out.println("\nCommandes for client CL001:");
        Commande.Affiche_Commandes("CL001");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}