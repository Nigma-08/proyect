import java.util.ArrayList;

/**
 * Write a description of class Ruta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Route
{
    // instance variables - replace the example below with your own
    private Rectangle route;
    private ArrayList <String> locationsA;
    private ArrayList <String> locationsB;
    private int cost;


    public Route()
    {
        // initialise instance 
        route = new Rectangle();
        locationsA = new ArrayList<String>();
        locationsB = new ArrayList<String>();
        cost = 0;
    }
    
    public String getLocationA(String locationA){
        String location = "none";
        for (int i=0;i<locationsA.size();i++){
            if (locationA.equals(locationsA.get(i))){
                location=locationA;
            }
        }
        return location;
    }
    // se crea la ubicacion de la ruta, pero como ubicarla (x) y (y)
    // si es diagonal ...
    // calcular lo largo o ancho de suta para asi dibujarla ...


    public void createRoute(String locationA,String locationB)
    {
        
    }
}
