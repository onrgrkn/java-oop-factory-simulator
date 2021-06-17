public class Milk implements IUncountable {
    private double volume;

    public Milk(){
        this(0);
    }
    public Milk(int number){
        setVolume(number);
    }

    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        if(volume>=0) {
            this.volume = volume;
        }
    }
    public String toString(){
        return volume+"L Milk.";
    }
}
