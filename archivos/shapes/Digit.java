import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
/**
 * Write a description of class Digit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Digit
{
    // instance variables - replace the example below with your own
    public byte digit;
    public int xPosition;
    public int yPosition;
    public Rectangle fondo;
    public Rectangle barra1;
    public Rectangle barra2;
    public Rectangle barra3;
    public Rectangle barra4;
    public Rectangle barra5;
    public Rectangle barra6;
    public Rectangle barra7;
    public String color;

    /**
     * Constructor for objects of class Digit
     */
    public Digit(byte digit)
    {
        // initialise instance variables
        xPosition = 0;
        yPosition = 0;
        color= "green";
        this.digit=digit;
        fondo = new Rectangle();
        barra1 = new Rectangle();
        barra2 = new Rectangle();
        barra3 = new Rectangle();
        barra4 = new Rectangle();
        barra5 = new Rectangle();
        barra6 = new Rectangle();
        barra7 = new Rectangle();
        fondo.changeSize(1000,1000);
        fondo.changeColor("black");
        fondo.moveHorizontal(xPosition - 70);
        fondo.makeVisible();
        barra1.changeSize(50,10);
        barra1.moveVertical(yPosition + 50);
        barra2.changeSize(10,50);
        barra2.moveVertical(yPosition + 40);
        barra2.moveHorizontal(xPosition + 10); 
        barra3.changeSize(50,10);
        barra3.moveVertical(yPosition + 50);
        barra3.moveHorizontal(xPosition + 60);
        barra4.changeSize(10,50);
        barra4.moveVertical(yPosition + 100);
        barra4.moveHorizontal(xPosition + 10);
        barra5.changeSize(50,10);
        barra5.moveVertical(yPosition + 110);
        barra6.changeSize(10,50);
        barra6.moveVertical(yPosition + 160);
        barra6.moveHorizontal(xPosition + 10);
        barra7.changeSize(50,10);
        barra7.moveVertical(yPosition + 110);
        barra7.moveHorizontal(xPosition + 60);
    }
    public void get(){
        barra1.changeColor("black");
        barra2.changeColor("black");
        barra3.changeColor("black");
        barra4.changeColor("black");
        barra5.changeColor("black");
        barra6.changeColor("black");
        barra7.changeColor("black");
        
        if (digit==0){
            barra1.changeColor(color);
            barra2.changeColor(color);
            barra3.changeColor(color);
            barra5.changeColor(color);
            barra6.changeColor(color);
            barra7.changeColor(color);
        }
        else if (digit==1){
            barra1.changeColor(color);
            barra5.changeColor(color);
        }
        else if (digit==2){
            barra2.changeColor(color);
            barra3.changeColor(color);
            barra4.changeColor(color);
            barra5.changeColor(color);
            barra6.changeColor(color);
        }
        else if (digit==3){
            barra2.changeColor(color);
            barra3.changeColor(color);
            barra4.changeColor(color);
            barra6.changeColor(color);
            barra7.changeColor(color);
        }
        else if (digit==4){
            barra1.changeColor(color);
            barra3.changeColor(color);
            barra4.changeColor(color);
            barra7.changeColor(color);
        }
        else if (digit==5){
            barra1.changeColor(color);
            barra2.changeColor(color);
            barra4.changeColor(color);
            barra6.changeColor(color);
            barra7.changeColor(color);
        }
        else if (digit==6){
            barra1.changeColor(color);
            barra2.changeColor(color);
            barra4.changeColor(color);
            barra5.changeColor(color);
            barra6.changeColor(color);
            barra7.changeColor(color);
        }
        else if (digit==7){
            barra2.changeColor(color);
            barra3.changeColor(color);
            barra7.changeColor(color);
        }
        else if (digit==8){
            barra1.changeColor(color);
            barra2.changeColor(color);
            barra3.changeColor(color);
            barra4.changeColor(color);
            barra5.changeColor(color);
            barra6.changeColor(color);
            barra7.changeColor(color);
        }
        else if(digit==9) {
            barra1.changeColor(color);
            barra2.changeColor(color);
            barra3.changeColor(color);
            barra4.changeColor(color);
            barra6.changeColor(color);
            barra7.changeColor(color);  
        }
        
    }
    public void next(){
        
        if (digit==9){
            digit=0;
        }
        else{
            digit+=1;
        }
        get();
    }
    public void change(byte digit){
        this.digit=digit;
        get();
    }
    public void change (){
        int a = ThreadLocalRandom.current().nextInt(0,10);
        this.digit = (byte)a;
        get();
    }
    public void moveTo(int xPosition, int yPosition){
       barra1.moveVertical(-yPosition + 50);
       barra2.moveVertical(-yPosition + 40);
       barra2.moveHorizontal(-xPosition + 10);
       barra3.moveVertical(-yPosition + 50);
       barra3.moveHorizontal(-xPosition + 60);
       barra4.moveVertical(-yPosition + 100);
       barra4.moveHorizontal(-xPosition + 10);
       barra5.moveVertical(-yPosition + 110);
       barra6.moveVertical(-yPosition + 160);
       barra6.moveHorizontal(-xPosition + 10);
       barra7.moveVertical(-yPosition + 110);
       barra7.moveHorizontal(-xPosition + 60);
    }
    public void changeColor(String newColor){
        color = newColor;
        get();
    }
    public void makeVisible(){
        get();
        barra1.makeVisible();
        barra2.makeVisible();
        barra3.makeVisible();
        barra4.makeVisible();
        barra5.makeVisible();
        barra6.makeVisible();
        barra7.makeVisible();
    }  
}
