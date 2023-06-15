package raytracer;
import java.awt.Color;

/** Classe permettant la représentation d'une sphère 
 */
public class Sphere extends Primitive {

		// attributs spécifiques aux sphères
		private float rayon;
		private Point centre;

		public Sphere(){
				rayon = 1.0f;
				centre = new Point(0.0f, 0.0f, 0.0f);
		}

		public Sphere(float rayon, Point centre, Materiau m) {
				super(m.getAmbient(), m.getDiffuse(), m.getSpecular(), m.getCoeffSpec());
				this.rayon = rayon;
				this.centre = new Point(centre);
		}

		// implantation des méthodes abstraites communes aux primitives
		public Intersection intersecte(Rayon r){
				float t, t1, t2;
				float delta,a, b, c, dx, dy, dz, xx, yy, zz;
				Vecteur d = r.getDirection();
				Point o = r.getOrigine();


				dx = d.getX();
				dy = d.getY();
				dz = d.getZ();

				xx = o.x - centre.x;
				yy = o.y - centre.y;
				zz = o.z - centre.z;

				a=dx*dx+dy*dy+dz*dz;
				b = dx*xx + dy*yy + dz*zz ;
				c = xx*xx + yy*yy + zz*zz -	rayon*rayon;

				delta = b*b - a*c;

				if(delta<0) return null;

				if(delta==0.0){
						t = -b/a;
						if(t<=EPSILON)
								return null;
				}else{
						delta = (float)Math.sqrt(delta);
						t1 = (-b - delta)/a;
						t2 = (-b + delta)/a;
			
						if(t2<=EPSILON) 
								return null;

						if(t1>EPSILON)
								t=t1;
						else
								t=t2;
				}

				// calcul du point d'intersection
	
				//	 return new Point(r.x+t*r.dx, r.y+t*r.dy, r.z+t*r.dz);
				return new Intersection(o.x+t*dx,
																o.y+t*dy,
																o.z+t*dz,
																this,
																t);

		}// intersecte


		public boolean coupe(Rayon r, float tmax){
				float t, t1, t2;
				float delta, a, b, c,dx, dy, dz, xx, yy, zz;

				Vecteur d = r.getDirection();
				Point o = r.getOrigine();
	

				dx = d.getX();
				dy = d.getY();
				dz = d.getZ();
		
				xx = o.x - centre.x;
				yy = o.y - centre.y;
				zz = o.z - centre.z;

				a=dx*dx+dy*dy+dz*dz;
				b = dx*xx + dy*yy + dz*zz ;
				c = xx*xx + yy*yy + zz*zz -	rayon*rayon;

				delta = b*b - a*c;

				if(delta<0) return false;

				if(delta==0.0){
						t = -b/a ;
						if((t<=EPSILON)||(t>tmax-EPSILON))
								return false;
				}else{
						delta = (float)Math.sqrt(delta);
						t1 = (-b - delta)/a;
						t2 = (-b + delta)/a;

						if(t2<=EPSILON)
								return false;
						if((t1>EPSILON)&&(t1<tmax-EPSILON))
								return true;
						if((t2>EPSILON)&&(t2<tmax-EPSILON))
								return true;
				}
				return false;	    
		}

		public Vecteur getNormale(Point i){
				Vecteur v = new Vecteur(centre, i);
				v.normalise();
				return v;
		}

		public String toString(){
				return "sphere de centre "+centre+" et de rayon "+rayon;

		}

}
