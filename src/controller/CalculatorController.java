package controller;

import javax.swing.*;

import model.CalculatorModel;
import observer.Observer;
import view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorController implements Observer {
    private final CalculatorView view;
    private final CalculatorModel model;

    public CalculatorController (CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;

        this.view.addCalculationListener(new CalculateListener());
        this.view.addOperationListener(new OperationListener());
        this.model.registerObserver(this);
    }

    private class CalculateListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            String numberUserPressed = ((JButton)e.getSource()).getText();
            model.addDigit(numberUserPressed);
        }
    }

    private class OperationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String operationPressed = ((JButton)e.getSource()).getText();
            
            model.incCounter();
            
            switch (operationPressed) {
            case ".":
            	if (Objects.equals(operationPressed, ".") && !model.getNumber().contains(".")) {
                	model.addDigit(operationPressed);
                }
            	break;
            case "+/-":
            	double n = Double.parseDouble(model.getNumber());
            	n = n * (-1);
            	model.setNumber(String.valueOf(n));
            	break;
            case "=":
            	double result = model.makeOperation();
            	model.setNumber(String.valueOf(result));
            	break;
        	default:
        		if (model.getCounter() >= 2) {
        			model.setPreviousNumber(String.valueOf(model.makeOperation()));
        		} else {
        			model.setPreviousNumber(model.getNumber());
        		}
        		model.setCurrentTypeOfOperation(operationPressed);
                model.setNumber("0");
        		break;
            }
        }
    }

    @Override
    public void update() {
        view.setNumber(model.getNumber());
        view.setPreviousNumber(model.getPreviousNumber(), model.getCurrentTypeOfOperation());
    }
}
