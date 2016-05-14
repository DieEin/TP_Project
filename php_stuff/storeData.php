<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['name']) && isset($_POST['money']) && isset($_POST['xp']) && isset($_POST['level'])) {
 
    // receiving the post params
    $name = $_POST['name'];
	$money = $_POST['money'];
    $xp = $_POST['xp'];
    $level = $_POST['level'];
        // create a new user
        $store_data = $db->storePlayerData($name, $money, $xp, $level);
}
?>