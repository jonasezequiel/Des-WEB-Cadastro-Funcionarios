package com.funcionarios.funcionarios.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.funcionarios.funcionarios.config.Conexao;
import com.funcionarios.funcionarios.model.Cargo;

@Repository
public class CargoRepository {
    public void inserir(Cargo cargo) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String query = "INSERT INTO CARGO(id_cargo, profissao, descricao) VALUES(NEXTVAL('cargo_id_cargo_seq'),?,?)";

        PreparedStatement pstm;

        try {
            pstm = conn.prepareStatement(query);

            pstm.setString(1, cargo.getProfissao());
            pstm.setString(2, cargo.getDescricao());

            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(conn);
        }
    }

    public List<Cargo> buscar() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        List<Cargo> cargos = new ArrayList<>();

        String consulta = "SELECT * FROM CARGO";

        Statement stm = conn.createStatement();

        ResultSet resultado = stm.executeQuery(consulta);

        try {
            while (resultado.next()) {
                Cargo cargo = new Cargo();
                cargo.setId_cargo(resultado.getInt("id_cargo"));
                cargo.setProfissao(resultado.getString("profissao"));
                cargo.setDescricao(resultado.getString("descricao"));
                cargos.add(cargo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(conn);
        }

        return cargos;
    }

    public void excluir(int id_cargo) throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String consulta = "DELETE FROM CARGO WHERE ID_CARGO = ?";

        PreparedStatement pstm;
        pstm = conn.prepareStatement(consulta);

        pstm.setInt(1, id_cargo);
        pstm.execute();

        try {

        } catch (Exception e) {
            System.out.println("Não conseguiu excluir a tabela Cargo");
        } finally {
            conexao.desconectar(conn);

        }
    }
}
