public class BoxedMilk implements ICountable {
    private final double volume = 1;

    public BoxedMilk(){
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public String toString(){
        return "One BoxedMilk"+volume+"L.";
    }
}
