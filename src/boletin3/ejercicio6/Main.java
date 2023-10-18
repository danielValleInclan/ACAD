package boletin3.ejercicio6;

import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        String nomFichero = "peidos.dat";
        Pedido pedido1 = new Pedido("Este es el pedido 1", 1, 5.99);
        Pedido pedido2 = new Pedido("Este es el pedido 2", 6, 4.99);
        Pedido pedido3 = new Pedido("Este es el pedido 3", 9, 0.50);
        Pedido[] pedidos = {pedido1, pedido2, pedido3};
        escribirPedidos(pedidos, nomFichero);
        leerPedidos(nomFichero);
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
