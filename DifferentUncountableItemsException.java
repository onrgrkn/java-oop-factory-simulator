public class DifferentUncountableItemsException extends Exception {
    public   DifferentUncountableItemsException(){
        super("different uncountable item!");
    }
    public  DifferentUncountableItemsException(String message){
        super(message);
    }
}
