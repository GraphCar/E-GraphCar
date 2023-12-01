package school.sptech.utils;

import school.sptech.banco.dao.UsuarioDao;
import school.sptech.model.Usuario;

import java.util.List;

public class UsuarioUtils {

    public static Usuario validarLogin(String email, String senha) {

        List<Usuario> usuarios = UsuarioDao.listarUsuarios();
        Usuario usuario = null;
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuarioAtual = usuarios.get(i);
            if (usuarioAtual.usuarioAutenticado(email, senha)) {
                usuario = usuarioAtual;
            }
        }
        return usuario;
    }

    public static void criarAdministrador() {

    }
}
