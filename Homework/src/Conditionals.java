public class Conditionals {

    String question = "Is Natalie in my top 5 friends?";
    String res1 = "Absolutely";
    String res2 = "Unclear";
    String res3 = "Like 67% chance yes ";
    String res4 = "All sign point no";
    String res5 = "I don't think you want my answer";
    String res6 = "Prolly not";
    String res7 = "YESSIR!";

    public static void main(String[] args) {
        Conditionals MagicEightBall = new Conditionals();

    }

    public Conditionals(){
        int randomInt = (int)(Math.random() * 10) + 1;
        System.out.println(question);

        if (randomInt == 1){
            System.out.println(res1);
        }

        else if (randomInt == 2){
            System.out.println(res2);
        }

        else if (randomInt == 3){
            System.out.println(res3);
        }
        else if (randomInt == 4){
            System.out.println(res4);
        }
        else if (randomInt == 5){
            System.out.println(res5);
        }
        else if (randomInt == 6){
            System.out.println(res6);
        }
        else {
            System.out.println(res7);
        }


    }


}
