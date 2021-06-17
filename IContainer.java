import java.util.ArrayList;

public interface IContainer{

    // public void addtoContainer(ICountable or IUncountable)
    // public void increaseInContainer(ICountable or IUncountable)
    public double getEmptySpace();
    public boolean isContainerEmpty();
    public boolean isContainerFull();
    public void fillContainer(Object object);
    public int getCapacity();
    public Location getLocation();
    public void setLocation(Location location);
    //burada pop fonksiyonunu nasıl yazacağım ? dryStorageContainer countable item popluyor diğerleri uncountable item popluyor! üste bir tane daha interface oluştur
    public ArrayList<IItem> removeItemFromContainer();
    public void displayContainer();
}
