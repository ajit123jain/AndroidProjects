<?php
error_reporting(-1);
ini_set('display_errors', 'On');

include './include/DbHandler.php';
$db = new DbHandler();


$response = array();

// echo $_POST['mobile'];

if (isset($_POST['mobile']) && $_POST['mobile'] != '') {

    $name = '';
    $email = '';
    $mobile = $_GET['mobile'];

    $res = $db->createUser($name, $email, $mobile);

    if ($res == USER_CREATED_SUCCESSFULLY) {
        $response["error"] = false;
        $response["message"] = "SMS request is initiated! You will be receiving it shortly.";
    } else if ($res == USER_CREATE_FAILED) {
        $response["error"] = true;
        $response["message"] = "Sorry! Error occurred in registration.";
    } else if ($res == USER_ALREADY_EXISTED) {
        $response["error"] = true;
        $response["message"] = "Mobile number already existed!";
    }
} else {
    $response["error"] = true;
    $response["message"] = "Sorry! mobile number is not valid or missing.";
}


echo json_encode($response);
?>