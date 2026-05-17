<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/products.css">

<%@ include file="partials/navbar.jsp" %>

<div class="products-container">

    <h2 class="page-title">Category Products</h2>

    <div class="products-layout">

        <!-- OPTIONAL: keep empty space to align UI like products page -->
        <div class="filter-panel" style="visibility:hidden;"></div>

        <!-- PRODUCT GRID -->
        <div class="product-section">

            <div class="products-grid">

                <c:if test="${not empty products}">
                    <c:forEach var="p" items="${products}">

                        <div class="product-card">

                            <div class="product-img">
                                <img src="${pageContext.request.contextPath}/assets/images/${p.imageUrl}" />

                                <span class="discount-badge">
                                    ${p.discountPercentage}% OFF
                                </span>
                            </div>

                            <div class="product-info">

                                <h3>${p.productName}</h3>

                                <a href="${pageContext.request.contextPath}/product-details?id=${p.productId}" 
                                   class="view-btn">
                                    View Product
                                </a>

                                <!-- ✅ ADD TO CART -->
                                <form action="${pageContext.request.contextPath}/cart" method="post">
                                    <input type="hidden" name="action" value="add"/>
                                    <input type="hidden" name="productId" value="${p.productId}"/>
                                    <input type="hidden" name="sizeLabel" value="M"/>

                                    <button type="submit" class="btn-apply" style="margin-top:10px;">
                                        Add to Cart
                                    </button>
                                </form>

                            </div>

                        </div>

                    </c:forEach>
                </c:if>

                <c:if test="${empty products}">
                    <div class="no-products">
                        <p>No products found 😢</p>
                    </div>
                </c:if>

            </div>

        </div>

    </div>

</div>

<%@ include file="partials/footer.jsp" %>