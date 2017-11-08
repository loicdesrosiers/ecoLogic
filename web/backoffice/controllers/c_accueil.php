<?php
if($_SESSION['logged'] == 1) {
  header('Location: index.php?page=tab');
} else {
    require_once(PATH_VIEWS.$page.'.php');
}



 ?>
