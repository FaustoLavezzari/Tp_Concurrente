import java.io.FileWriter;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class Log implements Runnable {
    private ArrayList<Consumidor> consumidores;
    private Contenedor buffer_inicial;
    private Contenedor getBuffer_validados;
    private int tiempo_ejecucion;

    public Log(ArrayList<Consumidor> consumidores, Contenedor buffer_inicial, Contenedor buffer_validados){
        this.consumidores = consumidores;
        this.buffer_inicial = buffer_inicial;
        this.getBuffer_validados = buffer_validados;
        tiempo_ejecucion = 0;
    }

    private void imprimirLog (PrintWriter pw){
        int datos_totales=0;
        pw.printf("%s segundos de ejecución \n", tiempo_ejecucion);
        for(int i = 0 ; i<consumidores.size() ; i++){
            pw.printf("Se procesaron %d datos del consumidor %d \n",consumidores.get(i).getDatos_procesados(),i);
            datos_totales += consumidores.get(i).getDatos_procesados();
        }
        pw.printf("Se procesaron %d datos en total \n",datos_totales);
        pw.printf("El buffer inicial tiene %d datos almacenados, y el buffer de validados tiene %d datos almacenados \n\n" , buffer_inicial.getCantDeDatos() , getBuffer_validados.getCantDeDatos());
        tiempo_ejecucion += 2;
    }

    @Override
    public void run(){
        try(FileWriter file = new FileWriter("./data/log.txt");PrintWriter pw = new PrintWriter(file);){
            while (true) {
                try {
                   Thread.sleep(2000);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
                imprimirLog(pw);
                pw.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
