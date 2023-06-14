import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.server.*;


public class ServiceCentral implements ServiceDistributeur
{
    private ArrayList<ServiceCalculateur> listesServices = new ArrayList<>();
    private int index = 0;

    public void enregistrerClient(ServiceCalculateur c) throws RemoteException
    {   
        try {
            String clientHost = RemoteServer.getClientHost();
            System.out.println("Ajout du service calculateur : " + clientHost);
        } catch (ServerNotActiveException e) {
            System.err.println("Erreur lors de l'obtention de l'adresse de l'hôte client : " + e);
        }
        this.listesServices.add(c);
    }

    public ServiceCalculateur recupererCalculateur() throws RemoteException {
        if (listesServices.size() == 0)
            return null;
        if (index >= listesServices.size()-1)
            index = 0;
        else {
            index ++;
        }
        return listesServices.get(index);
    }

    public void supprimerCalculateur(ServiceCalculateur c) throws RemoteException{
        this.listesServices.remove(c);
        System.out.println("service : "+ c +" supprimé");
    }

}