public class ItemNotIUncountableException extends Exception {
    public  ItemNotIUncountableException(){
        super("Item is not countable!");
    }
    public  ItemNotIUncountableException(String message){
        super(message);
    }
}
