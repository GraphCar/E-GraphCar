package school.sptech.model.componentes;

import school.sptech.utils.ColetaDadosUtils;

public class Memoria implements Componente {

    private Double uso;

    public Memoria() {
    }

    public Memoria(Double uso) {
        this.uso = uso;
    }

    @Override
    public void registrarDados() {
        this.uso = 100. * ColetaDadosUtils.LOOCA.getMemoria().getEmUso() / ColetaDadosUtils.LOOCA.getMemoria().getTotal();
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return "Memoria{" +
                "uso=" + uso +
                '}';
    }
}
