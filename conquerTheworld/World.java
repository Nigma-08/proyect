
/**
 * Write a description of class world here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World
{
    // instance variables - replace the example below with your own
    private int length;
    private int width;
    private Rectangle fondo;

    /**
     * Constructor for objects of class world
     */
    public World( int length, int width)
    {
        // initialise instance variables
        this.length= length;
        this.width = width;
        fondo = new Rectangle();
        fondo.changeSize(length,width);
        fondo.changeColor("black");
        fondo.makeVisible();
    }


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void addNation(String color,int x ,int y , int armies ){
        Nation nation = new Nation();
        nation.changeColor(color);
        nation.changePosition(x,y);
        nation.makeVisible();
    }
    public void addRoute(){
        
    }
}
