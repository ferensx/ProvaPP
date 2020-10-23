package DAO;

import db.ConexaoHSQLDB;
import entity.Trator;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TratorDAO extends ConexaoHSQLDB {
    final String INSERT_SQL = "INSERT INTO TRATOR(MARCA, MODELO, ANO, VALOR ) VALUES (?, ?, ?, ? ); ";
    final String SQL_SELECT = "SELECT * FROM TRATOR;";
    final String SQL_SELECT_TRATOR_ID = "SELECT * FROM TRATOR WHERE ID =? ;";
    final String SQL_ALTERA_TRATOR = "UPDATE TRATOR SET MARCA = ?, MODELO =?, ANO =?, VALOR =? WHERE ID =? ;";
    final String SQL_DELETA_TRATOR= "DELETE FROM TRATOR WHERE ID = ?;";

    public int inserirTrator(Trator trator) {

        int quantidade = 0;
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(INSERT_SQL);) {
            pst.setString(1, trator.getMarca());
            pst.setString(2, trator.getModelo());
            pst.setInt(3, trator.getAno());
            pst.setFloat(4, trator.getValor());
            quantidade = pst.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }return quantidade;
    }
    public List<Trator> listAll() {
        List<Trator> listaTrator = new ArrayList<Trator>();
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(SQL_SELECT);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Trator trator = new Trator();
                trator.setId(rs.getInt("ID"));
                trator.setMarca(rs.getString("MARCA"));
                trator.setModelo(rs.getString("MODELO"));
                trator.setAno(rs.getInt("Ano"));
                trator.setValor(rs.getFloat("VALOR"));

                listaTrator.add(trator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTrator;



    }

    public Trator findByID(int id) {
        Trator trator = null;
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(SQL_SELECT_TRATOR_ID);) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                trator = new Trator();
                trator.setId(rs.getInt("ID"));
                trator.setMarca(rs.getString("MARCA"));
                trator.setModelo(rs.getString("MODELO"));
                trator.setAno(rs.getInt("ANO"));
                trator.setValor(rs.getFloat("VALOR"));
            }
        }catch(SQLException e) {
            e.printStackTrace();

        }
        return trator;
    }

    public int alterar(Trator trator) {
        int quantidade = 0;
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_TRATOR);) {
            pst.setString(1, trator.getMarca());
            pst.setString(2, trator.getModelo());
            pst.setInt(3, trator.getAno());
            pst.setFloat(4, trator.getValor());
            pst.setInt(5, trator.getId());
            quantidade = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantidade;
    }

    public int ExcluirTrator(int id) {
        int quantidade = 0;
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(SQL_DELETA_TRATOR);) {
            pst.setInt(1, id);
            quantidade = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }
}







