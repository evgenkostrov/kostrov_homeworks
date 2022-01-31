package com.epam.kostrov_homeworks.data.repository

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.epam.kostrov_homeworks.domain.Repository
import com.epam.kostrov_homeworks.domain.TextTrain
import java.io.*
import java.lang.Exception

class ExternalStorageRepository(private val context: Context) : Repository {

    private val folder = context.getExternalFilesDir("txt")
    private val fileName = "TextTrain.txt"
    private val file = File(folder, fileName)
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun writeTextData(file: File, text: String) {
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            fos.write(text.toByteArray())
            Toast.makeText(context, "Done" + file.absolutePath, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun readTextData(file: File): String {
        val fis = FileInputStream(file)
        val reader = BufferedReader(fis.reader())
        val content = StringBuilder()
        try {
            var line = reader.readLine()
            while (line != null) {
                content.append(line)
                line = reader.readLine()
            }
        } finally {
            reader.close()
        }
        return content.toString()
    }

    override fun get(): TextTrain {
        return TextTrain(readTextData(file))
    }

    override fun set(textTrain: TextTrain) {
        if (isExternalStorageWritable()) {
            writeTextData(file, textTrain.txt)
        }
    }
}

