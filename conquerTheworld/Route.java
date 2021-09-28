import java.awt.*;
//TENGO QUE DELEFGAR FUNCIONALIDADES DE LA CLASE WORLD A ESTA CLASE ROUTE.
/**
 * Route es una clase que manipula las rutas entre naciones(Edge) 
 * @author (Torres Julian-Romero Nicolas) 
 * @version (2021-02)
 */
public class Route{
    
    private String color;
    private boolean isVisible;
    private Nation origin;
    private Nation destination;
    private int value;
    private int[] xpoints ;
    private int[] ypoints;
    
    /**
     * Creador del camino.
     * @param xpoints puntos en x para las coordenadas.
     * @param ypoints puntos en y para las coordenadas,
     */
    public Route(Nation a,Nation b, int value){
        color = "gray";
        isVisible = false;
        this.value = value;
        this.origin = a;
        this.destination = b;
        xpoints = new int[4];
        ypoints = new int[4];
        //Cordenadas de los puntos centrales de los circulos
        int Cx1 = a.getPositionX() + a.getDiameter()/2 ;int Cy1 = a.getPositionY() + a.getDiameter()/2;// Punto centro de a
        int Cx2 = b.getPositionX() + b.getDiameter()/2;int Cy2 = b.getPositionY() + b.getDiameter()/2; // Punto centro de b
        if(a.getPositionX() == b.getPositionX()){
            pointsH(Cx1,Cy1,Cx2,Cy2);
        }else if(a.getPositionY() == b.getPositionY()){
            pointsV(Cx1,Cy1,Cx2,Cy2);
        }else if((a.getPositionY() < b.getPositionY() && a.getPositionX() < b.getPositionX())
        ||(a.getPositionY() > b.getPositionY() && a.getPositionX() > b.getPositionX())){
            points1and3(Cx1,Cy1,Cx2,Cy2);
        }else if((a.getPositionY() < b.getPositionY() && a.getPositionX() > b.getPositionX())
        ||(a.getPositionY() > b.getPositionY() && a.getPositionX() < b.getPositionX())){
            points2and4(Cx1,Cy1,Cx2,Cy2);
        }
    }
    
    
    /**
     * Ubica los puntos si las naciones estan alineadas entre el 1er y 3er cuadrante
     * @param Cx1 Coordenada X del circulo a
     * @param Cy1 Coordenada Y del circulo a
     * @param Cx2 Coordenada X del circulo b
     * @param Cy2 Coordenada Y del circulo b
     */
    private void points1and3(int Cx1,int Cy1,int Cx2,int Cy2){
        //puntos primer circulo (a)
        int r = origin.getDiameter()/2;
        xpoints[0] = Cx1 - (r/(int)Math.sqrt(2))/2;xpoints[1]=Cx1 + (r/(int)Math.sqrt(2))/2;
        ypoints[0] = Cy1 + (r/(int)Math.sqrt(2))/2;ypoints[1]=Cy1 - (r/(int)Math.sqrt(2))/2;
        //puntos segundo circulo (b)
        xpoints[2] = Cx2 + (r/(int)Math.sqrt(2))/2;xpoints[3]= Cx2 - (r/(int)Math.sqrt(2))/2;
        ypoints[2] = Cy2 - (r/(int)Math.sqrt(2))/2;ypoints[3]=Cy2 + (r/(int)Math.sqrt(2))/2 ;
    }
    
    /**
     * Ubica los puntos si las naciones estan alineadas entre el 2do y 4to cuadrante
     * @param Cx1 Coordenada X del circulo a
     * @param Cy1 Coordenada Y del circulo a
     * @param Cx2 Coordenada X del circulo b
     * @param Cy2 Coordenada Y del circulo b
     */
    private void points2and4(int Cx1,int Cy1,int Cx2,int Cy2){
        //puntos primer circulo (a)
        int r = origin.getDiameter()/2;
        xpoints[0] = Cx1 - (r/(int)Math.sqrt(2))/2;xpoints[1]=Cx1 + (r/(int)Math.sqrt(2))/2;
        ypoints[0] = Cy1 - (r/(int)Math.sqrt(2))/2;ypoints[1]=Cy1 + (r/(int)Math.sqrt(2))/2;
        //puntos segundo circulo (b)
        xpoints[2] = Cx2 + (r/(int)Math.sqrt(2))/2;xpoints[3]= Cx2 - (r/(int)Math.sqrt(2))/2;
        ypoints[2] = Cy2 + (r/(int)Math.sqrt(2))/2;ypoints[3]=Cy2 - (r/(int)Math.sqrt(2))/2 ;
    }
    
    /**
     * Ubica los puntos si las naciones estan alineadas horizontalmente
     * @param Cx1 Coordenada X del circulo a
     * @param Cy1 Coordenada Y del circulo a
     * @param Cx2 Coordenada X del circulo b
     * @param Cy2 Coordenada Y del circulo b
     */
    private void pointsH(int Cx1,int Cy1,int Cx2,int Cy2){
        //puntos primer circulo (a)
        xpoints[0] = Cx1 - origin.getDiameter()/4 ; xpoints[1]= Cx1 + origin.getDiameter()/4 ;
        ypoints[0] = Cy1;ypoints[1]=Cy1;
        //puntos segundo circulo (b)
        xpoints[2] = Cx2 + destination.getDiameter()/4 ; xpoints[3]= Cx2 - destination.getDiameter()/4 ;
        ypoints[2] = Cy2;ypoints[3]=Cy2;
    }
    
    /**
     * Ubica los puntos si las naciones estan alineadas Verticalmente
     * @param Cx1 Coordenada X del circulo a
     * @param Cy1 Coordenada Y del circulo a
     * @param Cx2 Coordenada X del circulo b
     * @param Cy2 Coordenada Y del circulo b
     */
    private void pointsV(int Cx1,int Cy1,int Cx2,int Cy2){
        //puntos primer circulo (a)
        xpoints[0] = Cx1 ; xpoints[1]= Cx1 ;
        ypoints[0] = Cy1 - origin.getDiameter()/4;ypoints[1]=Cy1 + origin.getDiameter()/4;
        //puntos segundo circulo (b)
        xpoints[2] = Cx2 ; xpoints[3]= Cx2 ;
        ypoints[2] = Cy2 + destination.getDiameter()/4;ypoints[3]=Cy2 - destination.getDiameter()/4 ;
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
    // get set
    
    public Nation getOrigin() {
        return origin;
    }
    
    public Nation getDestination() {
        return destination;
    }
 
    public double getValue() {
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

