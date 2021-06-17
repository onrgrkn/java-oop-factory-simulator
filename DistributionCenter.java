import java.util.*;

public class DistributionCenter {
    private StorageArea<Chocolate> chocolateStorageArea;
    private StorageArea<Yogurt>    yogurtStorageArea;
    private StorageArea<BoxedMilk> boxedMilkStorageArea;
    private Scanner keyboard;

    public DistributionCenter(){
        chocolateStorageArea = new StorageArea<>();
        yogurtStorageArea    = new StorageArea<>();
        boxedMilkStorageArea = new StorageArea<>();
        keyboard             = new Scanner(System.in);
    }

    public void sellProduct(int x,int amount){
        switch (x){
            case 1:
                boxedMilkStorageArea.drainStorage(amount);
                break;
            case 2:
                chocolateStorageArea.drainStorage(amount);
                break;
            case 3:
                yogurtStorageArea.drainStorage(amount);
                break;
        }
    }

    public void printDistributionCenter(){
        chocolateStorageArea.printStorage();
        yogurtStorageArea.printStorage();
        boxedMilkStorageArea.printStorage();

    }

    private int getPositiveIntFromUser(){
        int result = 0;
        boolean done = false;
        while (! done) {
            try {
                System.out.println("Enter a whole number:");
                result = keyboard.nextInt();
                if (result>0) done = true;

            } catch (InputMismatchException e) {
                keyboard.nextLine();
                System.out.println("Not a correctly written whole number.");
                System.out.println("Try again.");
            }

        }
        return result;
    }

    public ArrayList<IItem> cleanContainer(IContainer iContainer){
        if(iContainer instanceof TankContainer){
            return (iContainer).removeItemFromContainer();
        }
        else if(iContainer instanceof OpenTopContainer){
            return (iContainer).removeItemFromContainer();
        }
        else if(iContainer instanceof DryStorageContainer){
            return (iContainer).removeItemFromContainer();
        }
        return null;
    }
    public void cleanContainerAndStoreAtDistributionCenter(IContainer container){
        storeToDistributionCenter(cleanContainer(container));
    }
    public void storeToDistributionCenter(ArrayList<IItem> items){
        //are the items ICountable ??
        int size =items.size();
        int current =0;

        while(current != size){
           ICountable item =((ICountable) items.get(current));
            if(item.getClass() ==Chocolate.class){
                chocolateStorageArea.store(item);
              }
            else if(item.getClass()==BoxedMilk.class){
                boxedMilkStorageArea.store(item);
            }
            else if(item.getClass()==Yogurt.class){
                yogurtStorageArea.store(item);
            }
            current++;
        }
    }
    public void loadContainer(IContainer iContainer, int operation){//1-)Boxed Milk 2-) chocolate 3-)yogurt
        try {
            switch (operation){
                case 1:
                    while (!iContainer.isContainerFull()){
                        iContainer.fillContainer(boxedMilkStorageArea.getFirstElementInStorage());
                    }
                    break;
                case 2:
                    while (!iContainer.isContainerFull()){
                        iContainer.fillContainer(chocolateStorageArea.getFirstElementInStorage());
                    }
                    break;
                case 3:
                    while (!iContainer.isContainerFull()){
                        iContainer.fillContainer(yogurtStorageArea.getFirstElementInStorage());
                    }
                    break;

            }
        }catch (NullPointerException e){

        }

    }
}
