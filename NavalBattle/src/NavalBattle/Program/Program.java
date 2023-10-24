package NavalBattle.Program;
import static NavalBattle.Function.Function.*;
import static NavalBattle.Constant.Constant.*;

public class Program {
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
       /*int joueur_1 = 0;
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

        }*/
    }
}
