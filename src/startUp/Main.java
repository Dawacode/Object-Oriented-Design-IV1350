/**
 * The Main class serves as the entry point for the application. It initializes the Controller and View components
 * to start the system.
 */
package startUp;

import controller.Controller;
import view.View;

public class Main {
    /**
     * The main method instantiates a Controller and a View, initializing the system.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
    }
}