<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="navbar">

    <div class="container navbar-container">

        <!-- LOGO -->
        <div class="logo">
            <a href="<c:url value='/home' />">Aries Fashion Store</a>
        </div>

        <!-- SEARCH BAR -->
        <form class="search-bar" action="<c:url value='/products' />" method="get">
            <input type="text" name="keyword" placeholder="Search for Aries fashion products..." />
            <button type="submit">Search</button>
        </form>

        <!-- NAV LINKS -->
        <nav class="nav-links">
            <a href="<c:url value='/home' />">Home</a>
            <a href="<c:url value='/products' />">Products</a>
            <a href="<c:url value='/cart' />">Cart</a>
            <a href="<c:url value='/login' />">Login</a>
        </nav>

    </div>

</header>