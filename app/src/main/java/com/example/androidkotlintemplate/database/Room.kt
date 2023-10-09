/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidkotlintemplate.database

import androidx.room.*

@Entity
data class DatabaseCharacterInfo constructor(
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String,
    val description: String,
)

@Dao
interface CharacterDao {
    @Query("select * from databasecharacterinfo order by name ")
    suspend fun getCharacters(): List<DatabaseCharacterInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<DatabaseCharacterInfo>)
}

@Database(entities = [DatabaseCharacterInfo::class], version = 1)
abstract class CharactersDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
}