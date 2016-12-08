/**
 * Projet algo 
 * 
 * Classe permettant de memoriser et traiter les coordonnees d'un parcours
 * 
 * @author AMORY Marco, VERDONCK Florian
 * 
 **/
public class Vol {
	private Date date; // date du parcours
	private String pilote; // nom et prenom du pilote
	private Coordonnees[] tableCoordonnees;


	public Vol(Date date, String pilote, Coordonnees[] tableCoordonnees) {
		this.date = date;
		this.pilote = pilote;
		this.tableCoordonnees = tableCoordonnees;
	}
	
	

	public Coordonnees[] getTableCoordonnees() {
		return tableCoordonnees;
	}
	



	/**
	 * Cette methode renvoie la duree du vol
	 * Une unite de temps correspond au temps ecoule entre 2 mesures de position du gps
	 * @return la duree
	 */
	public int duree() {
		return this.tableCoordonnees.length-1;
	}
	
	public Coordonnees lieuLePlusEloigne(){
		
			double distanceMax = 0;
			Coordonnees coordonneesMax = tableCoordonnees[0];
			
			for (int i = 1; i < tableCoordonnees.length; i++) {
				double distanceZeroAvecCoordonnees = tableCoordonnees[0].distance(tableCoordonnees[i]);
				if(distanceZeroAvecCoordonnees > distanceMax){
					distanceMax = distanceZeroAvecCoordonnees;
					coordonneesMax = tableCoordonnees[i];
				}
				
			}
		return coordonneesMax;
	}
	

	public Coordonnees[] lieuxExtremes(){
		long latitudeMax = Integer.MIN_VALUE;
		long latitudeMin = Integer.MAX_VALUE;
		long longitudeMax = Integer.MIN_VALUE;
		long longitudeMin = Integer.MAX_VALUE;
		
		Coordonnees est = null;
		Coordonnees ouest = null;
		Coordonnees nord = null;
		Coordonnees sud = null;
		
		for (int i = 0; i < tableCoordonnees.length; i++) {
			if(tableCoordonnees[i].getLatitude() > latitudeMax){
				latitudeMax = tableCoordonnees[i].getLatitude();
				est = tableCoordonnees[i];
			}
			if(tableCoordonnees[i].getLatitude() < latitudeMin){
				latitudeMin = tableCoordonnees[i].getLatitude();
				ouest = tableCoordonnees[i];
			}
			if(tableCoordonnees[i].getLongitude() > longitudeMax){
				longitudeMax = tableCoordonnees[i].getLongitude();
				nord = tableCoordonnees[i];
			}
			if(tableCoordonnees[i].getLongitude() < longitudeMin){
				longitudeMin = tableCoordonnees[i].getLongitude();
				sud = tableCoordonnees[i];
			}
		}
		Coordonnees[] lieuxExtremes = new Coordonnees[] {est, ouest, nord, sud}; 
		
		return lieuxExtremes;
	}
	
	public Coordonnees lieuPlusProcheCible(Coordonnees cible) {
		
		double distanceMin = Integer.MAX_VALUE;
		Coordonnees lieuPlusProcheCible = cible;
		
		for (int i = 0; i < tableCoordonnees.length; i++) {
			
			double distanceCible = cible.distance(tableCoordonnees[i]);
			
			if (distanceCible < distanceMin) {
				
				lieuPlusProcheCible = tableCoordonnees[i];
				
			}
			
		}
		
		return lieuPlusProcheCible;
		
	}

	
} // fin classe
