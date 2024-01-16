package com.funcionarios.funcionarios.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.funcionarios.funcionarios.config.Conexao;
import com.funcionarios.funcionarios.model.Funcionario;

@Repository
public class FuncionarioRepository {
    public void inserir(Funcionario funcionario) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String query = "INSERT INTO FUNCIONARIO(ID, NOME, CPF, LOGIN, SENHA) VALUES(NEXTVAL('FUNCIONARIO_ID_SEQ'),?,?,?,?,)";

        PreparedStatement pstm;

        try {
            pstm = conn.prepareStatement(query);

            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getCpf());
            pstm.setString(3, funcionario.getLogin());
            pstm.setString(4, funcionario.getSenha());

            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(conn);
        }

    }

    public List<Funcionario> buscar() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Funcionario> funcionarios = new ArrayList<>();

        String consulta = "SELECT * FROM FUNCIONARIO";

        Statement stm = conn.createStatement();

        ResultSet resultado = stm.executeQuery(consulta);
        try {
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getInt("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setCpf(resultado.getString("cpf"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionarios.add(funcionario);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(conn);
        }

        return funcionarios;
    }

    public List<Funcionario> consulta() {
        return null;
    }

    public void excluir(int id) throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String consulta = "DELETE FROM FUNCIONARIO WHERE ID = ?";

        PreparedStatement pstm;
        pstm = conn.prepareStatement(consulta);

        pstm.setInt(1, id);
        pstm.execute();

        try {

        } catch (Exception e) {
            System.out.println("Não conseguiu excluir a tabela Funcionario");
        } finally {
            conexao.desconectar(conn);

        }
    }

}
