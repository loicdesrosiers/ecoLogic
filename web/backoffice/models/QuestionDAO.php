<?php

require_once(PATH_ENTITY.'Question.php');
require_once(PATH_MODELS.'DAO.php');

class QuestionDAO extends DAO {
  private $_res;

  public function addQuestion($_score,$_theme,$_intitule,$_rep1,$_rep2,$_rep3,$_rep4,$_goodrep,$_explication) {
    $sql = 'INSERT INTO questionQcm(score,theme,intitule,reponse1,reponse2,reponse3,reponse4,idReponse,explication) values (?,?,?,?,?,?,?,?,?)';
    $res = $this->queryBdd($sql,array($_score,$_theme,$_intitule,$_rep1,$_rep2,$_rep3,$_rep4,$_goodrep,$_explication));
    if($res) {
      return true;
    } else {
      return false;
    }
  }

  public function getAllQuestion() {
    $res = $this->queryAll('SELECT * from questionQcm');
    if($res) {
      foreach($res as $value) {
        $question[] = new Question($value['id'],$value['score'],$value['theme'],$value['intitule'],$value['reponse1'],$value['reponse2'],$value['reponse3'],$value['reponse4'],$value['idReponse'],$value['explication']);
      }
      return $question;
    } else {
      return false;
    }
  }

  public function deleteQuestion($id) {
    $sql = 'DELETE from questionQcm where id = ?';
    $res = $this->queryBdd($sql,array($id));
    if($res) {
      return true;
    } else {
      return false;
    }
  }
}
