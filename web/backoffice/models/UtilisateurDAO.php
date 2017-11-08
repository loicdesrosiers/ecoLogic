<?php

require_once(PATH_ENTITY.'Utilisateur.php');
require_once(PATH_MODELS.'DAO.php');
class UtilisateurDAO extends DAO
{
    private $_res;

    public function getCon($_username, $_password) {
      $res = $this->queryRow('select * from utilisateur where username = :username and password = :pwd',array(
        'username' => $_username,
        'pwd' => $_password));
      if($res) {
        $user = new Utilisateur($res['username'],$res['password']);
        return $user;
      } else {
        return false;
      }
    }
	}
