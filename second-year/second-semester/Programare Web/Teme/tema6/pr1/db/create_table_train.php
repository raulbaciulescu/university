<?php

$connection = new mysqli("localhost", "root", "", "first_database");

if ($connection->connect_error)
{
    die("Connection failed: " . $connection->connect_error);
}

$sql = "CREATE TABLE trains(
        id INT, 
        type VARCHAR(50),
        start VARCHAR(50), 
        destination VARCHAR(50), 
        start_date DATE, 
        destination_date DATE
        )";

if ($connection->query($sql) === TRUE)
{
    echo "Table trains created successfully";
}
else
{
    echo "Error creating table: " . $connection->error;
}

$connection->close();
