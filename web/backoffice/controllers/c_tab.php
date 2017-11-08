<?php
if($_SESSION['logged'] == 1) {
  if(isset($_GET['message'])) {
    $message = htmlspecialchars($_GET['message']);
    $alert = choixAlert($message);

  } else {
    if(isset($_POST['addqcm'])) {
      header('Location: index.php?page=addqcm');
    } else if(isset($_POST['updateqcm'])) {
      header('Location: index.php?page=updateqcm');
    } else if(isset($_POST['deleteqcm'])) {
      header('Location: index.php?page=deleteqcm');
    }
  }



    require_once(PATH_VIEWS.$page.'.php');

} else {
  if(isset($_POST['username']) and isset($_POST['password'])) {
    require_once(PATH_MODELS.'UtilisateurDAO.php');

    $userDAO = new UtilisateurDAO(DEBUG);
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
    require_once(PATH_VIEWS.$page.'.php');
  } else {
    header('Location: index.php?page=accueil');
  }

}
