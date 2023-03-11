package br.edu.fesa.dao;

import br.edu.fesa.exception.PersistenciaException;
import br.edu.fesa.model.Enfermeiro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnfermeiroDAO implements GenericoDAO<Enfermeiro> {

    @Override
    public List<Enfermeiro> Listar() throws PersistenciaException {
        String sql = "SELECT * FROM USUARIO.TBENFERMEIRO ORDER BY ID ";

        List<Enfermeiro> enfermeiros = new ArrayList();
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                enfermeiros.add(new Enfermeiro(
                        result.getString("COREN"),
                        result.getInt("ID")
                ));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return enfermeiros;
    }

    @Override
    public void Inserir(Enfermeiro enfermeiro) throws PersistenciaException {
        String sql = "INSERT INTO USUARIO.TBENFERMEIRO (COREN) VALUES(?);";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, enfermeiro.getCoren());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Alterar(Enfermeiro enfermeiro) throws PersistenciaException {
        String sql = "UPDATE USUARIO.TBENFERMEIRO SET COREN=? WHERE ID=?;";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, enfermeiro.getCoren());
            pStatement.setInt(7, enfermeiro.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Remover(Enfermeiro enfermeiro) throws PersistenciaException {
        String sql = "DELETE FROM USUARIO.TBENFERMEIRO WHERE ID=?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, enfermeiro.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EnfermeiroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
