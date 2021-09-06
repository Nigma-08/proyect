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
    private ArrayList<Object[]> routes;

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
        fondo.changeColor("white");
        nations = new ArrayList<Nation>();
        routes = new ArrayList<Object[]>();
    }


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void addNation(String color,int x ,int y , int armies ){
        Nation nation = new Nation(color, x, y, armies);
        nations.add(nation);
    }
    
    /**
     * Añade rutas desde una nacion a otra. Esta se representa visualmente.
     * Aparte hace el respectivo analisis para que los caminos queden bien posicionados
     * en el tablero del mundo.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void addRoute(String locationA , String locationB, int cost){
        int[] found = foundNations(locationA ,locationB);
        if(found[0] != 0 && found[1] != 0){
            Object[] puente = new Object[3];
            Nation a = this.nations.get(found[0]-1);
            Nation b = this.nations.get(found[1]-1);
            int Cx1 = a.getPositionX() + a.getDiameter()/2 ;int Cy1 = a.getPositionY() + a.getDiameter()/2;// Punto centro de a
            int Cx2 = b.getPositionX() + b.getDiameter()/2;int Cy2 = b.getPositionY() + b.getDiameter()/2; // Punto centro de b
            int[] posx = new int[4];
            int[] posy = new int[4];
            if(a.getPositionX() == b.getPositionX()){
                //puntos primer circulo (a)
                posx[0] = Cx1 - a.getDiameter()/4 ; posx[1]= Cx1 + a.getDiameter()/4 ;
                posy[0] = Cy1;posy[1]=Cy1;
                //puntos segundo circulo (b)
                posx[2] = Cx2 + b.getDiameter()/4 ; posx[3]= Cx2 - b.getDiameter()/4 ;
                posy[2] = Cy2;posy[3]=Cy2;
                Route route = new Route(posx,posy);
                puente[0] = locationA;
                puente[1] = locationB;
                puente[2] = route;
                routes.add(puente);
            }else if(a.getPositionY() == b.getPositionY()){
                //puntos primer circulo (a)
                posx[0] = Cx1 ; posx[1]= Cx1 ;
                posy[0] = Cy1 - a.getDiameter()/4;posy[1]=Cy1 + a.getDiameter()/4;
                //puntos segundo circulo (b)
                posx[2] = Cx2 ; posx[3]= Cx2 ;
                posy[2] = Cy2 + b.getDiameter()/4;posy[3]=Cy2 - b.getDiameter()/4 ;
                Route route = new Route(posx,posy);
                puente[0] = locationA;
                puente[1] = locationB;
                puente[2] = route;
                routes.add(puente);
            }else if((a.getPositionY() < b.getPositionY() && a.getPositionX() < b.getPositionX())||(a.getPositionY() > b.getPositionY() && a.getPositionX() > b.getPositionX())){
                //puntos primer circulo (a)
                int r = a.getDiameter()/2;
                posx[0] = Cx1 - (r/(int)Math.sqrt(2))/2;posx[1]=Cx1 + (r/(int)Math.sqrt(2))/2;
                posy[0] = Cy1 + (r/(int)Math.sqrt(2))/2;posy[1]=Cy1 - (r/(int)Math.sqrt(2))/2;
                //puntos segundo circulo (b)
                posx[2] = Cx2 + (r/(int)Math.sqrt(2))/2;posx[3]= Cx2 - (r/(int)Math.sqrt(2))/2;
                posy[2] = Cy2 - (r/(int)Math.sqrt(2))/2;posy[3]=Cy2 + (r/(int)Math.sqrt(2))/2 ;
                Route route = new Route(posx,posy);
                puente[0] = locationA;
                puente[1] = locationB;
                puente[2] = route;
                routes.add(puente);    
            }else if((a.getPositionY() < b.getPositionY() && a.getPositionX() > b.getPositionX())||(a.getPositionY() > b.getPositionY() && a.getPositionX() < b.getPositionX())){
                //puntos primer circulo (a)
                int r = a.getDiameter()/2;
                posx[0] = Cx1 - (r/(int)Math.sqrt(2))/2;posx[1]=Cx1 + (r/(int)Math.sqrt(2))/2;
                posy[0] = Cy1 - (r/(int)Math.sqrt(2))/2;posy[1]=Cy1 + (r/(int)Math.sqrt(2))/2;
                //puntos segundo circulo (b)
                posx[2] = Cx2 + (r/(int)Math.sqrt(2))/2;posx[3]= Cx2 - (r/(int)Math.sqrt(2))/2;
                posy[2] = Cy2 + (r/(int)Math.sqrt(2))/2;posy[3]=Cy2 - (r/(int)Math.sqrt(2))/2 ;
                Route route = new Route(posx,posy);
                puente[0] = locationA;
                puente[1] = locationB;
                puente[2] = route;
                routes.add(puente);
            }
        }else{
            //Msm de que no se encuentran las 2 naciones 
        }
    }
    
    /**
     * Mira si las naciones estan creadas y retorn un vector con sus posicones empezando
     * desde 1 si no retorna esa pos en 0-
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    private int[] foundNations(String locationA , String locationB){
        boolean found = false,foundT = false;
        int[] pos = new int[2];
        for(Nation n : nations){
            if(!found){
                if(n.getName().equals(locationA)){
                    found = true;
                    //System.out.println(locationA+" "+locationB);
                    pos[0] =(nations.indexOf(n)+1);
                }
            }else{
                if(n.getName().equals(locationB)){
                    foundT = true;
                    //System.out.println(locationA+" "+locationB);
                    pos[1] =(nations.indexOf(n)+1);
                }
            }
        }
        return pos;
    }
    
     /**
     * Añade ejercitos a la nacion seleccionada, se reprensta visualemte
     * por un triangulo.
     * @param  location Nacion en donde se pondran los ejercitos.
     */
    public void putArmy(String location){
        for (Nation n: nations){
            if (n.getName().equals(location)){
                Army arm = new Army(n.getPositionX() + 25, n.getPositionY() + 12);
                n.addArmy(arm);
                arm.makeVisible();
            }
        }
    }
    
    /**
     * Elimina una nacion si exite
     * @param color Nacion en donde se pondran los ejercitos.
     */
    public void delNation(String color){
        for (Nation n: nations  ){
            if (n.getName().equals(color)){
                nations.remove(n);
                n.makeInvisible();
            }
        }
    }
    
    /**
     * Remueve el camino que conecta dos naciones.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void delStreet(String locationA, String locationB){
        for (Object[] r: routes){
            if (r[0] == locationA && r[1] == locationB || 
            r[0] == locationB && r[1] == locationA){
                Route ruta = (Route)r[2];
                ruta.makeInvisible();
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
        for (Object[] r: routes){
            if (((String)r[0]).equals(locationA) && ((String)r[1]).equals(locationB) || 
            ((String)r[0]).equals(locationB) && ((String)r[1]).equals(locationA)){
                putArmy(locationB);
                removeArmy(locationA);
            }
        }
    }
    
     /**
     * hace invisible el tablero
     */
    public void makeVisible(){
        fondo.makeVisible();
    }
    
     /**
     * Hace visible el mundo
     */
    public void makeInvisible(){
        fondo.makeInvisible();
    }
    
    /**
     * Sale del programa
     */
    public void exit(){
        System.exit(0);
    }
}
