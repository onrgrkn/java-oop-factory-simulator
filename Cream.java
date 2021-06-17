public class Cream implements IUncountable {
    private double volume;

    public Cream(){
        this(0);
    }
    public Cream(int number){
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
        return volume+"L Cream.";
    }
}
