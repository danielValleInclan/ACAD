package pack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class AgendaContactos {

    private final String fichAgenda = "agenda.txt";

    private List<Contacto> contactoList;

    public AgendaContactos() {
        File agenda = new File(fichAgenda);
        if (agenda.exists()){
            cargarContactosDesdeArchivo();
        } else {
            contactoList = new ArrayList<>();
        }
    }

    public void cargarContactosDesdeArchivo() {
        File agenda = new File(fichAgenda);
        try (BufferedReader br = new BufferedReader(new FileReader(agenda))) {
            String s;
            while ((s = br.readLine()) != null){
                Contacto contacto = new Contacto();
                contacto.setNombre(s);
                s = br.readLine();
                contacto.setTelefono(s);
                s = br.readLine();
                contacto.setCorreo(s);
                contactoList.add(contacto);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarContactosEnArchivo() {
        File agenda = new File(fichAgenda);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(agenda))){
            for (Contacto c: contactoList) {
                bw.write(c.getNombre());
                bw.newLine();
                bw.write(c.getTelefono());
                bw.newLine();
                bw.write(c.getCorreo());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarContactosEnArchivoBin() {
        File agenda = new File("agenda.dat");
        try {
            ObjectOutputStream dataOS;
            if (!agenda.exists()){
                dataOS = new ObjectOutputStream(new FileOutputStream(agenda));
            } else {
                dataOS = new MiObjectInputStream(new FileOutputStream(agenda));
            }
            for (Contacto c: contactoList) {
                dataOS.writeObject(c);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarContacto(String nombre, String telefono, String correo) {
        contactoList.add(new Contacto(nombre, telefono, correo));
    }

    public void listarContactos() {
        for (Contacto contacto : contactoList) {
            System.out.println("Nombre: " + contacto.getNombre());
            System.out.println("Teléfono: " + contacto.getTelefono());
            System.out.println("Correo Electrónico: " + contacto.getCorreo());
            System.out.println();
        }
    }
}