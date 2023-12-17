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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NQueensGUI extends JFrame {

    private JTextField textField;
    private JButton solveButton;
    private JTextArea resultArea;

    public NQueensGUI() {
        setTitle("N-Queens Solver");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        textField = new JTextField();
        solveButton = new JButton("Solve");
        resultArea = new JTextArea();

        panel.add(new JLabel("Enter N:"));
        panel.add(textField);
        panel.add(solveButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveNQueens();
            }
        });
    }

    private void solveNQueens() {
        resultArea.setText(""); // Clear previous results

        String input = textField.getText();
        try {
            int N = Integer.parseInt(input);
            if (N > 0) {
                NQueensSolver solver = new NQueensSolver(N);
                solver.solve();
                System.out.println("Thread run");
                displaySolutions(solver.getSolutions());
            } else {
                resultArea.setText("Please enter a positive integer.");
            }
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input. Please enter a positive integer.");
        }
    }

    private void displaySolutions(String[] solutions) {
        for (String solution : solutions) {
            resultArea.append(solution + "\n");
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new NQueensGUI().setVisible(true);
//        });
//    }
}

