import java.util.ArrayList;

public class Factory {
    private StorageTank<Milk> storageTankMilk;
    private StorageTank<Cream> storageTankCream;
    private StorageTank<Cacao> storageTankCacao;
    private StorageTank<Yeast> storageTankYeast;
    private StorageArea<Chocolate> storageAreaChocolate;
    private StorageArea<BoxedMilk> storageAreaBoxedMilk;
    private StorageArea<Yogurt> storageAreaYogurt;

    public Factory(){
        storageTankMilk = new StorageTank();
        storageTankYeast = new StorageTank();
        storageTankCacao = new StorageTank();
        storageTankCream = new StorageTank();
        storageAreaBoxedMilk = new StorageArea();
        storageAreaChocolate = new StorageArea();
        storageAreaYogurt = new StorageArea();

    }

    public void produceBoxedMilk(){
        if(storageTankMilk.getCurrentVolumeOfTank()>=1000){
            for(int i=0;i<1000;i++){
                storageAreaBoxedMilk.store(new BoxedMilk());
            }
            storageTankMilk.drainStorage(1000);
        }
    }

    public void produceChocolate(){
        if((storageTankMilk.getCurrentVolumeOfTank()>=500) && (storageTankCacao.getCurrentVolumeOfTank()>=300) && (storageTankCream.getCurrentVolumeOfTank()>=200)){
            for (int i=0;i<1600;i++){
                storageAreaChocolate.store(new Chocolate());
            }
            storageTankCream.drainStorage(200);
            storageTankCacao.drainStorage(300);
            storageTankMilk.drainStorage(500);
        }
    }
    public void produceYogurt(){
        if((storageTankYeast.getCurrentVolumeOfTank()>=100) && storageTankMilk.getCurrentVolumeOfTank()>=1500){
            for(int i=0;i<700;i++){
                storageAreaYogurt.store(new Yogurt());
            }
            storageTankMilk.drainStorage(1500);
            storageTankYeast.drainStorage(100);
        }else{
            System.err.println("There is no enough ingredients! Buy ingredients.");
        }
    }

    public void printFactory(){
        storageTankMilk.printStorage();
        storageTankYeast.printStorage();
        storageTankCacao.printStorage();
        storageTankCream.printStorage();
        storageAreaBoxedMilk.printStorage();
        storageAreaChocolate.printStorage();
        storageAreaYogurt.printStorage();

    }

    public void loadContainer(IContainer container,int operation){//1-milk 2-cream 3-cacao 4-yeast 5-chocolate 6-boxed milk 7-yogurt
        if(container.getLocation() != Location.FACTORY){
            System.out.println("Container is not in Factory");
            return;
        }
        switch (operation){
            case 1:
                if(storageTankMilk.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankMilk.getFirstElementInStorage());
                    System.out.println("Container is loaded by milk empty space in container--->"+container.getEmptySpace());
                }
                else{
                    container.fillContainer(storageTankMilk.getFirstElementInStorage());
                    storageTankMilk = new StorageTank<>();
                    System.out.println("Container is loaded by milk empty space in container--->"+container.getEmptySpace());
                }
                break;
            case 2:
                if(storageTankCream.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankCream.getFirstElementInStorage());
                    System.out.println("Container is loaded by cream empty space in container--->"+container.getEmptySpace());
                }
                else{
                    container.fillContainer(storageTankCream.getFirstElementInStorage());
                    storageTankCream = new StorageTank<>();
                    System.out.println("Container is loaded by cream empty space in container--->"+container.getEmptySpace());
                }
                break;
            case 3:
                if(storageTankCacao.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankCacao.getFirstElementInStorage());
                    System.out.println("Container is loaded by cacao empty space in container--->"+container.getEmptySpace());
                }
                else{
                    container.fillContainer(storageTankCacao.getFirstElementInStorage());
                    storageTankCacao = new StorageTank<>();
                    System.out.println("Container is loaded by cacao empty space in container--->"+container.getEmptySpace());
                }
                break;
            case 4:
                if(storageTankYeast.getCurrentVolumeOfTank()> container.getCapacity()){
                    container.fillContainer(storageTankYeast.getFirstElementInStorage());
                    System.out.println("Container is loaded by yeast empty space in container--->"+container.getEmptySpace());
                }
                else{
                    container.fillContainer(storageTankYeast.getFirstElementInStorage());
                    storageTankYeast = new StorageTank<>();
                    System.out.println("Container is loaded by yeast empty space in container--->"+container.getEmptySpace());
                }
                break;
            case  5:
                try {
                    if( storageAreaChocolate.getCurrentVolumeOfTank()>container.getEmptySpace()){
                        while(!container.isContainerFull()){
                            container.fillContainer(storageAreaChocolate.getFirstElementInStorage());
                            storageAreaChocolate.setCurrentNumberOfProduct(storageAreaChocolate.getCurrentNumberOfProduct()-1);
                        }
                        storageAreaChocolate.setCurrentVolumeOfTank(storageAreaChocolate.getCurrentVolumeOfTank()-container.getEmptySpace());
                        System.out.println("Container is loaded by chocolate empty space in container--->"+container.getEmptySpace());
                    }
                    else{

                        while(storageAreaChocolate.getCurrentVolumeOfTank()==0){

                            container.fillContainer(storageAreaChocolate.getFirstElementInStorage());
                            storageAreaChocolate.setCurrentVolumeOfTank(storageAreaChocolate.getCurrentVolumeOfTank() - storageAreaChocolate.getFirstElementInStorage().getVolume());
                            storageAreaChocolate.setCurrentNumberOfProduct(storageAreaChocolate.getCurrentNumberOfProduct() - 1);
                        }
                        System.out.println("Container is loaded by chocolate empty space in container--->"+container.getEmptySpace());//bu print karışıklık yaratıyor
                    }

                    break;
                }catch (NullPointerException e){
                    break;
                }

            case 6 :
                try {
                    if( storageAreaBoxedMilk.getCurrentVolumeOfTank()>container.getEmptySpace()){
                        while(!container.isContainerFull()){
                            container.fillContainer(storageAreaBoxedMilk.getFirstElementInStorage());
                            storageAreaBoxedMilk.setCurrentNumberOfProduct(storageAreaBoxedMilk.getCurrentNumberOfProduct()-1);
                        }
                        storageAreaBoxedMilk.setCurrentVolumeOfTank(storageAreaBoxedMilk.getCurrentVolumeOfTank()-container.getEmptySpace());
                        System.out.println("Container is loaded by BoxedMilk empty space in container--->"+container.getEmptySpace());
                    }
                    else{

                        while(storageAreaBoxedMilk.getCurrentVolumeOfTank()==0){
                            container.fillContainer(storageAreaBoxedMilk.getFirstElementInStorage());
                            storageAreaBoxedMilk.setCurrentVolumeOfTank(storageAreaBoxedMilk.getCurrentVolumeOfTank()-storageAreaBoxedMilk.getFirstElementInStorage().getVolume());
                            storageAreaBoxedMilk.setCurrentNumberOfProduct(storageAreaBoxedMilk.getCurrentNumberOfProduct()-1);
                        }
                        System.out.println("Container is loaded by BoxedMilk empty space in container--->"+container.getEmptySpace());
                    }
                    break;
                }catch (NullPointerException e){
                    break;
                }

            case 7:
                try {
                    if( storageAreaYogurt.getCurrentVolumeOfTank()>container.getEmptySpace()){
                        while(!container.isContainerFull()){
                            container.fillContainer(storageAreaYogurt.getFirstElementInStorage());
                            storageAreaYogurt.setCurrentNumberOfProduct(storageAreaYogurt.getCurrentNumberOfProduct()-1);
                        }
                        storageAreaYogurt.setCurrentVolumeOfTank(storageAreaYogurt.getCurrentVolumeOfTank()-container.getEmptySpace());
                        System.out.println("Container is loaded by Yogurt empty space in container--->"+container.getEmptySpace());
                    }
                    else{

                        while(storageAreaYogurt.getCurrentVolumeOfTank()==0){
                            container.fillContainer(storageAreaYogurt.getFirstElementInStorage());
                            storageAreaYogurt.setCurrentVolumeOfTank(storageAreaYogurt.getCurrentVolumeOfTank()-storageAreaYogurt.getFirstElementInStorage().getVolume());
                            storageAreaYogurt.setCurrentNumberOfProduct(storageAreaYogurt.getCurrentNumberOfProduct()-1);
                        }
                        System.out.println("Container is loaded by Yogurt empty space in container--->"+container.getEmptySpace());

                    }
                    break;
                }catch (NullPointerException e){
                    break;
                }


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

    public void storeToFactory(ArrayList<IItem> items){//productlar eksik bide uncountable a cast edemeyiz
        if(items.get(0) instanceof IUncountable) {
            IUncountable item = ((IUncountable) items.get(0));
            if (item.getClass() == Milk.class) {
                storageTankMilk.store(item);
            } else if (item.getClass() == Yeast.class) {
                storageTankYeast.store(item);
            } else if (item.getClass() == Cream.class) {
                storageTankCream.store(item);
            } else if (item.getClass() == Cacao.class) {
                storageTankCacao.store(item);
            }
        }
        else if (items.get(0) instanceof  ICountable){
            for(IItem i : items){
                  if(i.getClass() == Yogurt.class){
                      storageAreaYogurt.store(i);
                  }
                  else if(i.getClass() == Chocolate.class){
                      storageAreaChocolate.store(i);
                  }
                  else if(i.getClass() == BoxedMilk.class){
                      storageAreaBoxedMilk.store(i);
                  }
            }
        }
    }

    public void cleanContainerAndStoreAtFactory(IContainer container){
        storeToFactory(cleanContainer(container));
    }
}
