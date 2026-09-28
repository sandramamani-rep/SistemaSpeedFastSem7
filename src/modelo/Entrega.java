package modelo;

/** Relaciona un pedido con el repartidor que realiza su entrega. */
public class Entrega {
    private final int id;
    private final int idPedido;
    private final int idRepartidor;
    private final java.time.LocalDate fecha;
    private final java.time.LocalTime hora;

    public Entrega(int idPedido, int idRepartidor) {
        this(0, idPedido, idRepartidor, java.time.LocalDate.now(), java.time.LocalTime.now().withNano(0));
    }

    public Entrega(int id, int idPedido, int idRepartidor, java.time.LocalDate fecha, java.time.LocalTime hora) {
        this.id = id;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public int getIdRepartidor() { return idRepartidor; }
    public java.time.LocalDate getFecha() { return fecha; }
    public java.time.LocalTime getHora() { return hora; }
}
