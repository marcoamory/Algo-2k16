
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
	
	public Date getDate(){
		return date;
	}
	
	public String getPilote(){
		return pilote;
	}



	/**
	 * Cette methode renvoie la duree du vol
	 * Une unite de temps correspond au temps ecoule entre 2 mesures de position du gps
	 * @return la duree
	 */
	public int duree() {
		return this.tableCoordonnees.length-1;
	}
	
	/**
	 * Cette methode renvoie les coordonnées les plus eloignées du point [0 0]
	 * @return les coordonnées les plus eloignées
	 */
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
	
	/**
	 * Cette methode renvoie les coordonnees les plus eloignees au quatre points cardinaux
	 * @return un tableau de quatre coordonnees
	 */
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
				nord = tableCoordonnees[i];
			}
			if(tableCoordonnees[i].getLatitude() < latitudeMin){
				latitudeMin = tableCoordonnees[i].getLatitude();
				sud = tableCoordonnees[i];
			}
			if(tableCoordonnees[i].getLongitude() > longitudeMax){
				longitudeMax = tableCoordonnees[i].getLongitude();
				est = tableCoordonnees[i];
			}
			if(tableCoordonnees[i].getLongitude() < longitudeMin){
				longitudeMin = tableCoordonnees[i].getLongitude();
				ouest = tableCoordonnees[i];
			}
		}
		Coordonnees[] lieuxExtremes = new Coordonnees[] {est, ouest, nord, sud}; 
		
		return lieuxExtremes;
	}
	
	/**
	 * Cette methode renvoie la coordonnee la plus proche d'une cible passee en paramètre et entrée par l'utilisateur
	 * @param cible les coordonnées de la cible
	 * @return la coordonnee la plus proche de la cible
	 */
	public Coordonnees lieuPlusProcheCible(Coordonnees cible) {
		if(cible == null){
			throw new IllegalArgumentException("Cible invalide");
		}
		double distanceMin = Integer.MAX_VALUE;
		Coordonnees lieuPlusProcheCible = tableCoordonnees[0];
		
		for (int i = 1; i < tableCoordonnees.length; i++) {
			
			double distanceCible = cible.distance(tableCoordonnees[i]);
			
			if (distanceCible < distanceMin) {
				
				distanceMin = distanceCible;
				lieuPlusProcheCible = tableCoordonnees[i];
				
			}
			
		}
		
		return lieuPlusProcheCible;
		
	}
	
	/**
	 * Cette methode renvoie la distance totale parcourue lors du vol
	 * @return la distance totale
	 */
	public double distanceTotale() {
		
		double distanceTotale = 0;
		
		for (int i = 0; i < tableCoordonnees.length-1; i++) {
			
			distanceTotale += tableCoordonnees[i].distance(tableCoordonnees[i+1]);
			
		}
		
		return distanceTotale;
		
	}
	/**
	 * Cette methode renvoie la distance maximum parcourue avec un point de contournement
	 * @return la distance max avec un point de contournement
	 */
	public double distanceUnPointsContournements(){
		
		double distanceMax = 0;
		int debut = 0;
		
			for (int i = 1; i < tableCoordonnees.length; i++) {
				if(tableCoordonnees[debut].distance(tableCoordonnees[i]) + tableCoordonnees[i].distance(tableCoordonnees[tableCoordonnees.length-1]) > distanceMax){
					distanceMax = tableCoordonnees[debut].distance(tableCoordonnees[i]) + tableCoordonnees[i].distance(tableCoordonnees[tableCoordonnees.length-1]);
					
				}
			}
		return distanceMax;
	}
	
	/**
	 * Cette methode renvoie la distance maximum parcourue avec deux points de contournements
	 * @return la distance max avec deux points de contournements
	 */
	
	public double distanceDeuxPointsContournements(){  
		int debut = 0;
		
		double distanceMax = 0;
		double distanceMax2 = 0;
		
		double distanceTotaleTemp = 0;
		double distanceMaxTotale = 0;
		
		for (int i = 1; i < tableCoordonnees.length; i++) {
			if(tableCoordonnees[debut].distance(tableCoordonnees[i]) + tableCoordonnees[i].distance(tableCoordonnees[tableCoordonnees.length-1]) > distanceMax){
				distanceMax = tableCoordonnees[debut].distance(tableCoordonnees[i]) + tableCoordonnees[i].distance(tableCoordonnees[tableCoordonnees.length-1]);
				
				double segment = tableCoordonnees[debut].distance(tableCoordonnees[i]);
				
				for(int j = i+1; j<tableCoordonnees.length; j++){
					if(tableCoordonnees[i].distance(tableCoordonnees[j]) + tableCoordonnees[j].distance(tableCoordonnees[tableCoordonnees.length-1]) > distanceMax2){
						distanceMax2 = tableCoordonnees[i].distance(tableCoordonnees[j]) + tableCoordonnees[j].distance(tableCoordonnees[tableCoordonnees.length-1]);
					}
				}
				distanceTotaleTemp = segment + distanceMax2;
				distanceMax2 = 0;
				if(distanceTotaleTemp > distanceMaxTotale){
					distanceMaxTotale = distanceTotaleTemp;
				}
			}
		}
		return distanceMaxTotale;
	}
	
	/**
	 * Cette methode renvoie la distance maximum parcourue avec k points de contournements calculée grace à la méthode homonyme, elle prend en parametre le nombre de point de contournement et
	 * le nombre de coordonnees total
	 * @param k nombre de point de contournement
	 * @param n nombre de coordonnees au total
	 * @return la distance max avec k point de contournement
	 */
	public double distanceKPointsContournements(int k, int n) {
		if(k < 0) throw new IllegalArgumentException("Nombre de point de contournement");
		if(n <= 0) throw new IllegalArgumentException("Nombre de coordonnées invalide");
		return distanceKPointsContournements(k, 0, n, new Coordonnees[k], 0);
	}
	
	/**
	 * Cette methode renvoie la distance maximum parcourue avec k points de contournements, elle prend en parametre le nombre de point de contournement,
	 * le nombre de coordonnees total, un nouveau tableau de coordonnées contenant les coordonnées à tester comme point de contournement
	 *
	 * @param k nombre de coordonnées total
	 * @param idx l'index de début de boucle dans la table de coordonnées
	 * @param n nombre de point de contournement
	 * @param cmb le tableau de coordonnées contenant les les points de contournement à tester
	 * @param jdx l'index dans le tableau de coordonnée cmb
	 * @return
	 */
	
	
	
	public double distanceKPointsContournements(int k, int idx, int n, Coordonnees[] cmb, int jdx) {
		if (k == 0) {
			double distanceMax = 0;
			double distanceDebut = tableCoordonnees[0].distance(cmb[0]);
			double distanceFin = cmb[cmb.length-1].distance(tableCoordonnees[tableCoordonnees.length-1]);
			for(int i = 0; i< cmb.length-1; i++){
				distanceMax += cmb[i].distance(cmb[i+1]);
			}
			distanceMax += distanceDebut + distanceFin;
			
			return distanceMax;
		}
		double distanceTotaleMax = 0;
		for(int i = idx; i < n; i++) {
			cmb[jdx] = tableCoordonnees[i];
			double distance = distanceKPointsContournements(k - 1, i, n, cmb, jdx + 1);
			if(distance > distanceTotaleMax){
				distanceTotaleMax = distance;
			}
		}
		return distanceTotaleMax;
	}
	/**
	 * Cette methode renvoie une methode en fonction du nombre de point de contournement demandé, elle prend en paramètre le nombre de points
	 * @param nbrPoints le nombre de points de contournement
	 * @return la methode adéquate
	 */
	public double distancePointsContournements(int nbrPoints){
		if(nbrPoints < 0) throw new IllegalArgumentException("Nombre de points de contournement invalide");
		switch(nbrPoints){
		case 1 : 
			return distanceUnPointsContournements();
			
		case 2 :
			return distanceDeuxPointsContournements();
			
		default: 	
			return distanceKPointsContournements(nbrPoints, tableCoordonnees.length);
		}	
	}
	
	/**
	 * Cette methode renvoie le nombre de croisement dans le parcours du vol
	 * @return le nombre de croisement
	 */
	public int nombreCroisements(){
		int nombreCroisements = 0;	
		for (int i = 0; i < tableCoordonnees.length-3; i++) {
			for(int j = i+1; j<tableCoordonnees.length-1; j++){
				if(Coordonnees.segmentsCroises(tableCoordonnees[i], tableCoordonnees[i+1], tableCoordonnees[j], tableCoordonnees[j+1])){
					if(tableCoordonnees[i+1] != tableCoordonnees[j]){
						nombreCroisements++;
					}
				}
			}
		}
		
		return nombreCroisements;
	}
	
	/**
	 * Cette methode renvoie si la cible passée en paramètre est atteinte durant le vol ou non, elle prend en paramètre les coordonnées de la cible entrée par
	 * l'utilisateur
	 * @param cible coordonnees de la cible
	 * @return true si la cible est atteinte, false si elle ne l'est pas
	 */
	public boolean cibleAtteinte(Coordonnees cible){
		if(cible == null){
			throw new IllegalArgumentException("Cible invalide");
		}
		if(tableCoordonnees[tableCoordonnees.length-1].equals(cible)){
			return true;
		}
		for (int i = 0; i < tableCoordonnees.length-1; i++) {
			if(tableCoordonnees[i].equals(cible)){
				return true;
			}
			if(Coordonnees.segmentsCroises(tableCoordonnees[i], tableCoordonnees[i+1], cible, cible)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * Cette méthode prend un tableau de cibles à atteindre en paramètre et renvoie un tableau avec les cibles atteintes durant le vol
	 * L'ordre des cibles entrées n'a pas d'importance!
	 * @param cibles à atteindre
	 * @return tableau de coordonnees avec les cibles atteintes
	 */
	public Coordonnees[] ciblesAtteintes(Coordonnees[] cibles){
		if(cibles.length <= 0){
			throw new IllegalArgumentException("Cibles invalides");
		}
		int nombreCibleAtteintes = 0;
		for(int i = 0; i<cibles.length; i++){
			if(cibleAtteinte(cibles[i])){
				nombreCibleAtteintes++;
			}
		}
		
		Coordonnees[] ciblesAtteintes = new Coordonnees[nombreCibleAtteintes];
		for(int i = 0, j = 0; i<cibles.length; i++){
			if(cibleAtteinte(cibles[i])){
				ciblesAtteintes[j] = cibles[i];
				j++;
			}
		}
		return ciblesAtteintes;
	}
	/**
	 * Cette methode renvoie le nombre de cible(s) atteinte(s) durant le vol, elle prend en paramètre un tableau de cible entré par l'utilisateur
	 * L'ordre des cibles entrées a de l'importance!
	 * @param cibles un tableau de coordonnees de  cibles
	 * @return nombre de cible atteinte
	 */
	public int nombreCiblesAtteintes(Coordonnees[] cibles){
		if(cibles.length <= 0){
			throw new IllegalArgumentException("Cibles invalide");
		}
		int index = 0;
		int nombreCibleAtteintes = 0;
		for(int i = 0; i<cibles.length; i++ ){
			for (int j = index; j < tableCoordonnees.length; j++) {
				if(tableCoordonnees[j].equals(cibles[i])){
					index = j;
					nombreCibleAtteintes++;
				}
			}
		}
		return nombreCibleAtteintes;
	}
	
	/**
	 * 
	 * Cette méthode renvoie le vitesse moyenne du pilote durant le vol.
	 * 
	 * @return vitesse moyenne
	 */
	
	public float vitesseMoyenne(){
		float distance = (float) distanceTotale();
		float temps = duree();
		
		float vitesseMoyenne = distance/temps;
		
		return vitesseMoyenne;
	}
	
} // fin classe
