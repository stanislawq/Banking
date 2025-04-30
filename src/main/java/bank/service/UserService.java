package bank.service;

import bank.utils.DbUtil;
import bank.utils.PasswordDecoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserService {

    public boolean isUserExist(String username) {
        String sql = "SELECT 1 FROM userdata WHERE username = ?";
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    public boolean checkPassword(String username, String password) {
        String sql = "SELECT passwordhash FROM userdata WHERE username = ?";
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return false;
                }
                String stored = rs.getString("passwordhash");
                return PasswordDecoder.decode(password, stored).equals(stored);
            }
        } catch (IllegalStateException ise) {
            throw ise;
        } catch (Exception e) {
            throw new RuntimeException("Error while typing password", e);
        }
    }

    public void registerUserData(UserData userData, UserEntity userEntity) {

        if (isUserExist(userData.getUsername())) {
            System.err.println("User already exists: " + userData.getUsername());
            return;
        }

        String sql1 = "INSERT INTO userdata (username, passwordhash) VALUES (?, ?)";
        String sql2 = """
                
                        INSERT INTO userentity 
                  (username, fullname, fullsurname, email, phonenumber, dateofbirth)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (Connection conn = DbUtil.getConnection()) {
            conn.setAutoCommit(false);

            //Insert into userdata
            try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                ps1.setString(1, userData.getUsername());
                ps1.setString(2, userData.getPasswordHash());
                ps1.executeUpdate();
            }

            //Insert into userEntity
            try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                ps2.setString(1, userEntity.getUsername());
                ps2.setString(2, userEntity.getFullName());
                ps2.setString(3, userEntity.getFullSurname());
                ps2.setString(4, userEntity.getEmail());
                ps2.setString(5, userEntity.getPhoneNumber());
                // convert your String date (YYYY-MM-DD) to java.sql.Date:
                ps2.setDate(6, java.sql.Date.valueOf(userEntity.getDateOfBirth()));
                ps2.executeUpdate();
            }

            conn.commit();
            System.out.println("User “" + userData.getUsername() + "” registered successfully.");

        } catch (Exception e) {
            try {
                if (!e.getSuppressed().equals(null)) {
                    e.printStackTrace();
                }
            } catch (Exception rollEx) {
                // ignore
            }
            System.err.println("Registration failed: " + e.getMessage());
        }
    }


}
