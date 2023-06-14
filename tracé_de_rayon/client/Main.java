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

        //nombre de division doit etre un carré parfait


        // on récupère les paramètres
        // ip du serveur centrale
        String adresse = args[0];
        // port de l'annuaire du serveur centrale
        int port = Integer.parseInt(args[1]);

        String fichier = args[2]; 
        // nombre de division de l'image
        int nbDivision = Integer.parseInt(args[3]);

        int largeur = Integer.parseInt(args[4]), hauteur = Integer.parseInt(args[5]);


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

            // Calculer le nombres de divisions
            int [] meilleureOptions = trouverMeilleureSolution(nbDivision);
            int nbLignes = meilleureOptions[0];
            int nbColonnes = meilleureOptions[1]; 
                
            int divHauteur = hauteur/nbLignes;
            int divLargeur = largeur/nbColonnes;

            // on fait un thread et on récupère des services de calculs (autant qu'il y'a de nbDivision)
                // distributeur.recupererCalculateur() 
                // on calcule une image grace au calculateur courant 
                // on affiche l'image sur la scene 

            for (int j =0; j<nbLignes; j++){
                for (int i = 0; i < nbColonnes; i++) {
                    int indexI = i;
                    int indexJ = j;
                     // Créer un nouveau thread pour chaque division
                    new Thread(() -> {
                            //tant que réaliser = false
                            boolean realiser=false;
                            while(!realiser){
                                
                                    // Récupérer un service calculateur auprès du distributeur
                                    ServiceCalculateur calculateur = distributeur.recupererCalculateur();
                                    int x = divLargeur * indexI;
                                    int y = divHauteur * indexJ;
                                    
                                try{
                                    // Calculer une image à l'aide du calculateur courant
                                    Image image = calculateur.calculer(scene, x, y, divLargeur, divHauteur);

                                    // Afficher l'image sur la scène
                                    disp.setImage(image, x, y);

                                    realiser = true;
                                    System.out.println(" Division : " + (nbColonnes*indexJ + indexI) +" construite !");

                                }catch(NullPointerException e){
                                    try{
                                        System.out.println("en attente d'un service ...");
                                        Thread.sleep(10000);
                                    }catch (InterruptedException except){}
                                }
                                catch(Exception e){
                                    distributeur.supprimerCalculateur(calculateur);
                                }
                            }
                    }).start();         
                }
            }


            

        }catch(RemoteException e){
            e.printStackTrace(); 
        }catch(NotBoundException e){
            e.printStackTrace();
        }



    }

    public static int[] trouverMeilleureSolution(int n) {
        if (n <= 0) {
            System.out.println("Le nombre de parties doit être supérieur à zéro.");
            return null;
        }
        
        int meilleureDifference = Integer.MAX_VALUE; // Initialiser avec une valeur maximale
        int[] meilleureSolution = new int[2];
        
        // Parcourir tous les diviseurs du nombre
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                int diviseur1 = i;
                int diviseur2 = n / i;
                
                int difference = Math.abs(diviseur1 - diviseur2);
                
                // Mettre à jour la meilleure solution si la différence est minimale
                if (difference < meilleureDifference) {
                    meilleureDifference = difference;
                    meilleureSolution[0] = diviseur1;
                    meilleureSolution[1] = diviseur2;
                }
            }
        }
        
        return meilleureSolution;
    }



}