import java.rmi.Remote;

public interface ServiceDistributeur extends Remote {

    public void enregistrerClient(ServiceCalculateur c);

    public ServiceCalculateur recupererCalculateur();

    public void supprimerCalculateur(ServiceCalculateur c);

}