package agency.five.codebase.android.movieapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class DbFavoriteMovie (
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Long = 0, //TODO remove
    @ColumnInfo(name="id")
    val id: Int,
    @ColumnInfo(name="posterUrl")
    val posterUrl: String,
)
