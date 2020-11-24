package observer3;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class OriginalColorPanel extends ColorPanel {

    float origin_hue;
    float origin_saturation;
    float origin_brightness;

    public OriginalColorPanel(Color initialColor){
        super(initialColor);
    }

    @Override
    public void update(float hueFloat, float saturationFloat, float brightnessFloat){

        origin_hue = hueFloat;
        origin_saturation = saturationFloat;
        origin_brightness = brightnessFloat;

        this.setColor((Color.getHSBColor(hueFloat,saturationFloat,brightnessFloat)));

    }
    
}