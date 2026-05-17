<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

<%@ include file="partials/navbar.jsp" %>

<div class="container" style="margin-top: 40px;">

    <h2>My Orders</h2>

    <!-- ❌ NO ORDERS -->
    <c:if test="${empty orders}">
        <p>You have not placed any orders yet.</p>
        <a href="${pageContext.request.contextPath}/products" class="btn">Start Shopping</a>
    </c:if>

    <!-- ✅ ORDERS LIST -->
    <c:if test="${not empty orders}">

        <table style="width:100%; border-collapse: collapse; margin-top: 20px;">

            <tr style="background:#111; color:white;">
                <th>Order ID</th>
                <th>Date</th>
                <th>Total Amount</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="order" items="${orders}">

                <tr style="text-align:center; border-bottom:1px solid #ccc;">

                    <td>${order.orderId}</td>

                    <td>${order.orderDate}</td>

                    <td>₹ ${order.totalAmount}</td>

                    <td>${order.orderStatus}</td>

                    <!-- 🔍 VIEW DETAILS -->
                    <td>
                        <a href="${pageContext.request.contextPath}/order-confirmation?id=${order.orderId}">
                            View
                        </a>
                    </td>

                </tr>

            </c:forEach>

        </table>

    </c:if>

</div>

<%@ include file="partials/footer.jsp" %>