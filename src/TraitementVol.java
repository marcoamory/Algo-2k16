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
		
		return null;
	}
	
	public static void afficherParcours(Coordonnees[] parcours){
		for (int i = 0; i<parcours.length; i++){
			System.out.println(parcours[i]);
		}
		System.out.println();
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
