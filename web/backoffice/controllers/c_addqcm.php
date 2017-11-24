<?php
if($_SESSION['logged'] == 1) {
require_once(PATH_MODELS.'QuestionDAO.php');

if(isset($_POST['intitule']) & isset($_POST['rep1']) & isset($_POST['rep2']) &
isset($_POST['rep3']) & isset($_POST['rep4']) & isset($_POST['bonnerep']) & isset($_POST['expl'])
& isset($_POST['score']) & isset($_POST['theme'])) {
  $intitule = htmlspecialchars($_POST['intitule']);
  $rep1 = htmlspecialchars($_POST['rep1']);
  $rep2 = htmlspecialchars($_POST['rep2']);
  $rep3 = htmlspecialchars($_POST['rep3']);
  $rep4 = htmlspecialchars($_POST['rep4']);
  $bonnerep = htmlspecialchars($_POST['bonnerep']);
  $explication = htmlspecialchars($_POST['expl']);
  $score = htmlspecialchars($_POST['score']);
  $theme = htmlspecialchars($_POST['theme']);

  if(!empty($intitule) & !empty($rep1) & !empty($rep2) & !empty($rep3) & !empty($rep4) & !empty($bonnerep) & !empty($explication) & !empty($score) & !empty($theme)) {
    $questionDAO = new QuestionDAO();
    $question = $questionDAO->addQuestion($score,$theme,$intitule,$rep1,$rep2,$rep3,$rep4,$bonnerep,$explication);
    if($question) {
      header('Location: index.php?page=tab&message=questiontrue');
      exit();
    } else {
      $alert = choixAlert('questionfalse');
    }

  } else {
    $alert = choixAlert('formvide');
  }


}
  require_once(PATH_VIEWS.$page.'.php');
} else {
  header('Location: index.php?page=accueil');
}
