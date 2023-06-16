<?php
$connection = new mysqli("localhost", "root", "", "first_database");

if ($connection->connect_error)
{
    die("Connection failed: " . $connection->connect_error);
}