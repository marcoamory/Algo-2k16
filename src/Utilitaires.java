import java.util.Scanner;


public class Utilitaires {
	public static Scanner scanner = new Scanner(System.in);

	/**
	 * Lit un caract�re au clavier tant que l'utilisateur n'a
	 * pas r�pondu 'O' ou 'N'
	 * @return
	 */
	public static char lireOouN(){
		char reponse=scanner.next().charAt(0);
		while (reponse !='O' && reponse !='N'){
			System.out.println("R�pondez O ou N");
			reponse=scanner.next().charAt(0);
		}
		return reponse;
	}

	public static int lireUnEntierStrictementPositif(){
		int entier = scanner.nextInt();
		while (entier<=0){
			System.out.println("Le nombre doit �tre strictement positif");
			entier = scanner.nextInt();
		}        
		return entier;
	}  

	public static int lireUnEntierPositifOuNul(){
		int entier = scanner.nextInt();
		while (entier<0){
			System.out.println("Le nombre doit �tre plus grand ou �gal � 0");
			entier = scanner.nextInt();
		}        
		return entier;
	}

	public static int lireUnEntierComprisEntre(int entier1, int entier2){
		int entier = scanner.nextInt();
		while (entier<entier1 || entier>entier2){
			System.out.println("Le nombre doit �tre compris entre "+entier1+" et "+entier2);
			entier = scanner.nextInt();
		}
		return entier;
	}  

	public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){
		double nombreReel;
		int resultat;

		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;
	}
	
	public static void afficherTableCoordonnees(Coordonnees[] coordonnees){
		for (int i = 0; i < coordonnees.length; i++) {
			System.out.println(coordonnees[i]);
			System.out.println();
			
		}
	}

}
