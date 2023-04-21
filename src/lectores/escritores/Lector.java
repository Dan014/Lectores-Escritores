/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectores.escritores;

/**
 *
 * @author DANIEL GARCIA
 */
public class Lector implements Runnable{

    private int num;
    private Administrar administrar;
    
    public Lector(int num, Administrar administrar) {
        this.num = num;
        this.administrar = administrar;
    }

    @Override
    public void run() {
        administrar.dormir();
        administrar.iniciarLectura();
        System.out.println(Thread.currentThread().getName()+"Leyendo.....");
        System.out.println(Thread.currentThread().getName()+"Finalizo de leer.....");

        //administrar.dormir();
        administrar.terminarLector();

    }
    
    
    
}
