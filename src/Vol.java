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
	
	public Coordonnees lieuExtreme(){
		return null; //PoloStyle
	}

	
} // fin classe
