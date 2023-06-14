import raytracer.Scene;
import raytracer.Image;

public class Calculateur implements ServiceCalculateur {

    public Image calculer(Scene sc, int x0, int y0, int  l, int h){
        return sc.compute(x0, y0, l, h);
    }

}