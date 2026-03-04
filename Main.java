import java.util.Scanner;
import java.io.*;
import java.util.*;

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
        try {

        // Mapa para guardar precio y nombre del producto
        HashMap<Integer, String> nombres = new HashMap<>();
        HashMap<Integer, Double> precios = new HashMap<>();

        // ===============================
        // LEER productos.csv
        // ===============================
        BufferedReader brProductos = new BufferedReader(new FileReader(PRODUCTOS_FILE));
        String linea = brProductos.readLine(); // Saltar encabezado

        while ((linea = brProductos.readLine()) != null) {
            String[] partes = linea.split(",");

            int id = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            double precio = Double.parseDouble(partes[3]);

            nombres.put(id, nombre);
            precios.put(id, precio);
        }

        brProductos.close();

        // ===============================
        // LEER pedidos.csv
        // ===============================
        HashMap<Integer, Double> totalVentas = new HashMap<>();

        BufferedReader brPedidos = new BufferedReader(new FileReader(PEDIDOS_FILE));
        brPedidos.readLine(); // Saltar encabezado

        while ((linea = brPedidos.readLine()) != null) {
            String[] partes = linea.split(",");

            int productoId = Integer.parseInt(partes[2]);
            int cantidad = Integer.parseInt(partes[3]);

            double precio = precios.get(productoId);
            double total = precio * cantidad;

            totalVentas.put(productoId,
                    totalVentas.getOrDefault(productoId, 0.0) + total);
        }

        brPedidos.close();

        // ===============================
        // PASAR A LISTA PARA ORDENAR
        // ===============================
        ArrayList<Integer> ids = new ArrayList<>(totalVentas.keySet());

        // ===============================
        // ORDENAMIENTO BURBUJA (mayor a menor)
        // ===============================
        for (int i = 0; i < ids.size() - 1; i++) {
            for (int j = 0; j < ids.size() - i - 1; j++) {

                double total1 = totalVentas.get(ids.get(j));
                double total2 = totalVentas.get(ids.get(j + 1));

                if (total1 < total2) {
                    int temp = ids.get(j);
                    ids.set(j, ids.get(j + 1));
                    ids.set(j + 1, temp);
                }
            }
        }

        // ===============================
        // ESCRIBIR total_ventas.csv
        // ===============================
        BufferedWriter bw = new BufferedWriter(new FileWriter(TOTAL_VENTAS_FILE));

        bw.write("producto_id,nombre_producto,total\n");

        System.out.println("\n===== TOTAL DE VENTAS POR PRODUCTO =====");

        for (int id : ids) {

            String nombre = nombres.get(id);
            double total = totalVentas.get(id);

            bw.write(id + "," + nombre + "," + total + "\n");

            System.out.println(nombre + " -> $" + total);
        }

        bw.close();

        System.out.println("\nArchivo total_ventas.csv generado correctamente.");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
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