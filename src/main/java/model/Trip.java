package model;

public class Trip {
    private int tripId;
    private String tripName;
    private String startDate;
    private String endDate;
    private String status;
    private int userId;
    private String createdAt;

    // 1. Constructor không đối số
    public Trip() {}

    // 2. Constructor đầy đủ để dùng khi lấy dữ liệu từ ResultSet (DAO)
    public Trip(int tripId, String tripName, String startDate, String endDate, String status, int userId, String createdAt) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    // 3. Getter và Setter
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}