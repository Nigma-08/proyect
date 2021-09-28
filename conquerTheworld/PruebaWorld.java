import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
/**
 * The test class PruebaWorld.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PruebaWorld
{
    private World w;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        w = new World(500,500);
        w.addNation("red",0,0,2);
    }
    
    /**
     * Prueba del metodo de añadir naciones
     */
    @Test
    public void DeberiaCrearNacion(){
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de añadir naciones
     */
    @Test
    public void NoDeberiaCrearNacion(){
        w.addNation("red",400,50,0);
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de borrar naciones
     */
    @Test
    public void DeberiaBorrarNacion(){
        w.delNation("red");
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de borrar naciones
     */
    @Test
    public void NoDeberiaBorrarNacion(){
        w.delNation("blue");
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de crear rutas
     */
    @Test
    public void DeberiaCrearRuta(){
        w.addNation("blue",150,50,4);
        w.addRoute("red","blue",5);
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de crear rutas
     */
    @Test
    public void NoDeberiaCrearRuta(){
        w.addRoute("red","red",10);
        assertTrue(w.ok());
        w.addRoute("red","blue",10);
        assertTrue(w.ok());
        w.addNation("yellow",50,50,0);
        w.addRoute("red","yellow",5);
        w.addRoute("yellow","red",0);
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de eliminar rutas
     */
    @Test
    public void DeberiaEliminarRuta(){
        w.addNation("blue",150,50,4);
        w.addRoute("red","blue",5);
        w.delStreet("red","blue");
        assertTrue(w.ok());
        w.addRoute("red","blue",5);
        w.delStreet("blue","red");
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de eliminar rutas
     */
    @Test
    public void NoDeberiaEliminarRuta(){
        w.delStreet("blue","red");
        assertTrue(w.ok());
        w.addNation("yellow",50,50,0);
        w.delStreet("yellow","red");
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de añadir ejercitos
     */
    @Test
    public void DeberiaAñadirEjercito(){
        w.putArmy("red");
        assertTrue(w.ok());
        w.addNation("blue",150,50,4);
        w.putArmy("blue");
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de añadir ejercitos
     */
    @Test
    public void NoDeberiaAñadirEjercito(){
        w.putArmy("blue");
        assertTrue(w.ok());
        w.putArmy("green");
        assertTrue(w.ok());
        w.putArmy("orange");
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de eliminhar ejercitos
     */
    @Test
    public void DeberiaEliminarEjercito(){
        w.putArmy("red");
        w.removeArmy("red");
        assertTrue(w.ok());
        w.addNation("blue",150,50,4);
        w.putArmy("blue");
        w.removeArmy("blue");
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de eliminhar ejercitos
     */
    @Test
    public void NoDeberiaEliminarEjercito(){
        w.removeArmy("blue");
        assertTrue(w.ok());
        w.removeArmy("red");
        assertTrue(w.ok());
    }
    
    
    /**
     * Prueba del metodo de eliminhar ejercitos
     */
    @Test
    public void DeberiaMoverEjercitos(){
        w.addNation("green",50,50,4);
        w.addRoute("red","green",0);
        for(int i =0; i < 3;i++){
            w.putArmy("green");
            w.moveArmyOneRoute("green","red");
        }
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de eliminhar ejercitos
     */
    @Test
    public void NoDeberiaMoverEjercitos(){
        for(int i =0; i < 3;i++){
            w.moveArmyOneRoute("green","blue");
        }
        assertTrue(w.ok());
    }
    
    /**
     * Prueba del metodo de eliminhar ejercitos
     */
    @Test
    public void DeberiaDarNacionesConquistadas(){
        w.addNation("green",50,50,4);
        for(int i = 0; i < 4;i++){
            w.putArmy("green");
        }
        assertTrue(w.conqueredNations()[0]=="green");
        w.addNation("yellow",150,50,15);
        for(int i =0; i < 15;i++){
            w.putArmy("yellow");
        }
        assertTrue(w.conqueredNations()[1]=="yellow");
    }
    
    /**
     * Prueba del metodo de eliminhar ejercitos
     */
    @Test
    public void NoDeberiaDarNacionesConquistadas(){
        w.addNation("green",50,50,4);
        for(int i = 0; i < 3;i++){
            w.putArmy("green");
        }
        assertTrue(w.conqueredNations()[0]=="green");
        w.addNation("yellow",150,50,15);
        for(int i =0; i < 14;i++){
            w.putArmy("yellow");
        }
        assertTrue(w.conqueredNations()[1]=="yellow");
    }
    
    /**
     * Prueba metodo payment
     */
    @Test
    public void DeberiaDarPaymentCorrecto(){
        w.addNation("green",50,50,4);
        for(int i = 0; i < 3;i++){
            w.putArmy("green");
        }
        w.addNation("yellow",150,50,15);
        for(int i =0; i < 14;i++){
            w.putArmy("yellow");
        }
        w.addRoute("green","red",5);
        w.addRoute("red","yellow",10);
        w.moveArmyOneRoute("green","red");
        w.moveArmyOneRoute("red","yellow");
        assertTrue(w.payment() == 15);
    }
    
    /**
     * Prueba metodo conquer
     */
    @Test
    public void DeberiaEstarConquistado(){
        w.addNation("green",50,50,4);
        for(int i = 0; i < 4;i++){
            w.putArmy("green");
        }
        w.addNation("yellow",150,50,15);
        for(int i =0; i < 15;i++){
            w.putArmy("yellow");
        }
        for(int i =0; i < 2;i++){
            w.putArmy("red");
        }
        assertTrue(w.conquer() == (true));
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
