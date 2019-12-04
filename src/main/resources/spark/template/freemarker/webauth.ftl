<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>UBUlexa - Conecta tu cuenta</title>

  <style type="text/css"><#include "css/bulma.css"></style>
</head>

<body>
<section class="hero is-light is-fullheight">
  <div class="hero-body">
    <div class="container has-text-centered">
      <div class="column is-4 is-offset-4">
        <h3 class="title has-text-black">Bienvenido</h3>
        <hr class="login-hr">
        <p class="subtitle has-text-black">Por favor, introduce tus credenciales como miembro de la
          UBU.</p>

        <div class="box">
            <#if error??>
              <div class="notification is-danger">
                Datos err&oacute;neos. Por favor, int&eacute;ntelo otra vez.
              </div>
            </#if>

          <form action="webauth" method="post">
            <div class="field">
              <div class="control">
                <label>
                  <input class="input is-large" name="username" type="text"
                         placeholder="Nombre de Usuario">
                </label>
              </div>
            </div>

            <div class="field">
              <div class="control">
                <label>
                  <input class="input is-large" name="password" type="password"
                         placeholder="Contrase&ntilde;a">
                </label>
              </div>
            </div>

              <#if state??>
                <input name="state" type="hidden" value="${state}">
              </#if>

              <#if redirectUri??>
                <input name="redirectUri" type="hidden" value="${redirectUri}">
              </#if>

            <button class="button is-block is-info is-large is-fullwidth">
              Acceder
            </button>
          </form>

        </div>

        <div class="buttons is-centered">
          <a class="button is-text" href="privacypolicy" target="_blank">
            Pol&iacute;tica de Privacidad
          </a>
          <a class="button is-text" href="termsofuse" target="_blank">
            T&eacute;rminos de Uso
          </a>
        </div>
      </div>
    </div>
  </div>
</section>
</body>

</html>