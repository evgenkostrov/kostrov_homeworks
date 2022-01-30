package com.epam.kostrov_homeworks.repository

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
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

    private fun readTextData(file: File): String? {
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(file)
            val i = fis.read()
            val buffer = StringBuffer()
            while (i != -1) {
                buffer.append(i)
            }
            return buffer.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fis != null) {
                try {
                    fis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }


    override fun get(): TextTrain? {

            return readTextData(file)?.let { TextTrain(it) }

    }


    override fun set(textTrain: TextTrain) {
        if (isExternalStorageWritable()) {
            writeTextData(file, textTrain.txt)
        }
    }
}

