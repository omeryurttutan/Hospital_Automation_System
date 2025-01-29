package hbys.AdminPanelDAO;

import hbys.database.DatabaseConnection;
import hbys.AdminModels.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection connection) {
        this.conn = connection;
    }

    public UserDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    // Fetch all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("UserID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("UserType"),
                        rs.getTimestamp("RegistrationDate")
                ));
            }
        }
        return users;
    }

    // Add a new user
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (FirstName, LastName, Email, Password, UserType, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserType());
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        }
    }

    // Update a user
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, Password = ?, UserType = ? WHERE UserID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserType());
            ps.setInt(6, user.getUserID());
            ps.executeUpdate();
        }
    }

    // Delete a user
    public void deleteUser(int userID) throws SQLException {
        String query = "DELETE FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            ps.executeUpdate();
        }
    }

    // Search users by keyword
    public List<User> searchUsers(String keyword) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE FirstName LIKE ? OR LastName LIKE ? OR Email LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("UserID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("UserType"),
                            rs.getTimestamp("RegistrationDate")
                    ));
                }
            }
        }
        return users;
    }
}
