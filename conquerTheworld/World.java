
import java.util.*;

/**
 *Esta calse sirve para manejar las naciones.
 *las naciones seran representadas por circulos utilizando (shapes)
 * 
 * @author (Torres Julian-Romero Nicolas) 
 * @version (1.5)
 */
public class World 
{
    // instance variables - replace the example below with your own
    private int length;
    private int width;
    private Rectangle fondo;
    private HashMap<String,Nation> nations;
    private boolean ok;
    private int payment;
    private boolean isVisible;

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
        payment = 0;
        fondo = new Rectangle();
        fondo.changeSize(length,width);
        fondo.changeColor("black");
        ok = false;
        isVisible = false;
    }

    /**
     * Crea el mundo con atributos iniciales dados
     * @param
     * @param
     * @param
     */
    public World(int nations ,int[][] routes , int[][] armies){
        this(500,500);
        int pos;
        Stack < Integer > posXrandom = random(length);
        Stack < Integer > posYrandom = random(width);
        String[] colores = {"yellow","blue","orange","cyan","red","pink","light gray","white","magenta","green"};
        for(int i = 0; i < nations; i++){
            this.addNation(colores[i],posXrandom.pop(),posYrandom.pop(),armies[i][1]);
            for(int j = 0; j < armies[i][0];j++){
                putArmy(colores[i]);
            }
        }
        
        for(int i = 0;i < routes.length;i ++){
            this.addRoute(colores[routes[i][0]-1],colores[routes[i][1]-1],routes[i][2]);
        }
    }
    
    /**
     * Random de numeros desde 50 hasta el num dado
     * con saltos de a 50
     */
    private Stack<Integer> random(int num){
        Stack < Integer > posRandom = new Stack < Integer > ();
        int x = num-50 ;int pos;
        for (int i = 50; i < x ; i+=50) {
          pos = (int) Math.floor(Math.random() * x );
          while (posRandom.contains(pos)) {
            pos = (int) Math.floor(Math.random() * x );
          }
          posRandom.push(pos);
        }
        return posRandom;
    }
    
    /**
     * Mueve las naciones de la nacionA a la nacionB
     * con el menor costo posible
     * @param nationA nacion origen
     * @param nationB nacion destino
     */
    public void moveArmy(String nationA,String nationB){
        ok = false;
        Nation a = nations.get(nationA);
        Nation b = nations.get(nationB);
        if(a != null && b != null && (a.getArmys()!= null && a.getArmys().size()> 0)){
            for(Nation n :nations.values()){
            n.setShortestPath(new LinkedList<>());
            n.setDistance(Integer.MAX_VALUE);
            }
            
            Nation destination = Dijkstra.calculateShortestPathFromSource(this,nations.get(nationA),nations.get(nationB));
            List<Nation> path = destination.getShortestPath();
            if(path.size() > 0 ){
                String[] road = new String[nations.size()];
                int count = 0;
                while(path.size() != 0 ){
                    road[count] = (path.get(0).getName());
                    path.remove(0);
                    count++;
                }
                road[count] = nationB;
                for(int i = 0 ;i< road.length -1;i++){
                    moveArmyOneRoute(road[i],road[i+1]);
                    try
                    {Thread.sleep(1500);}
                    catch(InterruptedException ex)
                    {Thread.currentThread().interrupt();}
                }
            }else{ok = false;} 
        }else{ok = false;}
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void addNation(String color,int x ,int y , int armies ){
        if(nations == null ){
            nations = new HashMap<String,Nation>();
        }
        if(nations.size() == 0){
            Nation na = new Nation(color,x,y,armies);
            nations.put(na.getName(),na);
            ok = true;
        }else{
            if(nations.get(color) == null){
                Nation na = new Nation(color,x,y,armies);
                nations.put(na.getName(),na);
                ok = true;
            }else{ok = false;}
        }if(isVisible){makeVisible();
        }else{makeInvisible();}
    }
    
    
    /**
     * Elimina una nacion si exite
     * @param color Nacion en donde se pondran los ejercitos.
     */
    public void delNation(String color){
        Nation deleteNation = nations.get(color);
        if(nations.get(color) != null){
            for(String n :nations.keySet()){
                Nation a = nations.get(n);
                a.removeRoute(deleteNation.getName());
            }   
            deleteNation.remove();
            deleteNation.makeInvisibleArmies();
            nations.remove(color);
            ok = true;
        }else{ok = false;}
        if(isVisible){makeVisible();
        }else{makeInvisible();}
    }
    
    
    /**
     * Añade rutas desde una nacion a otra. Esta se representa visualmente.
     * Aparte hace el respectivo analisis para que los caminos queden bien posicionados
     * en el tablero del mundo.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void addRoute(String locationA , String locationB, int cost){
        boolean flag = true;
        Nation a = nations.get(locationA);
        Nation b = nations.get(locationB);
        if(a != null && b != null && locationA != locationB){
            ArrayList<Route> rutasNacionA = a.getRoutes();
            ArrayList<Route> rutasNacionB = b.getRoutes();
            if(rutasNacionA != null && rutasNacionB != null){
                //Buscar si existe la ruta en esa nacion
                for(Route r:rutasNacionA){
                    if(r.getDestination().equals(b)){
                        flag = false;
                        ok = false;
                    }
                }
                if(flag){
                    a.addRoute(a,b,cost);
                    b.addRoute(b,a,cost);
                    ok = true;
                }
            }else{
                a.addRoute(a,b,cost);
                b.addRoute(b,a,cost);
                ok = true;
            }
        }else{ok=false;}
        if(isVisible){makeVisible();
        }else{makeInvisible();}
    }
    
    
    
    /**
     * Remueve el camino que conecta dos naciones.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void delStreet(String locationA, String locationB){
        Nation a = nations.get(locationA);
        Nation b = nations.get(locationB);
        ok = false;
        boolean flag = false;
        if(a != null && b != null){
            ArrayList<Route> rutasNacionA = a.getRoutes();
            ArrayList<Route> rutasNacionB = b.getRoutes();
            if(rutasNacionA != null && rutasNacionB != null){
                for(Route r:rutasNacionA){
                    if(r.getDestination().equals(b)){
                        flag = true;
                    }
                }
                if(flag){
                    a.removeRoute(b.getName());
                    b.removeRoute(a.getName());
                    ok = true;
                }
            }else{
                ok = false;
            }
        }else{ok=false;}
        if(isVisible){makeVisible();
        }else{makeInvisible();}
    }
    
    
    /**
     * Añade ejercitos a la nacion seleccionada, se reprensta visualemte
     * por un triangulo.
     * @param  location Nacion en donde se pondran los ejercitos.
     */
    public void putArmy(String location){
        Nation n = nations.get(location);
        if (n != null){
            Army arm = new Army(n.getPositionX() + 25, n.getPositionY() + 12);
            if(n.getName() == "green"){
                arm.changeColor("gray");
            }
            n.addArmy(arm);
            ok = true;
        }else{ok = false;}
        if(isVisible){makeVisible();
        }else{makeInvisible();}
    }
    
    
    /**
     * Remueve los ejercitos que tiene una nacion si exite la nacion y si tienen ejercitos
     * @param color nombre de las nacion.
     */
    public void removeArmy(String color){
        Nation n = nations.get(color);
        if (n != null && n.getArmys().size() > 0){
            n.removeArmy();
            ok = true;
        }else{ok = false;}
        if(isVisible){makeVisible();
        }else{makeInvisible();}
    }
    
    
    /**
     * Mueve los ejercitos de una nacion a otra si existe un camino.
     * 
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void moveArmyOneRoute(String locationA, String locationB){
        Nation a = nations.get(locationA);
        Nation b = nations.get(locationB);
        ok = false;
        boolean flag = true;
        if(a != null && b != null && (a.getArmys()!= null && a.getArmys().size()> 0)){
            if(a.getRoutes() != null){
                for(Route r: a.getRoutes()){
                    if(r.getDestination().equals(b)){
                        a.removeArmy();
                        putArmy(b.getName());
                        payment += r.getValue(); 
                        ok = true;
                    }else{ok=false; flag = false;}
                }
            }
        }else{ok = false;}
        if(isVisible){makeVisible();
        }else{makeInvisible();}
    }
    
    /**
     * Retorna el nombre de las naciones que estan conquistadas
     */
    public String[] conqueredNations(){
        String[] conqueredN = new String[nations.size()];
        int cont = 0;
        for(String n :nations.keySet()){
            Nation a = nations.get(n);
            if(a.conquer()){
                conqueredN[cont] = a.getName();
                cont++;
            }
        }
        return conqueredN;
    }
    
    /**
     * Retorna el valor de mover las naciones
     */
    public int payment(){
        return payment;
    }
    
    
    /**
     * Retorna si todas las naciones estan conquistadas
     */
    public boolean conquer(){
        boolean flag = false;
        int count = -1;
        for(String n :nations.keySet()){
            Nation a = nations.get(n);
            if(a.conquer()){
                count++;
            }
        }
        if(count == nations.size()){
            flag = true;
        }
        return flag;
    }
    
    /**
     * hace invisible el tablero
     */
    public void makeVisible(){
        fondo.makeVisible();
        if(nations != null){
            fondo.makeVisible();
            for(Nation n :nations.values()){
                n.makeVisibleRoutes();
            }
            
            for(String n :nations.keySet()){
                Nation a = nations.get(n);
                a.makeVisible();
            }
            
            for(String n :nations.keySet()){
                Nation a = nations.get(n);
                a.makeVisibleArmies();
            }
            
        }
        isVisible = true;
    }
     
    /**
     * Hace visible el mundo
     */
    public void makeInvisible(){
        fondo.makeInvisible();
        if(nations != null){
            for(String n :nations.keySet()){
                Nation a = nations.get(n);
                a.makeInvisible();
                a.makeInvisibleRoutes();
                a.makeInvisibleArmies();
            }
        }
        isVisible = false;
    }
    
    /**
     * Verifica si el ultimo metodo se ejecuto satisfactoriamente
     */
    public boolean ok(){
        return ok;
    }
    
    /**
     * Sale del programa
     */
    public void exit(){
        System.exit(0);
    }
    
}
