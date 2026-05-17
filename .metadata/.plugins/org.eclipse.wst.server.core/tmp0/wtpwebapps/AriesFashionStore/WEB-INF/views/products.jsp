<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/products.css">

<%@ include file="partials/navbar.jsp" %>

<div class="products-container">

    <h2 class="page-title">Explore Our Fashion Collection</h2>

    <div class="products-layout">

        <!-- ===== FILTER SIDEBAR ===== -->
        <div class="filter-panel">

            <form method="get" action="${pageContext.request.contextPath}/products">

                <h3>Filters</h3>

                <!-- SEARCH -->
                <div class="filter-group">
                    <label>Search</label>
                    <input type="text" name="keyword" placeholder="Search products..." />
                </div>

                <!-- CATEGORY -->
                <div class="filter-group">
                    <label>Category</label>
                    <select name="categoryId">
                        <option value="">All</option>
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.categoryId}">
                                ${c.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- PRICE -->
                <div class="filter-group">
                    <label>Min Price</label>
                    <input type="number" name="minPrice" placeholder="0"/>
                </div>

                <div class="filter-group">
                    <label>Max Price</label>
                    <input type="number" name="maxPrice" placeholder="5000"/>
                </div>

                <!-- SORT -->
                <div class="filter-group">
                    <label>Sort By</label>
                    <select name="sort">
                        <option value="">Default</option>
                        <option value="asc">Price Low → High</option>
                        <option value="desc">Price High → Low</option>
                    </select>
                </div>

                <div class="filter-actions">
                    <button type="submit" class="btn-apply">Apply</button>
                    <a href="${pageContext.request.contextPath}/products" class="btn-clear">Clear</a>
                </div>

            </form>

        </div>

        <!-- ===== PRODUCT GRID ===== -->
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