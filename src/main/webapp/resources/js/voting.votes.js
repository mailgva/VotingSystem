const ajaxUrl = "ajax/voting/";

// $(document).ready(function () {
$(function () {
    let inp_date = $('#filter :input[name="date"]');
    d = new Date();
    sd = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + (d.getDate() + (d.getHours() < 11 ? 0 : 1));
    inp_date.val(sd);
    updateFace();
});

function updateFace() {
    let form = $('#voteForm');
    let inp_date = $('#filter :input[name="date"]').val();

    form.empty();

    $.ajax({
         type: "GET",
         url: ajaxUrl,
         dataType: 'json',
         data: {date: inp_date},
         success: function(data){
             let input_voteId = $("<input/>")
                 .attr("type", "hidden")
                 .attr("name", "voteId");

             let input_date = $("<input/>")
                 .attr("type", "hidden")
                 .attr("name", "date");

             let div = $('<div/>').addClass("row");

             form.append(input_voteId);
             form.append(input_date);
             form.append(div);
             $.each(data, function(i, item_r) {
                 if(input_date.val() === "") input_date.val(item_r.date);
                 if((item_r.voteId !== 0) &&
                    (input_voteId.val() !== 0)) input_voteId.val(item_r.voteId);

                 let restoId = item_r.resto.id;

                 let dl = $('<dl/>').attr("data-restSelected", item_r.selected);

                 let dt = $('<dt/>');
                 let input = $("<input/>")
                     .attr("type", "radio")
                     .attr("id", restoId)
                     .attr("name", "restoId_" + restoId)
                     .attr("checked", item_r.selected)
                     .change(function(){
                         setVote(this);
                     })
                     .val(restoId);
                 if(item_r.selected === "true") input.attr("checked","true");

                 let label = $('<label/>')
                         .attr("for", "restoId_" + restoId)
                         .text(item_r.resto.name);

                 dt.append(input);
                 dt.append(label);

                 let dd = $('<dd/>');
                 let table = $('<table/>').addClass("table table-bordered");
                 let thead = $('<thead/>').addClass("thead-dark");
                 let tr    = $('<tr/>');
                 let thn   = $('<th/>').attr("style", "width:250px;").text("Наименование");
                 let thp   = $('<th/>').text("Цена");

                 tr.append(thn);
                 tr.append(thp);
                 thead.append(tr);

                 var tbody = $('<tbody/>');
                 $.each(item_r.resto.dishes, function(j, item_d) {
                     let trb = $('<tr/>');
                     let tdn = $('<td/>').text(item_d.name);
                     let tdp = $('<td/>').text(item_d.price);

                     trb.append(tdn);
                     trb.append(tdp);
                     tbody.append(trb);
                 })

                 table.append(thead);
                 table.append(tbody);
                 dd.append(table);

                 dl.append(dt);
                 dl.append(dd);

                 div.append(dl);
             });

         }
     });
}

function setVote(radioBox) {
    let fDate    = $('#filter :input[name="date"]').val();
    let restoId = radioBox.id;
    let voteId  = $('#voteForm :input[name="voteId"]').val();

    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: { date: fDate, restoId: restoId, voteId: voteId}
    }).done(function () {
        updateFace();
        successNoty("common.saved");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        //$("#"+radioBox.id).prop('checked', false);
        $(radioBox).prop('checked', false);
        failNoty(jqXHR);
    });
}