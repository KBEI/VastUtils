/*
 * Copyright 2022 VastGui guihy2019@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gcode.vasttools.activity

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/9/14 12:22
// Description: 
// Documentation:

sealed interface VastActivityInterface {

    /**
     * Get the activity [ViewBinding].
     *
     * @since 0.0.9
     */
    fun getBinding(): ViewBinding

    /**
     * Get the activity [ViewModel].
     *
     * @since 0.0.9
     */
    fun getViewModel(): ViewModel

    /**
     * Get default [Snackbar] for activity.
     *
     * @since 0.0.9
     */
    fun getSnackbar(): Snackbar

    /**
     * True if you want to show the ActionBar,false otherwise.
     * Please call the method before super.onCreate.
     * ```kotlin
     * override fun onCreate(savedInstanceState: Bundle?) {
     *      enableActionBar(true)
     *      super.onCreate(savedInstanceState)
     *      ... //Other setting
     * }
     * ```
     *
     * @since 0.0.9
     */
    fun enableActionBar(enable: Boolean)

    /**
     * True if you want to set fullscreen,false otherwise. If you set
     * [enableFullScreen] to true,the ActionBar will not be shown.
     * Please call the method before super.onCreate.
     * ```kotlin
     * override fun onCreate(savedInstanceState: Bundle?) {
     *      enableFullScreen(true)
     *      super.onCreate(savedInstanceState)
     *      ... //Other setting
     * }
     * ```
     *
     * @since 0.0.9
     */
    fun enableFullScreen(enable: Boolean)

    /**
     * Return a [ViewModel].
     *
     * If you want to initialization a [ViewModel] with parameters,just do like
     * this:
     * ```kotlin
     * override fun createViewModel(modelClass: Class<out ViewModel>): ViewModel {
     *      return MainSharedVM("MyVM")
     * }
     * ```
     *
     * @param modelClass by default, Activity will get the [ViewModel] by
     *     `modelClass.newInstance()`.
     * @return the [ViewModel] of the Activity.
     * @since 0.0.9
     */
    fun createViewModel(modelClass: Class<out ViewModel>): ViewModel

}