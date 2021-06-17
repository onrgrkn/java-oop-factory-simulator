public class Yeast implements IUncountable{
    private double volume;

    public Yeast(){
        this(0);
    }
    public Yeast(int number){
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
        return volume+"L Yeast.";
    }
}
