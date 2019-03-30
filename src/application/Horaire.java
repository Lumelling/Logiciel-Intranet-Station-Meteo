package application;


/**
 * Classe gérant une horaire
 * @author Thibault
 *
 */
public class Horaire {

        /**
         * exception levée si l'horaire est invalide
         */
        public static class ErreurHoraireInvalide extends Exception {

			private static final long serialVersionUID = -5919640939066247609L;

        };
    
	/** Attribut contenant l'heure */
	private int heure;
	
	/** Attribut contenant la minute*/
	private int minute;

	
	/** Constructeur pour une horaire avec l'heure, la minute et la seconde en arguments 
	 * @param heure 
	 * @param minute 
	 * @param seconde */
	public Horaire(int heure, int minute) {
		this.heure = heure;
		this.minute = minute;
	}
	
	/**
	 * Convertit l'horaire en String
	 * @return String l'horaire sous forme de String
	 */
	@Override
	public String toString() {
		return this.heure + "_" + this.minute;
	}
	
	/**
	 * Détermine si l'objet horaire instancié est valide, c'est à dire que l'horaire existe bel et bien
	 * @throws ErreurHoraireInvalide 
	 */
	public void estValide() throws ErreurHoraireInvalide {

	    if(this.heure < 0 || this.heure > 23) {
		throw new ErreurHoraireInvalide();
	    }
	 
	    if(this.minute < 0 || this.minute > 59) {
		throw new ErreurHoraireInvalide();
	    }
	}

}
