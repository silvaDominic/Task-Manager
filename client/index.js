$(document).ready(function() {
    $('.dropdate').dropdate({
        dateFormat: 'mm/dd/yyyy'
    });

    var request;

    // Binds submit event from form
    $("task_form").submit(function(event){

        // Prevent form from submitting from browser
        event.preventDefault();

        $form = $(this);

        // Aborts any pending requests
        if (request) {
            request.abort();
        }

        // Selects and caches input field
        var $inputs = $form.find("input");

        // Briefly disables input fields during duration of AJAX request
        $inputs.prop("disabled", true);

        // AJAX requests
        request = $.ajax({
            url: $form.attr('action'),
            type: $form.attr('method'),
            data: $form.serialize()
        }).done(function(response, textStatus, jqXHR){
            console.log("Post successful.");
            console.log("Response: " + response);
            console.log("Text Status: " + textStatus);
            console.log("JQ XMLHttpReq: " + jQuery.parseJSON(jqXHR.responseText));
        }).fail(function(data){
            console.log("Error.");
            console.log("Response: " + response);
            console.log("Text Status: " + textStatus);
            console.log("JQ XMLHttpReq: " + jQuery.parseJSON(jqXHR.responseText));
        });

        // TODO: What's the format of the date when sent via JSON?

        // Callback handler for successful requests
        request.done(function (response, textStatus, jqXHR){
            console.log("Success")
        });

        // Callback handler for failed requests
        request.fail(function(jqXHR, textStatus, errorThrown){
            console.log("Error: " + textStatus, errorThrown)
        });

        // Reenable input fields
        request.always(function(){
            $inputs.prop("disabled", false);
        });
    });
});