/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package thread;
import javax.swing.SwingUtilities;
/**
 *
 * @author omar moheb
 */
public class Seed {

    private final int N;
    private int usedSeeds;

    public Seed(int N, int ncores) {
        this.N = N;
        this.usedSeeds = ncores;
    }

    public synchronized int getSeed() {
        if (usedSeeds > 0) {
            usedSeeds--;
            return N - usedSeeds - 1;
        } else {
            return -1;  // No more available seeds
        }
    }

    public synchronized void releaseSeed() {
        usedSeeds++;
    }
}
