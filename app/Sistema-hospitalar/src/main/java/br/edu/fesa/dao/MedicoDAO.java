package br.edu.fesa.dao;

import br.edu.fesa.exception.PersistenciaException;
import br.edu.fesa.model.Medico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicoDAO implements GenericoDAO<Medico> {

    @Override
    public List<Medico> Listar() throws PersistenciaException {
        String sql = "SELECT * FROM USUARIO.TBENFERMEIRO ORDER BY ID ";

        List<Medico> medicos = new ArrayList();
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                medicos.add(new Medico(
                        result.getString("CRM"),
                        result.getString("ESPECIALIDADE"),                        
                        result.getInt("ID")
                ));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return medicos;
    }

    @Override
    public void Inserir(Medico medico) throws PersistenciaException {
        String sql = "INSERT INTO USUARIO.TBMEDICO (CRM, ESPECIALIDADE) VALUES(?, ?);";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, medico.getCrm());
            pStatement.setString(2, medico.getEspecialidade());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Alterar(Medico medico) throws PersistenciaException {
        String sql = "UPDATE USUARIO.TBMEDICO SET CRM=?, ESPECIALIDADE=? WHERE ID=?;";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, medico.getCrm());
            pStatement.setString(1, medico.getEspecialidade());
            pStatement.setInt(7, medico.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Remover(Medico medico) throws PersistenciaException {
        String sql = "DELETE FROM USUARIO.TBMEDICO WHERE ID=?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, medico.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
