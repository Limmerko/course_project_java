function WordLimit(){
    var maxCount = 90;

    $('#wordCounter').text(maxCount);

    $('#descText').keyup(function() {
        var revText = this.value.length;

        if (this.value.length > maxCount)
        {
            this.value = this.value.substr(0, maxCount);
        }
        var cnt = (maxCount - revText);
        if(cnt <= 0){$('#wordCounter').text('0');}
        else {$('#wordCounter').text(cnt);}

    });
}