import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.ServerNotActiveException;



public class Main{
    public static void main(String[] args) throws RemoteException, NotBoundException, ServerNotActiveException{

        // on passe en paramètre l'adresse, le port et le nombre de division de l'image
        if(args.length != 4){
            System.out.println("Usage : java Main [adresse service central] [port service central] [fichier image] [nbDivision]");
            System.exit(1);
        }

        // on récupère les paramètres
        // ip du serveur centrale
        String adresse = args[0];
        // port de l'annuaire du serveur centrale
        int port = Integer.parseInt(args[1]);

        String fichier = args[2]; 
        // nombre de division de l'image
        int nbDivision = Integer.parseInt(args[3]);



        try{

            // on se connecte à l'annuaire du service central
            Registry reg = LocateRegistry.getRegistry(adresse, port);
            // on recupere le service à grace à son nom
            // et on crée une instance de classe à l'aide de ce service, en le castant à l'aide de l'interface partagée.
            ServiceDistributeur distributeur = (ServiceDistributeur) reg.lookup("distributeur");



            // on charge la scène
            System.out.println("Chargement de la scène...");
            Scene scene = new Scene(fichier, 512, 512);
            System.out.println("Scène chargée");

            // Largeur et hauteur de chaque division
            int divLargeur = scene.getLargeur() / nbDivision;
            int divHauteur = scene.getHauteur() / nbDivision;

            // on fait un thread et on récupère des services de calculs (autant qu'il y'a de nbDivision)
                // distributeur.recupererCalculateur() 
                // on calcule une image grace au calculateur courant 
                // on affiche l'image sur la scene 

                for (int i = 0; i < nbDivision; i++) {
                    int divisionIndex = i;

                     // Créer un nouveau thread pour chaque division
                    Thread thread = new Thread(() -> {
                        try {
                            // Récupérer un service calculateur auprès du distributeur
                            ServiceCalculateurr calculateur = distributeur.recupererCalculateur();
                            int x = 0+divLargeur*i; 
                            int y = 0+divHauteur*i; 
                            // Calculer une image à l'aide du calculateur courant
                            Image image = calculateur.calculerImage(scene, x, y, divLargeur, divHauteur);

                            // Afficher l'image sur la scène
                            disp.setImage(image, x0, y0);
                        } catch (RemoteException e) {
                            // Gérer l'exception RemoteException
                        }
    });

    
    // Démarrer le thread
    thread.start();
}
            


            

        }catch(RemoteException e){
        }catch(NotBoundException e){
        }



    }



}