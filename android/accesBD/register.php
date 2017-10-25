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

if(isset($_POST) and $results['error'] == false) {

        // vérif du pseudo
        $pseudo = preg_replace("/[^_A-Za-z0-9-\.&=]/i",'', $_POST['pseudo']);
        $email = preg_replace("/[^_A-Za-z0-9-\.&=]/i",'', $_POST['email']);
        $password =  preg_replace("/[^_A-Za-z0-9-\.&=]/i",'', $_POST['password']);
        $password2 =  preg_replace("/[^_A-Za-z0-9-\.&=]/i",'', $_POST['password2']);


        if(strlen($pseudo) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $pseudo) || strlen($pseudo) > 60) {
            $results['error'] = true;
            $results['message']['pseudo'] = ERREUR_PSEUDO;
        } else {
            // Vérifier que le pseudo n'existe pas
            $requete = $db->prepare("select id from users where pseudo = ?");
            $requete->execute(array($pseudo));
            $row = $requete->fetch();
            if($row) {
                $results['error'] = true;
                $results['message']['pseudo'] = ERREUR_PSEUDO_DEJA_PRIS;
            }
        }

        // vérif de l'email
        if(!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $results['error'] = true;
            $results['message']['email'] = ERREUR_EMAIL;
        } else {
            // Vérifier que l'email n'existe pas

            $requete = $db->prepare("select id from users where email = :email");
            $requete->execute(['email' => $email]);
            $row = $requete->fetch();
            if($row) {
                $results['error'] = true;
                $results['message']['email'] = ERREUR_EMAIL_DEJA_PRIS;
            }
        }

        // vérif du password
        if($password !== $password2) {
            $results['error'] = true;
            $results['message']['password'] = ERREUR_PASSWORD;
        }

        if($results['error'] === false) {

            $password = password_hash($password, PASSWORD_BCRYPT);
            $sql = $db->prepare("insert into users(pseudo, email, password) values(:pseudo, :email, :password)");
            $sql->execute([":pseudo" => $pseudo, ":email" => $email, ":password" => $password]);

            if(!$sql) {
                $results['error'] = true;
                $results['message'] = ERREUR_REGISTER;
            }


    } else {
        $results['error'] = true;
        $results['message'] = ERREUR_INCOMPLET;
    }

    echo json_encode($results);
}
