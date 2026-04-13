<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Kế hoạch Chuyến đi - TravelGood</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        body { font-family: 'Segoe UI', system-ui, sans-serif; }
    </style>
</head>
<body class="bg-gray-50 min-h-screen">

<div class="max-w-5xl mx-auto py-10 px-6">

    <div class="flex items-center justify-between mb-8">
        <div>
            <h1 class="text-3xl font-bold text-gray-800 flex items-center gap-3">
                <i class="fas fa-plane text-blue-600"></i>
                Quản lý Kế hoạch Chuyến đi
            </h1>
            <p class="text-gray-500 mt-1">TravelGood - Lập kế hoạch và quản lý chuyến đi của bạn</p>
        </div>
        <a href="trips"
           class="flex items-center gap-2 px-5 py-2.5 bg-white border border-gray-300 rounded-xl hover:bg-gray-50 transition text-sm font-medium">
            <i class="fas fa-rotate"></i>
            Làm mới danh sách
        </a>
    </div>

    <div class="bg-white rounded-3xl shadow-sm border border-gray-100 p-8 mb-10">
        <h2 class="text-xl font-semibold text-gray-700 mb-6 flex items-center gap-2">
            <i class="fas ${tripEdit != null ? 'fa-pen-to-square text-orange-500' : 'fa-plus-circle text-blue-600'}"></i>
            ${tripEdit != null ? 'Chỉnh sửa kế hoạch' : 'Tạo kế hoạch chuyến đi mới'}
        </h2>

        <form action="trips" method="post" class="space-y-6">
            <input type="hidden" name="tid" value="${tripEdit.tripId}">

            <div>
                <label class="block text-sm font-medium text-gray-600 mb-2">Tên chuyến đi</label>
                <input type="text" name="tname" value="${tripEdit.tripName}" required
                       placeholder="Ví dụ: Du lịch hè Đà Lạt 2026"
                       class="w-full px-5 py-3 border border-gray-300 rounded-2xl focus:outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-200 transition">
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                    <label class="block text-sm font-medium text-gray-600 mb-2">Ngày bắt đầu</label>
                    <input type="date" name="startDate" value="${tripEdit.startDate}"
                           class="w-full px-5 py-3 border border-gray-300 rounded-2xl focus:outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-200 transition">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-600 mb-2">Ngày kết thúc</label>
                    <input type="date" name="endDate" value="${tripEdit.endDate}"
                           class="w-full px-5 py-3 border border-gray-300 rounded-2xl focus:outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-200 transition">
                </div>
            </div>

            <div class="flex gap-4 pt-4">
                <c:choose>
                    <c:when test="${tripEdit != null}">
                        <button type="submit" name="btnAction" value="update"
                                class="flex-1 bg-orange-500 hover:bg-orange-600 text-white font-medium py-3.5 rounded-2xl transition flex items-center justify-center gap-2 shadow-sm">
                            <i class="fas fa-save"></i> Cập nhật kế hoạch
                        </button>
                        <a href="trips" class="flex-1 bg-gray-100 hover:bg-gray-200 text-gray-700 font-medium py-3.5 rounded-2xl transition text-center">
                            Hủy bỏ
                        </a>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="btnAction" value="add"
                                class="w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-3.5 rounded-2xl transition flex items-center justify-center gap-2 shadow-lg shadow-blue-100">
                            <i class="fas fa-paper-plane"></i> Lưu kế hoạch mới
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>

    <div class="bg-white rounded-3xl shadow-sm border border-gray-100 overflow-hidden">
        <div class="px-8 py-5 border-b border-gray-100 bg-gray-50 flex items-center justify-between">
            <h2 class="text-xl font-semibold text-gray-700 text-sm">Danh sách kế hoạch</h2>
            <span class="px-3 py-1 bg-blue-100 text-blue-700 rounded-lg text-xs font-bold uppercase">${data.size()} chuyến đi</span>
        </div>

        <div class="overflow-x-auto">
            <table class="w-full border-collapse">
                <thead>
                    <tr class="bg-white border-b border-gray-200">
                        <th class="px-8 py-4 text-left text-xs font-bold text-gray-400 uppercase tracking-wider">ID</th>
                        <th class="px-8 py-4 text-left text-xs font-bold text-gray-400 uppercase tracking-wider">Chuyến đi</th>
                        <th class="px-8 py-4 text-left text-xs font-bold text-gray-400 uppercase tracking-wider">Thời gian</th>
                        <th class="px-8 py-4 text-left text-xs font-bold text-gray-400 uppercase tracking-wider">Trạng thái</th>
                        <th class="px-8 py-4 text-center text-xs font-bold text-gray-400 uppercase tracking-wider">Thao tác</th>
                    </tr>
                </thead>
                <tbody class="divide-y divide-gray-100">
                    <c:forEach items="${data}" var="t">
                        <tr class="hover:bg-gray-50 transition group">
                            <td class="px-8 py-5 text-gray-400 text-sm italic">#${t.tripId}</td>
                            <td class="px-8 py-5">
                                <div class="font-bold text-gray-800 group-hover:text-blue-600 transition">${t.tripName}</div>
                                <div class="text-xs text-gray-400">Tạo lúc: ${t.createdAt}</div>
                            </td>
                            <td class="px-8 py-5 text-sm text-gray-600">
                                <div class="flex items-center gap-2">
                                    <i class="far fa-calendar-alt text-gray-300"></i>
                                    <span>${t.startDate} → ${t.endDate}</span>
                                </div>
                            </td>
                            <td class="px-8 py-5">
                                <span class="inline-flex items-center px-3 py-1 rounded-full text-xs font-bold uppercase tracking-tighter
                                    ${t.status == 'Confirmed' ? 'bg-emerald-100 text-emerald-700' :
                                      t.status == 'Draft' ? 'bg-blue-100 text-blue-700' : 'bg-red-100 text-red-700'}">
                                    <i class="fas fa-circle text-[6px] mr-2"></i> ${t.status}
                                </span>
                            </td>
                            <td class="px-8 py-5 text-center">
                                <div class="flex items-center justify-center gap-3">
                                    <a href="trips?action=edit&id=${t.tripId}" title="Chỉnh sửa"
                                       class="w-9 h-9 flex items-center justify-center rounded-xl bg-blue-50 text-blue-600 hover:bg-blue-600 hover:text-white transition">
                                        <i class="fas fa-edit"></i>
                                    </a>
                                    <a href="trips?action=cancel&id=${t.tripId}" title="Hủy chuyến"
                                       onclick="return confirm('Bạn chắc chắn muốn hủy chuyến đi này?')"
                                       class="w-9 h-9 flex items-center justify-center rounded-xl bg-amber-50 text-amber-600 hover:bg-amber-600 hover:text-white transition">
                                        <i class="fas fa-ban"></i>
                                    </a>
                                    <a href="trips?action=delete&id=${t.tripId}" title="Xóa vĩnh viễn"
                                       onclick="return confirm('Xóa vĩnh viễn chuyến đi này?')"
                                       class="w-9 h-9 flex items-center justify-center rounded-xl bg-red-50 text-red-600 hover:bg-red-600 hover:text-white transition">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${empty data}">
            <div class="py-20 text-center text-gray-300 bg-gray-50/50">
                <i class="fas fa-plane-arrival text-6xl mb-4 opacity-20"></i>
                <p class="text-lg font-medium">Bầu trời đang trống trải...</p>
                <p class="text-sm italic">Hãy tạo một kế hoạch du lịch mới ngay!</p>
            </div>
        </c:if>
    </div>

</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const startDateInput = document.querySelector('input[name="startDate"]');
        const endDateInput = document.querySelector('input[name="endDate"]');

        // 1. Ràng buộc: Ngày bắt đầu >= Ngày hiện tại
        const today = new Date().toISOString().split('T')[0];
        startDateInput.setAttribute('min', today);

        // 2. Ràng buộc: Ngày kết thúc phải sau Ngày bắt đầu
        startDateInput.addEventListener('change', function() {
            if (startDateInput.value) {
                // Khi chọn ngày bắt đầu, ngày kết thúc tối thiểu phải là ngày đó
                endDateInput.setAttribute('min', startDateInput.value);
            }
        });
    });
</script>
</body>
</html>