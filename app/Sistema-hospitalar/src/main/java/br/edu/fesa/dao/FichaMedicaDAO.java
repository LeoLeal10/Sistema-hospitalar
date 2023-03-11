package br.edu.fesa.dao;

import br.edu.fesa.exception.PersistenciaException;
import br.edu.fesa.model.FichaMedica;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FichaMedicaDAO implements GenericoDAO<FichaMedica> {

    @Override
    public List<FichaMedica> Listar() throws PersistenciaException {
        String sql = "SELECT * FROM USUARIO.TBFICHAMEDICA BY ID;";

        List<FichaMedica> fichas = new ArrayList();
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                fichas.add(new FichaMedica(
                        result.getInt("ID"),
                        result.getInt("IDPACIENTE"),
                        result.getInt("IDATENDENTE"),
                        result.getInt("IDENFERMEIRO"),
                        result.getInt("IDENFERMEIRODOIS"),
                        result.getInt("IDMEDICO"),
                        result.getString("PRESSAO"),
                        result.getInt("TEMPERATURA"),
                        result.getString("CLASSIFICACAO"),
                        result.getString("DIAGNOSTICO"),
                        result.getString("MEDICAMENTO"),
                        result.getString("NOMEPACIENTE"),
                        result.getDate("DATA").toLocalDate(),
                        result.getTime("HORARIOATENDIMENTO").toLocalTime()
                ));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fichas;
    }

    @Override
    public void Inserir(FichaMedica ficha) throws PersistenciaException {
        String sql = "INSERT INTO USUARIO.TBFICHAMEDICA (PRESSAO, TEMPERATURA, CLASSIFICACAO, DIAGNOSTICO, MEDICACAO, NOMEPACIENTE, DATANASC, HORARIOANTEDIMENTO, IDPACIENTE, IDATENDENTE, IDENFERMEIRO, IDENFERMEIRODOIS, IDMEDICO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, ficha.getPressao());
            pStatement.setInt(2, ficha.getTemperatura());
            pStatement.setString(3, ficha.getClassificacao());
            pStatement.setString(4, ficha.getDiagnostico());
            pStatement.setString(5, ficha.getMedicacao());
            pStatement.setString(6, ficha.getNomePaciente());
            pStatement.setDate(7, Date.valueOf(ficha.getDataNasc()));
            pStatement.setTime(8, Time.valueOf(ficha.getHorarioAtendimento()));
            pStatement.setInt(9, ficha.getIdPaciente());
            pStatement.setInt(10, ficha.getIdAtendente());
            pStatement.setInt(11, ficha.getIdEnfermeiro());
            pStatement.setInt(13, ficha.getIdMedico());
            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Alterar(FichaMedica ficha) throws PersistenciaException {
        String sql = "UPDATE USUARIO.TBFICHAMEDICA SET PRESSAO=?, TEMPERATURA=?, CLASSIFICACAO=?, DIAGNOSTICO=?, MEDICACAO=?, NOMEPACIENTE=?, DATANASC=?, HORARIOANTEDIMENTO=?, IDPACIENTE=?, IDATENDENTE=?, IDENFERMEIRO=?, IDENFERMEIRODOIS=?, IDMEDICO=? WHERE ID=?;";
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, ficha.getPressao());
            pStatement.setInt(2, ficha.getTemperatura());
            pStatement.setString(3, ficha.getClassificacao());
            pStatement.setString(4, ficha.getDiagnostico());
            pStatement.setString(5, ficha.getMedicacao());
            pStatement.setString(6, ficha.getNomePaciente());
            pStatement.setDate(7, Date.valueOf(ficha.getDataNasc()));
            pStatement.setTime(8, Time.valueOf(ficha.getHorarioAtendimento()));
            pStatement.setInt(9, ficha.getIdPaciente());
            pStatement.setInt(10, ficha.getIdAtendente());
            pStatement.setInt(11, ficha.getIdEnfermeiro());
            pStatement.setInt(13, ficha.getIdMedico());
            pStatement.setInt(14, ficha.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void Remover(FichaMedica ficha) throws PersistenciaException {
        String sql = "DELETE FROM USUARIO.TBFICHAMEDICA WHERE ID=?";

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, ficha.getId());
            pStatement.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(FichaMedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
