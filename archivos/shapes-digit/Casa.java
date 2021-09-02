
/**
 * Write a description of class Casa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Casa
{
    // instance variables - replace the example below with your own
    private String direccion;
    private int valor;
    private Rectangle puerta;
    private Rectangle pared;
    private Triangle techo;
    
    public Casa(String direccion, int valor)
    {
       puerta = new Rectangle();
       pared = new Rectangle();
       techo = new Triangle();
       this.direccion = direccion;
       this.valor = valor;
       puerta.changeSize(40,30);
       puerta.moveVertical(130);
       puerta.moveHorizontal(35);
       puerta.changeColor("black");
       pared.changeSize(100,100);
       pared.moveVertical(70);
       pared.changeColor("red");
       techo.changeSize(70,100);
       techo.moveVertical(-1);
       techo.moveHorizontal(-20);
    }
    
    public void makeVisible()
    {
        pared.makeVisible();
        techo.makeVisible();
        puerta.makeVisible();
    }
    public void makeInvisible()
    {
        pared.makeInvisible();
        techo.makeInvisible();
        puerta.makeInvisible();
    }
    public String Mayus(){
       return direccion.toUpperCase();
    }

}
