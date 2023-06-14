package raytracer;
import java.util.Iterator;

public class Intersection extends Point{

	// attributs
	private Primitive objet; // référence à l'objet intersecté 
	private float t; // "distance" paramétrique depuis l'origine du rayon
    
	/** crée une intersection vide
	 */
	public Intersection(){ 
		objet = null;
	}

	/** crée une intersection. L'objet correspondant contient les coordonnées
			du point d'intersection, l'objet sur lequel se trouve ce point et la
			distance paramétrique à laquelle se trouve ce point
			par rapport à l'origine du rayon qui l'a généré.
			* @param x l'abscisse du point d'intersection
			* @param y l'ordonnée du point d'intersection
			* @param z la hauteur du point d'intersection
			* @param objet l'objet intersecté
			* @param t la distance paramétrique de cet objet par rapport à l'origine
			du rayon
	*/
	public Intersection(float x, float y, float z, Primitive objet, float t){
		super(x, y, z);
		this.objet = objet;
		this.t = t;
	}

	/** compare deux intersections. Le test de comparaison s'effectue sur la
	 * distance paramétrique des deux intersections comparées.
	 * @param i est l'intersection avec laquelle l'objet doit être comparé
	 * @return 0 si les deux intersections sont à même distance de l'origine
	 * du rayon ; un nombre positif si l'intersection i est plus proche de 
	 * l'origine du rayon que l'objet courant ; un nombre négatif sinon.
	 */
	public int compareTo(Intersection i){
		if(i==null){
			return -1;
		}
		if(t<i.t){
			return -1;
		}
		if(t>i.t){
			return 1;
		}
		return 0;
	}

	/** fournit l'objet sur lequel se trouve l'intersection courante.
	 * @return l'objet intersecté
	 */
	public Primitive getObjet(){
		return objet;
	}
	
	/** fournit la distance paramétrique à laquelle se trouve l'objet intersecté
	 * par le rayon.
	 * @return la distance paramétrique
	 */
	public float getDistance(){
		return t;
	}


	/** Calcule l'éclairage du point d'intersection courant par la méthode du
	 * lancer de rayons. Seuls l'éclairage direct (rayons d'ombrage) et
	 * l'éclairage par réflexion sont pris en compte dans cette fonction.
	 * Le paramètre niveau permet de régler la profondeur de l'arbre des rayons.
	 * @param s la scène utilisée
	 * @param obs la position de l'observateur
	 * @param niveau le nombre maximum de niveaux de réflexion qui doit être
	 * pris en compte
	 * @param px l'abscisse du pixel en cours de calcul
	 * @param py l'ordonnée du pixel en cours de calcul
	 * @return l'éclairage calculé
	 */ 
	public Intensite eclairer(Scene s, Point obs, int niveau,
														int px, int py){
		Point inter = new Point(x, y, z);
		Intensite i = new Intensite(0.0f, 0.0f, 0.0f);

		// lancer les rayons d'ombrage
		Iterator it = s.sourcesIterator();
		while(it.hasNext()){
			Source source = (Source)it.next();
			if(!s.coupe(inter, source.getPosition())){
				i.add(objet.computeSourceContribution(inter, source, obs));
			}else{
				i.add(objet.computeSourceAmbientContribution(source));
			}
		}


		// lancer l'éclairage par réflexion et / ou par réfraction
		if(objet.isSpecular() && (niveau > 0)){

			// éclairage par réflexion
			Vecteur incident = new Vecteur(inter, obs);
			Rayon ref = objet.reflechi(inter, incident);
			Intersection intRef = s.intersecte(ref);

			if(intRef != null){ 

				i.add(intRef.eclairer(s, new Point(this.x, this.y, this.z),
															niveau-1, px, py));
	    
			}//else  rajouter la couleur de fond	

		}

		//lancer l'éclairage par réfraction
		if(objet.transmet() && (niveau > 0)){
			// éclairage par réfraction
			float n1,n2;
			Vecteur incident = new Vecteur(inter, obs);
			n1=1.0f;
			n2=1.0f;
			Rayon trans = objet.refracte(inter, incident,n1, n2);
			Intersection intTrans = s.intersecte(trans);
			if(intTrans != null){ 
				i.add(intTrans.eclairer(s, new Point(this.x, this.y, this.z),
																niveau-1, px, py));
			}
		}
		return i;
	} // eclairer

	public String toString(){
		return("intersection en "+super.toString()+" avec "+objet);
	}

}// Intersection
