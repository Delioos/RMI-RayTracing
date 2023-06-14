package raytracer;
import java.io.*;

public class Vecteur implements Serializable{

	// attributs
	private float dx, dy, dz; /* direction du vecteur */

	/** crée un vecteur de direction (0,0,-1)
	 */
	public Vecteur(){ 
		dx = 0.0f;
		dy = 0.0f;
		dz = -1.0f;
	}
	/** crée un vecteur de direction (dx,dy,dz)
	 * @param x l'abscisse de la direction du nouveau vecteur
	 * @param y l'ordonnée de la direction du nouveau vecteur
	 * @param z la hauteur de la direction du nouveau vecteur
	 */
	public Vecteur(float dx, float dy, float dz){
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}

	/** crée un vecteur de direction p1p2
	 * @param v1 l'origine du nouveau vecteur
	 * @param v2 l'extrémité du nouveau vecteur
	 */
	public Vecteur(Point p1, Point p2){
		this.dx = p2.x - p1.x; 
		this.dy = p2.y - p1.y;
		this.dz = p2.z - p1.z;
	}

	/** crée un vecteur de même direction que celle du vecteur v
	 * @param v le vecteur servant à l'initialisation du nouveau vecteur
	 */
	Vecteur(Vecteur v){
		this.dx = v.dx;
		this.dy = v.dy; 
		this.dz = v.dz;
	}

	/** fournit l'abscisse du vecteur 
	 * @return la coordonnée x du vecteur
	 */
	public float getX(){ return dx;}

	/** fournit l'ordonnée du vecteur 
	 * @return la coordonnée y du vecteur
	 */
	public float getY(){ return dy;}

	/** fournit la hauteur du vecteur 
	 * @return la coordonnée z du vecteur
	 */
	public float getZ(){ return dz;}


	public String toString(){
		return(":->("+dx+","+dy+","+dz+")");
	}

	/** normalise le vecteur courant
	 */
	public void normalise(){
		float norme = (float)Math.sqrt(dx*dx + dy*dy +dz*dz);
		if(norme !=0.0){
			dx /= norme;
			dy /= norme;
			dz /= norme;
		}
	}

	/** calcule la norme du vecteur courant
	 * @return la norme du vecteur courant
	 */
	public float norme(){
		return (float)Math.sqrt(dx*dx + dy*dy +dz*dz);
	}

	/** calcule le produit scalaire entre le vecteur courant et le vecteur passé
	 * en paramètre.
	 * @param v le vecteur avec lequel effectuer le produit scalaire.
	 * @return le produit scalaire this . v
	 */
	public float produitScalaire(Vecteur v){
		return dx*v.dx + dy*v.dy + dz*v.dz;
	}

	/** calcule le produit vectoriel entre le vecteur courant et le vecteur passé
	 * en paramètre. Le sens du produit est le suivant : this x v
	 * @param v le vecteur intervenant dans le produit vectoriel.
	 * @return le vecteur perpendiculaire à this et à v
	 */
	public Vecteur produitVectoriel(Vecteur v){
		return new Vecteur(dy*v.dz - dz*v.dy,
											 dz*v.dx - dx*v.dz,
											 dx*v.dy - dy*v.dx);
	}

	/** inverse la direction du vecteur courant
	 */
	public void neg(){
		dx = -dx;
		dy = -dy;
		dz = -dz;
	}

	/** effectue la somme du vecteur courant et du vecteur passé en paramètre
	 * @param v le vecteur à ajouter au vecteur courant.
	 */	
	public void add(Vecteur v){
		dx += v.dx;
		dy += v.dy;
		dz += v.dz;
	}

	/** effectue la différence entre le vecteur courant et le vecteur passé
	 * en paramètre. Le sens de la soustraction est le suivant : this - v.
	 * @param v le vecteur à soustraire du vecteur courant.
	 */
	public void sub(Vecteur v){
		dx -= v.dx;
		dy -= v.dy;
		dz -= v.dz;
	}

	/** multiplie le vecteur courant par le scalaire passé en paramètre.
	 * @param f le scalaire par lequel multiplier les composantes du vecteur
	 * courant.
	 */
	public void multiply(float f){
		dx *= f;
		dy *= f;
		dz *= f;
	}

	/** divise le vecteur courant par le scalaire passé en paramètre. Le sclaire
	 * doit être différent de 0, sous peine de déclencher une exception.
	 * @param f le scalaire par lequel diviser les composantes du vecteur courant.
	 */
	public void div(float f){
		dx /= f;
		dy /= f;
		dz /= f;
	}
	/** teste l'égalité de objets instances de vecteur 3D. La comparaison
	 * s'effectue coordonnée par coordonnée.
	 * @param v le vecteur avec lequel faire la comparaison
	 * @return true si les deux vecteurs sont égaux, false sinon
	 * @author Samuel
	 */
	public boolean equals(Object o){	
		if (!(o instanceof Vecteur)) return false;
		Vecteur v = (Vecteur)o;

		if( dx != v.dx )
			return false;
		if( dy != v.dy )
			return false;
		if( dz != v.dz )
			return false;
		return true;
	}

}// Vecteur
