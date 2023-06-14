import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Serveur 
{
    public static void main(String[] args) throws RemoteException {
        
            ServiceCentral service = new ServiceCentral();
            ServiceDistributeur distrib = (ServiceDistributeur)UnicastRemoteObject.exportObject(service, 0);

            Registry registry = LocateRegistry.createRegistry(1098);
            registry.rebind("Distributeur", distrib);
            System.out.println()
    }
}