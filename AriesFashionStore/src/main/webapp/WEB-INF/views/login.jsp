<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">

<div class="auth-container">

    <div class="auth-card">

        <!-- 🔥 FIXED: LOGIN TEXT WHITE -->
        <h2 style="color: #ffffff;">Login</h2>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <input type="email" name="email" placeholder="Email" required />

            <input type="password" name="password" placeholder="Password" required />

            <!-- 🔥 OPTIONAL: ALSO FIX BUTTON TEXT -->
            <button type="submit" style="color: #ffffff;">Login</button>

        </form>

        <p class="switch">
            Don’t have an account?
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </p>

    </div>

</div>