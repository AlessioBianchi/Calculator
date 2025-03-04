package main;
import controller.CalculatorController;
import model.CalculatorModel;
import view.CalculatorView;

public class CalculatorApp {
    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        CalculatorModel model = new CalculatorModel();
        CalculatorController controller = new CalculatorController(view, model);

        controller.update();
    }
}
