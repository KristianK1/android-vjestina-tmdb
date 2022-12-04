package agency.five.codebase.android.movieapp.mock

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object FavoritesDBMock {
    private val favoriteIdsInternal: MutableStateFlow<Set<Int>> = MutableStateFlow(emptySet())
    val favoriteIds: StateFlow<Set<Int>> = favoriteIdsInternal.asStateFlow()
    fun insert(movieId: Int){
        favoriteIdsInternal.update {
            it.plus(movieId)
        }
    }
    fun delete(movieId: Int){
        favoriteIdsInternal.update {
            it.minus(movieId)
        }
    }
}