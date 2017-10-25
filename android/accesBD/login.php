<?php
require_once('define.php');
require_once('configuration.php');

$results['error'] = false;
$results['message'] = [];

try {
    $db = new PDO('mysql:host=' . BD_HOST . '; dbname=' . BD_DBNAME . '; charset=utf8', BD_USER , BD_PWD);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $e) {
    $results['error'] = true;
    $results['message'] = ERREUR_CONNEXION;
}

if(!empty($_POST)) {

    if(!empty($_POST['pseudo']) && !empty($_POST['password'])) {

        $pseudo = $variable = preg_replace("/[^_A-Za-z0-9-\.&=]/i",'', $_POST['pseudo']);
        $password = $variable = preg_replace("/[^_A-Za-z0-9-\.&=]/i",'', $_POST['password']);

        $sql = $db->prepare("SELECT * from users where pseudo = :pseudo");
        $sql->execute([":pseudo" => $pseudo]);
        $row = $sql->fetch(PDO::FETCH_OBJ);
        if($row) {
            if(password_verify($password, $row->password)) {
                $results['error'] = false;
                $results['id'] = $row->id;
                $results['pseudo'] = $row->pseudo;
            } else {
                $results['error'] = true;
                $results['message'] = ERREUR_LOG;
            }
        } else {
            $results['error'] = true;
            $results['message'] = ERREUR_LOG;
        }
    } else {
        $results['error'] = true;
        $results['message'] = ERREUR_INCOMPLET;
    }

    echo json_encode($results);
}
