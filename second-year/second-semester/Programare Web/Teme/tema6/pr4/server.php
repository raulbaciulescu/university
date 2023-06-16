<?php
session_start();

$username = "";
$email = "";
$errors = array();
$password1 = "";
$password2 = "";

$db = mysqli_connect("localhost", "root", "", "first_database");


if (isset($_POST['reg_user']))
{
    $username = mysqli_real_escape_string($db, $_POST["username"]);
    $email = mysqli_real_escape_string($db, $_POST["email"]);
    $password1 = mysqli_real_escape_string($db, $_POST['password1']);
    $password2 = mysqli_real_escape_string($db, $_POST['password2']);
}

if (empty($username))
{
    $errors[] = "Username is required";
}
if (empty($email))
{
    $errors[] = "Email is required";
}
if (empty($password1))
{
    $errors[] = "Password is required";
}
if ($password1 != $password2)
{
    $errors[] = "The two passwords do not match";
}

$user_check_query = "SELECT * FROM user WHERE username='$username' OR email='$email' LIMIT 1";
$result = mysqli_query($db, $user_check_query);
$user = mysqli_fetch_assoc($result);

if ($user)
{
    if ($user['username'] === $username)
    {
        $errors[] = "Username already exists";
    }

    if ($user['email'] === $email)
    {
        $errors[] = "email already exists";
    }
}

if (count($errors) == 0)
{
    $query = "INSERT INTO user (username, email, password) 
  			  VALUES('$username', '$email', '$password1')";
    mysqli_query($db, $query);
    $_SESSION['username'] = $username;
    $_SESSION['success'] = "You are now logged in";

    $to = $email;
    $subject = "test email";
    $message = "test message";
    $headers = "From emailsendraul@gmail.com\r\nReply-To: ".$email;
    $mail_send = mail($to, $subject, $message, $headers);
    if ($mail_send == true)
    {
        echo "Mail send";
    }
    else
    {
        echo "Mail failed";
    }

    header('location: index.php');
}



// LOGIN

if (isset($_POST["login_user"]))
{
    $errors = array();
    $username = mysqli_real_escape_string($db, $_POST['username']);
    $password = mysqli_real_escape_string($db, $_POST['password']);

    echo $username;
    echo $password;
    if (empty($username))
    {
        $errors[] = "Username is required";
    }
    if (empty($password))
    {
        $errors[] = "Password is required";
    }
    if (count($errors) == 0)
    {
        $query = "SELECT * FROM user WHERE username='$username' AND password='$password'";
        $results = mysqli_query($db, $query);
        if (mysqli_num_rows($results) == 1)
        {
            $_SESSION['username'] = $username;
            $_SESSION['success'] = "You are now logged in";
            echo $password;
            header('location: index.php');
        }
        else
        {
            $errors[] = "Wrong username/password combination";
        }
    }
}

