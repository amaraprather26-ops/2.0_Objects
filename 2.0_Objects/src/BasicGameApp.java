//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section
//Implements play button use and key use
public class BasicGameApp implements Runnable, KeyListener, MouseListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;
    public Image roidPic;
    public Image background;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro1;
    public Astronaut astro2;
    public Asteroid asteroid1;
    public Asteroid asteroid2;
    public Rectangle startButton;
    public boolean isStart;
    public Asteroid[] asteroids;

   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();

      //random range 0-9
      int randx = (int)(Math.random() * 900) + 1;
      //Math.random picks # between a little more than 0 and a little less than one
        // ex. 0.0001 and .9999
        // *10 and take first digit as integer
        // (int)(Math.random() * 10) + 1 for 1-10 etc.

      int randy = (int)(Math.random()*600) + 1;

      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png");
        roidPic = Toolkit.getDefaultToolkit().getImage("asteroid.png");
        background = Toolkit.getDefaultToolkit().getImage("space.jpg");
        //load the picture
		astro1 = new Astronaut(500,400);
        astro2 = new Astronaut(randx, randy);
        asteroid1 = new Asteroid(30,30);
        asteroid2 = new Asteroid(40, 300);
        asteroid2.dx = 2;
        asteroid2.dy = -2;
        startButton = new Rectangle(100, 100, 350, 400);

        asteroids = new Asteroid[5];
        for(int i=0;i<asteroids.length;i++){
            asteroids[i] = new Asteroid(i*100, i*60);
        }

        asteroids[0].dy = -2;
        asteroids[1].dx = -3;
        asteroids[2].dy = 2;
        asteroids[3].dy =-1;
        asteroids[4].dx =-1;

    }// BasicGameApp()

   //
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up

	public void run() {

      //for the moment we will loop things forever.
		while (true) {
         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings(){

        if (isStart) {
            //calls the move( ) code in the objects
            astro1.move();
            astro2.move();
            asteroid1.move();
            asteroid2.move();
            for(int i=0; i<asteroids.length; i++) {
                asteroids[i].move();
            }
            Collision();
            if (astro1.isUp){
                astro1.dy = -Math.abs(astro1.dy);
            }
            if(astro1.isDown){
                astro1.dy = Math.abs(astro1.dy);
            }
            if(astro1.isLeft){
                astro1.dx = -Math.abs(astro1.dx);
            }
            if(astro1.isRight){
                astro1.dx = Math.abs(astro1.dx);
            }
        }
	}

    public void Collision(){
        if(astro1.hitBox.intersects(astro2.hitBox)&& !astro1.isCrashing) {
            //System.out.println("Crash");
            astro1.dx = -astro1.dx;
            astro1.dy = -astro1.dy;
            astro2.dx = -astro2.dx;
            astro2.dy = -astro2.dy;
            astro1.isCrashing = true;
            //astro1.isAlive = false
        }

        if(asteroid1.hitBox.intersects(asteroid2.hitBox) && asteroid2.isCrashing == false) {
            System.out.println("asteroid collision");
            asteroid2.isCrashing = true;
            asteroid2.height = asteroid2.height+10;
        }

        if(!asteroid1.hitBox.intersects(asteroid2.hitBox)){
            asteroid2.isCrashing = false;
        }
    }

   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();

      //add key listener to canvas
       canvas.addKeyListener(this);

       canvas.addMouseListener(this);

      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        //start
        //draw the image of the astronaut
        //if(astro1.isAlive = true){
        g.drawImage(astroPic, astro1.xpos, astro1.ypos, astro1.width, astro1.height, null);
        //}
        g.drawImage(astroPic, astro2.xpos, astro2.ypos, astro2.width, astro2.height, null);
        //draw things here
        g.drawImage(roidPic, asteroid1.xpos, asteroid1.ypos, asteroid1.width, asteroid1.height, null);
        g.drawImage(roidPic, asteroid2.xpos, asteroid2.ypos, asteroid2.width, asteroid2.height, null);
        //g.drawRect(xpos, ypos, width, height)<-- would actually draw the hitBox rectangle
        for(int i=0; i<asteroids.length; i++){
            g.drawImage(roidPic, asteroids[i].xpos, asteroids[i].ypos, asteroids[i].width, asteroids[i].height, null);
        }
        g.setColor(Color.GREEN);
        g.fillRect(100,100,100,100);

        //end
		g.dispose();

		bufferStrategy.show();
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode()== 37){
            astro1.isLeft = true;
        }
        if(e.getKeyCode() == 38){
            astro1.isUp = true;
        }

        if(e.getKeyCode()== 39){
            astro1.isRight = true;
        }
        if(e.getKeyCode()== 40){
            astro1.isDown = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode()== 37){
            astro1.isLeft = false;
        }
        if(e.getKeyCode() == 38){
            astro1.isUp = false;
        }

        if(e.getKeyCode()== 39){
            astro1.isRight = false;
        }
        if(e.getKeyCode()== 40){
            astro1.isDown = false;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle pointBox = new Rectangle(e.getX(), e.getY(), 1, 1);
        if(startButton.intersects(pointBox)){
            System.out.println("Start Game");
            isStart = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("entered");
        astro1.dy = 0;
        astro2.dy = 0;
        astro1.dx = 0;
        astro2.dx = 0;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        astro1.dy =1;
        astro2.dy = 1;
        astro1.dx = 2;
        astro2.dx =2;
    }


    //add key methods
}