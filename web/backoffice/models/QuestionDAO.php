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
    $sql = 'SELECT * FROM questionsQcm';
    $res = $this->queryAll($sql);
    if($res) {
      foreach($res as $value) {
        $question[] = new Question($value['id'],$value['intitule'],$value['reponse1'],$value['reponse2'],$value['reponse3'],$value['reponse4'],$value['bonnereponse'],$value['explication'],$value['score']);
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
