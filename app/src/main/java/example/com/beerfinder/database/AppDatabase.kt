package example.com.beerfinder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import example.com.beerfinder.BuildConfig
import example.com.beerfinder.data.database.MarkerDataModel

@Database(
        entities = [
            MarkerDataModel::class
        ],
        version = BuildConfig.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun markerDao(): MarkerDao
}