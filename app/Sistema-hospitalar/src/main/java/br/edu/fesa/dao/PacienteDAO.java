package br.edu.fesa.dao;

import br.edu.fesa.exception.PersistenciaException;
import br.edu.fesa.model.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacienteDAO implements GenericoDAO<Paciente> {

    @Override
    public List<Paciente> Listar() throws PersistenciaException {
        String sql = "SELECT * FROM USUARIO.TBPACIENTE ORDER BY NOME ";

        List<Paciente> pacientes = new ArrayList();
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                pacientes.add(new Paciente(
                        result.getInt("ID"),
                        result.getString("NOME"),
                        result.getDate("DATANASC").toLocalDate(),
                        result.getString("CPF"),
                        result.getInt("IDCONVENIO"),
                        result.getString("CEP"),
                        result.getString("ENDERECO"),
                        result.getInt("NUMERO"),
                        result.getString("TELEFONE")
                ));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pacientes;
    }

    @Override
    public void Inserir(Paciente paciente) throws PersistenciaException {
        String sql = "INSERT INTO USUARIO.TBPACIENTE  (nome, dataNasc, cpf, idConvenio, cep, endereco, numero, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, paciente.getNome());
            pStatement.setDate(2, Date.valueOf(paciente.getDataNasc()));
            pStatement.setString(3, paciente.getCpf());
            pStatement.setInt(4, paciente.getIdConvenio());
            pStatement.setString(5, paciente.getCep());
            pStatement.setString(6, paciente.getEndereco());
            pStatement.setInt(7, paciente.getNumero());
            pStatement.setString(8, paciente.getTelefone());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Alterar(Paciente paciente) throws PersistenciaException {
        String sql = "UPDATE USUARIO.TBPACIENTE SET NOME=?, DATANASC=?, CPF=?, IDCONVENIO=?, CEP=?, ENDERECO=?, NUMERO=?, TELEFONE=? WHERE ID=?";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, paciente.getNome());
            pStatement.setDate(2, Date.valueOf(paciente.getDataNasc()));
            pStatement.setString(3, paciente.getCpf());
            pStatement.setInt(4, paciente.getIdConvenio());
            pStatement.setString(5, paciente.getCep());
            pStatement.setString(6, paciente.getEndereco());
            pStatement.setInt(7, paciente.getNumero());
            pStatement.setString(8, paciente.getTelefone());
            pStatement.setInt(9, paciente.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Remover(Paciente e) throws PersistenciaException {
        String sql = "DELETE FROM USUARIO.TBPACIENTE WHERE ID=?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, e.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
