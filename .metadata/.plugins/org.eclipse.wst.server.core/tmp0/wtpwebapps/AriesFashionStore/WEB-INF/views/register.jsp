<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">

<div class="auth-container">

    <div class="auth-card">

        <!-- 🔥 FIXED: WHITE HEADING -->
        <h2 style="color: #ffffff;">Create Account</h2>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">

            <input type="text" name="fullName" placeholder="Full Name" required />

            <input type="email" name="email" placeholder="Email" required />

            <input type="text" name="phone" placeholder="Phone" required />

            <input type="password" name="password" placeholder="Password" required />

            <select name="gender">
                <option value="">Select Gender</option>
                <option>Male</option>
                <option>Female</option>
            </select>

            <textarea name="address" placeholder="Address"></textarea>

            <!-- 🔥 FIXED: BUTTON TEXT WHITE -->
            <button type="submit" style="color: #ffffff;">Register</button>

        </form>

        <p class="switch">
            Already have an account?
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </p>

    </div>

</div>