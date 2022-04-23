
public class Main {

    public static void main(String[] args) {
        Thread[] creadores = new Thread[4];
        Contenedor inicial = new Contenedor(0);

        for(int i=0;i<4;i++){
            Creador c = new Creador(i, inicial);
            Thread t = new Thread(c);
            t.start();
            creadores[i] = t;
        }



    }
}