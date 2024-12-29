import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField codeArticleField;
    @FXML
    private TextField libelleField;
    @FXML
    private TextField qteField;
    @FXML
    private TextField prixHTField;
    @FXML
    private Button addButton;

    private final ArticleDb articleDb = new ArticleDb();

    // Méthode pour ajouter un article
    @FXML
    public void addArticle(ActionEvent event) {
        try {
            String codeArticle = codeArticleField.getText();
            String libelle = libelleField.getText();
            int qte = Integer.parseInt(qteField.getText());
            double prixHT = Double.parseDouble(prixHTField.getText());

            Article article = new Article(codeArticle, libelle, qte, prixHT);
            articleDb.createArticle(article);

            showAlert("Succès", "Article ajouté avec succès !");
            clearFields();
        } catch (Exception e) {
            showAlert("Erreur", "Impossible d'ajouter l'article : " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour un article
    @FXML
    public void updateArticle(ActionEvent event) {
        try {
            String codeArticle = codeArticleField.getText();
            String libelle = libelleField.getText();
            int qte = Integer.parseInt(qteField.getText());
            double prixHT = Double.parseDouble(prixHTField.getText());

            Article article = new Article(codeArticle, libelle, qte, prixHT);
            if (articleDb.getArticle(codeArticle) == null) {
                showAlert("Information", "Aucun article trouvé avec ce code pour la mise à jour.");
                return;
            }
            articleDb.updateArticle(article);

            showAlert("Succès", "Article mis à jour avec succès !");
            clearFields();
        } catch (Exception e) {
            showAlert("Erreur", "Impossible de mettre à jour l'article : " + e.getMessage());
        }
    }

    // Méthode pour supprimer un article
    @FXML
    public void deleteArticle(ActionEvent event) {
        try {
            String codeArticle = codeArticleField.getText();

            // Vérifier si l'article existe
            Article article = articleDb.getArticle(codeArticle);
            if (article == null) {
                showAlert("Information", "Aucun article trouvé avec ce code.");
                return;
            }

            // Supprimer l'article
            articleDb.deleteArticle(codeArticle);
            showAlert("Succès", "Article supprimé avec succès !");
            clearFields();
        } catch (Exception e) {
            showAlert("Erreur", "Impossible de supprimer l'article : " + e.getMessage());
        }
    }

    // Méthode pour rechercher un article
    @FXML
    public void searchArticle(ActionEvent event) {
        try {
            String codeArticle = codeArticleField.getText();
            Article article = articleDb.getArticle(codeArticle);

            if (article != null) {
                libelleField.setText(article.getLibelle());
                qteField.setText(String.valueOf(article.getQte()));
                prixHTField.setText(String.valueOf(article.getPrixHT()));
            } else {
                showAlert("Information", "Aucun article trouvé avec ce code.");
            }
        } catch (Exception e) {
            showAlert("Erreur", "Impossible de rechercher l'article : " + e.getMessage());
        }
    }

    // Méthode utilitaire pour afficher des alertes
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode utilitaire pour effacer les champs
    private void clearFields() {
        codeArticleField.clear();
        libelleField.clear();
        qteField.clear();
        prixHTField.clear();
    }
}
