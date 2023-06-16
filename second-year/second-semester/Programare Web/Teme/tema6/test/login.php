<?php
$formEmail = addslashes(htmlentities($_POST['email'], ENT_COMPAT, "UTF-8"));
$formPassword = addslashes(htmlentities($_POST['parola'], ENT_COMPAT, "UTF-8"));
require_once "connection.php";
$stmt = $pdo->prepare("SELECT email FROM user WHERE email=:email and password=:parola");
// 1 record will be selected if the e-mail and password ar ok

$stmt->bindParam(':email', $formEmail);
$stmt->bindParam(':parola', $formPassword);
$stmt->execute();
$result = $stmt->fetchAll();
if (count($result) != 1) { // invalid login header("Location: index.php"); return; }
// all ok session_start(); $_SESSION['email'] = $formEmail; header("Location: showMoney.php");
}?>