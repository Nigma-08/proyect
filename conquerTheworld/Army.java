/**
 * La clase army son las tropas que tienen las naciones 
 * Se representa por medio de triangulos.
 * 
 * @author (Torres Julian-Romero Nicolas) 
 * @version (2021-2)
 */
public class Army
{
    // instance variables - replace the example below with your own
    private int xPosition;
    private int yPosition;
    private Triangle army;

    /**
     * Constructor for objects of class Ejercito
     */
    public Army(int xPosition, int yPosition)
    {
        army = new Triangle(xPosition, yPosition);
    }
    
    public void changeColor(String color){
        army.changeColor(color);
    }
    
    public void makeVisible()
    {
        army.makeVisible();
    }
    
    public int getX(){
        return xPosition;
    }
    
    public int getY(){
        return yPosition;
    }
    
    public void makeInvisible()
    {
        army.makeInvisible();
    }
}
