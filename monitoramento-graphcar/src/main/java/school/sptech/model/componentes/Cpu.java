package school.sptech.model.componentes;

import school.sptech.utils.ColetaDadosUtils;

public class Cpu implements Componente {

    private Double uso;
    private Double temperatura;

    public Cpu() {
    }

    public Cpu(Double uso, Double temperatura) {
        this.uso = uso;
        this.temperatura = temperatura;
    }

    @Override
    public void registrarDados() {
        this.uso = ColetaDadosUtils.LOOCA.getProcessador().getUso();
        if (!ColetaDadosUtils.LOOCA.getSistema().getSistemaOperacional().equals("Windows")) {
            this.temperatura = ColetaDadosUtils.LOOCA.getTemperatura().getTemperatura();
        }
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "uso=" + uso +
                ", temperatura=" + temperatura +
                '}';
    }
}
