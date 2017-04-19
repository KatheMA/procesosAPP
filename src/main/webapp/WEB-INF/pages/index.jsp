<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Intranet Procesos</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<header>
		<div class="container">
			<img src="images/izzi_logo_white2x.png" alt="logo izzi"
				class="pull-left" />
			<h1 class="text-center">BBBBBienvenidos a intranet de procesos</h1>
		</div>
	</header>
	<section id="search">
		<div class="container">

			<fieldset class="field-container">
				<input type="text" placeholder="Buscar... " class="field" />
				<div class="icon-search material-icons">search</div>
			</fieldset>
		</div>
	</section>

	<section id="monthly-items">
		<div class="container">
			<div id="carrousel">
				<p>Últimos documentos del mes de Octubre</p>
				<i id="left-arrow" class="material-icons">keyboard_arrow_left</i> <i
					id="right-arrow" class="material-icons">keyboard_arrow_right</i>
				<div id="inner-carrousel">
					<div class="file text-center">
						<i class="material-icons">insert_drive_file</i>
						<p>Formato para evaluacion de ventas y personal v(1).pdf</p>
					</div>
					<div class="file text-center">
						<i class="material-icons">insert_drive_file</i>
						<p>File 2.pdf</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="contenedor-files"></section>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>