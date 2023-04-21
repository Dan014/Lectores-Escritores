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
public class Administrar {
    
  //  Contador contador = new Contador();
    double contemp;
    double contempLect;


    public Administrar() {
    }
    
    

    void dormir() {
        try {
            Thread.sleep((int)(3000* Math.random()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void escribir() {
        long inicio = System.nanoTime();
        try {
            LectoresEscritores.esc_mutex.acquire();
          //  System.out.println("iniciando escritura1");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            LectoresEscritores.esc_mutex.release();
        }
        long fin = System.nanoTime(); 
        double tiempo = (double) ((fin - inicio));
   //     contador.setCountEscritor( tiempo);
        contemp += tiempo;
        System.out.println("TIEMPO DE ESCRITURA PROCESO "+tiempo +" ms");
        System.out.println("TIEMPO TOTAL ACUMULADO DE ESCRITURA "+contemp +" ms");

    }

    void iniciarLectura() {

        try {
            LectoresEscritores.lec_mutex.acquire(); 
            if (LectoresEscritores.rc == 0) {
                LectoresEscritores.esc_mutex.acquire();
            }
            LectoresEscritores.rc++;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            LectoresEscritores.lec_mutex.release();
                                 //       System.out.println("iniciando lectura");

        }
        
    
   //     contador.setCountEscritor( tiempo);
      
    }

    void terminarLector() {
        long inicio = System.nanoTime();
        try {
            LectoresEscritores.lec_mutex.acquire();
            LectoresEscritores.rc--;
            if (LectoresEscritores.rc == 0) {
                LectoresEscritores.esc_mutex.release();
               System.out.println("terminando Bloque de lectura ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            LectoresEscritores.lec_mutex.release();

        }
        long fin = System.nanoTime(); 
        double tiempo = (double) ((fin - inicio));
          contempLect += tiempo;
        System.out.println("TIEMPO DE LECTURA PROCESO "+tiempo +" ms");
        System.out.println("TIEMPO TOTAL ACUMULADO DE LECTURA  "+contempLect +" ms");
    }

   

    public double getContemp() {
        return contemp;
    }

    public double getContempLect() {
        return contempLect;
    }
    
    
    
    
}
