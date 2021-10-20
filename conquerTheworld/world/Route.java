package world;


import shapes.*;
//TENGO QUE DELEFGAR FUNCIONALIDADES DE LA CLASE WORLD A ESTA CLASE ROUTE.
/**
 * Route es una clase que manipula las rutas entre naciones(Edge) 
 * @author (Torres Julian-Romero Nicolas) 
 * @version (2021-02)
 */
public class Route extends Poligon{

    private Nation origin;
    private Nation destination;
    private Integer value;
    
    /**
     * Creador del camino.
     * @param xpoints puntos en x para las coordenadas.
     * @param ypoints puntos en y para las coordenadas,
     */
    public Route(Nation a,Nation b, int value){
        
        super(a,b);
        this.value = value;
        this.origin = a;
        this.destination = b;
        
    }
    
    // get set
    
    public Nation getOrigin() {
        return origin;
    }
    
    public Nation getDestination() {
        return destination;
    }
 
    public Integer getValue() {
        return value;
    }
    
    
    
    /*
    public void setOrigin(Nation origin) {
        this.origin = origin;
    }
    public void setDistance(int newValue) {
        this.value = newValue;
    }
    public void setDestination(Nation destination) {
        this.destination = destination;
    }
    */
   
   
}

