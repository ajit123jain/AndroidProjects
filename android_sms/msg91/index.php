<?php

//Your authentication key
$authKey = "88276AGwzewOEdFs559d20ae";

//Multiple mobiles numbers separated by comma
$mobileNumber = "7032732586";

//Sender ID,While using route4 sender id should be 6 characters long.
$senderId = "102234";

//Your message to send, Add URL encoding here.
$message = urlencode("Your OPT is: 123456");

$response_type = 'json';

//Define route 
$route = "default";
//Prepare you post parameters
$postData = array(
    'authkey' => $authKey,
    'mobiles' => $mobileNumber,
    'message' => $message,
    'sender' => $senderId,
    'route' => $route,
    'response' => $response_type
);

//API URL
$url = "https://control.msg91.com/sendhttp.php";

// init the resource
$ch = curl_init();
curl_setopt_array($ch, array(
    CURLOPT_URL => $url,
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_POST => true,
    CURLOPT_POSTFIELDS => $postData
        //,CURLOPT_FOLLOWLOCATION => true
));


//Ignore SSL certificate verification
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);


//get response
$output = curl_exec($ch);

//Print error if any
if (curl_errno($ch)) {
    echo 'error:' . curl_error($ch);
}

curl_close($ch);

echo $output;
?>
