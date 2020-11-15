package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey()
    var profileId: String,
    var name: String,
    var email: String
)