public class ConsultasUsuario {
    private String monedaBase;
    private String monedaDestino;
    private double cantidad;
    private double tasa_Conversion;
    private double resultado;

    public ConsultasUsuario(String monedaBase, String monedaDestino, double cantidad, double tasa_Conversion, double resultado) {
        this.monedaBase = monedaBase;
        this.monedaDestino = monedaDestino;
        this.cantidad = cantidad;
        this.tasa_Conversion = tasa_Conversion;
        this.resultado = resultado;
    }


}
