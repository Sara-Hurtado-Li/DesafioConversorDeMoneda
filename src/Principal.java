import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        do {
            if (continuar) {
                System.out.println("""
                    *************************************************
                    ¡Bienvenido/a al Conversor de Moneda!
                    
                    Elija entre las siguientes opciones y escriba el número de la selección.

                    1) Dólar a Peso Argentino
                    2) Peso Argentino a Dólar
                    3) Dólar a Real Brasileño
                    4) Real Brasileño a Dólar
                    5) Peso Colombiano a Dólar
                    6) Dólar a Peso Colombiano
                    7) SALIR
                    *************************************************
                    """);
            }
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Ingrese la cantidad que desea convertir: ");
                double cantidad = scanner.nextDouble();

                ConsumoAPI.buscaDivisa(opcion, cantidad);

                scanner.nextLine(); // limpiar buffer
                System.out.print("¿Desea seguir realizando conversiones? (YES / NO): ");
                String respuesta = scanner.nextLine().trim().toUpperCase();

                if (respuesta.equals("NO")) {
                    System.out.println("Para salir, escriba el número 7.");
                    continuar = false; // Se detiene el menú hasta que escriba 7
                } else {
                    continuar = true; // Sigue mostrando el menú
                }

            } else if (opcion != 7) {
                System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 7);

        System.out.println("¡Hasta luego, vuelva pronto!");
        scanner.close();
    }
}
