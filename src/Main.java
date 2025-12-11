import controller.Controller;
import model.Model;
import view.View;

public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller(new View(), new Model());

    }
}