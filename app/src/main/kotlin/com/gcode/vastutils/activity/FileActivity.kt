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

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.gcode.vasttools.activity.VastVbActivity
import com.gcode.vasttools.utils.FileUtils
import com.gcode.vasttools.utils.FileUtils.appExternalCacheDir
import com.gcode.vasttools.utils.FileUtils.appExternalFilesDir
import com.gcode.vasttools.utils.FileUtils.appInternalCacheDir
import com.gcode.vasttools.utils.FileUtils.appInternalFilesDir
import com.gcode.vasttools.utils.FileUtils.deleteDir
import com.gcode.vasttools.utils.FileUtils.makeDir
import com.gcode.vasttools.utils.FileUtils.saveFile
import com.gcode.vasttools.utils.FileUtils.writeFile
import com.gcode.vasttools.utils.LogUtils
import com.gcode.vasttools.utils.UriUtils
import com.gcode.vastutils.databinding.ActivityFileBinding
import java.io.File
import java.io.FileWriter


// Author: SakurajimaMai
// Email: guihy2019@gmail.com
// Date: 2022/5/31
// Description: 
// Documentation:

class FileActivity : VastVbActivity<ActivityFileBinding>() {

    private val openGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            UriUtils.getRealPath(it!!)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.i(defaultTag, appInternalFilesDir().path)
        LogUtils.i(defaultTag, appInternalCacheDir().path)
        LogUtils.i(defaultTag, appExternalFilesDir(null)?.path)
        LogUtils.i(defaultTag, appExternalCacheDir()?.path)

        mBinding.openGallery.setOnClickListener {
            openGalleryLauncher.launch("image/*")
        }

        mBinding.openSnackBar.setOnClickListener {
            mSnackbar.show()
        }

        saveFile(File(appInternalFilesDir().path,"save.txt"))

        makeDir(File(appInternalFilesDir().path,"makeDir"))

        makeDir(File(appInternalFilesDir().path,"makeDir2"))

        val res = deleteDir(File(appInternalFilesDir().path,"makeDir2"))

        LogUtils.i(defaultTag, res.toString())

        val res1 = writeFile(File(appInternalFilesDir().path, "picture.jpg"),
            object : FileUtils.WriteEventListener {
                override fun writeEvent(fileWriter: FileWriter) {
                    fileWriter.write("Hello")
                }
            })

        LogUtils.i(defaultTag, res1.toString())

        val res2 = writeFile(File(appInternalFilesDir().path, "save.txt"),
            object : FileUtils.WriteEventListener {
                override fun writeEvent(fileWriter: FileWriter) {
                    fileWriter.write("Hello")
                }
            })

        LogUtils.i(defaultTag, res2.toString())
    }
}