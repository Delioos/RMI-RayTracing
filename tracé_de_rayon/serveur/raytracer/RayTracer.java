package raytracer;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.*;


public class RayTracer implements Serializable {
    private int width;
    private int height;
    
    // constructeur 
    public RayTracer(int w, int h){
	
	width  = w;
	height = h;
    }
    
    // calcul d'un imagette (x0,y0) (x0+w, y0+h) 
    public Image compute(Scene sc, int x0, int y0, 
		  int w, int h, int nr, int d){
	
	Intensite in;
	Point dir;
	float largeurPixel = 2.0f/width;
	float hauteurPixel = 2.0f/height;
	float aleaX, aleaY;
	float milieuX, milieuY;
		
	Point obs = new Point(0.0f, 0.0f, 1.5f);

	// création de l'image 
	Image image = new Image(w, h);
	int x=0, y=0; 
	for(y=y0; y<y0+h; y++){
	    for(x=x0; x<x0+w; x++){

		// création d'un intensité nulle
		in = new Intensite();
		for(int n=0; n<nr; n++){
		    if(nr==1){
			// calcul du milieu du pixel (si nb_rayon=1)
			milieuX = -1.0f + (x+0.5f)*largeurPixel;
			milieuY =  1.0f - (y+0.5f)*hauteurPixel;
			dir = new Point(milieuX, milieuY, 0.0f);
		    }else{
			// calcul d'une position aléatoire dans le pixel
			aleaX = -1.0f + (x+(float)Math.random())*largeurPixel;
			aleaY =  1.0f - (y+(float)Math.random())*hauteurPixel;
			dir = new Point(aleaX, aleaY, 0.0f);
		    }
				
		    // générer le rayon primaire			 
		    Rayon ray = new Rayon(obs, dir);

		    // calculer l'intersection du rayon primaire avec la scene
		    Intersection inter = sc.intersecte(ray);

		    // lancer le calcul d'eclairage du point d'intersection
		    if(inter!=null)
			in.add(inter.eclairer(sc, obs, d, x, y));
		}// for n
		
		// clacule de la couleur moyenne du pixel
		in.div(nr);

		// affecter le pixel a l'image
		image.setPixel(x-x0, y-y0, in.getColor());
		
		
	    }
	    
	}
	
	return image;
    }
}

    

