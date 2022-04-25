import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import java.io.IOException;

public class Log implements Runnable {
    private ArrayList<Consumidor> consumidores;
    private Contenedor buffer_inicial;
    private Contenedor getBuffer_validados;

    public Log(ArrayList<Consumidor> consumidores, Contenedor buffer_inicial, Contenedor buffer_validados){
        this.consumidores = consumidores;
        this.buffer_inicial = buffer_inicial;
        this.getBuffer_validados = buffer_validados;
    }

    private void imprimirLog (){
        int datos_totales=0;
        int tiempo_ejecucion = 0;
        try(FileWriter file=new FileWriter(".\\data\\log.txt");
            PrintWriter pw=new PrintWriter(file);){
            pw.printf("%s segundos de ejecución \n", tiempo_ejecucion);
            for(int i = 0 ; i<consumidores.size() ; i++){   // consumidores.size == 2 siempre
                pw.printf("Se procesaron %d datos del consumidor %d \n",consumidores.get(i).getDatos_procesados(),i);
                datos_totales += consumidores.get(i).getDatos_procesados();
            }
            pw.printf("Se procesaron %d datos en total \n",datos_totales);
            tiempo_ejecucion += 2;
            pw.printf("%s segundos de ejecución \n", tiempo_ejecucion);pw.printf("El buffer inicial tiene %d datos almacenados, y el buffer de validados tiene %d datos almacenados \n\n" , buffer_inicial.getCantDeDatos() , getBuffer_validados.getCantDeDatos());
        }catch(IOException e){e.printStackTrace();}
    }

    @Override
    public void run(){
        try(FileWriter file=new FileWriter(".\\data\\log.txt"); PrintWriter pw=new PrintWriter(file);){
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                    imprimirLog();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch(IOException e){e.printStackTrace();}
    }

}
