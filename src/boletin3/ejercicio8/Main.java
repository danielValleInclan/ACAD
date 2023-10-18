package boletin3.ejercicio8;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String nomFich = "agenda.dat";
        Agenda agenda = new Agenda();
        Contacto contacto1 = new Contacto("Daniel", "Rodríguez Vilches", "675220724");
        Contacto contacto2 = new Contacto("Joselu", "Gayardo García", "123456789");
        Contacto contacto3 = new Contacto("Leo", "Rojo Armando", "987654321");
        agenda.list.add(contacto1);
        agenda.list.add(contacto2);
        agenda.list.add(contacto3);
        escribirFich(nomFich, agenda.list);
        leerFich(nomFich);
    }

    public static void leerFich(String nomFich) {

        Contacto c;
        try (ObjectInputStream dataOIS = new ObjectInputStream(new FileInputStream(nomFich))) {
            while (true){
                c = (Contacto) dataOIS.readObject();
                System.out.println("Nombre: " + c.getNombre() + " Apellidos: " + c.getApellidos() + " Num Teléfono: " +
                        c.getTelefono());
            }
        } catch (EOFException e) {
            System.out.println("Final del fichero");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirFich(String nomFich, List<Contacto> list){
        try (ObjectOutputStream dataOOS = new ObjectOutputStream(new FileOutputStream(nomFich))){
            for (Contacto c: list) {
                dataOOS.writeObject(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
