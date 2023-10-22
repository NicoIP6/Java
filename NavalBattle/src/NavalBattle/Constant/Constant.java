package NavalBattle.Constant;

import java.util.Random;

public class Constant {
    // Définition des grilles
    public static final int GRILLE_ATTAQUES = 0;
    public static final int GRILLE_BATEAUX = 1;

    // Définition des caractères de remplissage pour les grilles
    public static final char VAGUE = '~';
    public static final char BATEAU = 'O';
    public static final char TOUCHE = 'X';
    public static final char RATE = '*';
    public static Random rand = new Random();
}
