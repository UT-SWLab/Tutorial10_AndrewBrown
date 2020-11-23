package observer2;

import java.awt.*;

public class ComplementaryColorPanel extends ColorPanel {

    public ComplementaryColorPanel(Color initialColor) {
        super(initialColor);
    }

    public float calculateCompHue(float initial_hue){

        float temp = initial_hue - (float) 0.5;
        if (temp < 0){
            temp++;
        }
        return temp;
    }

    @Override
    public void update(float hueFloat, float saturationFloat, float brightnessFloat){

        float comp_hue = calculateCompHue(hueFloat);
        this.setColor((Color.getHSBColor(comp_hue,saturationFloat,brightnessFloat)));

    }
}