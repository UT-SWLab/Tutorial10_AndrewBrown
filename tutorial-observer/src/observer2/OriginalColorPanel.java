package observer2;

import java.awt.*;

public class OriginalColorPanel extends ColorPanel {

    public OriginalColorPanel(Color initialColor){
        super(initialColor);
    }

    @Override
    public void update(float hueFloat, float saturationFloat, float brightnessFloat){

        this.setColor((Color.getHSBColor(hueFloat,saturationFloat,brightnessFloat)));

    }
}