/*
 * Copyright © 2019 Birju Vachhani (https://github.com/BirjuVachhani)
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

package com.birjuvachhani.locus

import android.os.Parcelable
import androidx.annotation.StringRes
import com.google.android.gms.location.LocationRequest
import kotlinx.android.parcel.Parcelize

/*
 * Created by Birju Vachhani on 07 February 2019
 * Copyright © 2019 locus-android. All rights reserved.
 */

/**
 * Data class to store location related configurations which includes dialog messages and instance of LocationRequest class.
 * */
@LocusMarker
@Parcelize
data class Configuration(
    @StringRes
    var rationaleText: Int = R.string.rationale_text,
    @StringRes
    var rationaleTitle: Int = R.string.rationale_title,
    @StringRes
    var blockedTitle: Int = R.string.blocked_title,
    @StringRes
    var blockedText: Int = R.string.blocked_text,
    @StringRes
    var resolutionTitle: Int = R.string.resolution_title,
    @StringRes
    var resolutionText: Int = R.string.resolution_text,
    internal var locationRequest: LocationRequest = getDefaultRequest(),
    var shouldResolveRequest: Boolean = true,
    var enableBackgroundUpdates: Boolean = false,
    var forceBackgroundUpdates: Boolean = false
) : Parcelable {

    companion object {
        internal const val INTERVAL_IN_MS = 1000L
        internal const val FASTEST_INTERVAL_IN_MS = 1000L
        internal const val MAX_WAIT_TIME_IN_MS = 1000L
    }

    /**
     * Create an instance of LocationRequest class
     * @param func is a LocationRequest's lambda receiver which provide a block to configure LocationRequest
     * */
    fun request(func: (@LocusMarker LocationRequest).() -> Unit) {
        locationRequest = LocationRequest().apply(func)
    }

}

/**
 * Creates [LocationRequest] instance with default settings
 * @return LocationRequest
 */
internal fun getDefaultRequest(): LocationRequest {
    return LocationRequest().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = Configuration.INTERVAL_IN_MS
        fastestInterval = Configuration.FASTEST_INTERVAL_IN_MS
        maxWaitTime = Configuration.MAX_WAIT_TIME_IN_MS
    }
}
