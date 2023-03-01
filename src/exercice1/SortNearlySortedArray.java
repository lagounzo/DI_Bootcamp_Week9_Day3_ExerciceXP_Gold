/**
 * Exercice 1 : Trier Un Tableau Presque Trié (Ou K Trié)
 * Instructions
 * Étant donné un tableau de N éléments, où chaque élément est au plus éloigné de K de sa position cible, concevez un algorithme qui trie en temps O(N log K).
 *
 * Exemples:
 *
 * Input: arr[] = {6, 5, 3, 2, 8, 10, 9}, K = 3
 * Output: arr[] = {2, 3, 5, 6, 8, 9, 10}
 * Input: arr[] = {10, 9, 8, 7, 4, 70, 60, 50}, k = 4
 * Output: arr[] = {4, 7, 8, 9, 10, 50, 60, 70}
 *
 *
 * Utilisation Du Tri Par Insertion :
 * Pour résoudre le problème, suivez l'idée ci-dessous :
 *
 * Nous pouvons utiliser le tri par insertion pour trier efficacement
 * le tableau car l'index de chaque élément peut être modifié d'au plus K places, ce qui réduira la complexité temporelle de cet algorithme
 * de O(N2) à O(NK).
 *
 * Suivez les étapes ci-dessous pour résoudre le problème :
 *
 * Itérer de arr[1] à arr[N] sur le tableau.
 * Comparez l'élément actuel (clé) à son prédécesseur.
 * Si l'élément clé est plus petit que son prédécesseur, comparez-le aux éléments précédents. Déplacez les éléments les plus
 * grands d'une position vers le haut pour faire de la place à l'élément échangé.
 */

package exercice1;

import java.util.PriorityQueue;

public class SortNearlySortedArray {
    public static void sort(int[] arr, int k) {
        int n = arr.length;
        
        // Create a min-heap of size k+1
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k+1);
        
        // Insert the first k+1 elements into the heap
        for (int i = 0; i <= k; i++) {
            minHeap.add(arr[i]);
        }
        
        // Process the remaining elements
        for (int i = k+1; i < n; i++) {
            // Insert the current element into the heap
            minHeap.add(arr[i]);
            
            // Extract the minimum element from the heap
            int min = minHeap.poll();
            
            // Replace the extracted element with the newly inserted element
            arr[i-k-1] = min;
        }
        
        // Sort the remaining elements in the heap using insertion sort
        while (!minHeap.isEmpty()) {
            arr[n-k-1] = minHeap.poll();
            k--;
        }
        
        // The array is now sorted
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 7, 4, 70, 60, 50, 100};
        int k = 3;
        sort(arr, k);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
