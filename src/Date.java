import java.util.*;

/**
 * Cette classe Date permet de construire une date et de la valider. Elle permet
 * aussi de lire la date system, de tester si une ann�e est bissextile.
 * Remarque: il faudrait compl�ter cette classe en y ajoutant un nombre de jours
 * �coul�s depuis une date de r�f�rence. Cela facilite les comparaisons, ...
 * 
 * @author Olivier Legrand 01/2003.
 */
public class Date implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// le 1er janvier 1980 est la date de r�f�rence, c'�tait un mardi
	private static final int JOUR_REF = 2; // 2 car mardi (1: lundi, 2:
											// mardi,...)
	private static final int ANNEE_MIN = 1980; // pour la validation
	private static final int ANNEE_MAX = 2050; // pour la validation
	private static int[] NB_JOURS_MOIS = { 0, 31, 28, 31, 30, 31, 30, 31, 31,
			30, 31, 30, 31 };

	private int jour;
	private int mois;
	private int annee;

	/**
	 * constructeur de la classe Date.
	 * 
	 * @param jour
	 *            entier repr�sentant le jour.
	 * @param mois
	 *            entier repr�sentant le mois.
	 * @param ann�e
	 *            entier repr�sentant l'ann�e.
	 * @throws Exception
	 *             si la date est invalide.
	 */
	public Date(int jour, int mois, int ann�e) throws Exception {
		if (Date.estValide(jour, mois, ann�e)) {
			this.jour = jour;
			this.mois = mois;
			this.annee = ann�e;
		} else {
			throw new Exception("Date incorrecte : " + jour + " / " + mois
					+ " / " + ann�e);
		}
	}

	/**
	 * constructeur qui re�oit une cha�ne de caract�res contenant une date. Elle
	 * permet de valider et de construire une date. Si la date extraite de cette
	 * cha�ne n'est pas valide, elle renvoie une exception.
	 * 
	 * @param stringDate
	 *            cha�ne de caract�res contenant la date � cr�er.
	 * @throws Exception
	 *             si la date est invalide.
	 */
	public Date(String stringDate) throws Exception {

		// format valide : jj/mm/aaaa
		String s = stringDate.trim();

		// jours
		int jour = 0;
		StringTokenizer st = new StringTokenizer(s, "/", true);
		try {
			if (!st.hasMoreTokens())
				throw new Exception("Date incorrecte. Veuillez v�rifier les "
						+ " jours.");
			String sJours = (st.nextToken()).trim();
			jour = (new Integer(sJours)).intValue();
		} catch (Exception e) {
			throw new Exception("Date incorrecte. Veuillez v�rifier les "
					+ " jours.");
		}

		// les mois
		int mois;
		try {
			if (!st.hasMoreTokens())
				throw new Exception("Date incorrecte. Veuillez v�rifier le "
						+ " mois.");
			st.nextToken(); // lecture du d�limiteur
			String sMois = (st.nextToken()).trim();
			mois = (new Integer(sMois)).intValue();
		} catch (Exception e) {
			throw new Exception("Date incorrecte. Veuillez v�rifier le "
					+ " mois.");
		}

		// les ann�es
		int ann�e;
		try {
			String sAnn�es = s.substring(s.length() - 4, s.length());
			ann�e = (new Integer(sAnn�es)).intValue();
		} catch (Exception e) {
			throw new Exception("Date incorrecte. Veuillez v�rifier l' "
					+ " ann�e.");
		}
		if (Date.estValide(jour, mois, ann�e)) {
			this.jour = jour;
			this.mois = mois;
			this.annee = ann�e;
		} else {
			throw new Exception("Date incorrecte : " + jour + " / " + mois
					+ " / " + ann�e);
		}
	}

	/**
	 * constructeur de la classe Date.
	 * 
	 * @param numJour
	 *            entier repr�sentant le num�ro du jour dans l'ann�e. Attention,
	 *            1er janvier => 0
	 * @param ann�e
	 *            entier repr�sentant l'ann�e.
	 * @throws Exception
	 *             si la date est invalide.
	 */
	public Date(int numJour, int ann�e) throws Exception {
		boolean ann�eBis = Date.anneeBissextile(ann�e);
		int nbMaxJours = ann�eBis ? 366 : 365;
		if (numJour < 0 || numJour >= nbMaxJours) {
			throw new Exception("Impossible de cr�er une date."
					+ " numJour incorrecte : " + numJour);
		}
		int nbJours = numJour + 1;
		int mois;
		for (mois = 1; mois < 13; mois++) {
			int nbJoursMois;
			if (mois == 2 && ann�eBis)
				nbJoursMois = 29;
			else
				nbJoursMois = NB_JOURS_MOIS[mois];
			if (nbJours <= nbJoursMois)
				break;
			nbJours -= nbJoursMois;
		}
		if (Date.estValide(nbJours, mois, ann�e)) {
			this.jour = nbJours;
			this.mois = mois;
			this.annee = ann�e;
		} else {
			throw new Exception("Date incorrecte : " + nbJours + " / " + mois
					+ " / " + ann�e);
		}
	}

	/**
	 * m�thode de lecture de la date syst�me.
	 * 
	 * @return elle retourne la date syst�me.
	 */
	public static Date lireDateSystem() throws Exception {
		long time = System.currentTimeMillis();
		GregorianCalendar date = new GregorianCalendar(Locale.FRANCE);
		date.setTime(new java.util.Date(time));
		int jour = date.get(Calendar.DAY_OF_MONTH);
		int mois = date.get(Calendar.MONTH) + 1;
		int ann�e = date.get(Calendar.YEAR);
		return new Date(jour, mois, ann�e);
	}

	/**
	 * m�thode de comparaison de 2 dates.
	 * 
	 * @param date2
	 *            la date � comparer � la date de l'objet courant.
	 * @return entier <0 si la date pr�c�de la date2. =0 si les dates sont
	 *         identiques. >0 si la date est post�rieure � la date2.
	 */
	public int compareTo(Date date2) {
		if (this.annee < date2.annee)
			return -1;
		if (this.annee > date2.annee)
			return 1;
		// m�me ann�e
		if (this.mois < date2.mois)
			return -1;
		if (this.mois > date2.mois)
			return 1;
		// m�me mois
		if (this.jour < date2.jour)
			return -1;
		if (this.jour > date2.jour)
			return 1;
		// m�me jour
		return 0;
	}

	/**
	 * m�thode de formatage de la date en string.
	 * 
	 * @return elle retourne une string contenant la date (format jj/mm/aa).
	 */
	public String toString() {
		String sJours, sMois;
		if ((this.jour / 10) == 0)
			sJours = new String("0" + this.jour);
		else
			sJours = "" + this.jour;
		if ((this.mois / 10) == 0)
			sMois = "0" + this.mois;
		else
			sMois = "" + this.mois;
		return "" + sJours + "/" + sMois + "/" + annee;
	}

	/*
	 * m�thode qui teste si ann�e bissextile.
	 * 
	 * @param ann�e entier indicant l'ann�e � tester.
	 */
	public static boolean anneeBissextile(int ann�e) {
		if (((ann�e % 100) == 0) && ((ann�e % 400) != 0))
			return false;
		if ((ann�e % 4) == 0)
			return true;
		return false;
	}

	/*
	 * m�thode qui teste la validit� d'une date.
	 * 
	 * @param jour entier repr�sentant le jour.
	 * 
	 * @param mois entier repr�sentant le mois.
	 * 
	 * @param ann�e entier repr�sentant l'ann�e.
	 * 
	 * @return boolean signale si la date est valide ou pas.
	 */
	public static boolean estValide(int jour, int mois, int ann�e) {
		if ((ann�e < ANNEE_MIN) || (ann�e > ANNEE_MAX))
			return false;

		// validation des jours et des mois.
		switch (mois) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if ((jour < 1) || (jour > 31))
				return false;
			else
				return true;
		case 4:
		case 6:
		case 9:
		case 11:
			if ((jour < 1) || (jour > 30))
				return false;
			else
				return true;
		case 2: {
			if ((jour < 1) || (jour > 29))
				return false;
			if (jour == 29 && !Date.anneeBissextile(ann�e))
				return false;
			return true;
		}
		default:
			return false;
		} // fin switch
	}

	/**
	 * m�thode qui renvoie le num�ro du jour dans l'ann�e (en commen�ant � 0).
	 * ex: 1 janvier => 0, 31 janvier => 30. Ce nombre indique aussi le nombre
	 * de jours �coul�s depuis le d�but de l'ann�e.
	 * 
	 * @return int nombre de jours �coul�s depuis le d�but de l'ann�e.
	 */
	public int numJourDsAnnee() {
		int mois;
		int nbJours = 0;
		for (mois = 1; mois < this.getMois(); mois++) {
			if (mois == 2 && Date.anneeBissextile(this.annee)) {
				nbJours += 29;
			} else {
				nbJours += NB_JOURS_MOIS[mois];
			}
		}
		return nbJours + this.jour - 1;
	}

	public int getJour() {
		return this.jour;
	}

	public int getMois() {
		return this.mois;
	}

	public int getAnnee() {
		return this.annee;
	}

	/**
	 * M�thode qui renvoie un entier indiquant le num�ro du jour de la semaine.
	 * 1 : lundi .. 7 : dimanche.
	 * 
	 * @return entier indiquant le num�ro du jour de la semaine.
	 */
	public int jourDeLaSemaine() {
		int ann�e1;
		int nbJours = 0;
		for (ann�e1 = Date.ANNEE_MIN; ann�e1 < this.annee; ann�e1++) {
			if (Date.anneeBissextile(ann�e1)) {
				nbJours += 366;
			} else {
				nbJours += 365;
			}
		}
		int mois;
		for (mois = 1; mois < this.getMois(); mois++) {
			if (mois == 2 && Date.anneeBissextile(this.annee)) {
				nbJours += 29;
			} else {
				nbJours += NB_JOURS_MOIS[mois];
			}
		}
		nbJours += this.jour;
		return nbJours % 7 + JOUR_REF - 1;
	}

} // fin de la classe