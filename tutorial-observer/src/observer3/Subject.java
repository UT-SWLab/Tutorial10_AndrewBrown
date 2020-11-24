package observer3;

public interface Subject {

    public void remove_observer(Observer remove_observer);
    public void add_observer_to_list(Observer add_observer);
    public void notify_all_observers_in_list(float hueFloat, float saturationFloat, float brightnessFloat);
}