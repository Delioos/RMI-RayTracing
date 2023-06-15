package raytracer;
/**
 * Classe qui représente un rayon. 
 * Un rayon est défini par son origine (un point) et sa direction (un vecteur).
 */

public class Rayon {

	// attributs
	private Point origine; /* origine du rayon */
	private Vecteur direction; /* direction du rayon */
	private float index;/* index de réfraction du milieu*/

	/** crée un rayon d'origine (0,0,0), de direction (0,0,-1)
	 * et d'index 1.0f
	 */
	public Rayon(){ 
		origine = new Point();
		direction = new Vecteur(0.0f, 0.0f, -1.0f);
		index = 1.0f;
	}

	/** crée un rayon d'origine (x,y,z), de direction (dx,dy,dz),
	 * et d'index 1.0f
	 * @param x l'abscisse de l'origine du rayon
	 * @param y l'ordonnée de l'origine du rayon
	 * @param z la côte de l'origine du rayon
	 * @param dx abscisse du vecteur direction du rayon
	 * @param dy ordonnée du vecteur direction du rayon
	 * @param dz côte du vecteur direction du rayon
	 */
	public Rayon(float x, float y, float z, float dx, float dy, float dz){
		origine = new Point(x,y,z);
		direction = new Vecteur(dx,dy,dz);
		index = 1.0f;
	}

	/** crée un rayon d'origine (x,y,z), de direction (dx,dy,dz),
	 * et d'index donné
	 * @param x l'abscisse de l'origine du rayon
	 * @param y l'ordonnée de l'origine du rayon
	 * @param z la côte de l'origine du rayon
	 * @param dx abscisse du vecteur direction du rayon
	 * @param dy ordonnée du vecteur direction du rayon
	 * @param dz côte du vecteur direction du rayon
	 * @param i indice de réfraction du milieu contenant le rayon
	 */
	public Rayon(float x, float y, float z, float dx, float dy, float dz,
							 float i){
		origine = new Point(x,y,z);
		direction = new Vecteur(dx,dy,dz);
		index = i;
	}

	/** crée un rayon d'origine le point p1, de direction le vecteur p1p2
	 * et d'index 1.0f
	 * @param p1 origine du rayon
	 * @param p2 point vers lequel se dirige le rayon
	 */
	public Rayon(Point p1, Point p2){
		origine = new Point(p1);
		direction = new Vecteur(p1, p2);
		index = 1.0f;
	}

	/** crée un rayon d'origine le point p1, de direction le vecteur p1p2
	 * et d'index donné
	 * @param p1 origine du rayon
	 * @param p2 point vers lequel se dirige le rayon
	 * @param i  indice de réfraction du milieu contenant le rayon
	 */
	public Rayon(Point p1, Point p2, float i){
		origine = new Point(p1);
		direction = new Vecteur(p1, p2);
		index = i;
	}

	/** crée un rayon d'origine le point p1, de direction le vecteur v
	 * et d'index 1.0f
	 * @param p1 origine du rayon
	 * @param v vecteur direction du rayon.
	 */
	public Rayon(Point p1, Vecteur v){
		origine = new Point(p1);
		direction = new Vecteur(v);
		index = 1.0f;
	}

	/** crée un rayon d'origine le point p1, de direction le vecteur v
	 * et d'index donné
	 * @param p1 origine du rayon
	 * @param v vecteur direction du rayon.
	 * @param i indice de réfraction du milieu contenant le rayon
	 */
	public Rayon(Point p1, Vecteur v, float i){
		origine = new Point(p1);
		direction = new Vecteur(v);
		index = i;
	}


	/** fournit la direction du rayon
	 * @return le vecteur direction
	 */
	public Vecteur getDirection(){
		return new Vecteur(direction);
	}

	/** fournit l'origine du rayon
	 * @return l'origne du rayon
	 */
	public Point getOrigine(){
		return new Point(origine);
	}
	
	/** fournit l'index du rayon
	 * @return l'indice de réfraction du milieu dans lequel se trouve le rayon
	 */
	public float getIndex(){
		return index;
	}
	/**
	 * Retourne une chaîne de caractères qui représente un rayon. 
	 * Le point d'origine suivi de son vecteur direction sont retournés. 
	 * La méthode toString de Point et de Vecteur est appelée.
	 * @return Une chaîne de caractères qui représente un rayon.
	 */
	public String toString(){
		return("("+origine.x+","+origine.y+","+origine.z+")->("+
					 direction.getX()+","+direction.getY()+","+direction.getZ()+")");
	}

}// Rayon
