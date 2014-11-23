package mvc;

import mvc.controllers.WeatherController;
import mvc.model.WeatherModel;
import mvc.views.WeatherView;

/**
 * @author Cosovanu Vasile
 *
 * This is the Main class that represents the entry point to our program
 */

public class WeatherMain {

    public static void main(String[] args) {

        // Instantiate the MVC elements
        WeatherModel model = new WeatherModel();
        WeatherView view = new WeatherView();
        WeatherController controller = new WeatherController();

        // Attach the view to the model
        model.addModelListener(view);

        // Tell the view about the model and the controller
        view.addModel(model);
        view.addController(controller);

        // Tell the controller about the model and the view
        controller.addModel(model);
        controller.addView(view);

        // Display the view
        view.setVisible(true);
    }

}
