<?php
$connection = new mysqli("localhost", "root", "", "first_database");

if ($connection->connect_error)
{
    die("Connection failed: " . $connection->connect_error);
}

$stmt = $connection->prepare("INSERT INTO trains(id, type, start, destination, start_date, destination_date)
                            VALUES (?, ?, ?, ?, ?, ?)");
$stmt->bind_param("isssss", $id, $type, $start, $destination, $start_date, $destination_date);
$id = 1;
$type = "cfr";
$start = "Bucuresti";
$destination = "Cluj-Napoca";
$start_date = "2012-12-31";
$destination_date = "2011-11-10";
$stmt->execute();

$stmt = $connection->prepare("SELECT id,
       type, start, destination, start_date, destination_date FROM trains");
$stmt->execute();
$stmt->bind_result($id, $type, $start, $destination, $start_date, $destination_date);
$trains = array();

while ($stmt->fetch()) {
    $trains[] = array("id" => $id, "type" => $type, "start" => $start, "destination" => $destination,
        "start_date" => $start_date, "destination_date" => $destination_date);
}

// Create an HTML list
echo "<table>";

// Add items to the list
foreach($trains as $train) {
    echo "<tr>";
    echo "<td>" . $train['id'] ."</td>";
    echo "<td>" . $train['type'] ."</td>";
    echo "<td>" . $train['start'] ."</td>";
    echo "<td>" . $train['destination'] . "</td>";
    echo "</tr>";
}

// End list
echo "</table>";


//echo json_encode($trains);