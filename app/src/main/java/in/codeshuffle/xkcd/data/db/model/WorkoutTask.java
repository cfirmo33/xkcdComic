package in.codeshuffle.xkcd.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity(nameInDb = "restaurant")
public class WorkoutTask {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Generated(hash = 1244246276)
    public WorkoutTask(Long id) {
        this.id = id;
    }

    @Generated(hash = 700695267)
    public WorkoutTask() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
