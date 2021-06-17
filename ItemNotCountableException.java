public class ItemNotCountableException extends Exception {
    public ItemNotCountableException(){
        super("Item is not countable!");
    }
    public ItemNotCountableException(String message){
        super(message);
    }
}
