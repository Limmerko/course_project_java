package vlsu.pri117.mep.bot.model;

import vlsu.pri117.mep.model.Problem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TgProblemWithStatus {
    private Problem _problem;
    private List<byte[]> _photosToBeAdded;
    private TgStatus _status;
    private final Long _chatId;
    private Date _date;

    public TgProblemWithStatus(Long chatId){
        _chatId = chatId;
        _photosToBeAdded = new ArrayList<>();
        _problem = new Problem();
        _status = TgStatus.JUST_STARTED;
        _date = new Date(System.currentTimeMillis());
    }

    public boolean checkPhotoCount() {
        return _photosToBeAdded.size() >= 5;
    }

    public void updateDate(){
        _date = new Date(System.currentTimeMillis());
    }
    public Problem get_problem() {
        return _problem;
    }

    public void set_problem(Problem _problem) {
        this._problem = _problem;
    }

    public List<byte[]> get_photosToBeAdded() {
        return _photosToBeAdded;
    }

    public void set_photosToBeAdded(List<byte[]> _photosToBeAdded) {
        this._photosToBeAdded = _photosToBeAdded;
    }

    public TgStatus get_status() {
        return _status;
    }

    public void set_status(TgStatus _status) {
        this._status = _status;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public Long get_chatId() {
        return _chatId;
    }
}
