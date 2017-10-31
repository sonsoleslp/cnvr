<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<html>
<head>
  <title>CNVR</title>
  <style></style>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://bootswatch.com/cosmo/bootstrap.min.css" crossorigin="anonymous">
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>

  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">
          CNVR
        </a>
      </div>
    </div>
  </nav>
  <div class="container"> 

    <br>
    <div class="row">
      <div class="col-xs-12 col-sm-6 col-md-4">
        <a href="#" data-toggle="modal" data-target="#consultar">
          <div class="panel panel-primary">
            <div class="panel-heading">Consultar saldo</div>
          </div> 
        </a>
      </div>
      <div id="consultar" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <form method="post" action="balance">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Consultar saldo</h3>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label class="control-label" for="account">Número de cuenta</label>
                  <div class="input-group">
                    <input type="number" id="account" name="account" class="form-control firstinput" placeholder="00000000">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                  </div>

                </div>
              </div>  
              <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-info">Consultar saldo</button>
              </div>
            </form>
          </div>

        </div>
      </div>

      <div class="col-xs-12 col-sm-6 col-md-4">
        <a href="#" data-toggle="modal" data-target="#ingresar">
          <div class="panel panel-danger">
            <div class="panel-heading">Ingresar dinero</div>
          </div>
        </a>
      </div>
      <div id="ingresar" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <form method="post" action="efectivo">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Ingresar dinero</h3>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label class="control-label" for="account">Número de cuenta</label>
                  <div class="input-group">
                    <input type="number" id="account" name="account" class="form-control firstinput" placeholder="00000000">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                  </div>

                </div>
                <div class="form-group">
                  <label class="control-label" for="amount">Cantidad</label>
                  <div class="input-group">
                    <input type="number" id="amount" name="amount" class="form-control" placeholder="0">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-eur"></i></span>
                  </div>
                </div>
              </div>
              <input type="hidden" id="operation" name="operation" value="ingresar">

              <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-danger">Ingresar</button>
              </div>
            </form>
          </div>

        </div>
      </div>
      
      <div class="col-xs-12 col-sm-6 col-md-4">
        <a href="#" data-toggle="modal" data-target="#retirar">
          <div class="panel panel-danger">
            <div class="panel-heading">Retirar dinero</div>
          </div>
        </a>
      </div>
      <div id="retirar" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <form method="post" action="efectivo">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Retirar</h3>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label class="control-label" for="account">Número de cuenta</label>
                  <div class="input-group">
                    <input type="number" id="account" name="account" class="form-control firstinput" placeholder="00000000">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                  </div>

                </div>
                <div class="form-group">
                  <label class="control-label" for="amount">Cantidad</label>
                  <div class="input-group">
                    <input type="number" id="amount" name="amount" class="form-control" placeholder="0">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-eur"></i></span>
                  </div>
                </div>
                <input type="hidden" id="operation" name="operation" value="retirar">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-danger">Retirar</button>
              </div>
            </form>
          </div>

        </div>
      </div>

      <div class="col-xs-12 col-sm-6 col-md-4">
        <a href="#" data-toggle="modal" data-target="#crear">
          <div class="panel panel-success">
            <div class="panel-heading">Crear cuenta nueva</div>
          </div>
        </a>
      </div>
      <div id="crear" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <form method="post" action="crearcuenta">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Crear cuenta nueva</h3>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label class="control-label" for="username">Nombre del cliente</label>
                  <div class="input-group">
                    <input type="text" id="username" name="username" class="form-control firstinput" placeholder="">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                  </div>

                </div>
                <div class="form-group">
                  <label class="control-label" for="amount">Cantidad inicial</label>
                  <div class="input-group">
                    <input type="text" id="amount" name="amount" class="form-control" placeholder="0">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-eur"></i></span>
                  </div>
                </div>              
              </div>
              <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-success">Crear</button>
              </div>
            </form>
          </div>

        </div>
      </div>

      <div class="col-xs-12 col-sm-6 col-md-4">
        <a href="#" data-toggle="modal" data-target="#borrar">
          <div class="panel panel-info">
            <div class="panel-heading">Borrar cuenta</div>
          </div>
        </a>
      </div>
      <div id="borrar" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <form method="post" action="borrarcuenta">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Borrar</h3>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label class="control-label" for="account">Número de cuenta</label>
                  <div class="input-group">
                    <input type="number" id="account" name="account" class="form-control firstinput" placeholder="00000000">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-info">Borrar cuenta</button>
              </div>
            </form>
          </div>

        </div>
      </div>
      <div class="col-xs-12 col-sm-6 col-md-4">
        <a href="#" data-toggle="modal" data-target="#transferir">
          <div class="panel panel-warning" >
            <div class="panel-heading">Transferir</div>
          </div>
        </a>
      </div>
      <div id="transferir" class="modal fade" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <form method="post" action="transferencia">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Transferir</h3>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <label class="control-label" for="origin">Número de cuenta de origen</label>
                  <div class="input-group">
                    <input type="number" id="origin" name="origin" class="form-control firstinput" placeholder="00000000">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="target">Número de cuenta de destino</label>
                  <div class="input-group">
                    <input type="number" id="target" name="target" class="form-control " placeholder="00000000">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                  </div>

                </div>
                <div class="form-group">
                  <label class="control-label" for="amount">Cantidad</label>
                  <div class="input-group">
                    <input type="number" id="amount" name="amount" class="form-control" placeholder="0">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-eur"></i></span>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-info">Transferir</button>
              </div>
            </form>
          </div>

        </div>
      </div>
    </div>
  </div>

  <style>
  .panel-heading {
    min-height: 200px;
    line-height: 200px;
    text-align: center;
    font-size: 2em;

  }
  a:hover, a:focus {
    text-decoration: none;
    outline: none;
  }
  .panel:hover {
    opacity: 0.7;
  }
  </style>
  <script>
  $('.modal').on('shown.bs.modal', function(e) { 
	  $(e.target).find('.firstinput').focus()
	});
  console.log("<%= request.getAttribute("ip") %> ");
  </script>
</body>
</html>

