package com.elanyudho.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.elanyudho.core.R
import com.elanyudho.core.data.local.room.ToolDao
import com.elanyudho.core.data.local.room.ToolDb
import com.elanyudho.core.data.local.room.entity.Friend
import com.elanyudho.core.data.local.room.entity.Tool
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private var INSTANCE: ToolDb? = null

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): ToolDb {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                ToolDb::class.java,
                "tool.db"
            ).addCallback(DatabaseCallback())
                .build()
                .also { INSTANCE = it }
            instance
        }
    }

    @Singleton
    @Provides
    fun provideToolDao(database: ToolDb): ToolDao = database.toolDao()

    private class DatabaseCallback() : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE.let {
                CoroutineScope(Dispatchers.IO).launch {
                    // Prepopulate database
                    with(it?.toolDao()) {
                        this?.insertTools(
                            Tool(
                                0,
                                "Wrench",
                                6,
                                6,
                                0,
                                R.drawable.ic_wrench
                            )
                        )
                        this?.insertTools(
                            Tool(
                                1,
                                "Cutter",
                                15,
                                15,
                                0,
                                R.drawable.ic_cutter
                            )
                        )
                        this?.insertTools(
                            Tool(
                                2,
                                "Pliers", 12,
                                12,
                                0,
                                R.drawable.ic_pliers
                            )
                        )
                        this?.insertTools(
                            Tool(
                                3,
                                "Screwdriver",
                                13,
                                13,
                                0,
                                R.drawable.ic_screwdriver
                            )
                        )
                        this?.insertTools(
                            Tool(
                                4,
                                "Welding Machine",
                                3,
                                3,
                                0,
                                R.drawable.ic_welding_machine
                            )
                        )
                        this?.insertTools(
                            Tool(
                                5,
                                "Welding Glasses",
                                7,
                                7,
                                0,
                                R.drawable.ic_welding_glasses
                            )
                        )
                        this?.insertTools(
                            Tool(
                                6, "Hammer",
                                4,
                                4,
                                0,
                                R.drawable.ic_hammer
                            )
                        )
                        this?.insertTools(
                            Tool(
                                7,
                                "Measuring Tape",
                                9,
                                9,
                                0,
                                R.drawable.ic_measuring_tape
                            )
                        )
                        this?.insertTools(
                            Tool(
                                8,
                                "Alan Key Set",
                                4,
                                4,
                                0,
                                R.drawable.ic_alan_key_set
                            )
                        )
                        this?.insertTools(Tool(9,
                            "Air Compressor",
                            2,
                            2,
                            0,
                            R.drawable.ic_air_compressor))

                        this?.insertFriend(Friend(0, "Brian", emptyList()))
                        this?.insertFriend(Friend(1, "Luke", emptyList()))
                        this?.insertFriend(Friend(2, "Letty", emptyList()))
                        this?.insertFriend(Friend(3, "Shaw", emptyList()))
                        this?.insertFriend(Friend(4, "Parker", emptyList()))
                    }
                }
            }
        }
    }
}