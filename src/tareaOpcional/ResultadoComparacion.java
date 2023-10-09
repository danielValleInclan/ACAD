package tareaOpcional;

public class ResultadoComparacion {
    private String nameFile;
    private ValorComparacion valorComparacion;

    public ResultadoComparacion(String nameFile, ValorComparacion valorComparacion){
        this.nameFile = nameFile;
        this.valorComparacion = valorComparacion;
    }

    public String getNomFichero(){
        return nameFile;
    }
    ValorComparacion getValorComparacion(){
        return valorComparacion;
    }

    @Override
    public String toString() {
        return "Archivo: " + nameFile + ", Comparación: " + valorComparacion;
    }

}
