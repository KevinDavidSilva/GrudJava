/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoadatos;
import entidades.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author elmer
 */
public class PrestamoDAL {
    public static int crear(Prestamo prestamo) {
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "INSERT INTO Prestamo (LibroID, UsuarioNombre, UsuarioCorreo, FechaPrestamo, FechaDevolucion) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, prestamo.getLibroID());
                statement.setString(2, prestamo.getUsuarioNombre());
                statement.setString(3, prestamo.getUsuarioCorreo());
                statement.setDate(4, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
                statement.setDate(5, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
                    
                
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el préstamo", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static int modificar(Prestamo prestamo) {
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "UPDATE Prestamo SET LibroID=?, UsuarioNombre=?, UsuarioCorreo=?, FechaPrestamo=?, FechaDevolucion=? WHERE PrestamoID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, prestamo.getLibroID());
                statement.setString(2, prestamo.getUsuarioNombre());
                statement.setString(3, prestamo.getUsuarioCorreo());
                statement.setDate(4, new Date(prestamo.getFechaPrestamo().getTime()));
                if (prestamo.getFechaDevolucion() != null) {
                    statement.setDate(5, new Date(prestamo.getFechaDevolucion().getTime()));
                } else {
                    statement.setNull(5, java.sql.Types.DATE);
                }
                statement.setInt(6, prestamo.getPrestamoID());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al modificar el préstamo", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static int eliminar(Prestamo prestamo) {
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "DELETE FROM Prestamo WHERE PrestamoID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, prestamo.getPrestamoID());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar el préstamo", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    public static ArrayList<Prestamo> buscar(Prestamo prestamoSearch) {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "SELECT PrestamoID, LibroID, UsuarioNombre, UsuarioCorreo, FechaPrestamo, FechaDevolucion FROM Prestamo WHERE UsuarioNombre LIKE ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "%" + prestamoSearch.getUsuarioNombre() + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Prestamo prestamo = new Prestamo();
                        prestamo.setPrestamoID(resultSet.getInt("PrestamoID"));
                        prestamo.setLibroID(resultSet.getInt("LibroID"));
                        prestamo.setUsuarioNombre(resultSet.getString("UsuarioNombre"));
                        prestamo.setUsuarioCorreo(resultSet.getString("UsuarioCorreo"));
                        prestamo.setFechaPrestamo(resultSet.getDate("FechaPrestamo"));
                        prestamo.setFechaDevolucion(resultSet.getDate("FechaDevolucion"));
                        prestamos.add(prestamo);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al buscar préstamos", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return prestamos;
    }
}
