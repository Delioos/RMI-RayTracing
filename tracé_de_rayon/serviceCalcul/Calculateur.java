import raytracer.Scene;
import raytracer.Image;
import java.rmi.RemoteException;
import java.rmi.server.*;

public class Calculateur implements ServiceCalculateur {

    public Image calculer(Scene sc, int x0, int y0, int  l, int h) throws RemoteException {

        String clientHost ="";
        try {
        clientHost = RemoteServer.getClientHost();
        System.out.println("+++++++++++++++++ je suis utilisé par l'hôte : " + clientHost);
        } catch (ServerNotActiveException e) {
            System.err.println("Erreur lors de l'obtention de l'adresse de l'hôte client : " + e);
        }
        Image img = sc.compute(x0, y0, l, h);
        
        if(clientHost!="")
            System.out.println("----------------- mon utilisation par "+clientHost+" est terminée.");

        return img;
    }

}