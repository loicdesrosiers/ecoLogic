<?php
require_once(PATH_MODELS.'UtilisateurDAO.php');

$userDAO = new UtilisateurDAO(DEBUG);
if(isset($_POST['username']) and isset($_POST['password'])) {
  $username = htmlspecialchars($_POST['username']);
  $password = hash("sha256",htmlspecialchars($_POST['password']));

  $user = $userDAO->getCon($username,$password);
  if(!$user) {
    $alert = choixAlert('connexion_err');
    $page = "accueil";
  } else {
    $_SESSION['logged'] = 1;
		$alert = choixAlert('connexion_succ');
  }
}
  require_once(PATH_VIEWS.$page.'.php');


 ?>
