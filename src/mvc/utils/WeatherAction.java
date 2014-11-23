package mvc.utils;

import mvc.exceptions.InputException;
import org.json.JSONException;

/**
 * Created by Cosovanu Vasile on 18/11/2014.
 *
 * The action tied to the update button
 */
public class WeatherAction {

    protected static final String initRequest = "?q=Bucharest,ro";

    public String[] getWeatherData() throws JSONException, InputException {
        String data = (new WeatherHttpClient()).getWeatherData(initRequest);
        try {
            return JSONWeatherParser.getWeather(data);
        } catch (JSONException e) {
            throw new InputException(data, e.getMessage());
        }
    }
}
