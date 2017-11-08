<?php

class Utilisateur {
    private $_username;
    private $_password;

    public function __construct($_username,$_password) {
      $this->_username = $_username;
      $this->_password = $_password;
    }

    public function getUsername() {
      return $this->_username;
    }

    public function getPassword() {
      return $this->_password;
    }
}
