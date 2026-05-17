<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/product-details.css">

<%@ include file="partials/navbar.jsp" %>

<div class="product-details-container">

    <c:if test="${not empty product}">

        <div class="product-details">

            <!-- ================= IMAGE ================= -->
            <div class="product-image">
                <img src="${pageContext.request.contextPath}/assets/images/${product.imageUrl}" 
                     alt="${product.productName}">
            </div>

            <!-- ================= DETAILS ================= -->
            <div class="product-info">

                <h2>${product.productName}</h2>

                <p class="description">${product.description}</p>

                <!-- 🔥 PRICE + DISCOUNT -->
                <div class="price-box">

                    <!-- ✅ PRICE ADDED -->
                    <span class="price">
                        ₹ ${product.price}
                    </span>

                    <!-- DISCOUNT -->
                    <span class="discount">
                        ${product.discountPercentage}% OFF
                    </span>

                </div>

                <!-- ================= ADD TO CART FORM ================= -->
                <form action="${pageContext.request.contextPath}/cart" method="post">

                    <input type="hidden" name="action" value="add"/>
                    <input type="hidden" name="productId" value="${product.productId}" />

                    <!-- SIZE -->
                    <div class="size-select">
                        <label>Select Size</label>

                        <select name="sizeLabel" required>
                            <c:forEach var="v" items="${variants}">
                                <option value="${v.sizeLabel}">
                                    ${v.sizeLabel} (Stock: ${v.stockQuantity})
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- BUTTON -->
                    <button type="submit" class="add-to-cart-btn">
                        🛒 Add to Cart
                    </button>

                </form>

            </div>

        </div>

    </c:if>

</div>

<%@ include file="partials/footer.jsp" %>