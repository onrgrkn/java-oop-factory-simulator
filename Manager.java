import java.util.InputMismatchException;
import java.util.Scanner;
// case 8 eklendi dist centerdaki boşaltma metodunun parametrresi değişti. case 8 en aşağıda araya alınacak
// drystorage conteierda remove item ın popo değişti ama boş  sanırım
// dc de  store to dc   while ın içindeki +1 i sildim
public class Manager {
    Scanner keyboard ;
    Factory factory;
    Warehouse warehouse;
    DistributionCenter distributionCenter;
    DryStorageContainer dryStorageContainer;
    TankContainer tankContainer;
    OpenTopContainer openTopContainer;

    public Manager(){
        keyboard            = new Scanner(System.in);
        factory             = new Factory();
        warehouse           = new Warehouse();
        distributionCenter  = new DistributionCenter();
        tankContainer       = new TankContainer();
        openTopContainer    = new OpenTopContainer();
        dryStorageContainer = new DryStorageContainer();
    }



    public void displayMainMenu() {
        int productID;
        int volume;
        mainMenu();
        int n = getPositiveIntFromUser();
        while(n>8){
            System.out.println("Wrong input");
            mainMenu();
            n=getPositiveIntFromUser();
        }
        switch(n){
            case 1://produce product
                System.out.println("Please select the product which you want to produce");
                productMenu();
                productID = getPositiveIntFromUser();
                while (productID>3){
                    System.out.println("Wrong input");
                    productMenu();
                    productID=getPositiveIntFromUser();
                }
                    switch (productID){
                        case 1://produce boxed milk
                            System.out.println("Boxed milk producing");
                            factory.produceBoxedMilk();
                            break;
                        case 2://produce chocolate
                            System.out.println("Chocolate producing");
                            factory.produceChocolate();
                            break;
                        case 3://produce yogurt
                            System.out.println("Yoghurt producing");
                            factory.produceYogurt();
                            break;

                    }
                factory.printFactory();
                break;

            case 2://buy ingredients
                System.out.println("Select ingredient which you want to buy");
                ingredientMenu();
                int ingredientID = getPositiveIntFromUser();
                while (ingredientID>4){
                    System.out.println("Wrong input");
                    ingredientMenu();
                    ingredientID=getPositiveIntFromUser();
                }
                System.out.println("Enter the amount of ingredient");
                volume = getPositiveIntFromUser();
                warehouse.buyIngredient(ingredientID,volume);
                warehouse.printWarehouse();
                break;
            case 3://sell product
                System.out.println("Please select the product which you want to sell");
                productMenu();
                productID = getPositiveIntFromUser();
                while (productID>3){
                    System.out.println("Wrong input");
                    productMenu();
                    productID=getPositiveIntFromUser();
                }
                volume = getPositiveIntFromUser();
                distributionCenter.sellProduct(productID,volume);
                distributionCenter.printDistributionCenter();
                break;
            case 4://transport the container
                int containerID = containerMenu();

                locationMenu();
                int locationID = getPositiveIntFromUser();
                while (locationID>3){
                    locationMenu();
                    locationID = getPositiveIntFromUser();
                }
                Location location = Location.FACTORY;
                switch (locationID){
                    case 1:
                        location = Location.FACTORY;
                        break;
                    case 2:
                        location = Location.WAREHOUSE;
                        break;
                    case 3:
                        location = Location.DISTRIBUTIONCENTER;
                        break;
                }
                switch (containerID){
                    case 1:
                        transport(tankContainer,location);
                        break;
                    case 2:
                        transport(openTopContainer,location);
                        break;
                    case 3:
                        transport(dryStorageContainer,location);
                        break;
                }
                break;

            case 5://load container
                System.out.println("choose container to load");
                int loadId=containerMenu();
                Location currentLocation=null;
                IContainer currentContainer=null;
                switch (loadId){
                    case 1:
                        currentLocation=tankContainer.getLocation();
                        currentContainer=tankContainer;
                        break;
                    case 2:
                        currentLocation=openTopContainer.getLocation();
                        currentContainer=openTopContainer;
                        break;
                    case 3:
                        currentLocation = dryStorageContainer.getLocation();
                        currentContainer=dryStorageContainer;
                        break;
                }
                if(currentLocation == Location.FACTORY){
                    factory.loadContainer(currentContainer,factoryLoadMenu());
                }
                else if(currentLocation == Location.WAREHOUSE){
                    ingredientMenu();
                    int y = getPositiveIntFromUser();
                    while (y>4){
                        System.out.println("Wrong input");
                        ingredientMenu();
                        y=getPositiveIntFromUser();
                    }
                    warehouse.loadContainer(currentContainer,y);
                }
                else if(currentLocation == Location.DISTRIBUTIONCENTER){
                    int y = getPositiveIntFromUser();
                    while (y>3){
                        System.out.println("Wrong input");
                        ingredientMenu();
                        y=getPositiveIntFromUser();
                    }
                    distributionCenter.loadContainer(currentContainer,y);
                }
                break;
            case 6://display factory, distribution center, warehouse
                int x=printingMenu();
                switch (x){
                    case 1:
                        factory.printFactory();
                        break;
                    case 2:
                        warehouse.printWarehouse();
                        break;
                    case 3:
                        distributionCenter.printDistributionCenter();
                        break;
                    case 4:
                        tankContainer.displayContainer();
                        break;
                    case 5:
                        openTopContainer.displayContainer();
                        break;
                    case 6:
                        dryStorageContainer.displayContainer();
                        break;
                }
                break;
            case 8:
                System.exit(0);
                break;

            case 7:
                System.out.println("choose container to make it empty");
                int contID = containerMenu();
                Location curLocation = null;
                IContainer cont =null;

                switch (contID){
                    case 1 :
                        curLocation =tankContainer.getLocation();
                        cont =tankContainer;
                        break;
                    case 2:
                        curLocation=openTopContainer.getLocation();
                        cont=openTopContainer;
                        break;
                    case 3:
                        curLocation = dryStorageContainer.getLocation();
                        cont=dryStorageContainer;
                        break;
                }
                if(curLocation ==Location.WAREHOUSE){
                    System.out.println("there is no clean method for warehouse");
                }
                else if(curLocation == Location.FACTORY){
                    factory.cleanContainerAndStoreAtFactory(cont);
                }
                else if(curLocation == Location.DISTRIBUTIONCENTER){
                    distributionCenter.cleanContainerAndStoreAtDistributionCenter(cont);
                }
                break;
        }

    }
    public void transport(IContainer iContainer,Location location){
        iContainer.setLocation(location);
        iContainer.displayContainer();
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
    public void productMenu(){
        System.out.println("1-BoxedMilk");
        System.out.println("2-Chocolate");
        System.out.println("3-Yoghurt");
    }
    public void ingredientMenu(){
        System.out.println("1-Milk");
        System.out.println("2-Cream");
        System.out.println("3-Cacao");
        System.out.println("4-Yeast");
    }
    public void mainMenu(){
        System.out.println("Please insert number");
        System.out.println("1-Produce.");
        System.out.println("2-Buy Ingredients");
        System.out.println("3-Sell Product");
        System.out.println("4-Transfer Container");
        System.out.println("5-Load Container");
        System.out.println("6-Display ");
        System.out.println("7-Drain Container");
        System.out.println("9-Quit");
    }
    public int containerMenu(){
        System.out.println("Please select the container");
        System.out.println("1-Tank Container");
        System.out.println("2-Open Top Container");
        System.out.println("3-Dry Storage Container");
        int containerID = getPositiveIntFromUser();
        while (containerID > 3 ){
            containerID = containerMenu();;
        }
        return containerID;
    }
    public void locationMenu(){
        System.out.println("1-Factory");
        System.out.println("2-Warehouse");
        System.out.println("3-Distributon Center");
    }
    public int factoryLoadMenu(){
        //1-milk 2-cream 3-cacao 4-yeast 5-chocolate 6-boxed milk 7-yogurt
        System.out.println("1-Milk");
        System.out.println("2-Cream");
        System.out.println("3-Cacao");
        System.out.println("4-Yeast");
        System.out.println("5-Chocolate");
        System.out.println("6-BoxedMilk");
        System.out.println("7-Yoghurt");
        int x = getPositiveIntFromUser();
        while (x > 7 ){
            x = factoryLoadMenu();
        }
        return x;
    }
    public int printingMenu(){
        System.out.println("1-Factory");
        System.out.println("2-Warehouse");
        System.out.println("3-Distributon Center");
        System.out.println("4-Tank Container");
        System.out.println("5-Open Top Container");
        System.out.println("6-Dry Storage Container");
        int x = getPositiveIntFromUser();
        while (x > 6){
            x = factoryLoadMenu();
        }
        return x;

    }



}
