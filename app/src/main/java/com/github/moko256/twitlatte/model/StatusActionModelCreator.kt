/*
 * Copyright 2015-2019 The twitlatte authors
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

package com.github.moko256.twitlatte.model

import android.content.Context
import com.github.moko256.latte.client.twitter.CLIENT_TYPE_TWITTER
import com.github.moko256.twitlatte.entity.Client
import com.github.moko256.twitlatte.model.base.StatusActionModel
import com.github.moko256.twitlatte.model.impl.OpenInBrowserStatusActionModelImpl
import com.github.moko256.twitlatte.model.impl.StatusActionModelImpl
import com.github.moko256.twitlatte.preferenceRepository
import com.github.moko256.twitlatte.repository.KEY_OPEN_IN_BROWSER_IN_TWITTER

fun createStatusActionModel(context: Context, client: Client): StatusActionModel {
    val statusActionModelImpl = StatusActionModelImpl(client.apiClient, client.statusCache)

    return if (
            preferenceRepository.getBoolean(KEY_OPEN_IN_BROWSER_IN_TWITTER, true) &&
            client.accessToken.clientType == CLIENT_TYPE_TWITTER
    ) {
        OpenInBrowserStatusActionModelImpl(statusActionModelImpl, client.statusCache, context)
    } else {
        statusActionModelImpl
    }
}