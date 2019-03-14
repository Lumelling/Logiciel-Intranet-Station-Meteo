package application;

/**
 * Classe permettant de gérer une date
 * @author Thibault
 *
 */
public class Date {
        
        /**
         * exception levée si la date est invalide
         * est incorrecte
         */
        public static class ErreurDateInvalide extends Exception {
    
    	    /** TODO commenter le rôle de ce champ (attribut, rôle d'association) */
    	    private static final long serialVersionUID = 1L;	
        };
	
	/** Attribut contenant le jour */
	private int jour;
	
	/** Attribut contenant le mois */
	private int mois;
	
	/** Attribut contenant l'année */
	private int annee;
	
	/**
	 * Constructeur pour une date avec en argument le jour, le mois et l'année
	 * @param jour 
	 * @param mois 
	 * @param annee 
	 */
	public Date(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	
	/**
	 * Convertit la date courante en un string
	 * @return La date sous forme de String
	 */
	@Override
	public String toString() {
		return this.jour + "-" + this.mois + "-" + this.annee;
		
	}
	
	/**
	 * Détermine si l'objet date instancié est valide, c'est à dire que le jour est correct par rapport au mois
	 * @throws ErreurDateInvalide 
	 */
	public void estValide() throws ErreurDateInvalide{
		
		if(this.mois < 1 || this.mois > 12) {
			throw new ErreurDateInvalide();
		}

		if (this.mois < 1 || this.mois > 12) {
			throw new ErreurDateInvalide();
		}
		
		if(this.annee < 0) {
			throw new ErreurDateInvalide();
		}
		
	    switch (this.mois){
		    case 4: 
		    case 6:  
		    case 9: 
		    case 11:  
			if (this.jour < 1 || this.jour > 30) {
			    //mois a 30 jours
			    throw new ErreurDateInvalide();
			}
			break;
		    case 2:
			if (this.jour < 1 || this.jour > 29) {
			    //fevrier
			    throw new ErreurDateInvalide();
			}
			break;
		    default: 
			if (this.jour < 1 || this.jour > 31) {
			    //mois a 31 jours
				throw new ErreurDateInvalide();
			}
	    }	
	    //date ok
	}

}
