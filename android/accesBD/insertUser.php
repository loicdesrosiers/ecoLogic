<?php
$sql=array();
if(!isset($_GET['pseudo']) || is_null($_GET['password']) || !isset($_GET['email']) ||
is_null($_GET['email']) || !isset($_GET['pseudo']) || is_null($_GET['pseudo'])) {
	echo 'Complétez tous les champs';
} else {
	$host_name = 'db699639168.db.1and1.com';
	$database = 'db699639168';
	$user_name = 'dbo699639168';
	$password = '4dm1n4ppl1c4t1on';
	try{
    // On se connecte à MySQL
    $bdd = new PDO('mysql:host=db699639168.db.1and1.com;dbname=db699639168;charset=utf8', 'dbo699639168', '4dm1n4ppl1c4t1on');
	}catch(Exception $e){
    // En cas d'erreur, on affiche un message et on arrête tout
    die('Erreur : '.$e->getMessage());
	}

	$password=htmlspecialchars($_GET['password']);
	$email=htmlspecialchars($_GET['email']);
	$pseudo=htmlspecialchars($_GET['pseudo']);
	$score=0;

	$sql = "INSERT INTO users(mail,pseudo,password,score) VALUES(?,?,?,?)";
	$req = $bdd->prepare($sql);
	$req->execute(array($email,$pseudo,$password,$score));

	if($req->rowCount() > 0) {
		echo 'true';
	} else {
		echo 'false';
	}
}
?>
