<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

<%@ include file="partials/navbar.jsp" %>

<div class="container" style="margin-top: 40px;">

    <!-- ================= SUCCESS UI ================= -->
    <div style="text-align:center; margin-bottom:30px;">

        <div style="
            width:70px;
            height:70px;
            background:#28a745;
            color:white;
            border-radius:50%;
            display:flex;
            align-items:center;
            justify-content:center;
            font-size:35px;
            margin:0 auto;
        ">
            ✔
        </div>

        <h2 style="color:#28a745; margin-top:15px;">
            Order Placed Successfully
        </h2>

    </div>

    <c:if test="${not empty order}">

        <!-- ================= ORDER INFO ================= -->
        <div style="background:#1e1e1e; padding:20px; border-radius:10px; color:white;">

            <p><strong>Order ID:</strong> ${order.orderId}</p>
            <p><strong>Payment Method:</strong> ${order.paymentMethod}</p>
            <p><strong>Status:</strong> ${order.orderStatus}</p>
            <p><strong>Delivery Address:</strong> ${order.deliveryAddress}</p>

        </div>

        <!-- ================= ITEMS ================= -->
        <h3 style="margin-top:25px;">Order Details</h3>

        <table style="width:100%; border-collapse: collapse; margin-top: 10px; background:#1e1e1e;">

            <tr style="background:#111; color:white;">
                <th>Product</th>
                <th>Size</th>
                <th>Qty</th>
                <th>Price</th>
                <th>Subtotal</th>
            </tr>

            <c:set var="grandTotal" value="0"/>

            <c:forEach var="item" items="${orderItems}">

                <c:set var="itemTotal" value="${item.subtotal}"/>
                <c:set var="grandTotal" value="${grandTotal + itemTotal}"/>

                <tr style="text-align:center; border-bottom:1px solid #333; color:white;">
                    <td>${item.productName}</td>
                    <td>${item.sizeLabel}</td>
                    <td>${item.quantity}</td>
                    <td>₹ ${item.unitPrice}</td>
                    <td>₹ ${itemTotal}</td>
                </tr>

            </c:forEach>

        </table>

        <!-- ================= TOTAL ================= -->
        <div style="margin-top:20px; text-align:right; color:white;">
            <h3>Total Paid: ₹ ${grandTotal}</h3>
        </div>

        <!-- ================= ACTION BUTTONS ================= -->
        <div style="margin-top:30px; text-align:center;">

            <a href="${pageContext.request.contextPath}/products" 
               style="padding:10px 20px; background:#C71585; color:white; text-decoration:none; border-radius:5px; margin-right:10px;">
                Continue Shopping
            </a>

            <a href="${pageContext.request.contextPath}/my-orders" 
               style="padding:10px 20px; background:#8B0000; color:white; text-decoration:none; border-radius:5px;">
                View My Orders
            </a>

        </div>

    </c:if>

</div>

<%@ include file="partials/footer.jsp" %>