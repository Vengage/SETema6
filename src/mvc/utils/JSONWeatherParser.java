package mvc.utils;

import mvc.exceptions.InputException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Cosovanu Vasile on 11/23/2014.
 *
 * Class for parsing the data returned by the http client
 */
public class JSONWeatherParser {

    public static String[] getWeather(String data) throws JSONException, InputException {
        String[] weather;
        weather = new String[2];

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        // Temperature
        JSONObject mainObj = getObject("main", jObj);
        try {
            weather[0] = Float.toString(getFloat("temp", mainObj));
        } catch (JSONException e) {
            throw new InputException(weather[0], e.getMessage());
        }

        // Wind
        JSONObject wObj = getObject("wind", jObj);
        try {
            weather[1] = Float.toString(getFloat("speed", wObj));
        } catch (JSONException e) {
            throw new InputException(weather[1], e.getMessage());
        }

        return weather;
    }

    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        return jObj.getJSONObject(tagName);
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}

