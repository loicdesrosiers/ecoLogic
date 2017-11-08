<?php
if(isset($_GET['message'])) {
  $message = htmlspecialchars($_GET['message']);
  $alert = choixAlert($message);

} else {
  if(isset($_POST['addqcm'])) {
    header('Location: index.php?page=addqcm');
  } else if(isset($_POST['updateqcm'])) {
    header('Location: index.php?page=updateqcm');
  } 
}



  require_once(PATH_VIEWS.$page.'.php');
