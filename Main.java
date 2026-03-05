import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Scanner global
    static Scanner sc = new Scanner(System.in);

    // Rutas de archivos (todos usarán las mismas)
    static final String PRODUCTOS_FILE = "productos.csv";
    static final String CLIENTES_FILE = "clientes.csv";
    static final String PEDIDOS_FILE = "pedidos.csv";
    static final String TOTAL_VENTAS_FILE = "total_ventas.csv";

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    ordenarProductosPorPrecio();
                    break;

                case 2:
                    agregarNuevoCliente();
                    break;

                case 3:
                    calcularTotalVentas();
                    break;

                case 4:
                    mostrarClientesConCompras();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        sc.close();
    }

    // =========================
    // MENÚ
    // =========================
    public static void mostrarMenu() {
        System.out.println("\n===== SISTEMA E-COMMERCE =====");
        System.out.println("1. Ver productos ordenados por precio");
        System.out.println("2. Agregar un nuevo cliente");
        System.out.println("3. Calcular total de ventas por producto");
        System.out.println("4. Ver clientes que han realizado compras");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // =========================
    // OPCIÓN 1
    // =========================
    public static void ordenarProductosPorPrecio() {
        System.out.println("Funcionalidad en construcción (Rama compañero 1)");
        // Aquí irá la lógica de lectura del CSV
        // Ordenamiento manual (burbuja, inserción, selección, etc.)
        // Mostrar productos ordenados
    }

    // =========================
    // OPCIÓN 2
    // =========================
    public static void agregarNuevoCliente() {
        System.out.println("Funcionalidad en construcción (Rama compañero 2)");
        // Pedir nombre
        // Pedir email
        // Leer último id
        // Insertar nuevo cliente en clientes.csv
    }

    // =========================
    // OPCIÓN 3
    // =========================
    public static void calcularTotalVentas() {
        System.out.println("Funcionalidad en construcción (Rama compañero 3)");
        // Leer productos.csv
        // Leer pedidos.csv
        // Calcular total por producto
        // Ordenar de mayor a menor
        // Escribir total_ventas.csv
    }

    // =========================
    // OPCIÓN 4
    // =========================
   public static void mostrarClientesConCompras() {

    try {

        BufferedReader brClientes = new BufferedReader(new FileReader(CLIENTES_FILE));
        BufferedReader brPedidos = new BufferedReader(new FileReader(PEDIDOS_FILE));

        ArrayList<String[]> clientes = new ArrayList<>();
        ArrayList<Integer> clientesConCompra = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();

        String linea;

        
        brClientes.readLine();
        brPedidos.readLine();

        
        while ((linea = brClientes.readLine()) != null) {
            String[] datos = linea.split(",");
            clientes.add(datos);
        }

        
        while ((linea = brPedidos.readLine()) != null) {
            String[] datos = linea.split(",");
            int idCliente = Integer.parseInt(datos[1]);

            boolean repetido = false;

            for (int i = 0; i < clientesConCompra.size(); i++) {
                if (clientesConCompra.get(i) == idCliente) {
                    repetido = true;
                }
            }

            if (!repetido) {
                clientesConCompra.add(idCliente);
            }
        }

        
        for (int i = 0; i < clientes.size(); i++) {

            int id = Integer.parseInt(clientes.get(i)[0]);

            for (int j = 0; j < clientesConCompra.size(); j++) {
                if (id == clientesConCompra.get(j)) {
                    nombres.add(clientes.get(i)[1]);
                }
            }
        }

        
        for (int i = 0; i < nombres.size() - 1; i++) {
            for (int j = 0; j < nombres.size() - i - 1; j++) {

                if (nombres.get(j).compareTo(nombres.get(j + 1)) > 0) {

                    String aux = nombres.get(j);
                    nombres.set(j, nombres.get(j + 1));
                    nombres.set(j + 1, aux);
                }
            }
        }

        System.out.println("\nClientes que han realizado compras:");

        for (int i = 0; i < nombres.size(); i++) {
            System.out.println(nombres.get(i));
        }

        brClientes.close();
        brPedidos.close();

    } catch (IOException e) {
        System.out.println("Error al leer los archivos.");
    }
}
    }
