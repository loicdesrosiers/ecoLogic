
<!DOCTYPE html>
<html>
	<head>
		<title><?= TITRE ?></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Language" content="<?= LANG ?>"/>
		<meta name="viewport" content="width=device-width; initial-scale=1; maximum-scale=1; user-scalable=0"/>

		<link href="<?= PATH_CSS ?>bootstrap.css" rel="stylesheet">
		<link href="<?= PATH_CSS ?>tab.css" rel="stylesheet">

		<script type="text/javascript" src="<?= PATH_SCRIPTS ?>boostrap.js"></script>
		<script type="text/javascript" src="<?= PATH_SCRIPTS ?>jquery-3.1.1.js"></script>
		<script type="text/javascript" src="<?= PATH_SCRIPTS ?>jquery.validate.min.js"></script>
		<script type="text/javascript" src="<?= PATH_SCRIPTS ?>monjs.js"></script>
	</head>
	<body>
		<!-- En-tete -->
		<header>
			<?php /*<section class="container" >
				<div class = "row">
					<div class = "col-md-2 col-sm-2 col-xs-12">
						<!--<img src="<?= PATH_LOGO ?>" class="img-circle">-->
					</div>
					<div class="col-md-10 col-sm-10 col-xs-12">
						<h2><?= TITRE ?></h2>
					</div>
				</div>
			</section>*/ ?>
		</header>
		<!-- Menu -->
		<?php include(PATH_VIEWS.'menu.php'); ?>
		<!-- Vue -->
