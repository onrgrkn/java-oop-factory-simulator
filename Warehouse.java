import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private StorageTank<Milk> storageTankMilk;
    private StorageTank<Cream> storageTankCream;
    private StorageTank<Cacao> storageTankCacao;
    private StorageTank<Yeast> storageTankYeast;

    public Warehouse() {
        storageTankMilk = new StorageTank();
        storageTankYeast = new StorageTank();
        storageTankCacao = new StorageTank();
        storageTankCream = new StorageTank();
    }

    public void buyIngredient(int x,int amount){
        switch (x){
            case 1:
                storageTankMilk.store(new Milk(amount));
                break;
            case 2:
                storageTankCream.store(new Cream(amount));
                break;
            case 3:
                storageTankCacao.store(new Cacao(amount));
                break;
            case 4:
                storageTankYeast.store(new Yeast(amount));
                break;
        }
    }

    public void printWarehouse(){
        storageTankMilk.printStorage();
        storageTankCream.printStorage();
        storageTankCacao.printStorage();
        storageTankYeast.printStorage();
    }

    public void loadContainer(IContainer container, int operation){// 1-milk 2-cream 3-cacao 4-yeast
        if(container.getLocation() != Location.WAREHOUSE){
            System.out.println("Container is not in warehouse");
            return;
        }
        switch (operation){
            case 1:
                if(storageTankMilk.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankMilk.getFirstElementInStorage());
                }
                else{
                    container.fillContainer(storageTankMilk.getFirstElementInStorage());
                    storageTankMilk = new StorageTank<>();
                }
                break;
            case 2:
                if(storageTankCream.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankCream.getFirstElementInStorage());
                }
                else{
                    container.fillContainer(storageTankCream.getFirstElementInStorage());
                    storageTankCream = new StorageTank<>();
                }
                break;
            case 3:
                if(storageTankCacao.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankCacao.getFirstElementInStorage());
                }
                else{
                    container.fillContainer(storageTankCacao.getFirstElementInStorage());
                    storageTankCacao = new StorageTank<>();
                }
                break;
            case 4:
                if(storageTankYeast.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankYeast.getFirstElementInStorage());
                }
                else{
                    container.fillContainer(storageTankYeast.getFirstElementInStorage());
                    storageTankYeast = new StorageTank<>();
                }
                break;
        }
    }
    public ArrayList<IItem> cleanContainer(IContainer iContainer){
        if(iContainer instanceof TankContainer){
            return ((TankContainer)iContainer).removeItemFromContainer();
        }
        else if(iContainer instanceof OpenTopContainer){
            return ((OpenTopContainer)iContainer).removeItemFromContainer();
        }
        return null;
    }

    public void cleanContainerAndStoreAtWarehouse(TankContainer tankContainer){
        storeToWarehouse(cleanContainer(tankContainer));
    }

    public void storeToWarehouse(ArrayList<IItem> items){
     IUncountable  item = ((IUncountable)items.get(0));
        if(item.getClass() == Milk.class){
            storageTankMilk.store(item);
        }
        else if(item.getClass() == Yeast.class){
            storageTankYeast.store(item);
        }
        else if(item.getClass() == Cream.class){
            storageTankCream.store(item);
        }
        else if(item.getClass() == Cacao.class){
            storageTankCacao.store(item);
        }
    }

    public void transport (IContainer container, int operation, int destination){// destination 1 to factory , 2 to DC
        loadContainer(container, operation);
        switch (destination){
            case 1:
                container.setLocation(Location.FACTORY);
                break;
            case 2:
                container.setLocation(Location.DISTRIBUTIONCENTER);
                break;
        }
    }

}
