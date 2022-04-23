import java.util.HashMap;

public class Contenedor {
    private int ID;
    private int capacidad;
    HashMap<Dato, Integer> datos;

    public Contenedor(int ID){
        this.capacidad = 100;
        this.ID = ID;
        this.datos = new HashMap<Integer, Dato>();
    }

    public int getID(){
        if(this.ID!=0){
            return this.ID;
        }else{
            System.out.println("ID no asignado");
            return -1;
        }
    }

    public Dato getDato(int ID){
        if (datos.isEmpty()){
            System.out.println("No existen datos");
            return null;
        }else{
            return datos.get(ID);
        }
    }

    //public void putDato();
}
