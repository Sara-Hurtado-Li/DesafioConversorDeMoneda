import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConsumoAPI {
    public static void buscaDivisa(int numeroDeSeleccion, double cantidad) {
        String base = "";
        String destino = "";

        // Diccionario de nombres completos
        Map<String, String> nombresMonedas = new HashMap<>();
        nombresMonedas.put("USD", "Dólar americano");
        nombresMonedas.put("ARS", "Peso argentino");
        nombresMonedas.put("BRL", "Real brasileño");
        nombresMonedas.put("COP", "Peso colombiano");

        switch (numeroDeSeleccion) {
            case 1 -> { base = "USD"; destino = "ARS"; }
            case 2 -> { base = "ARS"; destino = "USD"; }
            case 3 -> { base = "USD"; destino = "BRL"; }
            case 4 -> { base = "BRL"; destino = "USD"; }
            case 5 -> { base = "COP"; destino = "USD"; }
            case 6 -> { base = "USD"; destino = "COP"; }
            default -> {
                System.out.println("Selección inválida");
                return;
            }
        }

        String apiKey = "48b599dfb69aa276bbb9eb25";
        String direccion = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + base + "/" + destino;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());


            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.body(), JsonObject.class);

            if (!json.get("result").getAsString().equals("success")) {
                System.out.println("No se pudo obtener la tasa de conversión.");
                return;
            }

            double rate = json.get("conversion_rate").getAsDouble();
            double resultado = cantidad * rate;

            String nombreBase = nombresMonedas.getOrDefault(base, base);
            String nombreDestino = nombresMonedas.getOrDefault(destino, destino);

            System.out.printf("Tasa actual de %s a %s es: %.4f\n y ", nombreBase, nombreDestino, rate);
            System.out.printf("%.2f %s equivalen a %.2f %s\n ", cantidad, nombreBase, resultado, nombreDestino);

            // Guardando consultas del usuario
            ConsultasUsuario consulta = new ConsultasUsuario(
                    nombreBase,
                    nombreDestino,
                    cantidad,
                    rate,
                    resultado
            );
            GeneradorDeArchivo.guardarConversion(consulta);

        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
        }
    }
}