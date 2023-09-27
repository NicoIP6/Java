package NavalBattle;

import java.util.Arrays;

public class Prog {
    public static void main(String[] args) {
        System.out.println("Premier programme en java : touché-coulé !\n");
        System.out.println("Initialisation du jeu ...\n");

        //Déclaration et initialisation de J1_points
        var J1_Points = 0;
        
        /*
            Idem pour J2
            Nombre de point J2
         */
        var J2_Points = 0;

        //Déclaration d'une constante
        final var MAX_POINTS = 5 + 4 + 3 + 3 + 2;

        //Création d'un tableau pour la grille d'attaque
        var grilleAttaque = new char[100];
        // une autre façon de déclarer un tableau : int[] tabEntier = {1, 2, 3, 4, 5, 6, 7};

        // Creation d'un tableau de caractère pour la grille des bateaux
        var grilleBateau = new char[100];

        /*
        Technique pour emplir un tableau avec une boucle for :
        for (var i = 0; i < grilleAttaque.length; i++) {
            grilleAttaque[i] = '.';
        }
        */
        
        // Remplissage de la grille d'attaque avec des "."
        Arrays.fill(grilleAttaque, '.');
        
        // Remplissage de la grille de bateaux avec des "."
        Arrays.fill(grilleBateau, '.');

        //System.out.println(Arrays.toString(grilleBateau) + "\n");
        System.out.println("\n Grille Attaque :\n");
        displayGrid(grilleAttaque);
        System.out.println("\n Grille Bateau :\n");
        displayGrid(grilleBateau);
    }
    
    /**
     * This function make possible to display the grid with named row and column
     * @param tableau table to display
     */
     
    static void displayGrid (char[] tableau) {
            System.out.println();
            for (char lettre = 'A'; lettre < 'A' + 10; System.out.print("\t" + lettre++));
            System.out.println();
            for (int i = 0; i < tableau.length; i++){
                if (i % 10 == 0){
                    System.out.print(i / 10 + 1 + "\t");
                    //System.out.printf("%2d  ", i /10 + 1); //see format doc
                }
                System.out.print(tableau[i] + "\t");
                if (i % 10 == 9){
                    System.out.println();
                }
            }
        }
    }
