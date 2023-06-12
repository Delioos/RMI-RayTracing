import java.util.ArrayList;
import java.rmi.RemoteException;


public class ServiceCentral implements ServiceDistributeur
{
    private ArrayList<ServiceCalculateur> listesServices = new ArrayList<>();
    private int index = 0;

    public void enregistrerClient(ServiceCalculateur c)
    {
        this.listesServices.add(c);
    }

    public ServiceCalculateur recupererCalculateur(){
        if (listesServices.size() == 0)
            return null;
        if (index >= listesServices.size()-1)
            index = 0;
        else {
            index ++;
        }
        return listesServices.get(index);
    }

    public void supprimerCalculateur(ServiceCalculateur c){
        this.listesServices.remove(c);
    }

}