public class Chocolate implements ICountable{
    private final double volume = 0.5;

    public Chocolate(){
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public String toString(){
        return "One Chocolate"+volume+"L.";
    }
}
