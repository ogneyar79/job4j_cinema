<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>

<script type="text/javascript">
    $(document).ready(function () {
        alert("33 afterSuccess")
        $.ajax({
            type: 'Get',
            url: 'http://localhost:8080/job4j_cinema/hall',
            dataType: 'json',
        }).done(function (data) {
            console.log(data)
            let arr = Array.from(data);
            console.log(" Array 42 Массив распарсили из data" + data)
            data.forEach(function (item, i) {
// if occupation = false = free else occupied
                let valueArray = Object.values(item);
                if (valueArray[1] === true) {
                    // console.log("valueArray[0] :" + valueArray[0]);
                    // let v = valueArray[0];
                    // console.log(typeof (v) + "V= :" + v);
                    let bubu = document.getElementsByTagName('input');
                    // console.log(" Длина массива" + bubu.length);
                    // bubu[v].setAttribute('disabled', 'disabled')
                    for (let i = 0; i < bubu.length; i++) {
                        if (bubu[i].value == valueArray[0]) {
                            bubu[i].setAttribute('disabled', 'disabled');
                        }
                    }
                    $(`#${valueArray[0]}`).text(` Место ${valueArray[0]} OCCUPIED`).css("background-color", "#ff0000")
                    $(`#${valueArray[0]}`).parent().css("background-color", "#000000")
                } else {
                    $(`#${valueArray[0]}`).text(` Место ${valueArray[0]} FREE`).css("background-color", "#7FFF00")
                }
            })
        }).fail(function (err) {
            alert("Fail 55 " + err)
        })
    })

    function choosePlace() {
        let value = $('input[name="place"]:checked').val();
        console.log(value);
        if (value === undefined) {
            return false
        }
        console.log(true);
        return true;
    }
</script>
<div class="container">
    <form method="post" action="<%=request.getContextPath()%>/pay?dateSession=December 24, 2020">
        <div class="row pt-3">
            <h4>
                Бронирование месте на сеанс 2020.02.07 в 15:30:00
            </h4>
            <table class="table table-bordered">
                <tr>
                    <th style="width: 120px;">Ряд / Место</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>1</th>
                    <td><input type="radio" name="place" value="1"> <label id="1">Место 1 FREE </label></td>
                    <td><input type="radio" name="place" value="2"> <label id="2">Место 2 FREE </label></td>
                    <td><input type="radio" name="place" value="3"> <label id="3">Место 3 FREE </label></td>
                    <td><input type="radio" name="place" value="4"> <label id="4">Место 4 FREE </label></td>
                    <td><input type="radio" name="place" value="5"> <label id="5">Место 5 FREE </label></td>
                    <td><input type="radio" name="place" value="6"> <label id="6">Место 6 FREE </label></td>
                </tr>
                <tr>
                    <th>2</th>
                    <td><input type="radio" name="place" value="7"> <label id="7">Место 7 FREE </label></td>
                    <td><input type="radio" name="place" value="8"> <label id="8">Место 8 FREE </label></td>
                    <td><input type="radio" name="place" value="9"> <label id="9">Место 9 FREE </label></td>
                    <td><input type="radio" name="place" value="10"> <label id="10">Место 10 FREE </label></td>
                    <td><input type="radio" name="place" value="11"> <label id="11">Место 11 FREE </label></td>
                    <td><input type="radio" name="place" value="12"> <label id="12">Место 12 FREE </label></td>
                </tr>
                <tr>
                    <th>3</th>
                    <td><input type="radio" name="place" value="13"> <label id="13">Место 13 FREE </label></td>
                    <td><input type="radio" name="place" value="14"> <label id="14">Место 14 FREE </label></td>
                    <td><input type="radio" name="place" value="15"> <label id="15">Место 15 FREE </label></td>
                    <td><input type="radio" name="place" value="16"> <label id="16">Место 16 FREE </label></td>
                    <td><input type="radio" name="place" value="17"> <label id="17">Место 17 FREE </label></td>
                    <td><input type="radio" name="place" value="18"> <label id="18">Место 18 FREE </label></td>
                </tr>
                <tr>
                    <th>4</th>
                    <td><input type="radio" name="place" value="19"> <label id="19">Место 19 FREE </label></td>
                    <td><input type="radio" name="place" value="20"> <label id="20">Место 20 FREE </label></td>
                    <td><input type="radio" name="place" value="21"> <label id="21">Место 21 FREE </label></td>
                    <td><input type="radio" name="place" value="22"> <label id="22">Место 22 FREE </label></td>
                    <td><input type="radio" name="place" value="23"> <label id="23">Место 23 FREE </label></td>
                    <td><input type="radio" name="place" value="24"> <label id="24">Место 24 FREE </label></td>
                </tr>
                <tr>
                    <th>5</th>
                    <td><input type="radio" name="place" value="25"> <label id="25">Место 25 FREE </label></td>
                    <td><input type="radio" name="place" value="26"> <label id="26">Место 26 FREE </label></td>
                    <td><input type="radio" name="place" value="27"> <label id="27">Место 27 FREE </label></td>
                    <td><input type="radio" name="place" value="28"> <label id="28">Место 28 FREE </label></td>
                    <td><input type="radio" name="place" value="29"> <label id="29">Место 29 FREE </label></td>
                    <td><input type="radio" name="place" value="30"> <label id="30">Место 30 FREE </label></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row float-right">
            <button type="submit" class="btn btn-primary" onclick="return choosePlace()">Оплатить</button>
        </div>
    </form>
</div>
</body>
</html>