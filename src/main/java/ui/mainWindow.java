package ui;

import org.apache.commons.lang3.tuple.Pair;
import data.Polinom;
import repository.implementation.PolinomRepositoryImpl;
import service.PolinomService;
import single_point_access.ServiceSinglePointAccess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class mainWindow {
    private JTextField boxPol1;
    private JTextField boxPol2;
    private JLabel textPol1;
    private JLabel textPol2;
    private JLabel textResult;
    private JLabel boxResult;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton derivativeButton;
    private JButton primitiveButton;
    private JPanel mainPanel;

    private final PolinomService polinomService = ServiceSinglePointAccess.getPolinomService();
    public mainWindow() {

        mainPanel.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        textPol1.setBorder(new EmptyBorder(0, 5, 10, 10));
        textPol2.setBorder(new EmptyBorder(0, 5, 10, 10));
        textResult.setBorder(new EmptyBorder(0, 5, 10, 10));
        addButton.addActionListener(e -> {
            if(!boxPol1.getText().isEmpty() && !boxPol2.getText().isEmpty() && boxPol1.getText().matches("[\\sx\\d+\\-\\^]+") && boxPol2.getText().matches("[\\sx\\d+\\-\\^]+")) {
                Polinom p1 = polinomService.parsePolinom(boxPol1.getText());
                Polinom p2 = polinomService.parsePolinom(boxPol2.getText());
                Polinom p3 = polinomService.addPolinom(p1,p2);
                boxResult.setText(p3.prettyPrint());
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid polynomial!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        subtractButton.addActionListener(e -> {
            if(!boxPol1.getText().isEmpty() && !boxPol2.getText().isEmpty() && boxPol1.getText().matches("[\\sx\\d+\\-\\^]+") && boxPol2.getText().matches("[\\sx\\d+\\-\\^]+")) {
                Polinom p1 = polinomService.parsePolinom(boxPol1.getText());
                Polinom p2 = polinomService.parsePolinom(boxPol2.getText());
                Polinom p3 = polinomService.subPolinom(p1,p2);
                boxResult.setText(p3.prettyPrint());

            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid polynomial!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        multiplyButton.addActionListener(e -> {
            if(!boxPol1.getText().isEmpty() && !boxPol2.getText().isEmpty() && boxPol1.getText().matches("[\\sx\\d+\\-\\^]+") && boxPol2.getText().matches("[\\sx\\d+\\-\\^]+")) {
                Polinom p1 = polinomService.parsePolinom(boxPol1.getText());
                Polinom p2 = polinomService.parsePolinom(boxPol2.getText());
                Polinom p3 = polinomService.multiplyPolinom(p1,p2);
                boxResult.setText(p3.prettyPrint());
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid polynomial!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        derivativeButton.addActionListener(e -> {
            if(!boxPol1.getText().isEmpty() && boxPol1.getText().matches("[\\sx\\d+\\-\\^]+")) {
                boxPol2.setText("");
                Polinom p1 = polinomService.parsePolinom(boxPol1.getText());
                Polinom p3 = polinomService.derivPolinom(p1);
                boxResult.setText(p3.prettyPrint());
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid polynomial!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        primitiveButton.addActionListener(e -> {
            if(!boxPol1.getText().isEmpty() && boxPol1.getText().matches("[\\sx\\d+\\-\\^]+")) {
                boxPol2.setText("");
                Polinom p1 = polinomService.parsePolinom(boxPol1.getText());
                Polinom p3 = polinomService.intPolinom(p1);
                boxResult.setText(p3.prettyPrint() + " + \uD835\uDC9E");
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid polynomial!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        divideButton.addActionListener(e -> {
            if(!boxPol1.getText().isEmpty() && !boxPol2.getText().isEmpty() && boxPol1.getText().matches("[\\sx\\d+\\-\\^]+") && boxPol2.getText().matches("[\\sx\\d+\\-\\^]+")) {
                Polinom p1 = polinomService.parsePolinom(boxPol1.getText());
                Polinom p2 = polinomService.parsePolinom(boxPol2.getText());
                Pair<Polinom, Polinom> res =  polinomService.divPolinom(p1,p2);
                boxResult.setText("Quotient: " + res.getLeft().prettyPrint() + " ; Remainder: " + res.getRight().prettyPrint());
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid polynomial!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
