package world;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 

/**
 * The test class AcceptanceTestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AcceptanceTest
{
    private World world;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp(){
        world = new World(600, 600);
    }

    /**
     *  
     */
    @Test
    public void pruebaAceptación(){
        world.makeVisible();
        world.addNation("green", 10, 275, 2);
        world.addNation("blue", 160, 100, 1);
        world.addNation("red", 260, 450, 2);
        world.addNation("pink", 260, 275, 2);
        world.addNation("yellow", 360, 100, 2);
        world.addNation("orange", 430, 362, 2);
        world.addNation("cyan", 480, 188, 2);
        world.addRoute("green", "blue", 6);
        world.addRoute("green", "red", 4);
        world.addRoute("blue", "yellow", 3);
        world.addRoute("red", "pink", 11);
        world.addRoute("yellow", "pink", 4);
        world.addRoute("pink", "cyan", 3);
        world.addRoute("pink", "orange", 4);
        world.addRoute("orange", "cyan", 1);
        world.putArmy("green");
        try
        {Thread.sleep(1000);}
        catch(InterruptedException ex)
        {Thread.currentThread().interrupt();}
        
        world.moveArmy("green", "cyan");
        assertTrue(world.payment() == 16);
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