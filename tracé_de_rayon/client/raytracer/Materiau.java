package raytracer;
import java.io.*;
/** Classe définissant les caractéristiques d'un matériau. Ces caractéristiques
 * sont adaptées au modèle de réflexion de Phong et incluent les coefficients de
 * réflexion (ambiante, diffuse et spéculaire) pour chaque longueur d'onde
 * considérée, et le coefficient de spécularité.
 */
public class Materiau implements Serializable{

	// attributs d'un materiau
	private float[] Ka, Kd, Ks;
	private float coeffSpec;

	/** Construction d'un matériau par défaut. Les longueurs d'onde considérées
	 * sont au nombre de 3 (rouge, vert et bleu) et les coefficients sont
	 * initialisés de manière identique pour les 3 longueurs d'onde : 0.2 pour
	 * la réflexion ambiante, 0.8 pour la réflexion diffuse et 0.0 pour la
	 * réflexion spéculaire. Le coefficient spéculaire est également nul.
	 */
	public Materiau(){
	
		Ka = new float[3];
		Kd = new float[3];
		Ks = new float[3];

		for(int i=0; i<3; i++){
			Ka[i]=0.2f; Kd[i]=0.8f; Ks[i]=0.0f;
		}
		coeffSpec = 0.0f;
	}

	/** Construction d'un matériau spécifique. Les valeurs des différents
	 * coefficients de réflexion sont données sous forme de tableaux, supposés
	 * de taille 3 (rouge, vert, bleu).
	 * @param amb tableau contenant les coefficients de réflexion ambiant
	 * pour le rouge, le vert et le bleu
	 * @param dif tableau contenant les coefficients de réflexion diffus
	 * pour le rouge, le vert et le bleu
	 * @param dif tableau contenant les coefficients de réflexion spéculaire
	 * pour le rouge, le vert et le bleu
	 * @param coeffSpec le coefficient de spécularité du matériau
	 */
	public Materiau(float[] amb, float[] dif, float[] spec, float coeffSpec){
	
		Ka = new float[3];
		Kd = new float[3];
		Ks = new float[3];

		for(int i=0; i<3; i++){
			Ka[i]=amb[i]; Kd[i]=dif[i]; Ks[i]=spec[i];
		}

		this.coeffSpec = coeffSpec; 
	}

	// méthodes d'instance
	/** Fournit les coefficients de réflexion ambiante.
	 * @return un tableau de taille 3, dont les cases représentent
	 * respectivement les coefficients de réflexion ambiante pour le rouge,
	 * le vert et le bleu
	 */
	public float[] getAmbient(){
		float[] t = new float[3];

		t[0]=Ka[0]; t[1]=Ka[1]; t[2]=Ka[2];

		return t;
	}

	/** Fournit les coefficients de réflexion diffuse.
	 * @return un tableau de taille 3, dont les cases représentent
	 * respectivement les coefficients de réflexion diffuse pour le rouge,
	 * le vert et le bleu
	 */
	public float[] getDiffuse(){
		float[] t = new float[3];

		t[0]=Kd[0]; t[1]=Kd[1]; t[2]=Kd[2];

		return t;
	}

	/** Fournit les coefficients de réflexion spéculaire.
	 * @return un tableau de taille 3, dont les cases représentent
	 * respectivement les coefficients de réflexion spéculaire pour le rouge,
	 * le vert et le bleu
	 */
	public float[] getSpecular(){
		float[] t = new float[3];

		t[0]=Ks[0]; t[1]=Ks[1]; t[2]=Ks[2];

		return t;
	}
	/** Fournit le coefficient de spécularité du matériau
	 * @return le coefficient de spécularité du matériau
	 */
	public float getCoeffSpec(){ return coeffSpec;}

	/** Permet de déterminer si le matériau comporte une composante spéculaire.
	 * Ce test est effectué en vérifiant que l'un des facteurs de réflexion
	 * spéculaire est non nul.
	 * @return vrai si le matériau comporte une composante spéculaire, faux sinon
	 */
	public boolean isSpecular(){
		return ((Ks[0] != 0.0f)||(Ks[1] != 0.0f)||(Ks[2] != 0.0f));
	}
	public boolean transmet(){
		return false;
	}


	/**
	 * Retourne une chaîne de caractères qui représente le matériau.
	 * @return Une chaîne de caractères qui représente le matériau.
	 * Les coefficients de réflextion ambiants, diffus et spéculaire
	 * sont affichés
	 */
	public String toString(){
		String varLoc = "Coef. de réflexion ambiante : \n";
		for (int i=0; i<Ka.length; i++){
			varLoc += Ka[i]+" ; ";
		}

		varLoc += "\n Coef. de réflexion diffuse : \n";
		for (int i=0; i<Kd.length; i++){
			varLoc += Kd[i]+" ; ";
		}	
	
		varLoc += "\n Coef. de réflexion spéculaire : \n";
		for (int i=0; i<Ks.length; i++){
			varLoc += Ks[i]+" ; ";
		}
	
		varLoc += "\n Coef. de spécularité : "+coeffSpec;
		return varLoc;
	}

}// Materiau
