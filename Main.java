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
        System.out.println("Funcionalidad en construcción (Rama compañero 4)");
        // Leer clientes.csv
        // Leer pedidos.csv
        // Ver cuáles clientes aparecen en pedidos
        // Ordenar alfabéticamente
        // Mostrar resultado
    }
}