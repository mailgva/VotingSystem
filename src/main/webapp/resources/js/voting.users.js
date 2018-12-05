const ajaxUrl = "ajax/admin/users/";
let datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function setActive(checkBox) {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "setactive",
        data: { active: checkBox.checked, id: checkBox.closest('tr').id}
    }).done(function () {
        updateTable();
        successNoty("Saved");
    });
}

function editRow(id) {
    var row = $("#"+id);
    var name = row.find("td:eq(0)").html();
    var email = row.find("td:eq(1) a").text();
    var password = row.find("td:eq(2)").html();

    $("#detailsForm").find(":input").val("");

    $("#detailsForm").find("input[name='id']").val(id);
    //$("#detailsForm").find("input[name='id']").attr('id', id);
    $("#detailsForm").find("input[name='name']").val(name);
    $("#detailsForm").find("input[name='email']").val(email);
    $("#detailsForm").find("input[name='password']").val(password);
    setFormCaption('Редактирование пользователя');
    $("#editRow").modal();
}