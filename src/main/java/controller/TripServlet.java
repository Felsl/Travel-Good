package controller;

import dao.TripDAO;
import model.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

@WebServlet("/trips")
public class TripServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        // 1. LẤY userID TỪ URL
        String uidRaw = request.getParameter("uid");
        if (uidRaw == null || uidRaw.isEmpty()) uidRaw = "1"; // Default là user 1
        int uid = Integer.parseInt(uidRaw);

        try {
            TripDAO dao = new TripDAO();
            // Luôn gửi uid sang JSP để các thẻ <a> và <form> dùng lại
            request.setAttribute("currentUid", uid);

            switch (action) {
                case "delete":
                    int idDel = Integer.parseInt(request.getParameter("id"));
                    dao.deleteTrip(idDel);
                    response.sendRedirect("trips?uid=" + uid);
                    return;

                case "cancel":
                    int idCan = Integer.parseInt(request.getParameter("id"));
                    dao.cancelTrip(idCan);
                    response.sendRedirect("trips?uid=" + uid);
                    return;

                case "edit":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Trip t = dao.getTripByID(idEdit);
                    request.setAttribute("tripEdit", t);
                    request.setAttribute("data", dao.getTripsByUserId(uid)); // Chỉ lấy trip của user này
                    request.getRequestDispatcher("list-trips.jsp").forward(request, response);
                    return;

                default:
                    // Mặc định hiện danh sách theo UID
                    List<Trip> list = dao.getTripsByUserId(uid);
                    request.setAttribute("data", list);
                    request.getRequestDispatcher("list-trips.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("trips?uid=" + uid);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String btn = request.getParameter("btnAction");
        String name = request.getParameter("tname");
        String sDateStr = request.getParameter("startDate");
        String eDateStr = request.getParameter("endDate");
        String idRaw = request.getParameter("tid");

        // 2. LẤY userID TỪ HIDDEN FIELD CỦA FORM
        String uidRaw = request.getParameter("uid");
        int uid = (uidRaw != null && !uidRaw.isEmpty()) ? Integer.parseInt(uidRaw) : 1;

        try {
            // KIỂM TRA RÀNG BUỘC LOGIC (Date)
            LocalDate startDate = LocalDate.parse(sDateStr);
            LocalDate endDate = LocalDate.parse(eDateStr);
            LocalDate today = LocalDate.now();

            if (startDate.isBefore(today)) {
                request.setAttribute("error", "Ngày bắt đầu không được ở quá khứ!");
                doGet(request, response);
                return;
            }

            if (endDate.isBefore(startDate)) {
                request.setAttribute("error", "Ngày kết thúc phải sau ngày bắt đầu!");
                doGet(request, response);
                return;
            }

            TripDAO dao = new TripDAO();
            if ("update".equals(btn)) {
                int tid = Integer.parseInt(idRaw);
                dao.updateTrip(tid, name, sDateStr, eDateStr);
            } else {
                // TRUYỀN uid VÀO HÀM ADD
                dao.addTrip(name, sDateStr, eDateStr, uid);
            }

            // 3. QUAY LẠI ĐÚNG TRANG CỦA USER ĐÓ
            response.sendRedirect("trips?uid=" + uid);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("trips?uid=" + uid);
        }
    }
}