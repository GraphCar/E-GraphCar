package school.sptech.view;

import school.sptech.controller.ChamadosController;
import school.sptech.enums.MenuPrincipalEnum;
import school.sptech.model.Servidor;
import school.sptech.model.Usuario;
import school.sptech.tasks.ChamadosTask;
import school.sptech.tasks.ColetaDadosTask;
import school.sptech.utils.ColetaDadosUtils;
import school.sptech.utils.LeitoresUtils;
import school.sptech.utils.UsuarioUtils;

import java.io.IOException;
import java.util.Timer;

public class Menu {

    private Usuario usuarioLogado;
    private Timer agendador;

    public Menu() {
        agendador = new Timer();
    }

    public void logarUsuario() {
        System.out.println("GraphCar - Sistema de Monitoramento\n");

        do {
            System.out.println("Digite seu email:");
            String email = LeitoresUtils.LEITOR_TEXTO.nextLine();
            System.out.println("Digite sua senha");
            String senha = LeitoresUtils.LEITOR_TEXTO.nextLine();
            this.usuarioLogado = UsuarioUtils.validarLogin(email, senha);

            if (this.usuarioLogado == null) {
                System.out.println("Usuário inválido!");
            } else if (this.usuarioLogado.getNivelAcesso() != 4) {
                System.out.println("Usuário não possui acesso à esta aplicação");
            }
        } while ((this.usuarioLogado == null) || (this.usuarioLogado.getNivelAcesso() != 4));

        System.out.println("Bem-vindo(a), %s".formatted(this.usuarioLogado.getNome()));
    }

    public void menuPrincipal() throws InterruptedException {
        MenuPrincipalEnum opcao;

        Servidor servidor = ColetaDadosUtils.obterDadosServidor();

        ColetaDadosTask taskColeta = null;
        ChamadosTask taskIntegracoes = null;

        do {
            System.out.println("Escolha uma opção:");
            for (MenuPrincipalEnum opcaoMenu : MenuPrincipalEnum.values()) {
                System.out.println(opcaoMenu);
            }
            opcao = MenuPrincipalEnum.of(LeitoresUtils.LEITOR_NUM.nextInt());

            if (opcao == MenuPrincipalEnum.TOGGLE_MONITORAMENTO) {
                if (taskColeta == null || !taskColeta.isRunning()) {

                    taskColeta = new ColetaDadosTask(servidor);
                    taskIntegracoes = new ChamadosTask(servidor);
                    this.agendador.schedule(taskColeta, ColetaDadosTask.DELAY, ColetaDadosTask.PERIODO);
                    this.agendador.schedule(taskIntegracoes, ChamadosTask.DELAY, ChamadosTask.PERIODO);

                    taskColeta.setRunning(true);
                    System.out.println("Monitoramento do servidor foi iniciado\n");

                } else {
                    taskColeta.cancel();
                    taskIntegracoes.cancel();

                    taskColeta.setRunning(false);
                    System.out.println("Monitoramento do servidor foi interrompido\n");
                }

            } else if (opcao == MenuPrincipalEnum.CRIAR_USUARIO) {
                try {
                    ChamadosController.atualizarBancoSlack(servidor);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // System.out.println(ColetaDadosUtils.exibirResumo(servidor));
            }

        } while (opcao != MenuPrincipalEnum.SAIR);

        this.usuarioLogado = null;
        this.agendador.cancel();
        System.out.println("Logout realizado com sucesso");
    }
}
