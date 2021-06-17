import java.util.LinkedList;
import java.util.Queue;

public class StorageArea<T extends ICountable> implements Storage {
    private Queue<T> storageArea;
    private double currentVolumeOfTank;
    private int currentNumberOfProduct;
    private final int capacity =10000;

    public StorageArea(){
        storageArea = new LinkedList<>();
        setCurrentVolumeOfTank(0);
        setCurrentNumberOfProduct(0);
    }

    public int getCurrentNumberOfProduct() {
        return currentNumberOfProduct;
    }
    public void setCurrentNumberOfProduct(int currentNumberOfProduct) {
        this.currentNumberOfProduct = currentNumberOfProduct;
    }

    public Queue<T> getStorageArea() {
        return storageArea;
    }
    public void setStorageArea(Queue<T> storageArea) {
        this.storageArea = storageArea;
    }
    public double getCurrentVolumeOfTank() {
        return currentVolumeOfTank;
    }
    public void setCurrentVolumeOfTank(double currentVolumeOfTank) {
        this.currentVolumeOfTank = currentVolumeOfTank;
    }

    @Override
    public void store(Object object) {
        if(object instanceof ICountable){
                if (((T) object).getVolume() + currentVolumeOfTank <= capacity) {//chech is there enough space
                    storageArea.add((T) object);
                    setCurrentVolumeOfTank(((T) object).getVolume() + getCurrentVolumeOfTank());
                    setCurrentNumberOfProduct(getCurrentNumberOfProduct()+1);
                }
            }
    }


    @Override
    public void printStorage() {
        if(!storageArea.isEmpty()) {
            System.out.println("In storage area ,there are " + currentVolumeOfTank + "L " + storageArea.peek().getClass().toString().substring(6));
            int numberOfProduct = 0;
            for (T i : storageArea) {
                numberOfProduct++;
            }
            String str = storageArea.peek().getClass().toString();
            System.out.println(numberOfProduct + " " + str.substring(6));
        }

    }

    @Override
    public void drainStorage(int amount) {
        if(getCurrentNumberOfProduct() >= amount) {
            for (int i = 0; i < amount; i++) {
                setCurrentNumberOfProduct(getCurrentNumberOfProduct()-1);
                setCurrentVolumeOfTank(getCurrentVolumeOfTank() - storageArea.peek().getVolume());
                storageArea.poll();
            }
        }
        else {
            System.out.println("There is no enough Product in Storage Area");
        }
    }
    public T getFirstElementInStorage(){
        return storageArea.peek();
    }
}
