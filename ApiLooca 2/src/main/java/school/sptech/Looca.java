package school.sptech;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeParametros;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import oshi.SystemInfo;

public class Looca {

    private final Processador processador;
    private final Memoria memoria;
    private final DiscoGrupo disco;
    private final Temperatura temperatura;
    private final ProcessoGrupo processoGrupo;
    private final Sistema sistema;
    private final Rede rede;
    private final SystemInfo si;


    public Looca() {
        this.si = new SystemInfo();
        this.rede = new Rede(si);
        this.sistema = new Sistema();
        this.processoGrupo = new ProcessoGrupo();
        this.processador = new Processador();
        this.memoria = new Memoria();
        this.disco = new DiscoGrupo();
        this.temperatura = new Temperatura();
    }


    public Temperatura getTemperatura() {return temperatura;}

    public Processador getProcessador() {
        return processador;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public DiscoGrupo getDisco() {
        return disco;
    }

    public void obterMemoria(){

    }

    public ProcessoGrupo getProcessoGrupo() {
        return processoGrupo;
    }

    public Sistema getSistema() {
        return sistema;
    }
}
