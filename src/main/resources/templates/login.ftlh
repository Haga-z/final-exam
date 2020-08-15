<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-center">
        <div class="mx-3">
            <form action="/login" method="post">
                <h3 class="text-center">Login</h3>
                <div>
                    <#if error!false >
                        <p style="color:red">Неверные имя пользователя или пароль !</p>
                    </#if>
                </div>
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <input type="text" name="username" class="form-control mb-1" placeholder="Login"
                       value="${(dto.email)!''}">
                <input type="password" name="password" class="form-control mb-1" placeholder="Password" value="">
                <button type="submit" class="btn btn-outline-primary btn-block">Sign In</button>
            </form>
        </div>
    </div>
    <div class="bottom-box">
        <a href="/registration">
            <button type="button" class="btn btn-outline-primary " id="btn-login">Registration</button>
        </a>
    </div>
</div>
</body>
</html>