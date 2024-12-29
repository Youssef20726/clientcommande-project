public class Client {
    private String CodeClient ;
    private String Rs;
    private String Adresse;
    private String telephone;
    
    public Client(String CodeClient,String Rs,String Adresse,String telephone){
        this.CodeClient = CodeClient;
        this.Rs = Rs;
        this.Adresse = Adresse;
        this.telephone =telephone;
    }
    public Client(){
        this.CodeClient = " ";
        this.Rs = " ";
        this.Adresse = " ";
        this.telephone = " ";
    }
    
    //getters
    public String getCodeClient(){
        return CodeClient;
    }
    public String getRs(){
        return Rs;
    }
    public String getAdresse(){
        return Adresse;
    }
    public String gettelephone(){
        return telephone;
    }

    //setters
    public void SetCodeClient(String CodeClient){
        this.CodeClient = CodeClient;
    }
    public void SetRs(String Rs){
        this.Rs = Rs;
    }
    public void SetAdresse(String Adresse){
        this.Adresse = Adresse;
    }
    public void Settelephone(String telephone){
        this.telephone = telephone;
    }

}