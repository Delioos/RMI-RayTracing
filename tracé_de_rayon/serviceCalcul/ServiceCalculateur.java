import raytracer.Scene;
import raytracer.Image;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.ConnectException;


public interface ServiceCalculateur extends Remote{

    public Image calculer(Scene sc, int x0, int y0, int  l, int h) throws RemoteException;

}