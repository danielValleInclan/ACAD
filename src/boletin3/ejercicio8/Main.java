package boletin3.ejercicio8;

import java.io.*;
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
        escribirFich(nomFich, agenda);
        leerFich(nomFich);
    }

    public static void leerFich(String nomFich) {

        Agenda agenda = null;
        try (ObjectInputStream dataOIS = new ObjectInputStream(new FileInputStream(nomFich))) {
            while (true){
                agenda = (Agenda) dataOIS.readObject();
            }
        } catch (EOFException e) {
            System.out.println("Final del fichero");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Contacto c : agenda.list) {
            System.out.println("Nombre: " + c.getNombre() + "Apellidos: " + c.getApellidos() + "Telefono: " + c.getTelefono());
        }
    }

    public static void escribirFich(String nomFich, Agenda agenda){
        try (ObjectOutputStream dataOOS = new ObjectOutputStream(new FileOutputStream(nomFich))){
            dataOOS.writeObject(agenda);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
