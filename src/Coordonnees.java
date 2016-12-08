/* Projet algo
 *  Classe permettant de m�moriser les coordonn�es gps d'un lieu,
 *  de comparer et de calculer la distance s�parant 2 coordonn�es
 *  @author O.Legrand
 */
public class Coordonnees implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* latitude du lieu exprim�e sous forme d'un entier de type long
	 * Exemple : 3�20�30�� de latitude Sud = -12030��
	 * nord = +, sud = - et 3�20�30�� =  12030�� car ((3*60)+20)*60)+30
	 */
	private long latitude ;  

	/* longitude du lieu exprim�e sous forme d'un entier de type long
	 * Exemple : 2�10�20�� de longitude Ouest = -7820��
	 * car est = +, ouest = - et 2�10�20�� = -7820�� car ((2*60)+10)*60)+20
	 */
	private long longitude ;

	public Coordonnees( long latitude, long longitude ) throws IllegalArgumentException {
		final long LIMITE = 324000; // 90� * 60' * 60" 
		if ( latitude < -LIMITE || latitude > LIMITE ) {
			throw new IllegalArgumentException( "Latitude incorrecte : " + latitude );
		}
		if ( longitude < -LIMITE || longitude > LIMITE ) {
			throw new IllegalArgumentException( "Longitude incorrecte : " + longitude );
		}
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getLatitude() {
		return latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	// comparaison de 2 coordonn�es
	public boolean equals( Coordonnees autresCoordonn�es ) {
		return this.latitude == autresCoordonn�es.latitude &&
			   this.longitude == autresCoordonn�es.longitude;
	}

	/* calcul de la distance s�parant 2 coordonn�es.
	* calcul minimaliste supposant la terre parfaitement ronde (rayon=6378 km).
	* @param destination coordonn�es de l'autre lieu dont on calcule la distance
	* le s�parant de l'objet courant. 
	* @return double distance s�parant les 2 lieux, exprim�e en km.  
	*/
	public double distance( Coordonnees destination ) {
		final int RAYON = 6378;
		double sourceLong = Math.toRadians( this.longitude / 3600.0 );
		double sourceLat = Math.toRadians( this.latitude / 3600.0 );
		double destLong = Math.toRadians( destination.longitude / 3600.0 );
		double destLat = Math.toRadians( destination.latitude / 3600.0 );
		return RAYON * (Math.PI/2 - Math.asin( Math.sin(destLat) 
				* Math.sin(sourceLat) + Math.cos(destLong - sourceLong)
				* Math.cos(destLat) * Math.cos(sourceLat)));
	}

	/* D�termine si deux segments reliant deux paires de coordonn�es se croisent ou non.
	* @param p1 coordonn�es du premier point du premier segment.
	* @param p2 coordonn�es du second point du premier segment.
	* @param q1 coordonn�es du premier point du second segment.
	* @param q2 coordonn�es du second point du second segment.
	* @throw IllegalArgumentException si un des arguments est null.
	* @return true si les segments se touchent et false sinon.  
	*/
	public static boolean segmentsCroises(Coordonnees p1, Coordonnees p2,
			Coordonnees q1, Coordonnees q2) throws IllegalArgumentException{
		if(p1==null||p2==null||q1==null||q2==null) throw new IllegalArgumentException();

		long p1Lat = p1.getLatitude();
		long p1Long = p1.getLongitude();
		long p2Lat = p2.getLatitude();
		long p2Long = p2.getLongitude();
		long q1Lat = q1.getLatitude();
		long q1Long = q1.getLongitude();
		long q2Lat = q2.getLatitude();
		long q2Long = q2.getLongitude();
		
		// Si au moins un des 2 segments est d�g�n�r� (les 2 extr�mit�s sont identiques)
		if(p1.equals(p2)){
			if(q1.equals(q2)){
				if(p1.equals(q1)) return true; // 2 segments d�g�n�r�s et identiques
				return false; // 2 segments d�g�n�r�s et diff�rents
			}
			// si le segment d�g�n�r� est align� avec l'autre
			if((q1Lat-q2Lat)*(p1Long-q1Long) == (q1Long-q2Long)*(p1Lat-q1Lat)){
				if(p1Lat>=Math.min(q1Lat,q2Lat) && p1Lat<=Math.max(q1Lat,q2Lat)) return true;
			}
			return false;
		}
		if(q1.equals(q2)){
			// si le segment d�g�n�r� est align� avec l'autre
			if((p1Lat-p2Lat)*(q1Long-p1Long) == (p1Long-p2Long)*(q1Lat-p1Lat)){
				if(q1Lat>=Math.min(p1Lat,p2Lat) && q1Lat<=Math.max(p1Lat,p2Lat)) return true;
			}
			return false;
		}
		
		// Si les deux segments sont verticaux
		if(p1Lat==p2Lat && q1Lat==q2Lat){
			// Certainements pas superpos�s
			if(p1Lat!=q1Lat) return false;
			// Au moins partiellement superpos�s
			if(Math.min(q1Long,q2Long)>=Math.min(p1Long,p2Long) &&
					Math.min(q1Long,q2Long)<=Math.max(p1Long,p2Long)) return true;
			if(Math.min(p1Long,p2Long)>=Math.min(q1Long,q2Long) &&
					Math.min(p1Long,p2Long)<=Math.max(q1Long,q2Long)) return true;
			return false;
		}
		
		// Si les deux segments sont horizontaux
		if(p1Long==p2Long && q1Long==q2Long){
			// Certainements pas superpos�s
			if(p1Long!=q1Long) return false;
			// Au moins partiellement superpos�s
			if(Math.min(q1Lat,q2Lat)>=Math.min(p1Lat,p2Lat) &&
					Math.min(q1Lat,q2Lat)<=Math.max(p1Lat,p2Lat)) return true;
			if(Math.min(p1Lat,p2Lat)>=Math.min(q1Lat,q2Lat) &&
					Math.min(p1Lat,p2Lat)<=Math.max(q1Lat,q2Lat)) return true;
			return false;
		}
		
		// Si les deux segments sont parall�les
		if((p1Long-p2Long)*(q1Lat-q2Lat) == (p1Lat-p2Lat)*(q1Long-q2Long)){
			// si les ordonn�es � l'origine sont identiques
			if((q1Lat-q2Lat)*(p1Lat*p2Long-p1Long*p2Lat) == (p1Lat-p2Lat)*(q1Lat*q2Long-q1Long*q2Lat)){
				// Au moins partiellement superpos�s
				if(Math.min(q1Lat,q2Lat)>=Math.min(p1Lat,p2Lat) &&
						Math.min(q1Lat,q2Lat)<=Math.max(p1Lat,p2Lat)) return true;
				if(Math.min(p1Lat,p2Lat)>=Math.min(q1Lat,q2Lat) &&
						Math.min(p1Lat,p2Lat)<=Math.max(q1Lat,q2Lat)) return true;
			}
			return false;
		}
		
		// Cas g�n�ral
		double cLat, cLong;
		cLat = ((double)((q1Lat-q2Lat)*(p1Lat*p2Long-p1Long*p2Lat)-
				(p1Lat-p2Lat)*(q1Lat*q2Long-q1Long*q2Lat)))/
				((p1Lat-p2Lat)*(q1Long-q2Long)-(q1Lat-q2Lat)*(p1Long-p2Long));
		cLong = ((double)((q1Long-q2Long)*(p1Lat*p2Long-p1Long*p2Lat)-
				(p1Long-p2Long)*(q1Lat*q2Long-q1Long*q2Lat)))/
				((p1Lat-p2Lat)*(q1Long-q2Long)-(q1Lat-q2Lat)*(p1Long-p2Long));
		if(cLat<Math.min(p1Lat,p2Lat)||cLat>Math.max(p1Lat,p2Lat)) return false;
		if(cLat<Math.min(q1Lat,q2Lat)||cLat>Math.max(q1Lat,q2Lat)) return false;
		if(cLong<Math.min(p1Long,p2Long)||cLong>Math.max(p1Long,p2Long)) return false;
		if(cLong<Math.min(q1Long,q2Long)||cLong>Math.max(q1Long,q2Long)) return false;
		return true;
	}
	
	public String toString() {
		return this.latitude + "\t"+ this.longitude;
	}
} // fin classe



