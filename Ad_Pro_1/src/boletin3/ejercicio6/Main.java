package boletin3.ejercicio6;

import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        String nomFichero = "pedidos.dat";
        Pedido pedido1 = new Pedido("Este es el pedido 1", 1, 5.99);
        Pedido pedido2 = new Pedido("Este es el pedido 2", 6, 4.99);
        Pedido pedido3 = new Pedido("Este es el pedido 3", 9, 0.50);
        Pedido[] pedidos = {pedido1, pedido2, pedido3};
        escribirPedidos(pedidos, nomFichero);
        leerPedidos(nomFichero);
        Pedido pedido4 = new Pedido("Este es el pedido 4", 4, 20.00);
        addPedido(pedido4, nomFichero);
        System.out.println("Fichero después de añadir un pedido");
        leerPedidos(nomFichero);

    }

    public static void addPedido(Pedido pedido, String nomFich) throws IOException {
        File file = new File(nomFich);
        ObjectOutputStream dataOOS = new MiObjectOutputStream(new FileOutputStream(file, true));
        dataOOS.writeObject(pedido);
    }

    public static void leerPedidos(String nomFichero){
        Pedido p;
        File file = new File(nomFichero);
        try (ObjectInputStream dataOIS = new ObjectInputStream(new FileInputStream(file))){
          while (true){
              p = (Pedido) dataOIS.readObject();
              System.out.println("Desc: " + p.getDesc() + " Número de unidades: " + p.getNumUnits() + " Precio: " +
                      p.getPrice());
          }
        } catch (EOFException e){
            System.out.println("Fin del fichero");

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void escribirPedidos(Pedido[] pedidos, String nomFichero)
    {
        File file = new File(nomFichero);
        try (ObjectOutputStream dataOOS = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Pedido p: pedidos) {
                dataOOS.writeObject(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
