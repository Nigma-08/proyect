import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<Nation> nations;
    private boolean ok;
    private int payment;

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
        boolean flag = false;
        if(nations.size() == 0){
            this.nations.add(new Nation(color,x,y,armies));
            ok = true;
        }else{
            for(Nation n : nations ){
                if(n.getName() != color){
                    flag = true;
                }
            }
            if(flag){
                this.nations.add(new Nation(color,x,y,armies));
                ok = true;
            }else{ok = false;}
        }
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
                n.makeInvisibleArmies();
                ArrayList<Route> rutasNacion = n.getRoutes();
                if(rutasNacion != null){
                    for(Route r:rutasNacion){
                        r.getDestination().removeRoute(n.getName());
                    }               
                }
                indexNation = nations.indexOf(n);
            }
        }
        
        if(indexNation != -1){
            nations.remove(indexNation);
            ok = true;
        }else{ok = false;}
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
        Nation a, b; int indxA =-1, indxB=-1;ok = false;boolean flag = true;
        if(nationSearch.size() != 0){
            if(nationSearch.get(0).getName() == locationA){
                indxA = nations.indexOf(nationSearch.get(0));
                indxB = nations.indexOf(nationSearch.get(1));
            }else if(nationSearch.get(0).getName() == locationB){
                indxA = nations.indexOf(nationSearch.get(1));
                indxB = nations.indexOf(nationSearch.get(0));
            }else{ok = false;}
            if(indxA != -1 && indxB != -1){
                a = nations.get(indxA);
                b = nations.get(indxB);
                if(a.getRoutes() != null){
                    for(Route r: a.getRoutes()){
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
            }
        }else{ok = false;}
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
                ok = true;
            }else if(nationSearch.get(0).getName() == locationB){
                Nation a = nations.get(nations.indexOf(nationSearch.get(1)));
                Nation b = nations.get(nations.indexOf(nationSearch.get(0)));
                a.removeRoute(locationB);
                b.removeRoute(locationA);
                ok = true;
            }else{ok = false;}
        }else{ok = false;}
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
                ok = true;
            }else{ok = false;}
        }
    }
    
    /**
     * Remueve los ejercitos que tiene una nacion si exite la nacion y si tienen ejercitos
     * @param color nombre de las nacion.
     */
    public void removeArmy(String color){
        for (Nation n: nations  ){
            if (n.getName().equals(color) && n.getArmys().size() > 0){
                n.removeArmy();
                ok = true;
            }else{ok = false;}
        }
    }
    
    /**
     * Mueve los ejercitos de una nacion a otra si existe un camino.
     * 
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void moveArmyOneRoute(String locationA, String locationB){
        ArrayList<Nation> nationSearch = foundNations(locationA ,locationB);
        Nation a, b; int indxA =-1, indxB=-1;
        ok = false;
        if(nationSearch.size() != 0){
            if(nationSearch.get(0).getName() == locationA){
                indxA = nations.indexOf(nationSearch.get(0));
                indxB = nations.indexOf(nationSearch.get(1));
            }else if(nationSearch.get(0).getName() == locationB){
                indxA = nations.indexOf(nationSearch.get(1));
                indxB = nations.indexOf(nationSearch.get(0));
            }else{ok = false;}
            if(indxA != -1 && indxB != -1){
                a = nations.get(indxA);
                b = nations.get(indxB);  
                if(a.getRoutes() != null){
                    for(Route r: a.getRoutes()){
                        if(r.getDestination().equals(b)){
                            a.removeArmy();
                            putArmy(b.getName());
                            ok = true;
                            payment += r.getValue(); 
                        }
                    }
                }
            }
        }else{ok = false;}
    }
    
    /**
     * Retorna el nombre de las naciones que estan conquistadas
     */
    public String[] conqueredNations(){
        String[] conqueredN = new String[nations.size()];
        int cont = 0;
        for(Nation n :nations){
            if(n.conquer()){
                conqueredN[cont] = n.getName();
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
        int count = 0;
        for(Nation n :nations){
            if(n.conquer()){
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
            for(Nation n:nations){
                n.makeVisibleRoutes();
            }
            
            for(Nation n:nations){
                n.makeVisible();
            }
            
            for(Nation n:nations){
                n.makeVisibleArmies();
            }
            ok = true;
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
        ok = true;
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
    
    /**
     * Mira si las naciones estan creadas y retorna un array con las naciones.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    private ArrayList<Nation> foundNations(String locationA , String locationB){
        ArrayList<Nation> nationsFound = new ArrayList<Nation>();
        for(Nation n: nations){
            if(n.getName().equals(locationA)){
                nationsFound.add(n);
            }else if(n.getName().equals(locationB)){
                nationsFound.add(n);
            }
        }
        
        if(nationsFound.size() > 1 ){
            return nationsFound;
        }else{
            return nationsFound = new ArrayList<Nation>();
        }
        
    }
}
