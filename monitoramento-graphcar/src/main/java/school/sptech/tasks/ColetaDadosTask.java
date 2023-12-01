package school.sptech.tasks;

import school.sptech.model.Servidor;
import school.sptech.utils.ColetaDadosUtils;

import java.util.TimerTask;

public class ColetaDadosTask extends TimerTask {

    private Servidor servidor;
    private boolean isRunning;
    public static final int DELAY = 0;
    public static final int PERIODO = 30000;

    public ColetaDadosTask(Servidor servidor) {
        this.servidor = servidor;
        this.isRunning = false;
    }

    @Override
    public void run() {
        ColetaDadosUtils.monitorarDados(this.servidor);
    }

    public Servidor getServidor() {
        return servidor;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
