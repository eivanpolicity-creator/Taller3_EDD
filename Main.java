import java.io.*;
import java.util.*;

public class Main {
    // HASH, En python, pygame
    static final String CLIENTES_FILE = "clientes.csv";
    static final String PEDIDOS_FILE = "pedidos.csv";

    static Scanner sc = new Scanner(System.in);


    static class Cliente {
        int id;
        String nombre;
        String apellido;
        String telefono;
        int activo;

        Cliente(int id, String nombre, String apellido, String telefono, int activo) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
            this.activo = activo;
        }
    }


    static class Pedido {
        int id;
        int idCliente;
        String producto;
        double precio;
        int cantidad;
        int activo;

        Pedido(int id, int idCliente, String producto, double precio, int cantidad, int activo) {
            this.id = id;
            this.idCliente = idCliente;
            this.producto = producto;
            this.precio = precio;
            this.cantidad = cantidad;
            this.activo = activo;
        }
    }



    static ArrayList<Cliente> cargarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        File file = new File(CLIENTES_FILE);
        if (!file.exists()) return clientes;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                clientes.add(new Cliente(
                        Integer.parseInt(p[0]),
                        p[1], p[2], p[3],
                        Integer.parseInt(p[4])
                ));
            }
        } catch (Exception e) {
          System.err.println("Error al procesar los datos: " + e.getMessage());
}

        return clientes;
    }

    static void guardarClientes(ArrayList<Cliente> clientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CLIENTES_FILE))) {
            pw.println("id_cliente,nombre,apellido,telefono,activo");

            for (Cliente c : clientes) {
                pw.println(c.id + "," + c.nombre + "," + c.apellido + "," + c.telefono + "," + c.activo);
            }
        } catch (Exception e) {
    System.err.println("Error al procesar los datos: " + e.getMessage());
    }
    }
    
    static void registrarCliente(ArrayList<Cliente> clientes) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        int nuevoId = obtenerNuevoIdCliente(clientes);

        clientes.add(new Cliente(nuevoId, nombre, apellido, telefono, 1));
        guardarClientes(clientes);

        System.out.println("Cliente registrado con ID: " + nuevoId);
    }

    static void listarClientes(ArrayList<Cliente> clientes) {
        System.out.println("\nClientes activos:");

        for (Cliente c : clientes) {
            if (c.activo == 1) {
                System.out.println(c.id + " - " + c.nombre + " " + c.apellido + " - " + c.telefono);
            }
        }
    }

    static void eliminarCliente(ArrayList<Cliente> clientes) {
        System.out.print("ID del cliente a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Cliente c : clientes) {
            if (c.id == id && c.activo == 1) {
                c.activo = 0;
                guardarClientes(clientes);
                System.out.println("Cliente eliminado lógicamente");
                return;
            }
        }

        System.out.println("Cliente no encontrado o inactivo");
    }

    

    static ArrayList<Pedido> cargarPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        File file = new File(PEDIDOS_FILE);
        if (!file.exists()) return pedidos;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");

                pedidos.add(new Pedido(
                        Integer.parseInt(p[0]),
                        Integer.parseInt(p[1]),
                        p[2],
                        Double.parseDouble(p[3]),
                        Integer.parseInt(p[4]),
                        Integer.parseInt(p[5])
                ));
            }
       } catch (Exception e) {
    System.err.println("Error al procesar los datos: " + e.getMessage());
}

        return pedidos;
    }

    static void guardarPedidos(ArrayList<Pedido> pedidos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PEDIDOS_FILE))) {
            pw.println("id_pedido,id_cliente,producto,precio,cantidad,activo");

            for (Pedido p : pedidos) {
                pw.println(p.id + "," + p.idCliente + "," + p.producto + "," +
                        p.precio + "," + p.cantidad + "," + p.activo);
            }
       } catch (Exception e) {
    System.err.println("Error al procesar los datos: " + e.getMessage());
}
    }

    static void registrarPedido(ArrayList<Cliente> clientes, ArrayList<Pedido> pedidos) {
        System.out.print("ID del cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine());

        Cliente cliente = clientes.stream()
                .filter(c -> c.id == idCliente && c.activo == 1)
                .findFirst().orElse(null);

        if (cliente == null) {
            System.out.println("Cliente no encontrado o inactivo");
            return;
        }

        System.out.print("Producto: ");
        String producto = sc.nextLine();

        System.out.print("Precio (opcional): ");
        String precioStr = sc.nextLine();
        double precio = precioStr.isEmpty() ? 0 : Double.parseDouble(precioStr);

        System.out.print("Cantidad (opcional): ");
        String cantStr = sc.nextLine();
        int cantidad = cantStr.isEmpty() ? 0 : Integer.parseInt(cantStr);

        int nuevoId = obtenerNuevoIdPedido(pedidos);

        pedidos.add(new Pedido(nuevoId, idCliente, producto, precio, cantidad, 1));
        guardarPedidos(pedidos);

        System.out.println("Pedido registrado con ID: " + nuevoId);
    }

    

    static void listarPedidosCliente(ArrayList<Pedido> pedidos, ArrayList<Cliente> clientes) {

        System.out.print("ID del cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine());

        Cliente cliente = clientes.stream()
                .filter(c -> c.id == idCliente)
                .findFirst().orElse(null);

        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        System.out.println("\nPedidos de " + cliente.nombre + " " + cliente.apellido);

        boolean encontrado = false;

        for (Pedido p : pedidos) {
            if (p.idCliente == idCliente && p.activo == 1) {
                encontrado = true;
                System.out.println(p.id + " - " + p.producto +
                        " - " + p.precio + " - " + p.cantidad);
            }
        }

        if (!encontrado) {
            System.out.println("No tiene pedidos activos");
        }
    }

    

    static int obtenerNuevoIdCliente(ArrayList<Cliente> clientes) {
        return clientes.stream().mapToInt(c -> c.id).max().orElse(0) + 1;
    }

    static int obtenerNuevoIdPedido(ArrayList<Pedido> pedidos) {
        return pedidos.stream().mapToInt(p -> p.id).max().orElse(0) + 1;
    }


    

    public static void main(String[] args) {

        ArrayList<Cliente> clientes = cargarClientes();
        ArrayList<Pedido> pedidos = cargarPedidos();

        while (true) {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Registrar pedido");
            System.out.println("5. Listar pedidos de cliente");
            System.out.println("6. Salir");

            System.out.print("Opción: ");
            String op = sc.nextLine();

            switch (op) {
    case "1" -> registrarCliente(clientes);
    case "2" -> listarClientes(clientes);
    case "3" -> eliminarCliente(clientes);
    case "4" -> registrarPedido(clientes, pedidos);
    case "5" -> listarPedidosCliente(pedidos, clientes);
    case "6" -> {
        System.out.println("Chao...");
        return;
    }
    default -> System.out.println("Opción inválida");
}
        }
    }
}