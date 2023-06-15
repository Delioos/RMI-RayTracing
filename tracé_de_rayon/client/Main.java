import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.server.ServerNotActiveException;
import raytracer.*; 
import java.lang.Math;


public class Main{
    public static void main(String[] args) throws RemoteException, NotBoundException, ServerNotActiveException{

        // on passe en paramètre l'adresse, le port et le nombre de division de l'image
        if(args.length != 6){
            System.out.println("Usage : java Main [adresse service central] [port service central] [fichier image] [nbDivision par coté] [largeur(entier)] [hauteur(entier)]");
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
            int divHauteur = hauteur / nbDivision;
            int divLargeur = largeur / nbDivision;
            
            System.out.println(nbDivision+" colonnes et lignes de respectivement : "+divLargeur+" ; "+divHauteur + "\n donc finalement : "+divHauteur*nbDivision+"*"+divLargeur*nbDivision);

            // on fait un thread et on récupère des services de calculs (autant qu'il y'a de nbDivision)
                // distributeur.recupererCalculateur() 
                // on calcule une image grace au calculateur courant 
                // on affiche l'image sur la scene 

            
            for (int j =0; j<nbDivision; j++){
                for (int i = 0; i < nbDivision; i++) {
                    int indexI = i;
                    int indexJ = j;
                     // Créer un nouveau thread pour chaque division
                    new Thread(() -> {
                            //tant que réaliser = false
                            boolean realiser=false;
                            boolean recurrent = false;
                            while(!realiser){
                                try{
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
                                        System.out.println(" Division : " + (nbDivision*indexJ + indexI) +" construite !");

                                    }catch(NullPointerException e){
                                        try{
                                            System.out.println("en attente d'un service ... pour frame "+(nbDivision*indexJ + indexI));
                                            Thread.sleep(10000);
                                        }catch (InterruptedException except){}
                                    }
                                    catch(Exception e){
                                            try{
                                                distributeur.supprimerCalculateur(calculateur);
                                            }catch(IndexOutOfBoundsException except){}
                                    }
                                }catch(Exception err){
                                    try{
                                            System.out.println("en attente du serveur");
                                            Thread.sleep(10000);
                                        }catch (InterruptedException except){}
                                }
                            }
                    }).start();         
                }


            }
            
            // completer le dessin si affichage disproportionné : bidouillage pour bon affichage
            
            if(nbDivision*divLargeur<largeur){
                terminerDessin(scene, disp, nbDivision*divLargeur , nbDivision*divHauteur, largeur, hauteur, nbDivision, true, distributeur, divHauteur, divLargeur);
            }
            if(nbDivision*divHauteur<hauteur){
                terminerDessin(scene, disp, nbDivision*divLargeur , nbDivision*divHauteur, largeur, hauteur, nbDivision, false, distributeur, divHauteur, divLargeur);
            }
            if(nbDivision*divHauteur<hauteur && nbDivision*divLargeur<largeur)
                terminerDessinAngle(scene, disp, nbDivision*divLargeur , nbDivision*divHauteur, largeur, hauteur, nbDivision, distributeur, divHauteur, divLargeur);

            

        }catch(RemoteException e){
            e.printStackTrace(); 
        }catch(NotBoundException e){
            e.printStackTrace();
        }

    }

    public static void terminerDessin(Scene scene, Disp disp, int  x, int  y, int largeur , int hauteur , int nbDivision, boolean L, ServiceDistributeur distributeur , int divHauteur, int divLargeur){
                for (int i = 0; i < nbDivision; i++) {
                    int indexI = i;
                     // Créer un nouveau thread pour chaque division
                    new Thread(() -> {
                            //tant que réaliser = false
                            boolean realiser=false;
                            boolean recurrent = false;
                            while(!realiser){
                                try{
                                    // Récupérer un service calculateur auprès du distributeur
                                    ServiceCalculateur calculateur = distributeur.recupererCalculateur();
                                    int y0,x0, l, h;
                                    if(L){
                                        l = largeur-x;
                                        h = divHauteur;
                                        x0 = x;
                                        y0 = divHauteur * indexI; 
                                    }else{
                                        l = divLargeur;
                                        h = hauteur-y;
                                        x0 = divHauteur * indexI;
                                        y0 = y;
                                    }
                                    try{
                                        // Calculer une image à l'aide du calculateur courant
                                        Image image = calculateur.calculer(scene, x0, y0, l, h);

                                        // Afficher l'image sur la scène
                                        disp.setImage(image, x0, y0);

                                        realiser = true;
                                        System.out.println(" Division de complétion construite !");

                                    }catch(NullPointerException e){
                                        try{
                                            System.out.println("en attente d'un service ... pour frame de complétion");
                                            Thread.sleep(10000);
                                        }catch (InterruptedException except){}
                                    }
                                    catch(Exception e){
                                            try{
                                                distributeur.supprimerCalculateur(calculateur);
                                            }catch(IndexOutOfBoundsException except){}
                                    }
                                }catch(Exception err){
                                    try{
                                            System.out.println("en attente du serveur");
                                            Thread.sleep(10000);
                                        }catch (InterruptedException except){}
                                }
                            }
                    }).start();         
                }
    }

public static void terminerDessinAngle(Scene scene, Disp disp, int  x, int  y, int largeur , int hauteur , int nbDivision, ServiceDistributeur distributeur , int divHauteur, int divLargeur){
                     // Créer un nouveau thread pour chaque division
                    new Thread(() -> {
                            //tant que réaliser = false
                            boolean realiser=false;
                            boolean recurrent = false;
                            int l = largeur-x;
                            int h = hauteur-y;
                            while(!realiser){
                                try{
                                    // Récupérer un service calculateur auprès du distributeur
                                    ServiceCalculateur calculateur = distributeur.recupererCalculateur();
                                    try{
                                        // Calculer une image à l'aide du calculateur courant
                                        Image image = calculateur.calculer(scene, x, y, l, h);

                                        // Afficher l'image sur la scène
                                        disp.setImage(image, x, y);

                                        realiser = true;
                                        System.out.println(" Division de complétion construite !");

                                    }catch(NullPointerException e){
                                        try{
                                            System.out.println("en attente d'un service ... pour frame de complétion");
                                            Thread.sleep(10000);
                                        }catch (InterruptedException except){}
                                    }
                                    catch(Exception e){
                                            try{
                                                distributeur.supprimerCalculateur(calculateur);
                                            }catch(IndexOutOfBoundsException except){}
                                    }
                                }catch(Exception err){
                                    try{
                                            System.out.println("en attente du serveur");
                                            Thread.sleep(10000);
                                        }catch (InterruptedException except){}
                                }
                            }
                    }).start();         
    }

}