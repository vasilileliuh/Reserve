package main;

import org.apache.log4j.Logger;
import source.Point;
import source.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {
//        Point a = new Point(0, 0);
//        Point b = new Point(0, 10);
//        Point c = new Point(5, 15);
//
//        Vector ab = new Vector(a, b);
//        Vector ac = new Vector(a, c);

//        double length = Vector.module(ab);
//        Vector sumOfTwoVectors = Vector.sum(ab, ac);
//        LOGGER.info("Длинна вектора \'" + ab + "\' равна: " + length);
//        LOGGER.info("Сумма векторов ab + ac= " + sumOfTwoVectors);
//
//        double angle = Vector.angleBetweenVectors(ab, ac);
//
//        LOGGER.info("Угол между векторами " + ab + " и " + ac + " равен: " + angle);
//
//        Vector de = new Vector(new Point(1, 0), new Point(2, 2));
//        Vector df = new Vector(new Point(1, 0), new Point(3, 3));
//
//        Vector sumOfTwoVectors2 = Vector.sum(de, df);
//        LOGGER.info("Сумма векторов de + df= " + sumOfTwoVectors2);
//        double angle2 = Vector.angleBetweenVectors(de, df);
//        LOGGER.info("Угол между векторами " + de + " и " + df + " равен: " + angle2);

        JFrame frameOne = new JFrame("Vector calculator");//creating instance of JFrame
        frameOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame frameTwo = new JFrame("Vector calculator result");
        frameTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton buttonAddition = new JButton("Plus");//creating instance of JButton
        JButton buttonSubtraction = new JButton("Minus");//creating instance of JButton
        buttonAddition.setBounds(70, 200, 100, 40);//x axis, y axis, width, height
        buttonSubtraction.setBounds(240, 200, 100, 40);//x axis, y axis, width, height

        final JTextField tfVectorOneStartP = new JTextField();
        final JTextField tfVectorOneEndP = new JTextField();
        final JTextField tfVectorTwoStartP = new JTextField();
        final JTextField tfVectorTwoEndP = new JTextField();
        tfVectorOneStartP.setBounds(140, 50, 80, 30);
        tfVectorTwoStartP.setBounds(140, 110, 80, 30);
        tfVectorOneEndP.setBounds(240, 50, 80, 30);
        tfVectorTwoEndP.setBounds(240, 110, 80, 30);

        tfVectorOneStartP.setText("0,0");
        tfVectorOneEndP.setText("0,10");
        tfVectorTwoStartP.setText("0,0");
        tfVectorTwoEndP.setText("5,15");


        buttonAddition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstVectorPOneCoordinate = tfVectorOneStartP.getText();
                String firstVectorPTwoCoordinate = tfVectorOneEndP.getText();
                String secondVectorPOneCoordinate = tfVectorTwoStartP.getText();
                String secondVectorPTwoCoordinate = tfVectorTwoEndP.getText();
                Vector v1 = new Vector(makePoint(firstVectorPOneCoordinate), makePoint(firstVectorPTwoCoordinate));
                Vector v2 = new Vector(makePoint(secondVectorPOneCoordinate), makePoint(secondVectorPTwoCoordinate));
                Vector result = Vector.sum(v1, v2);
                JLabel label = new JLabel("Resulting vector = (" + String.valueOf(Math.round(result.getPROJECTION_X()) +
                        ", " + Math.round(result.getPROJECTION_Y())) + ")");
                label.setBounds(30, 100, 300, 30);
                frameTwo.add(label);
                frameOne.setVisible(false); //hides it temporarily
                frameTwo.setVisible(true); //shows it
            }
        });
        buttonSubtraction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstVectorPOneCoordinate = tfVectorOneStartP.getText();
                String firstVectorPTwoCoordinate = tfVectorOneEndP.getText();
                String secondVectorPOneCoordinate = tfVectorTwoStartP.getText();
                String secondVectorPTwoCoordinate = tfVectorTwoEndP.getText();
            }
        });

        JLabel l1, l2;
        l1 = new JLabel("Vector AB:");
        l2 = new JLabel("Second CD:");

        l1.setBounds(30, 50, 100, 30);
        l2.setBounds(30, 110, 100, 30);


        frameOne.add(buttonAddition);//adding button in JFrame
        frameOne.add(buttonSubtraction);
        frameOne.add(tfVectorOneStartP);
        frameOne.add(tfVectorOneEndP);
        frameOne.add(tfVectorTwoStartP);
        frameOne.add(tfVectorTwoEndP);

        frameOne.add(l1);
        frameOne.add(l2);

        frameTwo.setSize(410, 320);
        frameTwo.setLayout(null);//using no layout man

        frameOne.setSize(410, 320);
        frameOne.setLayout(null);//using no layout managers
        frameOne.setVisible(true);//making the frame visible

    }

    private static Vector makeNewVector(int startPX, int startPY, int endPX, int endPY) {
        return new Vector(new Point(startPX, startPY), new Point(endPX, endPY));
    }

    private static Point makePoint(String input) {
        String[] parts = input.split(",");
        return new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

}

