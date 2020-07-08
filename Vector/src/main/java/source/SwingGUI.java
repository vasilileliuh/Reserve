package source;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SwingGUI {
    static JFrame frameOne, frameTwo;
    JLabel jlOne, jlTwo;
    JTextField tfVectorOneStartP,
            tfVectorOneEndP,
            tfVectorTwoStartP,
            tfVectorTwoEndP;
    JButton buttonAddition, buttonSubtraction;


    public SwingGUI() {
        frameOne = new JFrame("Vector calculator");//creating instance of JFrame
        frameOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTwo = new JFrame("Vector calculator result");
        frameTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonAddition = new JButton("Plus");//creating instance of JButton
        buttonSubtraction = new JButton("Minus");
        buttonAddition.setBounds(70, 200, 100, 40);//x axis, y axis, width, height
        buttonSubtraction.setBounds(240, 200, 100, 40);

        tfVectorOneStartP = new JTextField();
        tfVectorOneEndP = new JTextField();
        tfVectorTwoStartP = new JTextField();
        tfVectorTwoEndP = new JTextField();
        tfVectorOneStartP.setBounds(140, 50, 80, 30);
        tfVectorTwoStartP.setBounds(140, 110, 80, 30);
        tfVectorOneEndP.setBounds(240, 50, 80, 30);
        tfVectorTwoEndP.setBounds(240, 110, 80, 30);

        tfVectorOneStartP.setText("0,0");
        tfVectorOneEndP.setText("0,10");
        tfVectorTwoStartP.setText("0,0");
        tfVectorTwoEndP.setText("5,15");

        buttonAddition.addActionListener(e -> {
            Vector result = Vector.sum(makeVectorFromInput(tfVectorOneStartP.getText(), tfVectorOneEndP.getText()),
                    makeVectorFromInput(tfVectorTwoStartP.getText(), tfVectorTwoEndP.getText()));
            showResult(result, e);
        });
        buttonSubtraction.addActionListener(e -> {
            Vector result = Vector.subtraction(makeVectorFromInput(tfVectorOneStartP.getText(), tfVectorOneEndP.getText()),
                    makeVectorFromInput(tfVectorTwoStartP.getText(), tfVectorTwoEndP.getText()));
            showResult(result, e);
        });

        jlOne = new JLabel("Vector AB:");
        jlTwo = new JLabel("Second CD:");
        jlOne.setBounds(30, 50, 100, 30);
        jlTwo.setBounds(30, 110, 100, 30);

        frameOne.add(buttonAddition);//adding button in JFrame
        frameOne.add(buttonSubtraction);
        frameOne.add(tfVectorOneStartP);
        frameOne.add(tfVectorOneEndP);
        frameOne.add(tfVectorTwoStartP);
        frameOne.add(tfVectorTwoEndP);
        frameOne.add(jlOne);
        frameOne.add(jlTwo);
        frameOne.setSize(410, 320);
        frameOne.setLayout(null);//using no layout managers
        frameOne.setVisible(true);//making the frame visible

        frameTwo.setSize(410, 320);
        frameTwo.setLayout(null);//using no layout man
    }


    private static Vector makeVectorFromInput(String StartPoint, String EndPoint) {
        return new Vector(makePoint(StartPoint), makePoint(EndPoint));
    }

    private static Point makePoint(String input) {
        String[] parts = input.split(",");
        return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    private static void showResult(Vector result, ActionEvent event) {
        String command = event.getActionCommand();
        JLabel label;
        switch (command) {
            case "Plus":
                label = new JLabel("Sum resulting vector = (" + Math.round(result.getPROJECTION_X()) +
                        ", " + Math.round(result.getPROJECTION_Y()) + ")");
                break;
            case "Minus":
                label = new JLabel("Subtraction resulting vector = (" + Math.round(result.getPROJECTION_X()) +
                        ", " + Math.round(result.getPROJECTION_Y()) + ")");
                break;
            default:
                label = new JLabel("Something went wrong!");
        }
        label.setBounds(30, 100, 300, 30);
        frameTwo.add(label);
        frameOne.setVisible(false);
        frameTwo.setVisible(true);
    }

}