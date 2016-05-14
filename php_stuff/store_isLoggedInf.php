<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['name'])) {
 
    // receiving the post params
    $name = $_POST['name'];
 
    // get the user by email and password
    $user = $db->storeIsLoggedInfo($name);
}

?>