package observer3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class DisplayColors implements ChangeListener, Subject {

    public static void main(String[] args) {
        SwingFacade.launch(new DisplayColors().mainPanel(), "Compute Complementary Colors");
    }

    protected OriginalColorPanel originalColorPanel;
    protected ComplementaryColorPanel complementaryColorPanel;

    protected JSlider hueSlider;
    protected JSlider saturationSlider;
    protected JSlider brightnessSlider;

    protected JLabel hueValueLabel;
    protected JLabel saturationValueLabel;
    protected JLabel brightnessValueLabel;

    private ArrayList<Observer> observerList = new ArrayList();

    protected JPanel colorsPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        originalColorPanel = new OriginalColorPanel(Color.getHSBColor(0, (float) .5, (float) .5));
        originalColorPanel.setPreferredSize(new Dimension(300,200));
        p.add(SwingFacade.createTitledPanel("Original Color", originalColorPanel));

        complementaryColorPanel = new ComplementaryColorPanel(Color.getHSBColor((float) .5, (float) .5, (float) .5), originalColorPanel);
        complementaryColorPanel.setPreferredSize(new Dimension(300,200));
        p.add(SwingFacade.createTitledPanel("Complementary Color", complementaryColorPanel));

        add_observer_to_list(originalColorPanel);
        add_observer_to_list(complementaryColorPanel);

        return p;
    }

    protected JPanel mainPanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        JPanel colorsPanel = colorsPanel();
        JPanel subP = new JPanel();
        subP.setLayout(new GridLayout(3, 1));
        hueSlider = slider();
        subP.add(sliderBox("H", hueSlider, hueValueLabel));
        saturationSlider = slider();
        saturationSlider.setValue(50);
        subP.add(sliderBox("S", saturationSlider, saturationValueLabel));
        brightnessSlider = slider();
        brightnessSlider.setValue(50);
        subP.add(sliderBox("B", brightnessSlider, brightnessValueLabel));
        p.add(subP);
        p.add(colorsPanel);
        return p;
    }

    private JSlider slider() {
        JSlider slider = new JSlider();
        // WHAT GOES HERE?
        // You need to make it possible for the app to get the slider values out.
        slider.addChangeListener(this);

        slider.setValue(slider.getMinimum());
        return slider;
    }

    private Box sliderBox(String sliderLabel, JSlider slider, JLabel valueLabel) {
        if (valueLabel == null) {
            valueLabel = new JLabel();
            valueLabel.setFont(SwingFacade.getStandardFont());
            valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            valueLabel.setForeground(Color.black);
        }
        Box b = Box.createHorizontalBox();
        JLabel label = new JLabel(sliderLabel);
        label.setFont(SwingFacade.getStandardFont());
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        label.setForeground(Color.black);
        b.add(label);
        b.add(valueLabel);
        b.add(slider);
        b.setPreferredSize(new Dimension(600, 50));
        return b;
    }

    public boolean checkSliders(){
        if (hueSlider != null && saturationSlider != null && brightnessSlider != null){
            return true;
        }
        return false;
    }


    public void stateChanged(ChangeEvent e) {
        if (checkSliders() == true) {
            notify_all_observers_in_list(((float)hueSlider.getValue() / 100), ((float)saturationSlider.getValue()/100), ((float)brightnessSlider.getValue()/100));
        }
    }

    @Override
    public void remove_observer(Observer remove_observer){

        observerList.remove(remove_observer);
    }

    @Override
    public void add_observer_to_list(Observer add_observer){

        observerList.add(add_observer);
    }

    @Override
    public void notify_all_observers_in_list(float hueFloat, float saturationFloat, float brightnessFloat){

        for(int i = 0; i < observerList.size(); i++){

            observerList.get(i).update(hueFloat, saturationFloat, brightnessFloat);
        }
    }

}
    
