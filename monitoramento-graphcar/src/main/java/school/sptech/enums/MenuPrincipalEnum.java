package school.sptech.enums;

public enum MenuPrincipalEnum {


    TOGGLE_MONITORAMENTO(1, "Iniciar/Parar Monitoramento"),
    CRIAR_USUARIO(2, "Criar novo usuário administrador"),
    SAIR(0, "Sair");

    private int codigo;
    private String descricao;

    MenuPrincipalEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static MenuPrincipalEnum of(int codigo) {
        switch (codigo) {
            case 1:
                return TOGGLE_MONITORAMENTO;
            case 2:
                return CRIAR_USUARIO;
            case 0:
                return SAIR;
            default:
                return null;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "%d - %s".formatted(this.codigo, this.descricao);
    }
}
