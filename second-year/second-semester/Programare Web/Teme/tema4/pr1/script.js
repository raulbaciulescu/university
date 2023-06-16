
$(function() {
    $("#cars").dblclick(function()  {
        const element = $("#cars").find(":selected");
        $("#fruits").append(element);
        $('#cars').remove(element);

    })

    $("#fruits").dblclick(function() {
        const element = $("#fruits").find(":selected");
        $("#cars").append(element);
        $('#fruits').remove(element);
    })
});