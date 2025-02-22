/*
 * Copyright 2022 VastGui
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

package com.gcode.vastutils.activity

import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.os.Bundle
import com.gcode.vasttools.activity.VastVbActivity
import com.gcode.vasttools.utils.ColorUtils
import com.gcode.vasttools.utils.ShapeAndStateUtils
import com.gcode.vastutils.databinding.ActivityShapeBinding

class ShapeActivity : VastVbActivity<ActivityShapeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val states = arrayOfNulls<IntArray>(6).apply {
            set(0, intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled))
            set(1, intArrayOf(android.R.attr.state_focused, android.R.attr.state_enabled))
            set(2, intArrayOf(-android.R.attr.state_focused, android.R.attr.state_enabled))
            set(3, intArrayOf(android.R.attr.state_focused))
            set(4, intArrayOf(android.R.attr.state_window_focused))
            set(5, intArrayOf())
        }

        val colorList = IntArray(6).apply {
            set(0, ColorUtils.colorHex2Int("#00F260"))
            set(1, ColorUtils.colorHex2Int("#FFFFFF"))
            set(2, ColorUtils.colorHex2Int("#0575E6"))
            set(3, ColorUtils.colorHex2Int("#FFFFFF"))
            set(4, ColorUtils.colorHex2Int("#EF3B36"))
            set(5, ColorUtils.colorHex2Int("#0575E6"))
        }

        val btnBK = ShapeAndStateUtils.create()
            .setShape(RECTANGLE)
            .setRadius(50f)
            .setGradient(45, ColorUtils.colorHex2Int("#0F2027"),ColorUtils.colorHex2Int("#78ffd6"))
            .setBgColorStateList(states,colorList)
            .build()

        mBinding.btn1.background = btnBK
    }

}