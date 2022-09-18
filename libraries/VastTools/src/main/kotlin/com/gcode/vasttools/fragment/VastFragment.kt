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

package com.gcode.vasttools.fragment

import androidx.fragment.app.Fragment
import com.gcode.vasttools.activity.VastActivity
import com.gcode.vasttools.base.BaseActive
import com.gcode.vasttools.extension.cast

// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/9/14 17:11
// Description: 
// Documentation:

abstract class VastFragment : Fragment(), VastFragmentInterface, BaseActive {

    override fun getBaseActivity(): VastActivity {
        return cast(requireActivity())
    }

}