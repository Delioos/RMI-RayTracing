import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.ServerNotActiveException;



public class Main{
    public static void main(String[] args) throws RemoteException, NotBoundException, ServerNotActiveException{

        // on passe en paramètre l'adresse, le port et le nombre de division de l'image
        if(args.length != 3){
            System.out.println("Usage : java Main [adresse] [port] [nbDivision]");
            System.exit(1);
        }

        // on récupère les paramètres
        // ip du serveur centrale
        String adresse = args[0];
        // port de l'annuaire du serveur centrale
        int port = Integer.parseInt(args[1]);

        // nombre de division de l'image
        int nbDivision = Integer.parseInt(args[2]);



        try{
            // on se connecte à l'annuaire du service contact
            Registry reg = LocateRegistry.getRegistry(adresse, port);
            // on recupere le service à grace à son nom
            // et on crée une instance de classe à l'aide de ce service, en le castant à l'aide de l'interface partagée.
            ServiceDistributeur instance = (ServiceDistributeur) reg.lookup("distributeur");

            // on charge la scène
            System.out.println("Chargement de la scène...");
            Scene scene = new Scene("simple.txt", 512, 512);
            System.out.println("Scène chargée");

            // Largeur et hauteur de chaque division
            int divLargeur = scene.getLargeur() / nbDivision;
            int divHauteur = scene.getHauteur() / nbDivision;

        }catch(RemoteException e){
        }catch(NotBoundException e){
        }



    }



}