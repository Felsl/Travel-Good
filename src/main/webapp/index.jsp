<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TravelGood - Quản lý chuyến đi</title>
</head>
<body>
    <h2>Quản lý kế hoạch chuyến đi - TravelGood</h2>

    <form action="trips" method="post">
        Tên chuyến đi: <input type="text" name="tname" required>
        <button type="submit">Lưu kế hoạch</button>
    </form>

    <hr>

   <table border="1" cellpadding="10">
           <tr>
               <th>ID</th>
               <th>Tên chuyến đi</th>
               <th>Trạng thái</th>
               <th>Hành động</th>
           </tr>
           <c:forEach items="${data}" var="t">
               <tr>
                   <td>${t.tripId}</td>
                   <td>${t.tripName}</td>
                   <td>${t.status}</td>
                   <td>
                       <c:if test="${t.status != 'Cancelled'}">
                           <a href="trips?action=cancel&id=${t.tripId}"
                              onclick="return confirm('Bạn có chắc muốn hủy chuyến này?')">Hủy</a>
                       </c:if>
                       |
                       <a href="trips?action=delete&id=${t.tripId}"
                          style="color:red;"
                          onclick="return confirm('Xóa vĩnh viễn chuyến này?')">Xóa</a>
                   </td>
               </tr>
           </c:forEach>
       </table>
</body>
</html>