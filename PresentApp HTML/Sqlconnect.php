<?php
/*
$user='SevenApp7';
$pass = 'PresentApp&';
$dbname = 'presentappdb';
$server = 'presentappdb.cwrlkiqzhztj.ap-southeast-1.rds.amazonaws.com';
*/

$server = "localhost";
$user = "root";
$pass = "wilson";
$dbname = "studentdata";

$conn = new mysqli($server,$user,$pass,$dbname);

if ($conn->connect_error)
{
    die("Connection failed: ". $conn->connect_error);
}

?>