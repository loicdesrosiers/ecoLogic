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

  public function getAllQuestion() {
    $res = $this->queryAll('SELECT * from questionsQcm');
    if($res) {
      foreach($res as $value) {
        $question[] = new Question($value['ID'],$value['Intitule'],$value['Reponse1'],$value['Reponse2'],$value['Reponse3'],$value['Reponse4'],$value['BonneReponse'],$value['Explication'],$value['Score']);
      }
      return $question;
    } else {
      return false;
    }
  }

  public function deleteQuestion($id) {
    $sql = 'DELETE from questionsQcm where id = ?';
    $res = $this->queryBdd($sql,array($id));
    if($res) {
      return true;
    } else {
      return false;
    }
  }
}
