public class Yogurt implements ICountable {
    private final double volume = 2;

    public Yogurt(){
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString(){
        return "One Yogurt"+volume+"L.";
    }
}
