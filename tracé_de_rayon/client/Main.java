import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.ServerNotActiveException;
import raytracer.*; 


public class Main{
    public static void main(String[] args) throws RemoteException, NotBoundException, ServerNotActiveException{

        // on passe en paramètre l'adresse, le port et le nombre de division de l'image
        if(args.length != 6){
            System.out.println("Usage : java Main [adresse service central] [port service central] [fichier image] [nbDivision] [largeur] [hauteur]");
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

        int largeur = 512, hauteur = 512;




        try{

            // on se connecte à l'annuaire du service central
            Registry reg = LocateRegistry.getRegistry(adresse, port);
            // on recupere le service à grace à son nom
            // et on crée une instance de classe à l'aide de ce service, en le castant à l'aide de l'interface partagée.
            ServiceDistributeur distributeur = (ServiceDistributeur) reg.lookup("Distributeur");



            // on charge la scène
            System.out.println("Chargement de la scène...");
            Scene scene = new Scene(fichier, largeur, hauteur);
            Disp disp = new Disp("Raytracer", largeur, hauteur);

            System.out.println("Scène chargée");

            // Calculer les dimensions de chaque division
            final int divLargeur = largeur / nbDivision;
            final int divHauteur = hauteur / nbDivision;


            System.out.println(divLargeur);
            // on fait un thread et on récupère des services de calculs (autant qu'il y'a de nbDivision)
                // distributeur.recupererCalculateur() 
                // on calcule une image grace au calculateur courant 
                // on affiche l'image sur la scene 

                for (int i = 0; i < nbDivision; i++) {
                    final int divisionIndex = i;

                     // Créer un nouveau thread pour chaque division
                    new Thread(() -> {
                            // Récupérer un service calculateur auprès du distributeur
                            ServiceCalculateur calculateur = distributeur.recupererCalculateur();
                            int x = divLargeur * divisionIndex;
                            int y = divHauteur * divisionIndex;
                            System.out.println("x : " + x );
                            System.out.println("y : " + y );
                            // Calculer une image à l'aide du calculateur courant
                            Image image = calculateur.calculer(scene, x, y, divLargeur, divHauteur);

                            // Afficher l'image sur la scène
                            disp.setImage(image, x, y);
                        
    }).start();

    
}
            


            

        }catch(RemoteException e){
            e.printStackTrace(); 
        }catch(NotBoundException e){
            e.printStackTrace();
        }



    }



}