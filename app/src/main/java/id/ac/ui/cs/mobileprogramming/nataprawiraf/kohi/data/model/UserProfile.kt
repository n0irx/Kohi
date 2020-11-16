package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey()
    var profileId: String,
    var name: String,
    var email: String
)