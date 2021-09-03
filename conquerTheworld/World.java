import java.util.ArrayList;
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
    private ArrayList<Nation> Nations;

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
        Nations = new  ArrayList<Nation>();
    }

    public void addNation(String color,int x ,int y , int armies ){
        Nation nation = new Nation();
        nation.changeColor(color);
        nation.changePosition(x,y);
        Nations.add(nation);
        nation.setNombre(String.valueOf(Nations.size()));
        System.out.println(Nations.size());
        nation.makeVisible();
    }
    
    public void putArmy(String location ){
        for(Nation n : Nations){
            if(n.getName().equals(location)){
                Army amr = new Army();
                n.addArmy(amr);
            }
        }
    }
    
    public void addRoute(String locationA , String locationB){
        boolean found = foundNations(locationA ,locationB);
        if(found){
            
        }
    }
    
    
    private boolean foundNations(String locationA , String locationB){
        boolean found = false,foundT = false;
        for(Nation n : Nations){
            if(!found){
                if(n.getName().equals(locationA)){
                    found = true;
                    System.out.println(locationA+" "+locationB);
                }
            }else{
                if(n.getName().equals(locationB)){
                    foundT = true;
                    System.out.println(locationA+" "+locationB);
                }
            }
        }
        return foundT;
    }
}
