<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 12.12.2020
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
    <script>
        function validate() {
            alert('function validate')
            let firstName = $('#firstname').val();
            let lastName = $('#lastname').val();
            let phone = $('#phone').val();

            let letters = /^[A-Za-z]+$/;
            let regexPhone = /^(\+7|7|8)?[\s\-]?\(?[489][0-9]{2}\)?[\s\-]?[0-9]{3}[\s\-]?[0-9]{2}[\s\-]?[0-9]{2}$/

            if (!firstName.match(letters) || !lastName.match(letters)) {
                console.log(firstName)
                console.log(lastName)
                alert('First name or Last name  must have alphabetcharactersonly');
                return false
            }
            if (!regexPhone.test(phone)) {
                console.log(phone);
                alert('Phone number incorrect ');
                return false
            }
            return true;
        }
    </script>

</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<div class="container">
    <div class="row pt-3">
        <h3>
            Вы выбрали <c:out value="${pN}"/> место on <c:out value="${data}"/>
            Сумма : 500 рублей.
        </h3>
    </div>
    <div class="row">
        <form method="post" action="<c:url value="/complete?place=${pN}&data=${data}"/>">
            <div class="form-group">
                <label for="firstname">FIRST NAME</label>
                <input type="text" class="form-control" name="firstName" id="firstname" placeholder="FIRST NAME">
            </div>
            <div class="form-group">
                <label for="lastname">LAST NAME</label>
                <input type="text" class="form-control" name="lastName" id="lastname" placeholder="LAST NAME">
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" class="form-control" name="phone" id="phone" placeholder="Номер телефона">
            </div>
            <button onclick="validate()" type="submit" class="btn btn-success">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>
