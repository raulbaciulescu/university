<?php
// Create connection
$conn = new mysqli("localhost", "root", "", "MyDatabase");
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// sql to create table
$sql = "CREATE TABLE Clients 
        (
            id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            firstname VARCHAR(30),
            lastname VARCHAR(30),
            phoneNumber VARCHAR(30),
            email VARCHAR(30),
            password VARCHAR(30)
        )";

if ($conn->query($sql) === TRUE) {
    echo "Table Clients created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}

$conn->close();
