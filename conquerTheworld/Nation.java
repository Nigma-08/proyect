import java.util.ArrayList;

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
    private int x;
    private int y;
    private Circle nation;
    private ArrayList<Army> armies_list;
    private int armies;
    /**
     * Crea la nacion e instancia todos sus componentes
     * @param color nombre de la nacion y su color.
     * @param x pos en x.
     * @param y pos en y.
     * @param armies numero de tropas para conquistar la nacion.
     */
    public Nation(String color, int x, int y, int armies)
    {
        nation = new Circle(x, y, color);
        this.x = x;
        this.y = y;
        this.name = color;
        this.armies = armies;
        armies_list = new ArrayList<Army>();
        nation.makeVisible();
    }
    
    /**
     * mueve la nacion a la nueva posicion.
     * 
     * @param newx nuevapos en x.
     * @param newy nuevapos en y.
     */
    public void changePosition(int newx, int newy){
        this.x = newx;
        this.y = newy;
    }
    
    /**
     * Añade una armada a la nacion
     * @param a armada que se añade.
     */
    public void addArmy(Army a){
        armies_list.add(a);
    }
    
    /**
     * Hace invicisible la nacion
     */
    public void makeInvisible(){
        nation.makeInvisible();
    }
    
    /**
     * Retira los ejercitos del mundo 
     * 
     */
    public void removeArmy(){
        Army arm = armies_list.get(armies_list.size() - 1);
        armies_list.remove(arm);
        arm.makeInvisible();
    }
    
    //GETS AND SETS
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
        return this.name;
    }
}
