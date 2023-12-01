package school.sptech.model;

public class ProcessoServidor {

    private Integer idProcesso;
    private String nomeProcesso;
    private Integer fkDadosServidor;

    public ProcessoServidor() {
    }

    public ProcessoServidor(Integer idProcesso, String nomeProcesso, Integer fkDadosServidor) {
        this.idProcesso = idProcesso;
        this.nomeProcesso = nomeProcesso;
        this.fkDadosServidor = fkDadosServidor;
    }

    public ProcessoServidor(String nomeProcesso, Integer fkDadosServidor) {
        this.idProcesso = idProcesso;
        this.nomeProcesso = nomeProcesso;
        this.fkDadosServidor = fkDadosServidor;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public void setNomeProcesso(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }


    public Integer getFkDadosServidor() {
        return fkDadosServidor;
    }

    public void setFkDadosServidor(Integer fkDadosServidor) {
        this.fkDadosServidor = fkDadosServidor;
    }
}
