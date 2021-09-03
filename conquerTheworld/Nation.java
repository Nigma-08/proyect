import java.util.ArrayList;
import java.util.Scanner;
public class Nation
{
    // instance variables - replace the example below with your own
    private String nombre;
    private Circle nation;
    private int armie;
    private ArrayList<Army> arms;
    
    /**
    * Constructor for objects of class Nation
    */
    public Nation()
    {   
        Scanner teclado = new Scanner(System.in);
        nation = new Circle();
        nation.changeSize(50);
        arms = new ArrayList<Army>();
    }
    
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void addArmy(Army A){
        arms.add(A);
        countArmy();
    }
    
    private void countArmy(){
        this.armie = arms.size();
    }
    
    public String getName(){
        return this.nombre;
    }
    //Pueba con name.
    public void setName(String nombre){
         this.nombre = nombre;
    }
    //
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
