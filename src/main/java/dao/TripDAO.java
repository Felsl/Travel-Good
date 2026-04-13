package dao;

import model.Trip;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {

    // 1. Lấy toàn bộ danh sách (READ)
    public List<Trip> getAllTrips() {
        List<Trip> list = new ArrayList<>();
        // Sắp xếp CreatedAt DESC để chuyến mới nhất hiện lên đầu
        String sql = "SELECT * FROM Trips ORDER BY CreatedAt DESC";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Trip(
                        rs.getInt("TripID"),
                        rs.getString("TripName"),
                        rs.getString("StartDate"),
                        rs.getString("EndDate"),
                        rs.getString("Status"),
                        rs.getInt("UserID"),
                        rs.getString("CreatedAt")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới chuyến đi (CREATE)
    public void addTrip(String name, String startDate, String endDate) {
        // Mặc định UserID = 1 (vì chưa làm login), Status = 'Draft'
        String sql = "INSERT INTO Trips (TripName, StartDate, EndDate, Status, UserID) VALUES (?, ?, ?, 'Draft', 1)";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Lấy 1 chuyến đi theo ID (Để đổ dữ liệu lên Form sửa)
    public Trip getTripByID(int id) {
        String sql = "SELECT * FROM Trips WHERE TripID = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Trip(
                            rs.getInt("TripID"),
                            rs.getString("TripName"),
                            rs.getString("StartDate"),
                            rs.getString("EndDate"),
                            rs.getString("Status"),
                            rs.getInt("UserID"),
                            rs.getString("CreatedAt")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Cập nhật thông tin (UPDATE)
    public void updateTrip(int id, String newName, String startDate, String endDate) {
        String sql = "UPDATE Trips SET TripName = ?, StartDate = ?, EndDate = ? WHERE TripID = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Hủy chuyến đi (SOFT UPDATE)
    public void cancelTrip(int id) {
        String sql = "UPDATE Trips SET Status = 'Cancelled' WHERE TripID = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 6. Xóa chuyến đi (DELETE)
    public void deleteTrip(int id) {
        String sql = "DELETE FROM Trips WHERE TripID = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Trip> getTripsByUserId(int userId) {
        List<Trip> list = new ArrayList<>();
        String sql = "SELECT * FROM Trips WHERE UserID = ? ORDER BY CreatedAt DESC";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Trip(
                        rs.getInt("TripID"), rs.getString("TripName"),
                        rs.getString("StartDate"), rs.getString("EndDate"),
                        rs.getString("Status"), rs.getInt("UserID"), rs.getString("CreatedAt")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Thêm mới trip gắn với userID cụ thể
    public void addTrip(String name, String startDate, String endDate, int userId) {
        String sql = "INSERT INTO Trips (TripName, StartDate, EndDate, Status, UserID) VALUES (?, ?, ?, 'Draft', ?)";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, startDate);
            ps.setString(3, endDate);
            ps.setInt(4, userId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}