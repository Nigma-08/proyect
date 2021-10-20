package world;

import java.util.*;
import shapes.*;

/**
 * Write a description of class GraphState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GraphState
{
    private int length;
    private int width;
    private Rectangle fondo;
    private HashMap<String,Nation> nations;
    private boolean ok;
    private int payment;
    private boolean isVisible;
    
    public GraphState(){
        length = 0;
        width = 0;
        fondo = null;
        nations = null;
        ok = false;
        payment = 0;
        isVisible = false;
    }
    //SETTS COMPUESTOS
    /**
     * Este metodo añade el ancho y el alto del mundo 
     * @param length alto
     * @param width ancho
     */
    public void addLengthAndWidth(int length, int width){
        this.length = length;
        this.width = width;
    }
    
    /**
     * Asigna un fondo tipo rectangulo 
     * @param f fondo de la nacion
     */
    public void addFondo(Rectangle f){
        fondo = f;
    }
    
    /**
     * Añade las naciones 
     * @param nations naciones del mundo
     */
    public void addNations(HashMap<String,Nation> nations){
        this.nations = nations;
    }
    
    /**
     * Añade si esta ok , los costos de movimiento y si esta visible el mundo
     * @param ok Ok(Ramus)
     * @param paymen costos de movimientos
     * @para, isVisible ok visible el mundo 
     */
    public void okAndPaymentAndisVisible(boolean ok, int payment,boolean isVisible){
         this.ok = ok;
         this.payment = payment;
         this.isVisible = isVisible;
    }
    
    //GET'S
    public int getLength(){
        return length;
    }
    
    public int getwidth(){
        return width;
    }
    
    public Rectangle getFondo(){
        return fondo;
    }
    
    public HashMap<String,Nation> getNations(){
        return nations;
    }
    
    public boolean getOk(){
        return ok;
    }
    
    public int getPayment(){
        return payment;
    }
    
    public boolean getIsVisible(){
        return isVisible;
    }
}
