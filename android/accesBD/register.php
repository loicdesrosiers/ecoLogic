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

if(isset($_GET) and $results['error'] == false) {

        // vérif du pseudo
        $pseudo = $_GET['pseudo'];
        $email = $_GET['email'];
        $password = $_GET['password'];
        $password2 = $_GET['password2'];


        if(strlen($pseudo) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $pseudo) || strlen($pseudo) > 60) {
            $results['error'] = true;
            $results['message']['pseudo'] = "Pseudo invalide";
        } else {
            // Vérifier que le pseudo n'existe pas
            $requete = $db->prepare("select id from users where pseudo = ?");
            $requete->execute(array($pseudo));
            $row = $requete->fetch();
            if($row) {
                $results['error'] = true;
                $results['message']['pseudo'] = "Le pseudo est déjà pris";
            }
        }

        // vérif de l'email
        if(!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $results['error'] = true;
            $results['message']['email'] = "Email invalide";
        } else {
            // Vérifier que l'email n'existe pas

            $requete = $db->prepare("select id from users where email = :email");
            $requete->execute(['email' => $email]);
            $row = $requete->fetch();
            if($row) {
                $results['error'] = true;
                $results['message']['email'] = "L'email existe déjà";
            }
        }

        // vérif du password
        if($password !== $password2) {
            $results['error'] = true;
            $results['message']['password'] = "Les mots de passes doivent être identiques";
        }

        if($results['error'] === false) {

            $password = password_hash($password, PASSWORD_BCRYPT);
            $sql = $db->prepare("insert into users(pseudo, email, password) values(:pseudo, :email, :password)");
            $sql->execute([":pseudo" => $pseudo, ":email" => $email, ":password" => $password]);

            if(!$sql) {
                $results['error'] = true;
                $results['message'] = "Erreur lors de l'inscription";
            }


    } else {
        $results['error'] = true;
        $results['message'] = "Veuillez remplir tous les champs";
    }

    echo json_encode($results);
}