$(document).ready(function () {
    $('.rateit.film_ratings').each(function () {
        $(this).bind('rated', function (event, value) {
            var user_id = $(this).attr('user-id');
            var film_id = $(this).attr('film-id');
            var user_mark_id = `#your_mark_${film_id}`;
            console.log(user_mark_id, $(user_mark_id).text());
            $(user_mark_id).text(value);
            $(`#rating_${film_id}`).attr('value', value);
            $.ajax({
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/user-films/" + user_id + "/rate",
                dataType: 'json',
                data: {
                    user: user_id, film: film_id, rating: value
                },
                header: {
                    'Accept': 'application/json, text/plain, */*',
                    'Content-Type': 'application/json'
                },
                success: function (data) {
                    console.info(data);
                }
            });
        });
    });
});

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});