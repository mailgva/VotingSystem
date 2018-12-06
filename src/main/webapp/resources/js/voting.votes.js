const ajaxUrl = "ajax/voting/";

// $(document).ready(function () {
$(function () {
    $('form :radio').change(function(){
        setVote(this);
        //$('#vote').submit();
    });
});

function setVote(radioBox) {
    let date    = $('form :input[name="date"]').val();
    let restoId = radioBox.id;
    let voteId  = $('form :input[name="voteId"]').val();

    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: { date: date, restoId: restoId, voteId: voteId},
        error: function (jqXHR) {
            failNoty(jqXHR);
        }
    }).done(function () {
        location.reload();
        successNoty("Saved");
    });
}