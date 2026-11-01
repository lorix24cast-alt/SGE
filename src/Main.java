import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean banderaSalir = false;
        Estudiante[] estudiantes = new Estudiante[8];
        int contador = 0;

        while (banderaSalir == false) {
            System.out.println("===== MENÚ =====");
            System.out.println("1. Agregar");
            System.out.println("2. Mostrar");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    if (contador == estudiantes.length) {
                        System.out.println("Almacenamiento lleno, no caben mas registros");
                    } else {
                        contador = agregarEstudiante(scanner, estudiantes, contador);
                    }
                    break;
                case "2":
                    mostrarEstudiantes(estudiantes, contador);
                    break;
                case "3":
                    buscarEstudiante(scanner, estudiantes, contador);
                    break;
                case "4":
                    banderaSalir = true;
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opción inválida, elige de 1 a 4");
            }
        }
    }

    // Devuelve la posición del ID en el arreglo, o -1 si no existe
    static int buscarPosicionPorId(Estudiante[] estudiantes, int contador, int id) {
        for (int i = 0; i < contador; i++) {
            if (estudiantes[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    // Lee un número entero mayor que 0; repite hasta que sea válido
    static int leerEntero(Scanner scanner, String mensaje) {
        int numero = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();
            try {
                numero = Integer.parseInt(texto);
                if (numero > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("El número debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Escribe solo números enteros, sin espacios o simbolos.");
            }
        }
        return numero;
    }

    // Pide un texto y repite hasta que no esté vacío
    static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        String texto = "";
        while (texto.isEmpty()) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Este campo no puede estar vacío.");
            }
        }
        return texto;
    }

    // Muestra un mini menú y devuelve el tipo de documento elegido
    static String leerTipoDocumento(Scanner scanner) {
        String tipo = "";
        while (tipo.isEmpty()) {
            System.out.println("Tipo de documento: 1. CC  2. TI  3. CE");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    tipo = "CC";
                    break;
                case "2":
                    tipo = "TI";
                    break;
                case "3":
                    tipo = "CE";
                    break;
                default:
                    System.out.println("Opción inválida, elige 1, 2 o 3.");
            }
        }
        return tipo;
    }

    // Agrega un estudiante y devuelve el contador actualizado
    // (la validación de "almacenamiento lleno" se hace antes, en el case "1")
    static int agregarEstudiante(Scanner scanner, Estudiante[] estudiantes, int contador) {
        int id = leerEntero(scanner, "ID del estudiante: ");
        if (buscarPosicionPorId(estudiantes, contador, id) != -1) {
            System.out.println("Este ID ya existe");
            return contador;
        }
        String nombre = leerTextoNoVacio(scanner, "Nombre: ");
        String tipoDocumento = leerTipoDocumento(scanner);
        estudiantes[contador] = new Estudiante(id, nombre, tipoDocumento);
        contador = contador + 1;
        System.out.println("Estudiante guardado: " + nombre);
        return contador;
    }
    // Imprime los datos de UN estudiante (se reutiliza en Mostrar y en Buscar)
    static void mostrarEstudiante(Estudiante estudiante) {
        System.out.println("ID: " + estudiante.getId()
                + " | Nombre: " + estudiante.getNombre()
                + " | Documento: " + estudiante.getTipoDocumento());
    }

    // Muestra todos los estudiantes registrados
    static void mostrarEstudiantes(Estudiante[] estudiantes, int contador) {
        if (contador == 0) {
            System.out.println("No hay estudiantes registrados");
        } else {
            System.out.println("\n--- LISTA DE ESTUDIANTES ---");
            for (int i = 0; i < contador; i++) {
                mostrarEstudiante(estudiantes[i]);
            }
        }
    }
    // Pide un ID y muestra los datos del estudiante si existe
    static void buscarEstudiante(Scanner scanner, Estudiante[] estudiantes, int contador) {
        if (contador == 0) {
            System.out.println("No hay estudiantes registrados");
        } else {
            int id = leerEntero(scanner, "ID a buscar: ");
            int posicion = buscarPosicionPorId(estudiantes, contador, id);
            if (posicion == -1) {
                System.out.println("No existe un estudiante con ese ID");
            } else {
                System.out.println("\n--- ESTUDIANTE ENCONTRADO ---");
                mostrarEstudiante(estudiantes[posicion]);
            }
        }
    }
}