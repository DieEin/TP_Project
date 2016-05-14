<?php
 
/**
 * @author Ravi Tamada
 * @link http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/ Complete tutorial
 */
 
class DB_Functions {
 
    private $conn;
 
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($name, $email, $password) {
        $uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
 
        $stmt = $this->conn->prepare("INSERT INTO users(unique_id, name, email, encrypted_password, salt, created_at) VALUES(?, ?, ?, ?, ?, NOW())");
        $stmt->bind_param("sssss", $uuid, $name, $email, $encrypted_password, $salt);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            return $user;
        } else {
            return false;
        }
    }
	
	/* NEW THING */
	public function storePlayerStats($name) {
        $stmt = $this->conn->prepare("INSERT INTO player_stats(name, money, xp, level) VALUES(?, ?, ?, ?)");
        $ssss = "ssss";
		$money = 10;
		$xp = 0;
		$level = 1;
		$stmt->bind_param($ssss, $name, $money, $xp, $level);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM player_stats WHERE name = ?");
            $stmt->bind_param("s", $name);
            $stmt->execute();
            $player_stats = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            return $player_stats;
        } else {
            return false;
        }
    }
	
	public function getPlayerstatsByName($name) {
 
        $stmt = $this->conn->prepare("SELECT * FROM player_stats WHERE name = ?");
 
        $stmt->bind_param("s", $name);
 
        if ($stmt->execute()) {
            $player_stats = $stmt->get_result()->fetch_assoc();
			$stmt->close();
 
            return $player_stats;
        } else {
            return NULL;
        }
    }
	
	public function storePlayerData($name, $money, $xp, $level) {
		/*$stmt = $this->conn->prepare("DELETE FROM player_stats WHERE name=?");
		$s = "s";
		$stmt->bind_param($s, $name);
		$stmt->execute();
		$stmt->close();*/
		
		//$stmt = $this->conn->prepare("INSERT INTO player_stats(name, money, xp, level) VALUES(?, ?, ?, ?)");
		$stmt = $this->conn->prepare("UPDATE player_stats SET money=?, xp=?, level=? WHERE name=?");
        $ssss = "ssss";
		$stmt->bind_param($ssss, $money, $xp, $level, $name);
        $result = $stmt->execute();
        $stmt->close();
	}
	
	public function getAllUsers() {
		$stmt = $this->conn->prepare("SELECT name, logged_in from user_logged_in");
		$s = "s";
		$value = "qqq";
		//$stmt->bind_param($s, $value);
		
		$stmt->execute();
		//$users = $stmt->get_result()->fetch_assoc();
		$stmt->bind_result($name, $is_logged);
		$counter = 0;
		while($stmt->fetch()) {
			//printf("%d - %s - %d\n", $id, $name, $is_logged);
			$users[$counter]["name"] = $name;
			$users[$counter]["is_logged"] = $is_logged;
			$counter++;
		}
		$stmt->close();
		
		return $users;
	}
	
	public function loginUser($name) {
		echo $name;
		$stmt = $this->conn->prepare("UPDATE user_logged_in SET logged_in=1 WHERE name=?");
        $s = "s";
		//$value = 1;
		$stmt->bind_param($s, $name);
        $result = $stmt->execute();
        $stmt->close();
	}
	
	public function logoutUser($name) {
		echo $name;
		$stmt = $this->conn->prepare("UPDATE user_logged_in SET logged_in=0 WHERE name=?");
        $s = "s";
		//$value = 0;
		$stmt->bind_param($s, $name);
        $result = $stmt->execute();
        $stmt->close();
	}
	
	/* NEW THING */
 
    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $password) {
 
        $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
 
        $stmt->bind_param("s", $email);
 
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            // verifying user password
            $salt = $user['salt'];
            $encrypted_password = $user['encrypted_password'];
            $hash = $this->checkhashSSHA($salt, $password);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $user;
            }
        } else {
            return NULL;
        }
    }
 
    /**
     * Check user is existed or not
     */
    public function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from users WHERE email = ?");
 
        $stmt->bind_param("s", $email);
 
        $stmt->execute();
 
        $stmt->store_result();
 
        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }
 
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
 
    /**
     * Decrypting password
     * @param salt, password
     * returns hash string
     */
    public function checkhashSSHA($salt, $password) {
 
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
 
        return $hash;
    }
 
}
 
?>