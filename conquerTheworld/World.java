import java.util.ArrayList;

/**
 *Esta calse sirve para manejar las naciones.
 *las naciones seran representadas por circulos utilizando (shapes)
 * 
 * @author (Torres Julian-Romero Nicolas) 
 * @version (2021-2)
 */
public class World
{
    // instance variables - replace the example below with your own
    private int length;
    private int width;
    private Rectangle fondo;
    private ArrayList<Nation> nations;
    

    /**
     * Crea el mundo en el cual van a estar las naciones caminos y ejercitos.
     * @param length largo del mundo.
     * @param width ancho del mundo.
     */
    public World(int length, int width)
    {
        // initialise instance variables
        this.length= length;
        this.width = width;
        fondo = new Rectangle();
        fondo.changeSize(length,width);
        fondo.changeColor("black");
    }


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void addNation(String color,int x ,int y , int armies ){
        if(nations == null ){
            nations = new ArrayList<Nation>();
        }
        this.nations.add(new Nation(color,x,y,armies));
    }
    
    /**
     * Elimina una nacion si exite
     * @param color Nacion en donde se pondran los ejercitos.
     */
    public void delNation(String color){
        int indexNation = -1;
        for (Nation n: nations){
            if (n.getName() == color ){
                n.remove();
                ArrayList<Route> rutasNacion = n.getRoutes();
                for(Route r:rutasNacion){
                    r.getDestination().removeRoute(n.getName());
                }
                indexNation = nations.indexOf(n);
            }
        }
        
        if(indexNation != -1){
            nations.remove(indexNation);
        }
    }

    
    
    /**
     * Añade rutas desde una nacion a otra. Esta se representa visualmente.
     * Aparte hace el respectivo analisis para que los caminos queden bien posicionados
     * en el tablero del mundo.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void addRoute(String locationA , String locationB, int cost){
        ArrayList<Nation> nationSearch = foundNations(locationA ,locationB);
        if(nationSearch.size()!= 0){
            if(nationSearch.get(0).getName() == locationA){
                Nation a = nations.get(nations.indexOf(nationSearch.get(0)));
                Nation b = nations.get(nations.indexOf(nationSearch.get(1)));
                a.addRoute(a,b,cost);
                b.addRoute(b,a,cost);
            }else{
                Nation a = nations.get(nations.indexOf(nationSearch.get(1)));
                Nation b = nations.get(nations.indexOf(nationSearch.get(0)));
                a.addRoute(a,b,cost);
                b.addRoute(b,a,cost);
            }
        }
    }
    
    /**
     * Remueve el camino que conecta dos naciones.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void delStreet(String locationA, String locationB){
        ArrayList<Nation> nationSearch = foundNations(locationA ,locationB);
        if(nationSearch.size()!= 0){
            if(nationSearch.get(0).getName() == locationA){
                Nation a = nations.get(nations.indexOf(nationSearch.get(0)));
                Nation b = nations.get(nations.indexOf(nationSearch.get(1)));
                a.removeRoute(locationB);
                b.removeRoute(locationA);
            }else{
                Nation a = nations.get(nations.indexOf(nationSearch.get(1)));
                Nation b = nations.get(nations.indexOf(nationSearch.get(0)));
                a.removeRoute(locationB);
                b.removeRoute(locationA);
            }
        }
    }
    
    
    
    /**
     * Añade ejercitos a la nacion seleccionada, se reprensta visualemte
     * por un triangulo.
     * @param  location Nacion en donde se pondran los ejercitos.
     */
    public void putArmy(String location){
        for (Nation n: nations){
            if (n.getName()==location){
                Army arm = new Army(n.getPositionX() + 25, n.getPositionY() + 12);
                n.addArmy(arm);
            }
        }
    }
    
    /**
     * Remueve los ejercitos que tiene una nacion si exite la nacion y si tienen ejercitos
     * @param color nombre de las nacion.
     */
    public void removeArmy(String color){
        for (Nation n: nations  ){
            if (n.getName().equals(color)){
                n.removeArmy();
            }
        }
    }
    
    /**
     * Mueve los ejercitos de una nacion a otra si existe un puente.
     * 
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void moveArmyOneRoute(String locationA, String locationB){
        ArrayList<Nation> nationSearch = foundNations(locationA ,locationB);
        if(nationSearch.size()!= 0){
            if(nationSearch.get(0).getName() == locationA){
                Nation a = nations.get(nations.indexOf(nationSearch.get(0)));
                Nation b = nations.get(nations.indexOf(nationSearch.get(1)));
                for(Route r: a.getRoutes()){
                    if(r.getDestination().equals(b)){
                        a.removeArmy();
                        putArmy(b.getName());
                    }
                }
            }else{
                Nation a = nations.get(nations.indexOf(nationSearch.get(1)));
                Nation b = nations.get(nations.indexOf(nationSearch.get(0)));
                for(Route r: a.getRoutes()){
                    if(r.getDestination().equals(b)){
                        a.removeArmy();
                        putArmy(b.getName());
                    }
                }
            }
        }
    }
    
    
    
    /**
     * hace invisible el tablero
     */
    public void makeVisible(){
        fondo.makeVisible();
        if(nations != null){
            for(Nation n:nations){
                n.makeVisibleRoutes();
            }
            
            for(Nation n:nations){
                n.makeVisible();
            }
            
            for(Nation n:nations){
                n.makeVisibleArmies();
            }
        }
    }
    
    /**
     * Hace visible el mundo
     */
    public void makeInvisible(){
        fondo.makeInvisible();
        for(Nation n:nations){
            n.makeInvisible();
            n.makeInvisibleRoutes();
            n.makeVisibleArmies();
        }
    }
    
    
    
    /**
     * Sale del programa
     */
    public void exit(){
        System.exit(0);
    }
    
    
    
    /**
     * Mira si las naciones estan creadas y retorna un array con las naciones.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    private ArrayList<Nation> foundNations(String locationA , String locationB){
        ArrayList<Nation> nationsFound = new ArrayList<Nation>();
        for(Nation n: nations){
            System.out.println(n.getName());
            if(n.getName().equals(locationA)){
                //System.out.println(locationA);
                nationsFound.add(n);
            }else if(n.getName().equals(locationB)){
                //System.out.println(locationA);
                nationsFound.add(n);
            }
        }
        if(nationsFound.size() > 1){
            return nationsFound;
        }
        return nationsFound;
    }
}
