package controller;

import exception.DivisionByZeroException;
import model.Model;
import view.View;

public class Controller {

    public Controller(View view, Model model){
        applyNumberListener(view, model);
        applyOperationListener(view, model);
    }

    private void applyNumberListener(View view, Model model) {
        view.addNumberListener(e -> {
            String numberPressed = e.getActionCommand();

            model.updateCurrent(numberPressed);
            view.updateDisplay(model.getDisplay());
        });
    }

    private void applyOperationListener(View view, Model model) {
        view.addOperationListener(e -> {
            try {
                String operationPressed = e.getActionCommand();

                switch (operationPressed) {
                    case "<=" -> model.goBack();
                    case "AC" -> model.clear();
                    case "%" -> model.calculatePercentage();
                    case "+/-" -> model.calculateNegative();
                    case "=" -> model.getResult();
                    default -> model.updateOperation(operationPressed);
                }

                view.updateDisplay(model.getDisplay());
            } catch (DivisionByZeroException exception) {
                model.clear();
                view.updateDisplay(exception.getMessage());
            }
        });
    }
}
