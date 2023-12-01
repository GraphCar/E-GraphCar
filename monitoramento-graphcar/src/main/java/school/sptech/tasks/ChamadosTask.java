package school.sptech.tasks;

import school.sptech.controller.ChamadosController;
import school.sptech.model.Servidor;

import java.io.IOException;
import java.util.TimerTask;

public class ChamadosTask extends TimerTask {

    private Servidor servidor;
    public static final int DELAY = 1000;
    public static final int PERIODO = 60000;

    public ChamadosTask(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            ChamadosController.compararServidorBanco(servidor);
            ChamadosController.atualizarBancoSlack(servidor);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public String toString() {
        return "ChamadosTask{" +
                "servidor=" + servidor +
                "} " + super.toString();
    }
}
