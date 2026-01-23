public class IceCreamShop {

    public String name;
    public boolean isOpen;
    public int numberOfFlavors;

    public static void main(String[] args) {
        IceCreamShop creamery = new IceCreamShop();
        System.out.println("Hello World! Good luck on your exams!");
    }

    public IceCreamShop(){
        name = "Scoops & Loops: the Code Creamery";
        isOpen = true;
        numberOfFlavors = 32;
        System.out.println("Welcome to "+name+"! It is " + isOpen+ " that we are open. We have "+numberOfFlavors+" flavors.");
        numberOfFlavors = 20;
        System.out.println("Welcome to "+name+"! It is " + isOpen+ " that we are open. We have "+numberOfFlavors+" flavors.");
        randomDiscount();
    }

    public void randomDiscount(){
        int randomInt = (int)(Math.random()*30);
        System.out.println("We spun the discount wheel and you get "+randomInt+" percent off your order!");

    }

}

