import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivo {

    private static final String ARCHIVO_SALIDA = "historial_conversiones.txt";

    public static void guardarConversion(ConsultasUsuario consulta) {
        try (FileWriter writer = new FileWriter(ARCHIVO_SALIDA, true)) { // true para modo append
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(consulta);
            writer.write(json + System.lineSeparator()); // Una conversión por línea
        } catch (IOException e) {
            System.out.println("Error al guardar la conversión: " + e.getMessage());
        }
    }
}
