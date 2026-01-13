public class Loops {
    public static void main(String[] args) {
        Loops myCount = new Loops();

    }
    public Loops (){
        countUp();
        countByThrees();
        countDown();
        nestedLoop();
        bonusLoop();
    }
    public void countUp(){
        for(int x = 1; x<6; x=x+1) {
            System.out.println(x);
        }
    }

    public void countByThrees(){
        for(int x = 3; x<16; x=x+3){
            System.out.print(x+" ");
        }
    }

    public void countDown(){
        System.out.println();
        for(int x=10; x>0; x=x-1){
            System.out.print(x+", ");
        }
        System.out.println();
        System.out.println("Happy Loop Year!");
    }

    public void nestedLoop(){
        for(int x=1; x<6; x=x+1){
            for(int y=x; y>0; y=y-1){
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }

    public void bonusLoop(){
        for(int x=1; x<5; x=x+1 ){
            System.out.println();
            for(int y=1; y<=x; y=y+1 ){
                System.out.print(y+" ");
            }
        }
    }

}
