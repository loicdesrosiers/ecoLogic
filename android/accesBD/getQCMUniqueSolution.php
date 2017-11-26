<?php
	$host_name = 'db699639168.db.1and1.com';
	$database = 'db699639168';
	$user_name = 'dbo699639168';
	$password = '4dm1n4ppl1c4t1on';
    try {
        // connection to the database.
        $pdo_options[PDO::ATTR_ERRMODE] = PDO::ERRMODE_EXCEPTION;
        $bdd = new PDO('mysql:host=db699639168.db.1and1.com;dbname=db699639168', 'dbo699639168', '4dm1n4ppl1c4t1on', $pdo_options);

        // Execute SQL request on the database.
        $sql = 'SELECT * FROM questionQcm;';
        $response = $bdd->query($sql);
        $output = $response->fetchAll(PDO::FETCH_ASSOC);
    } catch (Exception $e) {
        die('Erreur : ' . $e->getMessage());
    }

    // Print JSON encode of the array.
    echo(json_encode($output));
?>