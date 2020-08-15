!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-center">
        <form action="/registration" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <h3>Registration</h3>
            <input type="email" name="email" class="form-control mb-1" placeholder="Email" value="${(dto.email)!''}">
            <input type="text" name="name" class="form-control mb-1" placeholder="Login" value="${(dto.name)!''}">
            <input type="password" name="password" class="form-control mb-1" placeholder="Password" value="">
            <button class="btn btn-outline-primary btn-block" type="submit">Register</button>
            <#if errors?? >
                <p>Входные данные с ошибками!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
    </div>
    </form>
</div>
</div>
<div class="bottom-box">
    <a href="/">
        <button type="button" class="btn btn-outline-primary " id="btn-login">Main Page</button>
    </a>
</div>
</body>
</html>
<script src="/js/main.js"></script>