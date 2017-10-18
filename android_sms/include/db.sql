CREATE DATABASE android_sms;
 
USE android_sms;

CREATE TABLE `sms_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `code` varchar(6) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
 
 
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(250) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `apikey` varchar(32) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=64 ;
 
 
ALTER TABLE `sms_codes`
  ADD CONSTRAINT `sms_codes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users`



  http://localhost/android_sms/request_sms.php  post body url form encoded
  name email mobile

  http://localhost/android_sms/verify_otp.php
  otp 