<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>UBUlexa - Account Linking</title>

  <style type="text/css"><#include "css/bulma.css"></style>
</head>

<body>
<section class="hero is-light is-fullheight">
  <div class="hero-body">
    <div class="container has-text-centered">
      <div class="column is-4 is-offset-4">
        <h3 class="title has-text-black">Bienvenido</h3>
        <hr class="login-hr">
        <div class="box">
          <form action="webauth" method="post">
            <div class="field">
              <div class="control">
                <label>
                  <input class="input is-large" name="username" type="text" placeholder="Nombre de Usuario">
                </label>
              </div>
            </div>

            <div class="field">
              <div class="control">
                <label>
                  <input class="input is-large" name="password" type="password" placeholder="Contraseña">
                </label>
              </div>
            </div>

            <button class="button is-block is-info is-large is-fullwidth">
              Acceder
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>
</body>

</html>