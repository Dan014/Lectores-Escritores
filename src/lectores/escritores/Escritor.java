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
public class Escritor implements Runnable{
    
    private int num;
    private Administrar administrar;
    public boolean state = true;
    private int contador ;
    private Contador contadorEs;
    private double  time;

    public Escritor(int num, Administrar administrar, Contador cont, double time) {
        this.num = num;
        this.administrar = administrar;
        this.contadorEs = cont;
        this.state = state;
        this.contador = (int) System.currentTimeMillis();
        this.time =  this.administrar.getContemp();
    }

    @Override
    public void run() {

       // int count = 0;
         //   count = count+1;
        administrar.dormir();
        administrar.escribir();
        System.out.println(Thread.currentThread().getName()+"Escribiendo.....");
        System.out.println(Thread.currentThread().getName()+"Finalizo de escribir.....");
        this.contador = 145;
       // contador += System.currentTimeMillis();
        this.contadorEs.setCountEscritor(10);
      // sirve pero se puede mejorar  System.out.println( "Tiempo: " + System.currentTimeMillis() + " ms. "  );

    }

    public int getContador() {
        return contador;
    }

    public double getTime() {
        return time;
    }
    
  
    
}
