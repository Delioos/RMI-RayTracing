import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceDistributeur extends Remote {

    public void enregistrerClient(ServiceCalculateur c) throws RemoteException;

    public ServiceCalculateur recupererCalculateur() throws RemoteException;

    public void supprimerCalculateur(ServiceCalculateur c) throws RemoteException;

}