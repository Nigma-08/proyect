import java.awt.*;

/**
 * Route es una clase que manipula las rutas entre naciones
 * 
 * @author (Torres Julian-Romero Nicolas) 
 * @version (2021-02)
 */
public class Route{
    
    private String color;
    private boolean isVisible;
    private int[] xpoints ;
    private int[] ypoints;
    
    /**
     * Creador del camino.
     * @param xpoints puntos en x para las coordenadas.
     * @param ypoints puntos en y para las coordenadas,
     */
    public Route(int [] xpoints,int [] ypoints){
        color = "gray";
        isVisible = false;
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        makeVisible();
    }
    
    
    /**
     * Hace visible el camino 
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace invisible el camino, si ya esta visible no hace nada. 
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    
    /**
     * Dibuja el rectangulo en la pantalla del canva.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 4));
            canvas.wait(10);
        }
    }

    /**
     * Borra el rectangulo de la pantalla.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

