# Retrofitexamplegajjar
Sample retrofit example for get and post data from php/mysql

OPEN README.md AS RAW from above list!!!

Simply create 1 table and add id,name,role as columns (add some data)

create 3 scripts from below and host in your server 

connect.php code
define('DBHOST','yourserver');
define('DBUSER','yourvalue');
define('DBUSERPASS','');
define('DBNAME','yourvalue');
define('MESSAGE_SUCCESS',"SUCCESS");
define('MESSAGE_FAIL',"FAIL");
$connection = mysqli_connect(DBHOST,DBUSER,DBUSERPASS,DBNAME) or error("Unable to connect to database : " . mysqli_connect_errorno());


api.php

<?php
require_once('connect.php'); // make a connection in saperate file
$response = array();
$sql = "select * from gajjardemo ";
$result = mysqli_query($connection,$sql);
if($result){
  $response["result"] = true;
  $response["message"] = MESSAGE_SUCCESS;
  $response["data"] = array();
  while ($data = mysqli_fetch_assoc($result)) {
    array_push($response["data"],$data);
  }
}else{
  $response["result"] = false;
  $response["message"] = mysqli_error($connection);
}

encode($response);
?>

register.php

<?php
require_once('connect.php');

$response = array();

if(isset($_POST["name"]) && isset($_POST["role"])){

	$name=$_POST["name"];
	$role=$_POST["role"];

	$sql = "INSERT INTO gajjardemo(name,role) values('$name','$role') ";
	$result = mysqli_query($connection,$sql);
	if($result){
	  $response["result"] = true;
	  $response["message"] = MESSAGE_SUCCESS;
	}else{
	  $response["result"] = false;
	  $response["message"] = mysqli_error($connection);
	}

}else{
  $response["result"] = false;
  $response["message"] = "Some information was leaked";
  encode($response,true);
}


encode($response,true); //simply write encode 
?>
