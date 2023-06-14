package raytracer;

import java.awt.Color;
import java.io.*;

public class Intensite implements Serializable {

	private float rouge, vert, bleu;

	/** crée une intensité nulle
	 */
	public Intensite(){
		rouge = vert = bleu = 0.0f;
	}

	/** crée une intensité. L'intensité de chaque longueur d'onde (R,V,B) doit
	 * être positive ou nulle. En cas de valeur négative, celle-ci est remplacée
	 * par une valeur nulle.
	 * @param r valeur (réelle) de l'intensité pour la longueur d'onde du rouge
	 * @param v valeur (réelle) de l'intensité pour la longueur d'onde du vert
	 * @param b valeur (réelle) de l'intensité pour la longueur d'onde du bleu
	 */
	public Intensite(float r, float v, float b){
		rouge = (r<0.0f) ? 0.0f : r;
		vert  = (v<0.0f) ? 0.0f : v;
		bleu  = (b<0.0f) ? 0.0f : b;
	}

	/** crée une intensite. L'intensité fournie dans le paramètre i est copiée 
	 * dans l'objet courant.
	 * @param i l'intensite à utiliser pour l'initialisation de la nouvelle
	 * intensité.
	 */
	public Intensite(Intensite i){
		rouge = i.rouge;
		vert  = i.vert;
		bleu  = i.bleu;
	}

	/** fournit la valeur de l'intensité pour la longueur d'onde du rouge.
	 * La valeur obtenue se trouve dans l'intervalle [0,1]. Si la valeur stockée
	 * dans l'objet est supérieure à 1, la valeur retournée vaudra 1.
	 * @return l'intensité pour la longueur d'onde du rouge.
	 */ 
	public float getRed(){
		return (rouge>1.0f) ? 1.0f : rouge;
	}

	/** fournit la valeur de l'intensité pour la longueur d'onde du vert.
	 * La valeur obtenue se trouve dans l'intervalle [0,1]. Si la valeur stockée
	 * dans l'objet est supérieure à 1, la valeur retournée vaudra 1.
	 * @return l'intensité pour la longueur d'onde du vert.
	 */ 
	public float getGreen(){ 
		return (vert>1.0f) ? 1.0f : vert;
	}

	/** fournit la valeur de l'intensité pour la longueur d'onde du bleu.
	 * La valeur obtenue se trouve dans l'intervalle [0,1]. Si la valeur stockée
	 * dans l'objet est supérieure à 1, la valeur retournée vaudra 1.
	 * @return l'intensité pour la longueur d'onde du bleu.
	 */ 
	public float getBlue(){
		return (bleu>1.0f) ? 1.0f : bleu;
	}

	/** Convertit la valeur de l'intensité courante sous forme d'une instance
	 * de la classe Color.
	 * @return la représentation de l'intensité de l'objet courant sous forme
	 * d'une couleur de la classe Color.
	 */
	public Color getColor(){
		return new Color(getRed(), getGreen(), getBlue());
	}

	/** additionne une intensité à l'intensité courante. L'intensité à ajouter
			est fournie sous forme d'une valeur pour chaque longueur d'onde (R,V,B).
			* @param r valeur de l'intensité à ajouter pour la longueur d'onde du 
			*rouge
			* @param v valeur de l'intensité à ajouter pour la longueur d'onde du
			* vert
			* @param b valeur de l'intensité à ajouter pour la longueur d'onde du 
			* bleu
			*/
	public void add(float r, float v, float b){
		rouge += (r>=0.0f) ? r : 0.0f;
		vert  += (v>=0.0f) ? v : 0.0f;
		bleu  += (b>=0.0f) ? b : 0.0f;
	}

	/**
	 * additionne une intensité à l'intensité courante. L'intensité à ajouter
	 * est fournie sous forme d'une autre intensité.
	 * @param i intensité à ajouter
	 */
	public void add(Intensite i){
		this.add(i.rouge, i.vert, i.bleu);
	}
	
	/** divise l'intensité courante par un scalaire. Le résultat obtenu est
	 * une intensité nulle si le scalaire est 0.
	 * @param v le scalaire par lequel diviser l'intensité courante
	 */
	public void div(float v){
		if(v!=0.0f){
			rouge /= v;
			vert /= v;
			bleu /= v;
		}else{
			rouge = vert = bleu = 0.0f;
		}
	}

	/**
	 * retourne une chaîne de caractères qui décrit une intensité.
	 * @return décrit l'intensité.
	 */
	public String toString(){
		return "("+rouge+","+vert+","+bleu+")";
	}
    
	/**
	 * teste l'égalité de 2 intensités. La comparaison s'effectue longueur
	 * d'onde par longueur d'onde.
	 * @param o l'intensité avec laquel faire la comparaison.
	 * @return true si les deux intensités sont égales, false sinon.
	 */
	public boolean equals(Object o){
		/* sd */
		if (!(o instanceof Intensite)){
			return false;
		}
		Intensite in = (Intensite)o;
		/* sd */
		if(rouge != in.rouge){
			return false;
		}
		if(vert != in.vert){
			return false;
		}
		if(bleu != in.bleu){
			return false;
		}
		return true;
	}

}// Intensite
