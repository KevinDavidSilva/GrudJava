/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoadatos;

import entidades.Libros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author elmer
 */
public class LibrosDAL {
      public static ArrayList<Libros> obtenerTodos() {
        ArrayList<Libros> libros = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "SELECT LibroID, Titulo, Autor, Genero, Publicacion, Disponible FROM Libros";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int libroID = resultSet.getInt("LibroID");
                        String titulo = resultSet.getString("Titulo");
                        String autor = resultSet.getString("Autor");
                        String genero = resultSet.getString("Genero");
                        java.sql.Date publicacion = resultSet.getDate("Publicacion");
                        boolean disponible = resultSet.getBoolean("Disponible");
                        Libros libro = new Libros(libroID, titulo, autor, genero, new java.util.Date(publicacion.getTime()), disponible);
                        libros.add(libro);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al obtener los libros", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return libros;
    }

    public static void crear(Libros libro) {
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "INSERT INTO Libros (Titulo, Autor, Genero, Publicacion, Disponible) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, libro.getTitulo());
                statement.setString(2, libro.getAutor());
                statement.setString(3, libro.getGenero());
                statement.setDate(4, new java.sql.Date(libro.getPublicacion().getTime()));
                statement.setBoolean(5, libro.isDisponible());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el libro", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static void actualizar(Libros libro) {
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "UPDATE Libros SET Titulo = ?, Autor = ?, Genero = ?, Publicacion = ?, Disponible = ? WHERE LibroID = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, libro.getTitulo());
                statement.setString(2, libro.getAutor());
                statement.setString(3, libro.getGenero());
                statement.setDate(4, new java.sql.Date(libro.getPublicacion().getTime()));
                statement.setBoolean(5, libro.isDisponible());
                statement.setInt(6, libro.getLibroID());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error al actualizar el libro", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static void eliminar(int libroID) {
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "DELETE FROM Libros WHERE LibroID = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, libroID);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar el libro", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }
}
