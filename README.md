======================================================================

&#x20;               TRAVELGOOD - TRIP PLANNING MANAGEMENT SYSTEM

======================================================================



TravelGood là ứng dụng web Java đơn giản giúp người dùng lập kế hoạch và quản lý các chuyến đi du lịch. 

Dự án được xây dựng theo kiến trúc MVC (Model-View-Controller) với giao diện hiện đại sử dụng Tailwind CSS.



🌟 TÍNH NĂNG CHÍNH (CRUD)



• Xem danh sách chuyến đi theo người dùng (theo UserID)

• Tạo chuyến đi mới (Trip Name, Start Date, End Date)

• Chỉnh sửa thông tin chuyến đi đã tồn tại

• Hủy chuyến (đổi trạng thái thành Cancelled)

• Xóa chuyến đi hoàn toàn khỏi hệ thống



Ràng buộc dữ liệu:

• Ngày bắt đầu không được ở quá khứ

• Ngày kết thúc phải sau ngày bắt đầu



🛠 CÔNG NGHỆ SỬ DỤNG



• Backend     : Java Servlet, JDBC

• Frontend    : JSP, JSTL, Tailwind CSS 4.0, FontAwesome 6.0

• Database    : MySQL

• Build Tool  : Maven

• Server      : Apache Tomcat 7/8/9



📂 CẤU TRÚC DỰ ÁN



src/main/java

├── controller      # TripServlet - Xử lý điều hướng và logic

├── dao             # DBContext, TripDAO - Xử lý kết nối và truy vấn Database

└── model           # Trip.java - Lớp đối tượng Trip



src/main/webapp

├── WEB-INF         # web.xml

└── list-trips.jsp  # Giao diện chính của ứng dụng



🗄 CẤU TRÚC DATABASE



CREATE TABLE Trips (

&#x20;   TripID      INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   TripName    NVARCHAR(100) NOT NULL,

&#x20;   StartDate   DATE,

&#x20;   EndDate     DATE,

&#x20;   Status      NVARCHAR(20) DEFAULT 'Draft',

&#x20;   UserID      INT,

&#x20;   CreatedAt   DATETIME DEFAULT NOW(),

&#x20;   CONSTRAINT FK\_UserTrip FOREIGN KEY (UserID) REFERENCES Users(UserID)

);



🚀 HƯỚNG DẪN CÀI ĐẶT



1\. Clone project hoặc mở bằng IntelliJ IDEA / Eclipse.



2\. Cấu hình Database:

&#x20;  - Mở MySQL Workbench hoặc phpMyAdmin

&#x20;  - Chạy script tạo bảng Users và Trips

&#x20;  - Mở file dao/DBContext.java và sửa username + password của bạn



3\. Build Project:

&#x20;  - Chạy lệnh: mvn clean install



4\. Chạy ứng dụng:

&#x20;  - Sử dụng Tomcat plugin: mvn tomcat7:run

&#x20;  - Truy cập: http://localhost:8080/trips?uid=1



📝 LƯU Ý QUAN TRỌNG



• Hệ thống hiện tại quản lý dữ liệu dựa trên tham số uid trên URL.

• Đảm bảo cổng 8080 không bị chiếm dụng. 

&#x20; Nếu bị lỗi "Address already in use", dùng lệnh:

&#x20; taskkill /F /PID <ProcessID>



======================================================================

Phát triển bởi: LTD - 2026

======================================================================

