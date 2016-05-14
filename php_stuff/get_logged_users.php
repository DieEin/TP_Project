<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
// get the user by email and password
$users = $db->getAllUsers();
//echo var_dump($user);
//echo var_dump($player_stats);
//echo var_dump($users);
//echo count($users);

$number_of_users = count($users);
$counter = 0;

if ($users) {
	// use is found
	$response["error"] = FALSE;
	
	$response["number_of_users"] = $number_of_users;
	while($counter < $number_of_users) {
		$response["user_logged_in"]["names"][$counter] = $users[$counter]["name"];
		$response["user_logged_in"]["is_logged"][$counter] = $users[$counter]["is_logged"];
		//echo $users[$counter]["name"];
		
		$counter++;
	}
	
	echo json_encode($response);
} else {
	// user is not found with the credentials
	$response["error"] = TRUE;
	$response["error_msg"] = "Login credentials are wrong. Please try again!";
	echo json_encode($response);
}
?>