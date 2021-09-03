import java.util.ArrayList;

public class Nation
{
    // instance variables - replace the example below with your own
    private String nombre;
    private Circle nation;
    private int armie;
    /**
    * Constructor for objects of class Nation
    */
    public Nation()
    {
        nation = new Circle();
        nation.changeSize(50);
    }

    public void changeColor(String color){
        this.nation.changeColor(color);        
    }
    public void changePosition(int x, int y){
        this.nation.setY(y);
        this.nation.setX(x);
    }
    public void armie (int armies){
        this.armie=armies;
    }
    public void makeVisible(){
        this.nation.makeVisible();
    }
    public void makeInvisible(){
        this.nation.makeInvisible();
    }
}