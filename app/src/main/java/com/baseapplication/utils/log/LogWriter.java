/*******************************************************************************
 * Copyright 2016 Keval Patel.
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
 ******************************************************************************/

package com.baseapplication.utils.log;

import android.content.Context;

import com.baseapplication.R;
import com.baseapplication.utils.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by KP on 02-May-16.<p>
 */
public class LogWriter {

    public LogWriter() {
    }

    public void writeLog(Context context, String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

        try {
            File file = new File(FileUtils.getCacheDir(context), "log.txt");
            // if file doesn't exists, then create it
            if (!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sdf.format(new Date()) + ": " + content + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
