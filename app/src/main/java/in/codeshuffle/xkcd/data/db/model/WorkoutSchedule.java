package in.codeshuffle.xkcd.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity(nameInDb = "food")
public class WorkoutSchedule {

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Generated(hash = 1373917667)
    public WorkoutSchedule(Long id) {
        this.id = id;
    }

    @Generated(hash = 2019165881)
    public WorkoutSchedule() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
