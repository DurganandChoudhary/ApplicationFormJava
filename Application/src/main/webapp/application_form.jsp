<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>College Application Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        form {
            width: 40%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            text-align: left;
            margin-top: 10px;
        }
        input, select, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
        }
        .radio-group {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <h2>College Application Form</h2>
    <form action="ApplicationServlet" method="post" onsubmit="return validateForm()">
        <label>Full Name:</label>
        <input type="text" name="fullName" id="fullName" required>

        <label>Email:</label>
        <input type="email" name="email" id="email" required>

        <label>Phone Number:</label>
        <input type="text" name="phone" id="phone" required>

        <label>Date of Birth:</label>
        <input type="date" name="dob" id="dob" required>

        <label>Gender:</label>
        <div class="radio-group">
            <input type="radio" name="gender" value="Male" required> Male
            <input type="radio" name="gender" value="Female" required> Female
        </div>

        <label>Address:</label>
        <textarea name="address" id="address" required></textarea>

        <label>Course Applied For:</label>
        <select name="course" id="course" required>
            <option value="BSc Computer Science">BSc Computer Science</option>
            <option value="BBA">BBA</option>
            <option value="BCom">BCom</option>
            <option value="BA English">BA English</option>
        </select>

        <label>Password:</label>
        <input type="password" name="password" id="password" required>

        <label>Confirm Password:</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required>

        <input type="submit" value="Submit">
    </form>

    <script>
        function validateForm() {
            var phone = document.getElementById("phone").value;
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            var phoneRegex = /^[0-9]{10}$/;
            if (!phoneRegex.test(phone)) {
                alert("Enter a valid 10-digit phone number!");
                return false;
            }

            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                alert("Enter a valid email address!");
                return false;
            }

            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return false;
            }

            if (password.length < 6) {
                alert("Password must be at least 6 characters long!");
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
