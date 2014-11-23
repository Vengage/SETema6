package mvc.views;

import mvc.interfaces.IController;
import mvc.interfaces.IModelListener;
import mvc.interfaces.IView;
import mvc.model.WeatherModel;

import javax.swing.*;
import java.awt.*;


public class WeatherView extends JFrame implements IModelListener, IView {

    private static final long serialVersionUID = -5758555454500685115L;

    // View Components
    private JButton mUpdateBtn = new JButton("Update");
    private JTextField mTemperatureTf = new JTextField(6);
    private JTextField mWindTf = new JTextField(6);

    private WeatherModel mModel;

    /**
     * Constructor
     */
    public WeatherView() {
        // Initialize components
        mTemperatureTf.setEditable(false);
        mWindTf.setEditable(false);

        // Layout the components.
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(mUpdateBtn);
        content.add(new JLabel("Temperature"));
        content.add(mTemperatureTf);
        content.add(new JLabel("°C   Wind speed"));
        content.add(mWindTf);
        content.add(new JLabel("m/s"));

        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather App - Bucharest");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Sets the view's reference to the model - Only get operations allowed
     *
     * @param model The weather model
     */
    public void addModel(WeatherModel model) {
        mModel = model;
        /**
         * Set the values with data from the model
         */
        mTemperatureTf.setText(String.valueOf(model.getTemperatureValue()));
        mWindTf.setText(String.valueOf(model.getWindValue()));
    }

    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {
        mUpdateBtn.setActionCommand(IController.ACTION_UPDATE);
        mUpdateBtn.addActionListener(controller);
    }

    @Override
    public void onMessage(boolean isError, String message) {
        if (isError) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Weather MVC", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void onUpdate() {
        mTemperatureTf.setText(String.valueOf(mModel.getTemperatureValue()));
        mWindTf.setText(String.valueOf(mModel.getWindValue()));
    }
}