<?php
$connection = new mysqli("localhost", "root", "");

if ($connection->connect_error)
{
    die("Connection failed: " . $connection->connect_error);
}

$sql = "CREATE DATABASE first_database";
if ($connection->query($sql) === TRUE)
{
    echo "Database created successfully";
}
else {
    echo "Error creating database: " . $connection->error;
}

$connection->close();