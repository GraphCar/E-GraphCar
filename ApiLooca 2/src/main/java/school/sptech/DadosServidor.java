package school.sptech;

import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import java.awt.*;

public class DadosServidor {
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConecaoDoBanco();

    private Integer id;

    public DadosServidor() {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void dadosServidor() {
        Looca looca = new Looca();
        Sistema servidor = new Sistema();
        SystemInfo systemInfo = new SystemInfo();
        Rede rede = new Rede(systemInfo);

        System.out.println(servidor);
        con.update("INSERT INTO Servidor (modeloServidor, hostname, sistemaOperacional) VALUES (?,?,?)", servidor.getArquitetura(), rede.getParametros().getHostName(), servidor.getSistemaOperacional());

        id = con.queryForObject("SELECT idServidor FROM Servidor ORDER BY idServidor DESC LIMIT 1", Integer.class);
    }
}
