package sg.edu.rp.c346.p06_taskmanager;

import java.io.Serializable;

public class Task implements Serializable {

    private int _id;
    private String title;
    private String description;


    public Task(String title,String description){
        this.title = title;
        this.description = description;

    }

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }



    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
