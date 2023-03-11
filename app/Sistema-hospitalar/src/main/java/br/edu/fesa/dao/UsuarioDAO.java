package br.edu.fesa.dao;

import br.edu.fesa.exception.PersistenciaException;

import br.edu.fesa.model.Usuario;
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

public class UsuarioDAO implements GenericoDAO<Usuario> {

    @Override
    public List<Usuario> Listar() throws PersistenciaException {
        String sql = "SELECT * FROM USUARIO.TBUSUARIO ORDER BY ID ";

        List<Usuario> usuarios = new ArrayList();
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                usuarios.add(new Usuario(
                        result.getInt("ID"),
                        result.getString("NOME"),
                        result.getDate("DATANASC").toLocalDate(),
                        result.getString("LOGIN"),
                        result.getString("SENHA"),
                        result.getString("TIPOUSER"),
                        result.getInt("IDUSER")
                ));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;
    }

    @Override
    public void Inserir(Usuario usuario) throws PersistenciaException {
        String sql = "INSERT INTO USUARIO.TBUSUARIO (NOME, DATANASC, LOGIN, SENHA, TIPOUSER, IDUSER) VALUES(?, ?, ?', ?, ?, ?);";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getNome());
            pStatement.setDate(2, Date.valueOf(usuario.getDataNasc()));
            pStatement.setString(3, usuario.getLogin());
            pStatement.setString(4, usuario.getSenha());
            pStatement.setString(5, usuario.getTipoUser());
            pStatement.setInt(6, usuario.getIdUser());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Alterar(Usuario usuario) throws PersistenciaException {
        String sql = "UPDATE USUARIO.TBUSUARIO SET NOME=?, DATANASC=?, LOGIN=?, SENHA=?, TIPOUSER=?, IDUSER=? WHERE ID=?;";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getNome());
            pStatement.setDate(2, Date.valueOf(usuario.getDataNasc()));
            pStatement.setString(3, usuario.getLogin());
            pStatement.setString(4, usuario.getSenha());
            pStatement.setString(5, usuario.getTipoUser());
            pStatement.setInt(6, usuario.getIdUser());
            pStatement.setInt(7, usuario.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Remover(Usuario usuario) throws PersistenciaException {
        String sql = "DELETE FROM USUARIO.TBUSUARIO WHERE ID=?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, usuario.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
