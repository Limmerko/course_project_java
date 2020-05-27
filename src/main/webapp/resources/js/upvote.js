
function upvoteProblem(idProblem, login) {
    let  data = {
        authorLogin : login,
        idProblem : idProblem
    };
    $.ajax({
        type: "POST",
        url: "/problems/upvote",
        data: data,
        success: function (result) {
            if (result > 0){
                $('#countOfVOtes').text(result)
                return;
            }
            $('#countOfVOtes1').text("УЖЕ ЛАЙКНУЛ ДОЛБАЕБ")
        }
    });
}