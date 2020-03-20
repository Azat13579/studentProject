<#import "parts/commonHistory.ftl" as c>

<@c.pageHistory>
    <script type="text/javascript">


        function doSend() {
            //var inputText = 'AAAAAAAAaaa';//$("#input_str").val();
            var iid = ${medHistories[0].id};
            $.ajax({
                url : 'getNeiroResponse',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data : ({
                    iid: iid
                }),
                success: function (data) {
                    $("#neiro" + iid).text(data.neiro_diagnose);
                    $("#accuracy" + iid).text(data.accuracy);
                    // $("#customCheck" + iid).checked="unchecked";
                }
            });
        }

    </script>

    <form action="/history" method="post">
        <table class="table">
            <thead>
            <tr class="table-secondary">
                <th scope="col">Дата обращения</th>
                <th scope="col">ID</th>
                <th scope="col">ФИО</th>
                <th scope="col">Нейро-диагноз</th>
                <th scope="col">Процент точности</th>
                <th scope="col">Картинка</th>
                <th scope="col">Комментарий</th>
                <th scope="col">Удалить</th>
                <th scope="col">Отправить на email</th>
            </tr>
            </thead>
            <tbody>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <#list medHistories as history>
                <tr>
                    <td>${history.date_visit}</td>
                    <td>${history.id}</td>
                    <td>${history.user}</td>
                    <td id="neiro${history.id}">
                        <#if history.neiro_diagtose != " ">
                            ${history.neiro_diagtose}
                        <#else>
                            <div class="spinner-border text-danger" role="status">
                                <span class="sr-only">Обработка нейросетью...</span>
                            </div>
                        </#if>
                    </td>
                    <td id="accuracy${history.id}">
                        <#if history.accuracy != " ">
                        ${history.accuracy}%
                    </td>
                    <#else>
                        <div class="spinner-border text-danger" role="status">
                            <span class="sr-only">Обработка нейросетью..</span>
                        </div>
                    </#if>
                    <td>
                        <img src="photo/${history.imgName}">
                    </td>
                    <td>
                        ${history.comment}
                    </td>
                    <td>
                        <div class="custom-control custom-switch">
                            <input type="checkbox" class="custom-control-input" name="customDel"
                                   id="customDel[${history.id}]" value="${history.id}">
                            <label class="custom-control-label" for="customDel[${history.id}]">Удалить</label>
                        </div>
                    </td>
                    <td>
                        <div class="custom-control custom-switch">
                            <input type="checkbox" class="custom-control-input" name="customMail"
                                   id="customMail[${history.id}]" value="${history.id}">
                            <label class="custom-control-label" for="customMail[${history.id}]">Отправить</label>
                        </div>
                    </td>
                </tr>
            </#list>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <#if medHistories?size == 0><b>Записей нет</b></#if>
                </td>
                <td></td>
                <td></td>
                <td>
                    <#if medHistories?size gt 0>
                    <input class="btn btn-danger btn-block" type="submit" name="deleteAction" value="Delete">
                </td>
                <td>
                    <input class="btn btn-primary btn-block" type="submit" name="sendEmail" value="Send email">
                    </#if>
                </td>

            </tr>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <input class="btn btn-primary btn-block" type="button" value="больницы!"
                           onClick="location.href='geoip'">
                </td>
            </tr>
            </tbody>
        </table>
    </form>

</@c.pageHistory>