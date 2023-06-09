package raytracer;
import java.io.*;
/** Classe permettant la représentation d'une source ponctuelle. Une source
 * est caractérisée par sa position dans l'espace et ses valeurs d'intensité 
 * selon les composantes RVB.
 */
public class Source implements Serializable {

	private Intensite intensite;
	private Point position;

	/** Construit une source par défaut. La source est positionnée en (0,0,1)
	 * et son intensité est fixée à (1.0,1.0,1.0).
	 */
	public Source(){
		intensite = new Intensite(1.0f, 1.0f, 1.0f);
		position = new Point(0.0f, 0.0f, 1.0f);
	}

	/** Construit une source positionnée en p. Son intensité est fixée par défaut
	 * à (1.0,1.0,1.0).
	 * @param p la position à laquelle doit être placée la source
	 */
	Source(Point p){
		intensite = new Intensite(1.0f, 1.0f, 1.0f);
		position = new Point(p);
	}
	/** Construit une source en précisant son intensité et sa position.
	 * @param i l'intensité de la source
	 * @param p la position à laquelle doit être placée la source
	 */
	public Source(Intensite i, Point p){
		intensite = new Intensite(i);
		position = new Point(p);
	}

	public Intensite getIntensity(){ return intensite;}

	public Point getPosition() { return position;}

	public String toString(){
		return "source en "+position+" : intensite="+intensite;
	}
}//Source
