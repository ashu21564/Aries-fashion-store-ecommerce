<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/checkout.css">

<%@ include file="partials/navbar.jsp" %>

<div class="checkout-container">

    <!-- ✅ SUCCESS UI -->
    <c:if test="${orderSuccess}">

        <div class="success-box">

            <div class="checkmark-circle">
                <div class="background"></div>
                <div class="checkmark draw"></div>
            </div>

            <h2>Order Placed Successfully!</h2>
            <p>Your order has been confirmed 🎉</p>

            <a href="${pageContext.request.contextPath}/products" class="shop-btn">
                Continue Shopping
            </a>

        </div>

    </c:if>

    <!-- ✅ NORMAL CHECKOUT -->
    <c:if test="${not orderSuccess}">

        <h2 class="checkout-title">🧾 Checkout</h2>

        <c:if test="${empty cartItems}">
            <div class="checkout-empty">
                <h3>Your cart is empty</h3>
                <a href="${pageContext.request.contextPath}/products" class="shop-btn">
                    🛍️ Shop Now
                </a>
            </div>
        </c:if>

        <c:if test="${not empty cartItems}">

            <div class="checkout-layout">

                <!-- LEFT -->
                <div class="order-summary">

                    <h3>Order Summary</h3>

                    <c:set var="grandTotal" value="0" scope="page"/>

                    <c:forEach var="item" items="${cartItems}">
                        <c:set var="itemTotal" value="${item.quantity * item.unitPrice}" scope="page"/>
                        <c:set var="grandTotal" value="${grandTotal + itemTotal}" scope="page"/>

                        <div class="order-card">
                            <div class="order-info">
                                <h4>${item.productName}</h4>
                                <p>Size: ${item.sizeLabel}</p>
                                <p>Qty: ${item.quantity}</p>
                            </div>
                            <div class="order-price">
                                ₹ ${itemTotal}
                            </div>
                        </div>
                    </c:forEach>

                    <div class="order-total">
                        <span>Total</span>
                        <span>₹ ${grandTotal}</span>
                    </div>

                </div>

                <!-- RIGHT -->
                <div class="checkout-form-box">

                    <h3>Delivery Details</h3>

                    <form action="${pageContext.request.contextPath}/checkout" method="post" class="checkout-form">

                        <label>Delivery Address</label>
                        <textarea name="address" rows="4" required></textarea>

                        <label>Payment Method</label>
                        <select name="paymentMethod" required>
                            <option value="">Select</option>
                            <option value="COD">Cash on Delivery</option>
                            <option value="UPI">UPI</option>
                            <option value="CARD">Card</option>
                        </select>

                        <button type="submit" class="place-order-btn">
                            🚀 Place Order
                        </button>

                    </form>

                </div>

            </div>

        </c:if>

    </c:if>

</div>

<%@ include file="partials/footer.jsp" %>