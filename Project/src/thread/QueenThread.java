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
public class QueenThread extends Thread {

    private final int id;
    private final int N;
    private final Seed seed;
    private final NQueensSolver solver;

    private static String[] solutionStrings;

    public QueenThread(int id, int N, Seed seed, NQueensSolver solver) {
        this.id = id;
        this.N = N;
        this.seed = seed;
        this.solver = solver;
    }

    @Override
    public void run() {
        int[] s = new int[N];
        int semilla;

        while ((semilla = seed.getSeed()) != -1) {
            s[0] = semilla;
            backtrack(s, 1);
        }
    }

    private void backtrack(int[] s, int nivel) {
        if (nivel == N) {
            solver.incrementSolutions();
            addSolutionString(s);
            return;
        }

        for (int i = 0; i < N; i++) {
            s[nivel] = i;
            if (esValida(s, nivel)) {
                backtrack(s, nivel + 1);
            }
        }
    }

    private boolean esValida(int[] s, int nivel) {
        for (int i = 0; i < nivel; i++) {
            if (s[i] == s[nivel] || Math.abs(s[i] - s[nivel]) == nivel - i) {
                return false;
            }
        }
        return true;
    }

    private void addSolutionString(int[] s) {
        StringBuilder solution = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j == s[i]) {
                    solution.append("Q ");
                } else {
                    solution.append(". ");
                }
            }
            solution.append("\n");
        }
        solutionStrings[id] = solution.toString();
    }

    public static String[] getSolutionStrings() {
        return solutionStrings;
    }
}

