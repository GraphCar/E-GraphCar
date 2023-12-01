package school.sptech.model;

public class Usuario {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String foto;
    private Integer nivelAcesso;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nome, String email, String senha, String cpf, String foto, Integer nivelAcesso) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.foto = foto;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(Integer idUsuario, String nome, String email, String senha, String cpf, Integer nivelAcesso) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nivelAcesso = nivelAcesso;
    }

    public Boolean usuarioAutenticado(String email, String senha) {
        if (this.email.equals(email) && this.senha.equals(senha)) {
            return true;
        } else {
            return false;
        }
    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
