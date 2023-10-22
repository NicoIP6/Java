package NavalBattle.Prog;

import java.util.Arrays;
import java.util.Random;

public class Prog {
    // Définition des grilles
    static final int GRILLE_ATTAQUES = 0;
    static final int GRILLE_BATEAUX = 1;

    // Définition des caractères de remplissage pour les grilles
    static final char VAGUE = '~';
    static final char BATEAU = 'O';
    static final char TOUCHE = 'X';
    static final char RATE = '*';
    static Random rand = new Random();
    /**
     * Point de démarrage du programme
     * @param args Arguments reçus depuis la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("Premier programme en Java : touché-coulé !");
        System.out.println();
        System.out.println("Initialisation du jeu... \n");

        // Déclaration et initialisation de J1_Points (nombre de points du joueur 1)
        var J1_Points = 0;
        /*
            Idem pour J2_Points
            Nombre de points du joueur 2
         */
        var J2_Points = J1_Points; // J2_Points est initialisé à la même valeur que J1_Points

        // Déclaration des types de bateau et leur taille
        final var CARRIER =  5;
        final int BATTLESHIP; // = 4;
        final int DESTROYER; // = 3;
        final int SUBMARINE; // = 3;
        final int PATROL; // = 2;

        final var BOATS = new int[] {CARRIER , BATTLESHIP = 4, DESTROYER = 3, SUBMARINE = 3, PATROL = 2};

        // Déclaration d'une constante
        // final var MAX_POINTS = CARRIER + BATTLESHIP + DESTROYER + SUBMARINE + PATROL;
        final var MAX_POINTS = computeSum(BOATS);

        // Création d'un tableau de caractères pour la grille d'attaque
//        var grilleAttaque = new char[100];

        // Création d'un tableau de caractères pour la grille des bateaux
//        var grilleBateaux = new char[100];

        // Gestions des grilles Attaques et Bateaux par joueur
        var grillesJoueur1 = new char[2][100];
        var grillesJoueur2 = new char[2][100];

/*
        // Remplissage de la grille d'attaque avec des '.'
        for (var x = 0 ; x < grilleAttaque.length; x++ ) {
            grilleAttaque[x] = '.';
        }

        // Remplissage de la grille de nos bateaux avec des '.'
        Arrays.fill(grilleBateaux, '.');
*/
        fillGrid(grillesJoueur1, VAGUE);
        fillGrid(grillesJoueur2, VAGUE);
//        displayGrids(grillesJoueur1, grillesJoueur2);

//        Code pour quitter le programme avec valeur de sortie
//        System.exit(0);
/*
        // Affichage du tableau par ligne de 10 colonnes pour la grille d'attaque
        System.out.println("Grille d'attaque");
        displayGrid(grilleAttaque);
        System.out.println();

        // Affichage du tableau par ligne de 10 colonnes pour la grille des bateaux
        System.out.println("Grille des bateaux");
        displayGrid(grilleBateaux);
*/
        // Placement des bateaux
        System.out.println("\nPlacement des bateaux...");
        for (var bateau : BOATS) {
            System.out.println(bateau);
            setBoats(grillesJoueur1[GRILLE_BATEAUX], bateau);
            setBoats(grillesJoueur2[GRILLE_BATEAUX], bateau);
        }
        displayGrids(grillesJoueur1, grillesJoueur2);
/*
        System.out.println("\nGrille des bateaux");
        displayGrid(grilleBateaux);
 */
        int joueur_1 = 0;
        int jouoeur_2 = 1;
        int choix_joueur = rand.nextInt(2);
        System.out.println(choix_joueur);
        while (J1_Points < MAX_POINTS && J2_Points < MAX_POINTS) {
            if(choix_joueur == 0){
                attaque1(grillesJoueur1, grillesJoueur2, J1_Points);
                attaque2(grillesJoueur1, grillesJoueur2, J2_Points);
            }
            else{
                attaque2(grillesJoueur1, grillesJoueur2, J2_Points);
                attaque1(grillesJoueur1, grillesJoueur2, J1_Points);
            }

        }
    }

    /**
     * Remplissage des grilles Attaques et Bateaux d'un joueur
     * @param grillesJoueur les grilles d'un joueur
     * @param remplissage caractère de remplissage
     */
    static void fillGrid(char[][] grillesJoueur, char remplissage) {
        for(var grille : grillesJoueur) {
//      Affichage de la taille de la grille -> grillesJoueur[2][100] => grille[100]
//            System.out.println(grille.length);
            Arrays.fill(grille, remplissage);
//      Affichage du contenu de la grille
//            displayGrid(grille);
        }
    }

    /**
     * Cette fonction permet de placer les bateaux sur la grille.
     * Les bateaux ne peuvent pas se croiser
     * @param tableau La grille sur laquelle on va placer le bateau
     * @param taille Taille du bateau à placer
     */
    static void setBoats(char[] tableau, int taille) {
        boolean isBoatSet;
        do {
            var cell = -1;
        /*
        do {
            cell = (int) (Math.random() * tableau.length);
        } while (tableau[cell] != '.');
         */
            while (tableau[cell = (int) (Math.random() * tableau.length)] != VAGUE) ;
            //var direction = (int) (Math.random() * 4);
            var direction = NavalBattle.Direction.DIRECTION.values()[(int) (Math.random() * 4)];
            System.out.printf("Cell : %2d | Taille : %1d | Direction : %s%n", cell, taille, direction);
            isBoatSet = false;
            switch (direction) {
                case UP -> {
                    if (cell >= (taille - 1) * 10) {
                        isBoatSet = isBoatSet(tableau, taille, cell - (taille - 1) * 10, cell, 10);
                    }
                }
                case LEFT -> {
                    if (cell > 9 && (cell - taille + 1) % 10 < cell % 10) {
                        isBoatSet = isBoatSet(tableau, taille, cell - taille + 1, cell, 1);
                    }
                }
                case RIGHT -> {
                    if (cell < 90 && (cell + taille - 1) % 10 > cell % 10) {
                        isBoatSet = isBoatSet(tableau, taille, cell, cell + taille - 1, 1);
                    }
                }
                case BOTTOM -> {
                    if (cell < 100 - (taille - 1) * 10) {
                        isBoatSet = isBoatSet(tableau, taille, cell, cell + (taille - 1) * 10, 10);
                    }
                }
            }
        } while (! isBoatSet);
    }

    /**
     * Cette fonction vérifie que le bateau peut être placé comme souhaité et si c'est le cas, le bateau est encodé dans la grille
     * @param tableau La grille sur laquelle on va placer le bateau
     * @param taille Nombre de cases pour le bateau
     * @param start Case de départ pour le placement du bateau
     * @param end Case d'arrivée pour le placement sdu bateau
     * @param step Pas pour le calcul de la prochaine case
     * @return True si le bateau a pu être placé comme demandé
     */
    static boolean isBoatSet(char[] tableau, int taille, int start, int end, int step) {
        // On vérifie que toutes les cases nécessaires pour mettre le bateau soient disponibles
        for (var cell = start; cell <= end; cell += step) {
            if (tableau[cell] != VAGUE)
                return false;
        }
        // On place le bateau dans le tableau
        for (var cell = start; cell <= end; cell += step) {
            // tableau[cell] = (char) ('0' + taille);
            tableau[cell] = BATEAU;
        }
        return true;
    }

    /**
     * Calcule la somme des valeurs dans le tableau
     * @param tableau Le tableau qui contient les valeurs
     * @return La somme des valeurs dans le tableau
     */
    static int computeSum(int[] tableau) {
        var retour = 0;
        // for-each
        for (var valeur : tableau)
            retour += valeur;
        return retour;
    }

    // byte, short, char, int, long, float, double, boolean
    // Byte, Short, Character, Integer, Long, Float, Double, Boolean

    /**
     * Cette fonction permet d'afficher une grille avec les entêtes
     * @param tableau Le tableau à afficher
     */
    @Deprecated
    static void displayGrid (char[] tableau) {
        System.out.print("\t"); // Placement d'une tabulation
        for (char lettre = 'A'; lettre < 'A' + 10; System.out.print(lettre + " "), lettre++); // On affiche les lettres des colonnes
        System.out.println();
        for (var i = 0; i < tableau.length; i++) {
            if (i % 10 == 0) {
                System.out.printf(" %2d ", i / 10 + 1);
            }
            System.out.print(tableau[i] + " ");
            if (i % 10 == 9)
                System.out.println();
        }
    }

    /**
     * Affichage des grilles des 2 joueurs
     * @param grillesJoueur1 Grilles du 1er joueur
     * @param grillesJoueur2 Grilles du 2nd joueur
     */
    static void displayGrids(char[][] grillesJoueur1, char[][] grillesJoueur2) {
        System.out.println("\t\t  Grilles d'attaques des 2 joueurs");
        displayBaseGrids(grillesJoueur1[GRILLE_ATTAQUES], grillesJoueur2[GRILLE_ATTAQUES]);
        System.out.println("\t\t Grilles des bateaux des 2 joueurs");
        displayBaseGrids(grillesJoueur1[GRILLE_BATEAUX], grillesJoueur2[GRILLE_BATEAUX]);
    }
    static void attaque1(char[][] grillesJoueur1, char[][] grillesJoueur2, int J1_Points){
        int attaque = rand.nextInt(100);
        if(grillesJoueur2[GRILLE_BATEAUX][attaque] == VAGUE){
            System.out.println("Raté :P ");
            grillesJoueur1[GRILLE_ATTAQUES][attaque] = RATE;
        }
        else if(grillesJoueur2[GRILLE_BATEAUX][attaque] == BATEAU) {
            System.out.println("toucher");
            grillesJoueur1[GRILLE_ATTAQUES][attaque] = TOUCHE;
            J1_Points++;
        }
    }
    static void attaque2(char[][] grillesJoueur1, char[][] grillesJoueur2, int J2_Points){
        int attaquebis = rand.nextInt(100);
        if (grillesJoueur1[GRILLE_BATEAUX][attaquebis] == VAGUE) {
            System.out.println("Raté :P ");
            grillesJoueur2[GRILLE_ATTAQUES][attaquebis] = RATE;
        }
        else if (grillesJoueur1[GRILLE_BATEAUX][attaquebis] == BATEAU){
            System.out.println("toucher");
            grillesJoueur2[GRILLE_ATTAQUES][attaquebis] = TOUCHE;
            J2_Points++;
        }
    }
    /**
     * Affichage d'une grille par joueur pour les 2 joueurs
     * @param grillesJoueur1 Grille à afficher du 1er joueur
     * @param grillesJoueur2 Grille à afficher de 2nd joueur
     */
    static void displayBaseGrids(char[] grillesJoueur1, char[] grillesJoueur2) {
        System.out.println("\t\t  Joueur1\t\t\t\t  Joueur2");
        for (var i = 0; i < 2; i++) {
            System.out.print("\t");
            for (char lettre = 'A'; lettre < 'A' + 10; System.out.print(lettre++ + " "));
        }
        System.out.println();
        for (int i = 0, taille = grillesJoueur1.length; i < taille; i++) {
            if (i % 10 == 0) System.out.printf(" %2d ", i / 10 + 1);
            System.out.print(grillesJoueur1[i] + " ");
            if (i % 10 == 9) {
                for (var j = 0; j < 10; j++) {
                    if (j == 0) System.out.printf(" %2d ", i / 10 + j + 1);
                    System.out.print(grillesJoueur2[i - 9 + j] + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
