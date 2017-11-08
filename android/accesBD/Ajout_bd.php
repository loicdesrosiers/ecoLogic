<?php

$sql=array();

if(!isset($_GET['password']) || is_null($_GET['password']) || !isset($_GET['email']) ||
is_null($_GET['email']) || !isset($_GET['pseudo']) || is_null($_GET['pseudo'])) {
	echo 'Complétez tous les champs';
} else {
	$host_name = 'db699639168.db.1and1.com';
	$database = 'db699639168';
	$user_name = 'dbo699639168';
	$password = '4dm1n4ppl1c4t1on';




	try
{
    // On se connecte à MySQL
    $bdd = new PDO('mysql:host=db699639168.db.1and1.com;dbname=db699639168;charset=utf8', 'dbo699639168', '4dm1n4ppl1c4t1on');
}
catch(Exception $e)
{
    // En cas d'erreur, on affiche un message et on arrête tout
    die('Erreur : '.$e->getMessage());
}

	$password=hash("sha256",htmlspecialchars($_GET['password']));
	$email=htmlspecialchars($_GET['email']);
	$pseudo=htmlspecialchars($_GET['pseudo']);




	$sql = "INSERT INTO users(user_mail,user_pseudo,user_password) VALUES(?,?,?)";
	$req = $bdd->prepare($sql);
	$req->execute(array($email,$pseudo,$password));

	if($req) {
		echo 'Inscription validée';
	} else {
		echo 'Echec, cette adresse mail est déjà associée à un compte.';

	}
	}
