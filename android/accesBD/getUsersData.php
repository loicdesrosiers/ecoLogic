<?php
$host_name = 'db699639168.db.1and1.com';
$database = 'db699639168';
$user_name = 'dbo699639168';
$password = '4dm1n4ppl1c4t1on';

$con = mysqli_connect($host_name,$user_name,$password,$database);

if(!$con)
{
 die("Error ".mysqli_connect_error());

}

$sql = "select * from users;";

$res = mysqli_query($con,$sql);

while($row=mysqli_fetch_array($res))
{
	echo $row[0]."_";
	echo $row[1]."_";
	echo $row[2]."_";
	echo $row[3];

	echo 'NEWLINE';




	/*
 echo "Email : ".$row[0];
 echo "Pseudo : ".$row[1];
 echo "MotDePasse : ".$row[2];
 echo "Nom : ".$row[3];
 */

}




mysqli_close($con);
//echo "Hello world...";



?>﻿
