package observer3;

import javax.swing.event.ChangeEvent;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ComplementaryColorPanel extends ColorPanel implements PropertyChangeListener {

    private OriginalColorPanel orig_panel;

    public ComplementaryColorPanel(Color initialColor, OriginalColorPanel _orig_panel) {
        super(initialColor);
        orig_panel = _orig_panel;
        orig_panel.addPropertyChangeListener(this);
    }

    public float calculateCompHue(float initial_hue){

        float temp = initial_hue - (float) 0.5;
        if (temp < 0){
            temp++;
        }
        return temp;
    }

    @Override
    public void propertyChange(PropertyChangeEvent e){

        float comp_hue = calculateCompHue(orig_panel.origin_hue);
        this.setColor((Color.getHSBColor(comp_hue,orig_panel.origin_saturation,orig_panel.origin_brightness)));

    }

    @Override
    public void update(float hueFloat, float saturationFloat, float brightnessFloat){

    }
}