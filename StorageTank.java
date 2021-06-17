import java.util.LinkedList;
import java.util.Queue;

public class StorageTank<T extends IUncountable> implements Storage {
    private Queue<T> storageTank;
    private double currentVolumeOfTank;
    private final int capacity =10000;

    //constructor
    public StorageTank(){
        storageTank = new LinkedList<>();
        currentVolumeOfTank = 0;
    }
    //getter and setters
    public double getCurrentVolumeOfTank() {
        return currentVolumeOfTank;
    }
    public void setCurrentVolumeOfTank(double currentVolumeOfTank) {
        this.currentVolumeOfTank = currentVolumeOfTank;
    }
    public int getCapacity() {
        return capacity;
    }
    public T getFirstElementInStorage(){
        return storageTank.peek();
    }
    //store uncountable items -> increase or add.
    @Override
    public void store(Object object) {

        if(object instanceof IUncountable){
            for(T e : storageTank){
                if(e.getClass() == object.getClass()){
                    if(((T) object).getVolume()+currentVolumeOfTank <= capacity) {//chech is there enough space
                        setCurrentVolumeOfTank(getCurrentVolumeOfTank() + ((T) object).getVolume());//silinecek!!!!!!!!!!!!!!!!!!!!!!!
                        e.setVolume(e.getVolume() + ((T) object).getVolume());//aynı ürün tankta ise miktarını arttır
                    }
                        return;
                }
            }
            if(((T) object).getVolume()+currentVolumeOfTank <= capacity) {//chech is there enough space
                storageTank.add((T) object);
                setCurrentVolumeOfTank(getCurrentVolumeOfTank() + ((T) object).getVolume());
                return;
            }
            System.err.println("Store operation is fail!");
        }
    }

    public void drainStorage(int volume){
        if(currentVolumeOfTank>=volume){
            storageTank.peek().setVolume(storageTank.peek().getVolume()-volume);
            setCurrentVolumeOfTank(currentVolumeOfTank-volume);
        }
        else{
            System.out.println("There is no enough Item");
        }
    }

    public void printStorage(){
        if(!storageTank.isEmpty()) {
            System.out.println("In storage tank, there are " + currentVolumeOfTank + "L " + storageTank.peek().getClass().toString().substring(6));
            for (T i : storageTank) {
                System.out.println(i.toString());
            }
        }
    }
    /***
    public void addToStorage(IItem item){
        storageTank.add((T)item);
    }
    public void increaseStorage(IItem item , double volume){//verilen itemin belli bir hacmini storage'a ekler
        item.setVolume(item.getVolume()-volume);
        storageTank.peek().setVolume(storageTank.peek().getVolume()+volume);
        setCurrentVolumeOfTank(getCurrentVolumeOfTank()+volume);
    }
    public T splitAnItem(IItem item, double volume){
        storageTank.peek().setVolume(storageTank.peek().getVolume()-volume);
        item.setVolume(volume);
        return (T)item;
    }***/
}
