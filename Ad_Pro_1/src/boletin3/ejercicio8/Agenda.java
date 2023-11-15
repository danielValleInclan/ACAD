package boletin3.ejercicio8;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agenda implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    List<Contacto> list = new ArrayList<>();
}
