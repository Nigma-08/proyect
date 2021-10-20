package world;

import java.util.*;


/**
 * Write a description of class World2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World
{

    Graph graph;
    Stack<GraphState> stateStack = new Stack<GraphState>();
    /**
     * Crea el mundo en el cual van a estar las naciones caminos y ejercitos.
     * @param length largo del mundo.
     * @param width ancho del mundo.
     */
    public World(int length, int width)
    {
        graph = new Graph(length,width);
    }
    
    /**
     * Crea el mundo con atributos iniciales dados
     * @param nations cantidad de naciones
     * @param routes matrix con las rutas
     * @param armies matrix con las naciones y la cantidad para conquistar una nacion
     */
    public  World(int nations ,int[][] routes , int[][] armies){
        graph = new Graph(nations,routes,armies);
    }
    
    /**
     * Mueve las naciones de la nacionA a la nacionB
     * con el menor costo posible
     * @param nationA nacion origen
     * @param nationB nacion destino
     */
    public void moveArmy(String nationA,String nationB){
        graph.moveArmy(nationA,nationB);
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void addNation(String color,int x ,int y , int armies ){
        GraphState copyGraph = this.graph.createMemento();
        stateStack.push(copyGraph);
        graph.addNation(color,x,y,armies);
    }
    
    
    /**
     * Elimina una nacion si exite
     * @param color Nacion en donde se pondran los ejercitos.
     */
    public void delNation(String color){
       graph.delNation(color);
    }
    
    
    /**
     * Añade rutas desde una nacion a otra. Esta se representa visualmente.
     * Aparte hace el respectivo analisis para que los caminos queden bien posicionados
     * en el tablero del mundo.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void addRoute(String locationA , String locationB, int cost){
        graph.addRoute(locationA,locationB,cost);
    }
    
    
    
    /**
     * Remueve el camino que conecta dos naciones.
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void delStreet(String locationA, String locationB){
       graph.delStreet(locationA,locationB);
    }
    
    
    /**
     * Añade ejercitos a la nacion seleccionada, se reprensta visualemte
     * por un triangulo.
     * @param  location Nacion en donde se pondran los ejercitos.
     */
    public void putArmy(String location){
        graph.putArmy(location);
    }
    
    
    /**
     * Remueve los ejercitos que tiene una nacion si exite la nacion y si tienen ejercitos
     * @param color nombre de las nacion.
     */
    public void removeArmy(String color){
        graph.removeArmy(color);
    }
    
    
    /**
     * Mueve los ejercitos de una nacion a otra si existe un camino.
     * 
     * @param locationA Nacion de origen.
     * @param locationB nacion de llegada.
     */
    public void moveArmyOneRoute(String locationA, String locationB){
        graph.moveArmyOneRoute(locationA,locationB);
    }
    
    /**
     * Retorna el nombre de las naciones que estan conquistadas
     */
    public String[] conqueredNations(){
        return graph.conqueredNations();
    }
    
    /**
     * Retorna el valor de mover las naciones
     */
    public int payment(){
        return graph.payment();
    }
    
    
    /**
     * Retorna si todas las naciones estan conquistadas
     */
    public boolean conquer(){
        return graph.conquer();
    }
    
    /**
     * hace invisible el tablero
     */
    public void makeVisible(){
       graph.makeVisible();
    }
     
    /**
     * Hace visible el mundo
     */
    public void makeInvisible(){
        graph.makeInvisible();
    }
    
    /**
     * Verifica si el ultimo metodo se ejecuto satisfactoriamente
     */
    public boolean ok(){
        return graph.ok();
    }
    
    /**
     * Undo
     */
    public void undo(){
        graph.restore(stateStack.pop());
    }
    
    /**
     * Sale del programa
     */
    public void exit(){
        graph.exit();
    }
    
}
