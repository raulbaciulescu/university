const viewTasksElement1 = document.getElementById('cars');
const viewTasksElement2 = document.getElementById('fruits');
viewTasksElement1.addEventListener('dblclick', change, true);
viewTasksElement2.addEventListener('dblclick', change2, true);

function change() {
    const child = viewTasksElement1.options[viewTasksElement1.selectedIndex];
    console.log(child);
    viewTasksElement2.appendChild(viewTasksElement1.removeChild(child));
}

function change2() {
    const child = viewTasksElement2.options[viewTasksElement2.selectedIndex];
    console.log(child);
    viewTasksElement1.appendChild(viewTasksElement2.removeChild(child));
}