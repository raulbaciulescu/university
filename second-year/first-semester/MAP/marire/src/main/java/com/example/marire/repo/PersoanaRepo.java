package com.example.marire.repo;

import com.example.marire.domain.Persoana;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersoanaRepo {
    private String url;

//    private String nume;
//    private String prenume;
//    private String username;
//    private String parola;
//    private String oras;
//    private String strada;
//    private String numarStrada;
//    private String telefon;

    public PersoanaRepo() {
        this.url = "jdbc:postgresql://localhost:5432/meta";
    }


    public Persoana findOne(Long id) {
        String sql = "SELECT * from meta.public.user where id=" + String.valueOf(id);
        try (Connection connection = DriverManager.getConnection(url, "postgres", "266259");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id3 = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                String oras = resultSet.getString("oras");
                String strada = resultSet.getString("strada");
                String numarStrada = resultSet.getString("numarStrada");
                String telefon = resultSet.getString("telefon");

                Persoana utilizator = new Persoana(id, firstName, lastName, username, parola, oras, strada, numarStrada, telefon);
                return utilizator;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Iterable<Persoana> findAll() {
        Set<Persoana> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, "postgres", "266259");
             PreparedStatement statement = connection.prepareStatement("SELECT * from meta.public.user");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id3 = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String parola = resultSet.getString("parola");
                String oras = resultSet.getString("oras");
                String strada = resultSet.getString("strada");
                String numarStrada = resultSet.getString("numarStrada");
                String telefon = resultSet.getString("telefon");

                Persoana utilizator = new Persoana(id3, firstName, lastName, username, parola, oras, strada, numarStrada, telefon);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Persoana save(Persoana entity) {

        String sql = "insert into meta.public.user (id, first_name, last_name,username, parola, oras, numarStrada,strada, telefon" +
                " ) values (?, ? , ?, ?, ? , ? , ? , ?,?)";

        try (Connection connection = DriverManager.getConnection(url, "postgres", "266259");
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, entity.getId().intValue());
            ps.setString(2, entity.getPrenume());
            ps.setString(3, entity.getNume());
            ps.setString(4, entity.getUsername());
            ps.setString(5, entity.getParola());
            ps.setString(6, entity.getOras());
            ps.setString(7, entity.getNumarStrada());
            ps.setString(8, entity.getStrada());
            ps.setString(9, entity.getTelefon());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
