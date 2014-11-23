package mvc.controllers;

import mvc.exceptions.InputException;
import mvc.interfaces.IController;
import mvc.interfaces.IView;
import mvc.model.WeatherModel;
import mvc.utils.WeatherAction;
import org.json.JSONException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.Console;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class WeatherController implements IController {
    // The Controller needs to interact with both the Model and View.
    private WeatherModel mModel;

    // The list of views that listen for updates
    private List<IView> mViews;

    /**
     * Constructor
     */
    public WeatherController() {
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ACTION_UPDATE)) {
            // Make the operation
            try {
                JButton source = (JButton) event.getSource();
                if (source != null) {
                    makeOperation();
                } else {
                    notifyViews(true, "Invalid operation data");
                }
            } catch (InputException e) {
                notifyViews(true, e.getMessage());
            } catch (ClassCastException ec) {
                notifyViews(true, ec.getMessage());
            } catch (JSONException e) {
                notifyViews(true, e.getMessage());
            }
        }
    }

    /**
     * Adds a view reference in order to interact with it
     *
     * @param view The view from the controller will receive events and send messages
     */
    public void addView(IView view) {
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }

        mViews.add(view);
    }

    /**
     * Adds a reference to the model, so it can update it
     *
     * @param model The data model reference
     */
    public void addModel(WeatherModel model) {
        mModel = model;
    }

    /**
     * Notifies the views when an message must be displayed
     *
     * @param isError {@code true} if the message is an error, {@code false} otherwise
     * @param message The string to be displayed
     */
    private void notifyViews(boolean isError, String message) {
        if (mViews != null && !mViews.isEmpty()) {
            for (IView view : mViews) {
                view.onMessage(isError, message);
            }
        }
    }

    /**
     * Update the weather data: temperature and wind
     */
    private void makeOperation() throws InputException, JSONException {
        if (mModel != null) {

            WeatherAction weatherAction = new WeatherAction();
            String[] weather = weatherAction.getWeatherData();
            try {
                // Update the model
                mModel.setTemperatureValue(Float.parseFloat(weather[0]));
            } catch (NumberFormatException e) {
                throw new InputException(weather[0], e.getMessage());
            }


            try {
                mModel.setWindValue(Float.parseFloat(weather[1]));
            } catch (NumberFormatException e) {
                throw new InputException(weather[1], e.getMessage());
            }
        }
    }
}
