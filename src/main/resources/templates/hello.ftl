<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <h2 class="hello-title">Hello ${name}!</h2>
        <input type="file" name="file">
        <input type="submit" name="submit" value="UPLOAD FILE">
    </form>
    <script src="/js/main.js"></script>
</body>
</html>