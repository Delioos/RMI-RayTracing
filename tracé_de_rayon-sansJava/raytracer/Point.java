package raytracer;
import java.io.*;

/**
 * Classe qui représente un point dans un espace 3D
 */
public class Point implements Serializable{

	/**
	 * coordonnée x
	 */
	protected float x;

	/**
	 * coordonnée y
	 */
	protected float y;

	/**
	 * coordonnée z
	 */
	protected float z; /* coordonnées du point */
    
	/** crée un point de coordonnées (0,0,0)
	 */
	public Point(){
		x = y = z = 0.0f;
	}
    
	/** crée un point de coordonnées (x, y, z)
	 * @param x l'abscisse du point
	 * @param y l'ordonnée du point
	 * @param z la hauteur du point
	 */
	public Point(float x, float y, float z){
		this.x = x; this.y = y; this.z = z;
	}
    
	/** crée un point dont les coordonnées sont les mêmes que celles du point
	 * passé en paramètre
	 * @param p le point à "copier"
	 */
	public Point(Point p){
		x = p.x; y = p.y; z = p.z;
	}
    
	void set(Point p){
		x  = p.x; y = p.y; z = p.z;
	}
    
	void set(float x, float y, float z){
		this.x = x; this.y = y; this.z = z;
	}
    
	/**
	 * teste l'égalité de 2 points 3D. La comparaison s'effectue coordonnée
	 * par coordonnée.
	 * @param o le point avec lequel faire la comparaison
	 * @return true si les deux points sont égaux, false sinon
	 */
	public boolean equals(Object o){
		/* sd */
		if (!(o instanceof Point)) return false;
		Point p = (Point)o;
		/* sd */
		if(x != p.x) return false;
		if(y != p.y) return false;
		if(z != p.z) return false;
		return true;
	}
    
	/**
	 * Retourne une chaîne de caractères qui représente les coordonnées
	 * du point.
	 * Les coordonnées sont affichées suivant le format (x,y,z).
	 * @return Coordonnées du point. Format : (x,y,z).
	 */
	public String toString(){
		return("("+x+","+y+","+z+")");
	}
    
}// Point
