public class Accounting {
    double currentMoney;

    public Accounting(){
        currentMoney =10000;
    }

    public double containerShipping(){

        return currentMoney-100;
    }
    public double  buyingIngredient(IUncountable ingredient, int amount){
        double sum ;
        if(ingredient instanceof Milk){
            sum = amount*0.25;
        }
        else if(ingredient instanceof Yeast){
            sum = amount*5;
        }
        else if(ingredient instanceof  Cacao){
            sum = amount;
        }
        else if(ingredient instanceof Cream){
            sum = amount*0.30;
        }
        else{
            System.out.println("wrong ingredient entered");
            sum = 0;
        }
        return currentMoney-sum;


    }
    public double sellingProduct(ICountable product, int amount){
        double sum;
        if(product instanceof BoxedMilk){
            sum = amount;
        }
        else if(product instanceof Chocolate){
            sum = amount*4;
        }
        else if (product instanceof Yogurt){
            sum = amount*2;
        }
        else{
            System.out.println("wrong product entered");
            sum = 0;
        }
        return currentMoney+sum;
    }
}
