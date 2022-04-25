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
        for(int i = 0 ; i<consumidores.size() ; i++){
            System.out.printf("Se procesaron %d datos del consumidor %d \n",consumidores.get(i).getDatos_procesados(),i);
            datos_totales += consumidores.get(i).getDatos_procesados();
        }
        System.out.printf("Se procesaron %d datos en total \n",datos_totales);
        System.out.printf("El buffer inicial tiene %d datos almacenados, y el buffer de validados tiene %d datos almacenados \n\n" , buffer_inicial.getCantDeDatos() , getBuffer_validados.getCantDeDatos());
    }

    @Override
    public void run(){
        int tiempo_ejecucion = 0;
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                tiempo_ejecucion += 2;
                System.out.printf("%s segundos de ejecución \n", tiempo_ejecucion);
                imprimirLog();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
