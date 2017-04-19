<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Error</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row mt20 mb20 mobile-padding">
			<div class="desktop6 mobile12">
				<div class="desktop-show pt50"></div>
				<h2 class="f70 rosa bold error-title phone-texto-centrado tablet-texto-centrado">Lo sentimos</h2>
				<p class="f25 phone-texto-centrado tablet-texto-centrado">La página a la que deseas acceder, no existe.</p>
<!-- 				<p class="phone-texto-centrado tablet-texto-centrado">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec -->
<!-- 					odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis -->
<!-- 					ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta.</p> -->

				<div class="desktop5 tablet4 phone9 tablet-centered phone-centered">
					<a href="https://www.izzi.mx/" title="Regresar a izzi" class="btn-regresar-home mid mt20">Regresar a <strong>izzi</strong></a>
				</div>
			</div>
			<div class="tablet3"></div>
			<div class="desktop6 tablet6 phone12">
				<img src="/unity/img/img-error.jpg" alt="Lo sentimos, la página no existe" class="img-auto right img-error" />
			</div>
		</div>
	</div>
</body>
</html>