<?php
if($_SESSION['logged'] == 1) {
  require_once(PATH_MODELS.'QuestionDAO.php');
  $questionDAO = new QuestionDAO();
  $question = $questionDAO->getAllQuestion();
  if(!$question) {
    $alert = choixAlert('pb_select_qcm');
  } else {
    if(isset($_POST['idQuestion'])) {
      $id = htmlspecialchars($_POST['idQuestion']);
      $del = $questionDAO->deleteQuestion($id);
      if($del) {
        header('Location: index.php?page=tab&message=delok');
      } else {
        $alert = choixAlert('delpasok');
      }
    }

  }

  require_once(PATH_VIEWS.$page.'.php');
} else {
  header('Location: index.php?page=accueil');
}
