<?php
define('BD_HOST','db699672269.db.1and1.com');
define('BD_DBNAME','db699672269');
define('BD_USER','dbo699672269');
define('BD_PWD','testEcologic');
define('ERREUR_CONNEXION','Une erreur dans la connexion à la bd');

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

        $pseudo = $_POST['pseudo'];
        $password = $_POST['password'];

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
                $results['message'] = "Pseudo ou mot de passe incorrect";
            }
        } else {
            $results['error'] = true;
            $results['message'] = "Pseudo ou mot de passe incorrect";
        }
    } else {
        $results['error'] = true;
        $results['message'] = "Veuillez remplir tous les champs";
    }

    echo json_encode($results);
}