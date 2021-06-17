import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DryStorageContainer implements IContainer {
    private final int CAPACITY = 1000;
    private Stack<ICountable> stack;
    private Location location;

    public DryStorageContainer(){
        stack = new Stack<>();
        location = Location.FACTORY;

    }

    @Override
    public double getEmptySpace() {
        double sum=0;
        for(ICountable ic : stack){
            sum+=ic.getVolume();
        }
        return CAPACITY-sum;
    }

    @Override
    public boolean isContainerEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean isContainerFull() {
        return (getEmptySpace()==0);
    }

    @Override
    public void fillContainer(Object object) {
        try {
            if (object instanceof ICountable){
                if (!isContainerFull()) {
                    stack.push((ICountable) object);
                    return;
                }
                System.out.println("DryStorageContainer is full");
            }
            else{
                throw new ItemNotCountableException();
            }
        }catch (ItemNotCountableException e){
            System.out.println(e.getMessage());
        }
    }

   public ICountable removeOneCountableItemFromStack() {
        if(isContainerEmpty()){
            System.err.println("Container is empty!");
            return null;
        }
        return stack.pop();
    }
    @Override
    public int getCapacity() {
        return CAPACITY;
    }
    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public ArrayList<IItem> removeItemFromContainer(){
        ArrayList<IItem> items = new ArrayList<>();
        if(!isContainerEmpty()){
            while (!stack.isEmpty()){
                 IItem asd= stack.pop();
                items.add(asd);
            }
        }
        return items;
    }

    public void displayContainer(){
        System.out.println("Location:"+location);
        int numChocolate=0;
        int numYogurt=0;
        int numBoxedMilk=0;
        if(!stack.isEmpty()){
            for (ICountable c: stack) {
                if(c.getClass()==BoxedMilk.class){
                    numBoxedMilk++;
                }
                else if(c.getClass() == Chocolate.class){
                    numChocolate++;
                }
                else if(c.getClass() == Yogurt.class){
                    numYogurt++;
                }
            }
            System.out.println("Container---> cho:"+numChocolate+" yogurt:"+numYogurt+"Boxedmilk:"+numBoxedMilk);
        }
        else {
            System.out.println("container is empty");
        }
    }
}
