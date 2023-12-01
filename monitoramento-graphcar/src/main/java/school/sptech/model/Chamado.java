package school.sptech.model;

import java.time.LocalDateTime;

public class Chamado {

    private Integer idChamado;
    private Integer fkServidor;
    private String componente;
    private String chaveJira;
    private String status;
    private Boolean encerrado;
    private Boolean critico;
    private LocalDateTime dataAbertura;
    private LocalDateTime ultimaMensagemSlack;

    public Chamado(Integer fkServidor, String componente, String chaveJira, String status, Boolean critico) {
        this.fkServidor = fkServidor;
        this.componente = componente;
        this.chaveJira = chaveJira;
        this.status = status;
        this.encerrado = false;
        this.critico = critico;
        this.dataAbertura = LocalDateTime.now();
        this.ultimaMensagemSlack = LocalDateTime.now();
    }

    public Chamado(Integer idChamado, Integer fkServidor, String componente, String chaveJira, String status, Boolean encerrado, Boolean critico, LocalDateTime dataAbertura, LocalDateTime ultimaMensagemSlack) {
        this.idChamado = idChamado;
        this.fkServidor = fkServidor;
        this.componente = componente;
        this.chaveJira = chaveJira;
        this.status = status;
        this.encerrado = encerrado;
        this.critico = critico;
        this.dataAbertura = dataAbertura;
        this.ultimaMensagemSlack = ultimaMensagemSlack;
    }

    public Integer getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Integer idChamado) {
        this.idChamado = idChamado;
    }

    public Integer getFkServidor() {
        return fkServidor;
    }

    public void setFkServidor(Integer fkServidor) {
        this.fkServidor = fkServidor;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getChaveJira() {
        return chaveJira;
    }

    public void setChaveJira(String chaveJira) {
        this.chaveJira = chaveJira;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getEncerrado() {
        return encerrado;
    }

    public void setEncerrado(Boolean encerrado) {
        this.encerrado = encerrado;
    }

    public Boolean getCritico() {
        return critico;
    }

    public void setCritico(Boolean critico) {
        this.critico = critico;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getUltimaMensagemSlack() {
        return ultimaMensagemSlack;
    }

    public void setUltimaMensagemSlack(LocalDateTime ultimaMensagemSlack) {
        this.ultimaMensagemSlack = ultimaMensagemSlack;
    }

    @Override
    public String toString() {
        return "Chamado{" +
                "idChamado=" + idChamado +
                ", fkServidor=" + fkServidor +
                ", componente='" + componente + '\'' +
                ", chaveJira='" + chaveJira + '\'' +
                ", status='" + status + '\'' +
                ", encerrado=" + encerrado +
                ", critico=" + critico +
                ", dataAbertura=" + dataAbertura +
                ", ultimaMensagemSlack=" + ultimaMensagemSlack +
                '}';
    }
}
