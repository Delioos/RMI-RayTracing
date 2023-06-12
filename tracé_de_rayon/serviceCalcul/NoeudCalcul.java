import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.rmi.server.UnicastRemoteObject;


public class NoeudCalcul {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Scanner sc = new Scanner(System.in);

        try
        {
            String nom;
            String port;
            if(args.length<2){
                System.out.println("veuillez écrire votre host :");
                nom = sc.nextLine();
                System.out.println("veuillez saisir votre port pour le service:");
                port = sc.nextLine();

                if(port=="")
                    port="1099";
                if(nom=="")
                    nom="localhost";
            }else{
                nom=args[0];
                port=args[1];
            }
            
            Registry reg = LocateRegistry.getRegistry(nom,Integer.parseInt(port));

              System.out.println(reg.list().length);
            for (String e : reg.list()) {
                System.out.println(e);
            }

            ServiceDistributeur distrib = (ServiceDistributeur)reg.lookup("Distributeur");
            Calculateur calcul = new Calculateur();

            ServiceCalculateur service = (ServiceCalculateur) UnicastRemoteObject.exportObject(calcul,0);
            distrib.enregistrerClient(service);

        }
        catch(UnknownHostException e)
        {
            System.out.println("l'host est inconnu");
        }
        catch(InputMismatchException e)
        {
            System.out.println("le port est mauvais");
        }
        catch(NotBoundException e)
        {
            System.out.println("le service est inexistant");
        }
        catch(ConnectException e)
        {
            System.out.println("Problème de connexion");
        }
    }

}