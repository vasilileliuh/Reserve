package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class SwingGUI {
    static JFrame frameOne, frameTwo;
    JLabel jlOne, jlTwo;
    static JLabel jlThree;
    JTextField tfVectorOneStartP,
            tfVectorOneEndP,
            tfVectorTwoStartP,
            tfVectorTwoEndP;
    JButton buttonAddition, buttonSubtraction, buttonTryAgain;


    public SwingGUI() {
        frameOne = new JFrame("Vector calculator");//creating instance of JFrame
        frameOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTwo = new JFrame("Vector calculator result");
        frameTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonAddition = new JButton("Plus");//creating instance of JButton
        buttonSubtraction = new JButton("Minus");
        buttonAddition.setBounds(70, 200, 100, 40);//x axis, y axis, width, height
        buttonSubtraction.setBounds(240, 200, 100, 40);
        buttonTryAgain = new JButton("Try Again");
        buttonTryAgain.setBounds(155, 200, 100, 40);//x axis, y axis, width, height

        tfVectorOneStartP = new JTextField();
        tfVectorOneEndP = new JTextField();
        tfVectorTwoStartP = new JTextField();
        tfVectorTwoEndP = new JTextField();
        tfVectorOneStartP.setBounds(160, 50, 80, 30);
        tfVectorTwoStartP.setBounds(160, 110, 80, 30);
        tfVectorOneEndP.setBounds(260, 50, 80, 30);
        tfVectorTwoEndP.setBounds(260, 110, 80, 30);
        tfVectorOneStartP.setHorizontalAlignment(JTextField.RIGHT);
        tfVectorOneEndP.setHorizontalAlignment(JTextField.RIGHT);
        tfVectorTwoStartP.setHorizontalAlignment(JTextField.RIGHT);
        tfVectorTwoEndP.setHorizontalAlignment(JTextField.RIGHT);
        tfVectorOneStartP.setMargin(new Insets(0, 5, 0, 5));
        tfVectorOneEndP.setMargin(new Insets(0, 5, 0, 5));
        tfVectorTwoStartP.setMargin(new Insets(0, 5, 0, 5));
        tfVectorTwoEndP.setMargin(new Insets(0, 5, 0, 5));

        tfVectorOneStartP.setText("0,0");
        tfVectorOneEndP.setText("0,10");
        tfVectorTwoStartP.setText("0,0");
        tfVectorTwoEndP.setText("5,15");

        buttonAddition.addActionListener(e -> {
            try {
                Vector result = Vector.sum(makeVectorFromInput(tfVectorOneStartP.getText(), tfVectorOneEndP.getText()),
                        makeVectorFromInput(tfVectorTwoStartP.getText(), tfVectorTwoEndP.getText()));
                showResult(result, e);
            } catch (Exception exception) {
                showResult();
            }

        });
        buttonSubtraction.addActionListener(e -> {
            try {
                Vector result = Vector.subtraction(makeVectorFromInput(tfVectorOneStartP.getText(), tfVectorOneEndP.getText()),
                        makeVectorFromInput(tfVectorTwoStartP.getText(), tfVectorTwoEndP.getText()));
                showResult(result, e);
            } catch (Exception exception) {
                showResult();
            }
        });
        buttonTryAgain.addActionListener(e -> {
            frameOne.setVisible(true);
            frameTwo.setVisible(false);
        });

        jlOne = new JLabel("Vector AB:");
        jlTwo = new JLabel("Second CD:");
        jlThree = new JLabel();
        jlOne.setBounds(70, 50, 100, 30);
        jlTwo.setBounds(70, 110, 100, 30);

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
        frameTwo.add(buttonTryAgain);
        frameTwo.setLayout(null);
    }

    private static Vector makeVectorFromInput(String StartPoint, String EndPoint) throws NumberFormatException {
        return new Vector(makePoint(StartPoint), makePoint(EndPoint));
    }

    private static Point makePoint(String input) throws NumberFormatException {
        String[] parts = input.split(",");
        try {
            return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
//            LOGGER.info(e.getClass().getName());
            throw new NumberFormatException();
        }
    }

    private static void showResult() {
        jlThree.setText("Something went wrong!");
        jlThree.setBounds(140, 100, 150, 30);
        frameManagement();
    }

    private static void showResult(Vector result, ActionEvent event) {
        String command = event.getActionCommand();
        switch (command) {
            case "Plus":
                jlThree.setText("Sum resulting vector = (" + Math.round(result.getPROJECTION_X()) +
                        ", " + Math.round(result.getPROJECTION_Y()) + ")");
                break;
            case "Minus":
                jlThree.setText("Subtraction resulting vector = (" + Math.round(result.getPROJECTION_X()) +
                        ", " + Math.round(result.getPROJECTION_Y()) + ")");
                break;
            default:
                jlThree.setText("Something went wrong!");
        }
        jlThree.setBounds(116, 100, 250, 30);
        frameManagement();
    }

    private static void frameManagement() {
        frameTwo.add(jlThree);
        frameOne.setVisible(false);
        frameTwo.setVisible(true);
    }
}