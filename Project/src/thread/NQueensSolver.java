/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;
import javax.swing.SwingUtilities;
/**
 *
 * @author omar moheb
 */
public class NQueensSolver {

    private int N;
    private int solutions;
    private int ncores;

    public NQueensSolver(int N) {
        this.N = N;
        this.solutions = 0;
        this.ncores = Runtime.getRuntime().availableProcessors();
    }

    public void solve() {
        Seed seed = new Seed(N, ncores);

        QueenThread[] threads = new QueenThread[ncores];

        for (int i = 0; i < ncores; i++) {
            threads[i] = new QueenThread(i, N, seed, this);
            threads[i].start();
        }

        try {
            for (int i = 0; i < ncores; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error joining threads");
        }
    }

    public synchronized void incrementSolutions() {
        solutions++;
    }

    public int getSolutionsCount() {
        return solutions;
    }

    public String[] getSolutions() {
        return QueenThread.getSolutionStrings();
    }

    public static void main(String[] args) {
        int N = 8; // Set the desired board size
        NQueensSolver solver = new NQueensSolver(N);
        solver.solve();
    }
}

