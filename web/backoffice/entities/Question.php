<?php

class Question {
  private $_id,$_intitule,$_rep1,$_rep2,$_rep3,$_rep4,$_goodrep,$_explication,$_score;

  public function __construct($_id,$_intitule,$_rep1,$_rep2,$_rep3,$_rep4,$_goodrep,$_explication,$_score) {
    $this->_id = $_id;
    $this->_intitule = $_intitule;
    $this->_rep1 = $_rep1;
    $this->_rep2 = $_rep2;
    $this->_rep3 = $_rep3;
    $this->_rep4 = $_rep4;
    $this->_goodrep = $_goodrep;
    $this->_explication = $_explication;
    $this->_score = $_score;
  }

  public function getId() {
    return $this->_id;
  }

    /**
     * @return mixed
     */
    public function getRep4()
    {
        return $this->_rep4;
    }

    /**
     * @return mixed
     */
    public function getGoodrep()
    {
        return $this->_goodrep;
    }

    /**
     * @return mixed
     */
    public function getExplication()
    {
        return $this->_explication;
    }

    /**
     * @return mixed
     */
    public function getScore()
    {
        return $this->_score;
    }

  public function getIntitule() {
    return $this->_intitule;
  }

  public function getRep1() {
    return $this->_rep1;
  }

  public function getRep2() {
    return $this->_rep2;
  }

  public function getRep3() {
    return $this->_rep3;
  }
}
