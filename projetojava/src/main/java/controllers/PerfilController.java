package controllers;

import models.Pessoa;
import util.DBO;

import java.sql.SQLException;

public class PerfilController {
    private DBO dbo;

    public PerfilController(DBO dbo) {
        this.dbo = dbo;
    }

    public DBO getDBO() {
        return dbo;
    }

    public Pessoa obterPerfil(String login) throws SQLException {
        return dbo.obterPerfil(login);
    }

    public void atualizarPerfil(Pessoa pessoa) throws SQLException {
        dbo.atualizarPerfil(pessoa);
    }
}
