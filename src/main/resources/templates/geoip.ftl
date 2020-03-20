<!DOCTYPE html>
<html lang="en">
<head>
    <title>Geo Position</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        var vvv;
        $(document).ready(function () {
            $.get("https://api.ipify.org?format=json", function (data) {
                $("#ip").val(data.ip);
                vvv = data.ip;
               console.log(vvv);
                postRequest();
            });

            $.post({
                url: '/geoip',
                data: {'_token': $('meta[name="csrf-token"]').attr('content'), ipAddress: vvv}
            }).done(function (data) {
                alert('Успешно! ' + data);
            });
            /*function postRequest(){
                $.ajax({
                    url: "geoip",
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    data: ({ipAddress: $("#ip").val()}),
                   /!* success: function (data) {
                        $("#status").html(data);
                    },*!/
                });
            };*/
        });

    </script>



</head>
<body >
<h3>Введите свой адрес проживания:</h3>
<form id="ipForm"  action="geoip" method="POST">
    <input type="hidden" name="ipAddress" id="ip"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="text" name="Адрес проживания:" />
    <input type="hidden" />
    <input type="submit" name="submit" value=" ближайшие больницы!">
</form>

<div id="status"></div>
<div id="map" style="height: 500px; width:100%; position:absolute"></div>

</body>
</html>