import java.util.ArrayList;
import java.util.*;

/**
 * La clase Nacion son las naciones que se muestran el mundo 
 * las cuales se representan por medio de ciruculos en el mundo mapa.
 * 
 * @author (Torres Julian-Romero Nicolas) 
 * @version (2021-2)
 */
public class Nation
{
    // instance variables - replace the example below with your own
    private String name;
    private Circle nation;
    private ArrayList<Army> armies_list;
    private int armies;
    private ArrayList<Route> routes;
    private Integer distance = Integer.MAX_VALUE;
    //-------------------Dijkstra-----------------------------
    private List<Nation> shortestPath = new LinkedList<>();
    //-------------------DFS-----------------------------
    //private Set<ArrayList<Nation>> allRutes ;
    //private boolean isVisited = false;
    /**
     * Crea la nacion e instancia todos sus componentes
     * @param color nombre de la nacion y su color.
     * @param x pos en x.
     * @param y pos en y.
     * @param armies numero de tropas para conquistar la nacion.
     */
    public Nation(String color, int x, int y, int armies)
    {
        nation = new Circle();
        nation.changePosition(x,y);
        nation.changeColor(color);
        this.name = color;
        this.armies = armies;
        armies_list = new ArrayList<Army>();
    }

    
    /**
     * Añade una ruta a la nacion
     */
    public void addRoute(Nation a,Nation b,int cost){
        if(routes == null){
            routes = new ArrayList<Route>();
        }
        routes.add(new Route(a,b,cost));
    }
    
    /**
     * Retira una ruta
     */
    public void removeRoute(String locationB){
        if(routes !=null){
            for(int i = 0; i<routes.size();i++ ){
                if(routes.get(i).getDestination().getName() == locationB){
                    routes.get(i).makeInvisible();
                    this.routes.remove(i);
                }
            }
        }
    }
    
    
    
    /**
     * Añade una armada a la nacion
     * @param a armada que se añade.
     */
    public void addArmy(Army a){
        if(armies_list==null){
            armies_list = new ArrayList<Army>();
        }
        armies_list.add(a);
    }
    
    /**
     * Retira los ejercitos de la nacion
     */
    public void removeArmy(){
        if(armies_list.size() > 0 ){
            Army arm = armies_list.get(armies_list.size() - 1);
            arm.makeInvisible();
            armies_list.remove(arm);
        }
    }
    
    /**
     * Retorna si la nacion esta conquistada.
     */
    public boolean conquer(){
        boolean flag = false;
        if(armies_list.size() == armies){
            flag = true;
        }
        return flag;
    }
    
    /**
     * Hace visible la nacion
     */
    public void makeVisible(){
        nation.makeVisible();
    }
    
    /**
     * Hace invicisible la nacion
     */
    public void makeInvisible(){
        nation.makeInvisible();
    }
    
    /**
     * Hace invisible las rutas de la Nacion y a ella misma
     */
    public void remove(){
        makeInvisibleRoutes();
        makeInvisible();
    }
    
    
    
    /**
     * Hace visible sus rutas
     */
    public void makeVisibleRoutes(){
        if(routes != null){
            for(Route r:routes){
                r.makeVisible();
            }
        }
    }
    
    /**
     * Hace invisible sus rutas
     */
    public void makeInvisibleRoutes(){
        if(routes != null){
            for(Route r:routes){
                r.makeInvisible();
            }
        }
    }
    
    
    
    /**
     * Hace visible sus armadas de la nacion
     */
    public void makeVisibleArmies(){
        if(armies_list != null){
            for(Army Ar:armies_list){
                Ar.makeVisible();
            }
        }
    }
    
    /**
     * Hace invisible sus armadas de la nacion
     */
    public void makeInvisibleArmies(){
        if(armies_list != null){
            for(Army Ar:armies_list){
                Ar.makeInvisible();
            }
        }
    }
    
    
    //CHANGEs
    public void setShortestPath(List<Nation> shortestPath){
        this.shortestPath = shortestPath;
    }

    public List<Nation> getShortestPath(){
        return shortestPath;
    }
    
    /**
     * mueve la nacion a la nueva posicion.
     * 
     * @param newx nuevapos en x.
     * @param newy nuevapos en y.
     */
    public void changePosition(int newx, int newy){
        nation.changePosition(newx,newy);
    }
    
    /**
     * Agrega el color al circulo de la nacion
     * @param color color de la nacion
     */
    public void changeColor(String color){
        nation.changeColor(color);
    }
    
    
    //GETS AND SETS
    /**
     * Asigna un valor a la distancia
     */
    public void setDistance(int n){
        distance = n;
    }
    
    public Integer getDistance(){
        return distance;
    }
    
    /**
     * Devuelve las rutas de la nacion
     */
    public ArrayList<Route> getRoutes(){
        return routes;
    }
    
    public int getDiameter(){
        return this.nation.getDiameter();
    }
    
    public int getPositionX(){
        return this.nation.getXposition();
    }
    
    public int getPositionY(){
        return this.nation.getYposition();
    }
    
    public String getName(){
        return name;
    }
    
    public Nation getNation(){
        return this;
    }
    
    public ArrayList<Army> getArmys(){
        return this.armies_list;
    }
    
}
