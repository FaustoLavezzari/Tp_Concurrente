public abstract class Actor {
    protected int timer1;     //atributo que indica el tiempo de procesamiento de datos que tendra cada hilo
    protected int id;           //atributo que sirve como identificador para cada hilo
    protected float variacion;

    public Actor(int timer1, int id){
        this.timer1 = timer1;
        this.id = id;
        variacion = (float) 0.3;
    }
}
