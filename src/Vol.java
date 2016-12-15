import java.util.Arrays;

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
	 * Cette methode renvoie la coordonnee la plus eloignee du point 0 0
	 * @return la coordonnee la plus eloignee
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
	
	/**
	 * Cette methode renvoie la coordonnee la plus proche d'une cible passee en parametre et entree par l'utilisateur
	 * @param la coordonnee de la cible
	 * @return la coordonnee la plus proche de la cible
	 */
	public Coordonnees lieuPlusProcheCible(Coordonnees cible) {
		
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
	 * Cette methode renvoie la distance maximum parcourue avec k points de contournements, elle prend en parametre le nombre de point de contournement et
	 * le nombre de coordonnees total
	 * @param k nombre de point de contournement, n nombre de coordonnees au total
	 * @return la distance max avec k point de contournement
	 */
	public double distanceKPointsContournements(int k, int n) {
		return distanceKPointsContournements(k, 0, n, new Coordonnees[k], 0);
	}
	
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
	 * Cette methode renvoie une methode en fonction du nombre de point de contournement demande, elle prend en parametre le nombre de point
	 * @param nbrPoints le nombre de points de contournement
	 * @return la methode adequate
	 */
	public double distancePointsContournements(int nbrPoints){
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
	public int nombreCroisement(){
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
	 * Cette methode renvoie si la cible passee en parametre est atteinte durant le vol ou non, elle prend en parametre les coordonnees de la cible entre par
	 * l'utilisateur
	 * @param coordonnees de la cible
	 * @return true si la cible est atteinte, false si elle ne l'est pas
	 */
	public boolean cibleAtteintes(Coordonnees cible){
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
	 * Cette methode renvoie le nombre de cible(s) atteinte(s) durant le vol, elle prend en parametre un tableau de cible entre par l'utilisateur
	 * @param tableau de cible
	 * @return nombre de cible atteinte
	 */
	public int nombreCibleAtteintes(Coordonnees[] cibles){
		int nombreCibleAtteintes = 0;
		for (int i = 0; i < cibles.length; i++) {
			if(cibleAtteintes(cibles[i])){
				nombreCibleAtteintes++;
			}
		}
		return nombreCibleAtteintes;
	}
	
} // fin classe
