package cadastrobd.model.dao;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.util.ConectorBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) throws SQLException {
        String sql = "SELECT * FROM PessoaFisica WHERE id = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PessoaFisica(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
            }
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() throws SQLException {
        String sql = "SELECT * FROM PessoaFisica";
        List<PessoaFisica> pessoas = new ArrayList<>();
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = ConectorBD.getPrepared(conn, sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pessoas.add(new PessoaFisica(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                ));
            }
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica)) {

                stmtPessoa.setString(1, pessoa.getNome());
                stmtPessoa.setString(2, pessoa.getLogradouro());
                stmtPessoa.setString(3, pessoa.getCidade());
                stmtPessoa.setString(4, pessoa.getEstado());
                stmtPessoa.setString(5, pessoa.getTelefone());
                stmtPessoa.setString(6, pessoa.getEmail());
                stmtPessoa.executeUpdate();

                ResultSet rs = stmtPessoa.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    pessoa.setId(id);

                    stmtPessoaFisica.setInt(1, id);
                    stmtPessoaFisica.setString(2, pessoa.getCpf());
                    stmtPessoaFisica.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void alterar(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE id = ?";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
                 PreparedStatement stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica)) {

                stmtPessoa.setString(1, pessoa.getNome());
                stmtPessoa.setString(2, pessoa.getLogradouro());
                stmtPessoa.setString(3, pessoa.getCidade());
                stmtPessoa.setString(4, pessoa.getEstado());
                stmtPessoa.setString(5, pessoa.getTelefone());
                stmtPessoa.setString(6, pessoa.getEmail());
                stmtPessoa.setInt(7, pessoa.getId());
                stmtPessoa.executeUpdate();

                stmtPessoaFisica.setString(1, pessoa.getCpf());
                stmtPessoaFisica.setInt(2, pessoa.getId());
                stmtPessoaFisica.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";
        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica);
                 PreparedStatement stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa)) {

                stmtPessoaFisica.setInt(1, id);
                stmtPessoaFisica.executeUpdate();

                stmtPessoa.setInt(1, id);
                stmtPessoa.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
