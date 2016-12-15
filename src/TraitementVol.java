/** 
 * Dossier algo 
 * Programme principal a completer
 * 
 *    @author AMORY Marco, VERDONCK Florian
 **/
public class TraitementVol {
	private static final String REPERTOIRE = "data/";
	private static Vol vol;

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("\nTraitement d'un vol\n*******************");
		vol = chargerVol();
		afficherParcours(vol.getTableCoordonnees());
		int choix;
		do {
			choix = lireChoix();
			switch (choix) {
			case 1:
				statistique1();
				break;
			case 2:
				statistique2();
				break;
			case 3 :
				statistique3();
				break;
			case 4 :
				statistique4();
				break;
			case 5 :
				statistique5();
				break;
			case 6 :
				statistique6();
				break;
			case 7 :
				statistique7();
				break;
			case 8 :
				statistique8();
				break;
			case 9 :
				statistique9();
				break;
			case 10 :
				statistique10();
				break;
			} 
		}while(choix!=0);
		System.out.println("Au revoir!\n");
	}

	private static int lireChoix() {
		System.out.println("\nMenu");
		System.out.println("----");	
		System.out.println("   1 --> duree du vol");
		System.out.println("   2 --> lieu le plus éloigné");
		System.out.println("   3 --> lieux extrêmes");
		System.out.println("   4 --> lieu le plus proche d'une cible");
		System.out.println("   5 --> distance parcourue");
		System.out.println("   6 --> distance maximale avec point(s) de contournement(s)");
		System.out.println("   7 --> nombre de croisements");
		System.out.println("   8 --> cibles atteintes");
		System.out.println("   9 --> nombre cibles atteintes");
		System.out.println("   10 --> notre méthode à définir");
		System.out.println("   0 --> fin");
		System.out.print("\nTon choix : ");
		int choix = Utilitaires.lireUnEntierComprisEntre(0, 10);
		return choix;
	}

	public static Coordonnees lireCoordonnees(){
		System.out.print("Latitude: ");
		long latitude = Utilitaires.lireUnEntier();
		System.out.print("\nLongitude: ");
		long longitude = Utilitaires.lireUnEntier();
		
		Coordonnees coordonees = new Coordonnees(latitude, longitude);
		return coordonees;
	}
	
	public static void afficherParcours(Coordonnees[] parcours){
		System.out.println("Date du vol : " + vol.getDate());
		System.out.println("Pilote du vol : " + vol.getPilote());
		System.out.println("Coordonnées enregistrées");
		Utilitaires.afficherTableCoordonnees(vol.getTableCoordonnees());
	}
	
	public static Coordonnees[] creerParcours(){
		// A COMPLETER
		return null;
	}
	
	public static void statistique1(){
		System.out.println("\nTon vol a dure "+vol.duree()+ " unites temps.");
	}
	
	public static void statistique2(){
		System.out.println("\nLe lieu le plus éloigné est : [" +vol.lieuLePlusEloigne() + "]");
	}
	
	public static void statistique3(){
		System.out.println("\nLes coordonnées des 4 points extrème sont : ");
		System.out.println("Extrême EST : [" + vol.lieuxExtremes()[0] + "]");
		System.out.println("Extrême OUEST : [" + vol.lieuxExtremes()[1] + "]");
		System.out.println("Extrême NORD : [" + vol.lieuxExtremes()[2] + "]");
		System.out.println("Extrême SUD : [" + vol.lieuxExtremes()[3] + "]");
	}
	
	public static void statistique4(){
		System.out.println("Coordonnées de la cible");
		Coordonnees cible = lireCoordonnees();
		System.out.println("Le lieu le plus proche de la cible est : " + vol.lieuPlusProcheCible(cible));
	}
	
	public static void statistique5(){
		System.out.println("Distance totale parcourue: " + vol.distanceTotale() + " km");
	}
	
	public static void statistique6(){
		System.out.print("Combien de point(s) de contournement(s)? (Max : " + (vol.getTableCoordonnees().length-2) + ")");
		int nombrePointsContournements = Utilitaires.lireUnEntierPositifOuNul();
		System.out.println("Distance max avec " + nombrePointsContournements + " point(s) de contournement(s): " + vol.distancePointsContournements(0,0,nombrePointsContournements));
	}
	
	public static void statistique7(){
		System.out.println("Nombre de croisement(s) durant le vol : " + vol.nombreCroisement());
	}
	
	public static void statistique8(){
		
	}
	
	public static void statistique9(){
		
	}
	
	public static void statistique10(){
		
	}

	// A COMPLETER


	/**
	 * Construit un vol sur base des donnees du fichier dont le nom est
	 * encode au clavier
	 * 
	 * @return parcours cree sur base des donnees du fichier
	 */
	private static Vol chargerVol() {
		System.out.print("Introduis le nom du fichier : ");
		String nomFichier = scanner.next();
		Coordonnees[] tableCoordonnees = new Coordonnees[4];
		Fichier fichier = new Fichier(REPERTOIRE + nomFichier);
		int nombreCoordonnees = 0;
		Date date = null;
		String pilote = null;
		try {
			// ouverture fichier
			fichier.ouvrirEnLecture();

			// lecture Date, Pilote
			date = (Date) fichier.lireObjet();
			pilote = (String) fichier.lireObjet();

			// lecture des Coordonnees
			while (true) { // on quitte cette repetitive lorsque EOF rencontree
				Coordonnees coordonnee = (Coordonnees) fichier.lireObjet();
				if(nombreCoordonnees==tableCoordonnees.length){
					Coordonnees[]temp = new Coordonnees[tableCoordonnees.length*2];
					for (int i = 0; i < tableCoordonnees.length; i++) {
						temp[i]=tableCoordonnees[i];	
					}
					tableCoordonnees=temp;
				}
				tableCoordonnees[nombreCoordonnees] = coordonnee;
				nombreCoordonnees++;
			}
		} catch (java.io.EOFException ex) { // fin du fichier rencontree
			Coordonnees[] tableCoordonnees2 = new Coordonnees[nombreCoordonnees];
			for (int i = 0; i < nombreCoordonnees; i++) {
				tableCoordonnees2[i] = tableCoordonnees[i];
			}
			return new Vol(date, pilote, tableCoordonnees2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // quoi qu'il arrive, il doit essayer de fermer le fichier.
			try {
				fichier.fermer();
			} catch (java.io.IOException ex) { // si erreur lors de la fermeture
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}

} // fin pgm
