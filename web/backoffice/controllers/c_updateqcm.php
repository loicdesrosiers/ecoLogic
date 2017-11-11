<?php
if($_SESSION['logged'] == 1) {
  require_once(PATH_MODELS.'QuestionDAO.php');
  $questionDAO = new QuestionDAO();
  $question = $questionDAO->getAllQuestion();
  if(!$question) {
    $alert = choixAlert('pb_select_qcm');
  } else {
    

  }
  require_once(PATH_VIEWS.$page.'.php');
} else {
  header('Location: index.php?page=accueil');
}
