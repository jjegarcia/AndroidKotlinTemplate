/*
 * Copyright (C) 2019 Google Inc.
 *gi
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

package com.example.androidkotlintemplate.network

import com.example.androidkotlintemplate.database.CharactersDatabase
import com.example.androidkotlintemplate.database.DatabaseCharacterInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository for fetching characters data from the network and storing them on disk
 */

interface CharactersRepository {
    suspend fun characters(): List<DatabaseCharacterInfo>
    suspend fun refreshCharacters(characters: List<DatabaseCharacterInfo>)
}

class CharactersRepositoryImpl @Inject constructor(
    private val database: CharactersDatabase
) : CharactersRepository {
    override suspend fun characters(): List<DatabaseCharacterInfo> =
        database.characterDao.getCharacters()

    override suspend fun refreshCharacters(characters: List<DatabaseCharacterInfo>) {
        withContext(Dispatchers.IO) {
            database.characterDao.insertAll(characters)
        }
    }
}

