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

package com.example.androidkotlintemplate

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Database entities go in this file. These are responsible for reading and writing from the
 * database.
 */


/**
 * DatabaseVideo represents a video entity in the database.
 */
//@Entity
//data class DatabaseCharacter constructor(
//    @PrimaryKey
//    val id: Int,
//    val comics: Comics,
//    val description: String,
//    val events: EventsCharacters,
//    val modified: String,
//    val name: String,
//    val resourceURI: String,
//    val series: CharactersSeries,
//    val stories: Stories,
//    val thumbnail: Thumbnail,
//    val urls: List<Url>
//)

@Entity
data class DatabaseCharacterInfo constructor(
    @PrimaryKey
    val url: String,
    val description: String
)


/**
 * Map DatabaseVideos to domain entities
 */
//fun List<DatabaseCharacterInfo>.asDomainModel(): List<CharacterInfo> {
//    return map {
//        CharacterInfo(
//            url = it.url,
//            description = it.description
//        )
//    }
//}