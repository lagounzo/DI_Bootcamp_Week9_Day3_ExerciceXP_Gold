/**
 * Exercice 2 : Trier Un Tableau Selon L'ordre Défini Par Un Autre Tableau
 * Instructions
 * Étant donné deux tableaux A1[] et A2[], triez A1 de manière à ce que l'ordre relatif entre les éléments soit le
 * même que celui de A2. Pour les éléments non présents dans A2, ajoutez-les enfin dans l'ordre trié.
 *
 * Exemple:
 *
 * Input: A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8}
 *            A2[] = {2, 1, 8, 3}
 * Output: A1[] = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}
 *
 * Input: A1[] = {4, 5, 1, 1, 3, 2}
 *            A2[] = {3, 1}
 * Output: A1[] = {3, 1, 1, 2, 4, 5}
 *
 *
 * Soit la taille de A1[] soit m et la taille de A2[] soit n.
 *
 * Créez un tableau temporaire de taille m et copiez-y le contenu de A1[].
 * Créez un autre tableau visité [] et initialisez toutes les entrées qu'il contient comme fausses. visité[]
 * est utilisé pour marquer les éléments de temp[] qui sont copiés dans A1[].
 * Tri temp[]
 * Initialisez l'index de sortie ind à 0.
 * Faites ce qui suit pour chaque élément de A2[i] dans A2[]
 * Recherche binaire de toutes les occurrences de A2[i] dans temp[], si elles sont présentes, copiez toutes les occurrences dans A1[ind]
 * et incrémentez ind. Marquez également les éléments copiés visités[]
 * Copiez tous les éléments non visités de temp[] à A1[]
 */

package exercice2;

import java.util.*;

public class SortArray {

    public static void main(String[] args) {
        int[] A1 = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
        int[] A2 = {2, 1, 8, 3};
        int[] sortedArray = sortArray(A1, A2);
        System.out.println(Arrays.toString(sortedArray));
    }

    public static int[] sortArray(int[] A1, int[] A2) {
        int m = A1.length;
        int n = A2.length;

        // create a temporary array and copy the contents of A1 to it
        int[] temp = new int[m];
        for (int i = 0; i < m; i++) {
            temp[i] = A1[i];
        }

        // create a visited array and initialize all entries as false
        boolean[] visited = new boolean[m];

        // sort the temporary array
        Arrays.sort(temp);

        // initialize the output index as 0
        int ind = 0;

        // loop through every element in A2
        for (int i = 0; i < n; i++) {
            // binary search for all occurrences of A2[i] in temp
            int low = 0;
            int high = m - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp[mid] == A2[i]) {
                    // copy all occurrences to A1[ind] and mark them visited
                    int j = mid;
                    while (j < m && temp[j] == A2[i]) {
                        if (!visited[j]) {
                            A1[ind] = temp[j];
                            ind++;
                            visited[j] = true;
                        }
                        j++;
                    }
                    break;
                } else if (temp[mid] < A2[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        // copy all unvisited elements from temp to A1
        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                A1[ind] = temp[i];
                ind++;
            }
        }

        return A1;
    }
}
