/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectores.escritores;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author DANIEL GARCIA
 */
public class LectoresEscritores {

    /**
     * @param args the command line arguments
     */
    /**
     * Se declaran semaforos Binarios
     */
    public static final Semaphore esc_mutex = new Semaphore(1,true);
    public static final Semaphore lec_mutex = new Semaphore(1,true);
    public static int rc = 0;
    public int reloj;

    public static void main(String[] args) {
     long inicio = System.nanoTime();

        int esc,lec;
        Administrar administrar = new Administrar();
        Contador c = new Contador();
        Scanner cantidad = new Scanner(System.in);
        System.out.println("Ingrese cantidad de escritores");
        esc = cantidad.nextInt();
        System.out.println("Ingrese cantidad de lectores");
        lec = cantidad.nextInt();
        
       
           
                ArrayList <Thread> threArrayList = new ArrayList();
       for (int i = 0; i < esc; i++) {
            Thread thread = new Thread(new Escritor(i, administrar, c,administrar.getContemp()), "hilo de escritor r" + Integer.toString(i));
            thread.start();
            threArrayList.add(thread);
       
           // new Thread(new Escritor(i, administrar), "hilo de escritor r" + Integer.toString(i)).start();
            
        }
        for (int i = 0; i < lec; i++) {
            Thread thread = new Thread(new Lector(i, administrar), "hilo de LECTOR r" + Integer.toString(i));
            thread.start();
            threArrayList.add(thread);
            
        }
              Thread nThread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean comprobar = true;
                while (comprobar) {
                    comprobar = false;
                    for (int i = 0; i < threArrayList.size(); i++) {
                        if (threArrayList.get(i).isAlive()) {
                            comprobar = true;
                        }
                        
                    }
                }
                 System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                 System.out.println("TERMINO TIEMPO TOTAL ACUMLADO DE ESCRITURA  "+ administrar.getContemp());
                 System.out.println("PROMEDIO ESCRITURA  "+ administrar.getContemp()/esc);
                 System.out.println("TERMINO TIEMPO TOTAL ACUMLADO DE LECTURA  "+ administrar.getContempLect());
                 System.out.println("PROMEDIO LECTURA"  + administrar.getContemp()/lec);

                finishTiME(inicio);
            }
        });
              
              
      

        nThread.start();
        

      //POR EL MOMENTO NO System.out.println("ESCRIBIRRRRR DURO "+ administrar.getContador().getCountEscritor() +" segundos");
      //POR EL MOMENTO NO System.out.println("ESCRIBIRRRRR PRUEBA DEL CONTADOR "+ administrar.getContemp() +" segundos");

        
    }
    
    public  static void finishTiME(long inicio){
        long fin = System.nanoTime();  
        double tiempo = (double) ((fin - inicio));
         
       System.out.println("El tiempo TOTAL DE EJECUCUION es: "+tiempo +"ms");
    }
}
