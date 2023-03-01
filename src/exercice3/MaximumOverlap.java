/**
 * Exercice 3 : Trouver Le Point Où Les Intervalles Maximaux Se Chevauchent
 * Instructions
 * Considérez une grande fête où un journal de bord pour les heures d'entrée et de sortie des invités est maintenu.
 * Trouvez l'heure à laquelle il y a le maximum d'invités dans la fête. Notez que les entrées dans le registre ne sont pas dans n'importe quel ordre.
 * Exemple :
 *
 * Input: arrl[] = {1, 2, 9, 5, 5}
 *        exit[] = {4, 5, 12, 9, 12}
 * First guest in array arrives at 1 and leaves at 4,
 * second guest arrives at 2 and leaves at 5, and so on.
 *
 * Output: 5
 * There are maximum 3 guests at time 5.
 *
 *
 * Vous trouverez ci-dessous une méthode simple pour résoudre ce problème.
 *
 * Parcourez tous les intervalles et trouvez le temps minimum et maximum (heure à laquelle le premier invité arrive
 * et heure à laquelle le dernier invité part)
 * Créez un tableau de comptage de taille 'max – min + 1'. Soit le tableau count[].
 * Pour chaque intervalle [x, y], exécutez une boucle pour i = x à y et faites ce qui suit en boucle.
 * compter[i – min]++ ;
 * Trouvez l'index de l'élément maximum dans le tableau count. Laissez cet index être 'max_index', retournez max_index + min.
 * La solution ci-dessus nécessite O(max-min+1) d'espace supplémentaire. La complexité temporelle de la solution ci-dessus dépend
 * également de la longueur des intervalles. Dans le pire des cas, si tous les intervalles vont de 'min' à 'max', alors la complexité
 * temporelle devient O((max-min+1)*n) où n est le nombre d'intervalles.
 *
 * Une solution efficace est d'utiliser le temps de tri n O(nLogn). L'idée est de considérer tous les événements (toutes les arrivées et
 * toutes les sorties) dans un ordre trié. Une fois que nous avons tous les événements dans l'ordre trié, nous pouvons suivre le nombre d'invités
 * à tout moment en gardant une trace des invités qui sont arrivés, mais pas sortis.
 *
 * Considérez l'exemple ci-dessus.
 *
 *         arr[]  = {1, 2, 10, 5, 5}
 *         dep[]  = {4, 5, 12, 9, 12}
 */

package exercice3;

import java.util.*;

public class MaximumOverlap {

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Interval[] intervals = {new Interval(1, 4), new Interval(2, 5), new Interval(9, 12),
                                new Interval(5, 9), new Interval(5, 12)};
        int maxOverlapPoint = findMaxOverlapPoint(intervals);
        System.out.println("Maximum overlap point: " + maxOverlapPoint);
    }

    public static int findMaxOverlapPoint(Interval[] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];

        // store start and end times in separate arrays
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }

        // sort start and end times arrays
        Arrays.sort(starts);
        Arrays.sort(ends);

        int i = 0, j = 0;
        int maxGuests = 0, currGuests = 0, maxOverlapPoint = 0;

        // merge start and end times arrays to find maximum overlap point
        while (i < n && j < n) {
            if (starts[i] <= ends[j]) {
                currGuests++;
                if (currGuests > maxGuests) {
                    maxGuests = currGuests;
                    maxOverlapPoint = starts[i];
                }
                i++;
            } else {
                currGuests--;
                j++;
            }
        }

        return maxOverlapPoint;
    }
}
