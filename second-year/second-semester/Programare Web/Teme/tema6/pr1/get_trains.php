<?php
$connection = new mysqli("localhost", "root", "", "first_database");

if ($connection->connect_error)
{
    die("Connection failed: " . $connection->connect_error);
}


$formStart = addslashes(htmlentities($_POST['start'], ENT_COMPAT, "UTF-8"));
$formDestination = addslashes(htmlentities($_POST['destination'], ENT_COMPAT, "UTF-8"));
$typeForm = "orice";
if (!empty($_POST['type']))
{
    $typeForm = "direct";
}

$stmt = $connection->prepare("SELECT id, type, start, destination, start_date, destination_date 
                                    FROM trains 
                                    ");
$stmt->execute();
$stmt->bind_result($id, $type, $start, $destination, $start_date, $destination_date);
$trains = array();

while ($stmt->fetch())
{
    if ($typeForm === "direct" && $start === $formStart && $destination === $formDestination && $type === "direct")
        $trains[] = array("id" => $id, "type" => $type, "start" => $start, "destination" => $destination,
            "start_date" => $start_date, "destination_date" => $destination_date);
    else
        if ($typeForm === "orice" && $start === $formStart && $destination === $formDestination)
            $trains[] = array("id" => $id, "type" => $type, "start" => $start, "destination" => $destination,
                "start_date" => $start_date, "destination_date" => $destination_date);

}

echo "<table>";
foreach($trains as $train) {
    echo "<tr>";
    echo "<td>" . $train['id'] ."</td>";
    echo "<td>" . $train['type'] ."</td>";
    echo "<td>" . $train['start'] ."</td>";
    echo "<td>" . $train['destination'] . "</td>";
    echo "</tr>";
}
echo "</table>";