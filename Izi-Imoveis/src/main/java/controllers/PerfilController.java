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

    public Pessoa obterPerfilPorId(int pessoaId) throws SQLException {
        return dbo.obterPerfilPorId(pessoaId);
    }

    public void atualizarPerfil(Pessoa pessoa) throws SQLException {
        dbo.atualizarPerfil(pessoa);
    }
}
