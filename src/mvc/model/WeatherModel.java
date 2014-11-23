package mvc.model;

import mvc.exceptions.InputException;
import mvc.interfaces.IModelListener;

import java.util.ArrayList;
import java.util.List;

public class WeatherModel {

    // Constants
    public static final String INITIAL_VALUE = "0";

    // Member variable defining state of calculator, the total current value
    private float temperature;
    private float wind;

    private List<IModelListener> mListeners;

    /**
     * Constructor
     */
    public WeatherModel() {
        temperature = Float.parseFloat(INITIAL_VALUE);
        wind = Float.parseFloat(INITIAL_VALUE);
    }

    /**
     * Set the temperature.
     *
     * @param value New value that should be used for the temperature.
     */
    public void setTemperatureValue(float value) throws InputException {
        try {
            temperature = value;
            notifyListeners();
        } catch (NumberFormatException e) {
            throw new InputException(value, e.getMessage());
        }
    }

    /**
     * Return current temperature value.
     */
    public float getTemperatureValue() {
        return temperature;
    }


    /**
     * Set the wind.
     *
     * @param value New value that should be used for the wind.
     */
    public void setWindValue(float value) throws InputException {
        try {
            wind = value;
            notifyListeners();
        } catch (NumberFormatException e) {
            throw new InputException(value, e.getMessage());
        }
    }

    /**
     * Return current temperature value.
     */
    public float getWindValue() {
        return wind;
    }


    /**
     * Adds the view listener to the list
     *
     * @param listener The model event listener
     */
    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }
}
