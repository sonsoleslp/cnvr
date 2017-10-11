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
    <!-- <div class="input-group">
      <span class="input-group-addon">$</span>
      <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
      <span class="input-group-addon">.00</span>
    </div> -->
    <!-- <div class="input-group">
      <input type="text" class="form-control" aria-label="Client id">
    </div> -->
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
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Consultar saldo</h4>
            </div>
            <div class="modal-body">
              <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
              <button type="button" class="btn btn-primary" data-dismiss="modal">Cancelar</button>
            </div>
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
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Ingresar dinero</h4>
            </div>
            <div class="modal-body">
              <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">Aceptar</button>
              <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
            </div>
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
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Retirar</h4>
            </div>
            <div class="modal-body">
              <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">Aceptar</button>
              <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
            </div>
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
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Crear cuenta nueva</h4>
            </div>
            <div class="modal-body">
              <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-success" data-dismiss="modal">Aceptar</button>
              <button type="button" class="btn btn-success" data-dismiss="modal">Cancelar</button>
            </div>
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
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Borrar</h4>
            </div>
            <div class="modal-body">
              <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-info" data-dismiss="modal">Aceptar</button>
              <button type="button" class="btn btn-info" data-dismiss="modal">Cancelar</button>
            </div>
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
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Transferir</h4>
            </div>
            <div class="modal-body">
              <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-warning" data-dismiss="modal">Aceptar</button>
              <button type="button" class="btn btn-warning" data-dismiss="modal">Cancelar</button>
            </div>
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
</body>
</html>

