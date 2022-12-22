package agency.five.codebase.android.movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorites")
    fun favorites(): Flow<List<DbFavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(dbFavoriteMovie: DbFavoriteMovie)

    @Query("DELETE FROM favorites WHERE id=:id")
    suspend fun removeFavorite(id: Int)
}
