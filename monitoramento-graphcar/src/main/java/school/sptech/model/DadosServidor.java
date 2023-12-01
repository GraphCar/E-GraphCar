package school.sptech.model;

import school.sptech.banco.dao.ProcessoServidorDao;
import school.sptech.model.componentes.Componente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DadosServidor {

    private Integer idDadosServidor;
    private List<Componente> componentes;
    private LocalDateTime dateDado;
    private Integer fkServidor;
    private List<ProcessoServidor> processos;

    public DadosServidor() {
        this.componentes = new ArrayList<>();
        this.processos = new ArrayList<>();
    }

    public DadosServidor(Integer idDadosServidor, List<Componente> componentes, LocalDateTime dateDado, Integer fkServidor, List<ProcessoServidor> processos) {
        this.idDadosServidor = idDadosServidor;
        this.componentes = componentes;
        this.dateDado = dateDado;
        this.fkServidor = fkServidor;
        this.processos = processos;
    }

    public DadosServidor(Integer idDadosServidor, List<Componente> componentes, LocalDateTime dateDado, Integer fkServidor) {
        this.idDadosServidor = idDadosServidor;
        this.componentes = componentes;
        this.dateDado = dateDado;
        this.fkServidor = fkServidor;
        this.processos = new ArrayList<>();
    }

    public DadosServidor(List<Componente> componentes, LocalDateTime dateDado, Integer fkServidor, List<ProcessoServidor> processos) {
        this.componentes = componentes;
        this.dateDado = dateDado;
        this.fkServidor = fkServidor;
        this.processos = processos;
    }

    public DadosServidor(List<Componente> componentes, LocalDateTime dateDado, Integer fkServidor) {
        this.componentes = componentes;
        this.dateDado = dateDado;
        this.fkServidor = fkServidor;
        this.processos = new ArrayList<>();
    }

    public void adicionarProcesso(ProcessoServidor processo) {
        if (processo != null) {
            processo.setFkDadosServidor(this.idDadosServidor);
            ProcessoServidorDao.inserirProcesso(processo);
            processo = ProcessoServidorDao.buscarProcessoPorNomeEDadosServidor(this.idDadosServidor, processo.getNomeProcesso());
            this.processos.add(processo);
        }
    }

    public void registrarDados() {
        for (Componente componenteDaVez : this.componentes) {
            componenteDaVez.registrarDados();
        }
    }

    public void removerProcessoPorId(Integer idProcesso) {

    }

    public Integer getIdDadosServidor() {
        return idDadosServidor;
    }

    public void setIdDadosServidor(Integer idDadosServidor) {
        this.idDadosServidor = idDadosServidor;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public LocalDateTime getDateDado() {
        return dateDado;
    }

    public void setDateDado(LocalDateTime dateDado) {
        this.dateDado = dateDado;
    }

    public Integer getFkServidor() {
        return fkServidor;
    }

    public void setFkServidor(Integer fkServidor) {
        this.fkServidor = fkServidor;
    }

    @Override
    public String toString() {
        return "DadosServidor{" +
                "idDadosServidor=" + idDadosServidor +
                ", componentes=" + componentes +
                ", dateDado=" + dateDado +
                ", fkServidor=" + fkServidor +
                ", processos=" + processos +
                '}';
    }
}
