<?php

require_once(PATH_ENTITY.'Question.php');
require_once(PATH_MODELS.'DAO.php');

class QuestionDAO extends DAO {
  private $_res;

  public function addQuestion($_intitule,$_rep1,$_rep2,$_rep3,$_rep4,$_goodrep,$_explication,$_score) {
    $sql = 'INSERT INTO questionsQcm(intitule,reponse1,reponse2,reponse3,reponse4,bonnereponse,explication,score) values (?,?,?,?,?,?,?,?)';
    $res = $this->queryBdd($sql,array($_intitule,$_rep1,$_rep2,$_rep3,$_rep4,$_goodrep,$_explication,$_score));
    if($res) {
      return true;
    } else {
      return false;
    }
  }
}
