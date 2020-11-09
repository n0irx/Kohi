package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user_profile")
data class UserProfile(
    @ColumnInfo(name="profile_id")
    var profileId: String,

    @ColumnInfo(name="profile_name")
    var profileName: String,

    @ColumnInfo(name="profile_email")
    var email: String
)