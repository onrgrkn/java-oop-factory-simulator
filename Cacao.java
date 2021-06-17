public class Cacao implements IUncountable {
    private double volume;

    public Cacao(){
        this(0);
    }
    public Cacao(int number){
        setVolume(number);
    }

    @Override
    public void setVolume(double volume) {
        this.volume = volume;
    }
    @Override
    public double getVolume() {
        return volume;
    }

    public String toString(){
        return volume+"L Cacao.";
    }
}
