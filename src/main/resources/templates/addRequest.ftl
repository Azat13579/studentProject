<#import "parts/common.ftl" as c>

<@c.page>
    <form method="post" enctype="multipart/form-data">
        <#if name??>
            ${name}
            <img src="/img/${imgname}">
        </#if>
        <#if comment??>
            ${comment}
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="container">
            <input type="file" name="file">
            <input type="submit" name="submit" value="Проверить с помощью нейросети">
        </div>
        <div class="container mt-5">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">Комментарий <br> не более 250 символов</span>
                </div>
                <textarea class="form-control" aria-label="With textarea" name="comment" ></textarea>
            </div>
        </div>
    </form>
</@c.page>