package kz.nurs.android.drive.helper

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.api.client.http.FileContent
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import kz.nurs.android.drive.helper.GoogleDriveFileHolder
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.OutputStream
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DriveServiceHelper(private val mDriveService: Drive) {
    private val mExecutor: Executor =
        Executors.newSingleThreadExecutor()

    fun createNewFile(path: String?): Task<String> {
        return Tasks.call(mExecutor,
            Callable {
                val metaData =
                    File()
                metaData.name = "newTextFile.txt"
                val file = java.io.File(path)
                file.createNewFile()
                val mediaContent = FileContent("text/plain", file)
                var myFile: File? = null
                try {
                    val myWriter = FileWriter("newTextFile.txt")
                    myWriter.write("Files in Java might be tricky, but it is fun enough!")
                    myWriter.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                try {
                    myFile = mDriveService.files().create(metaData, mediaContent).execute()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                if (myFile == null) {
                    throw IOException("did not created")
                }
                myFile.id
            }
        )
    }

    val files: Task<*>
        get() = Tasks.call(mExecutor,
            Callable<Any?> {
                var pageToken: String? = null
                do {
                    val result = mDriveService.files().list()
                        .setQ("mimeType='application/pdf'")
                        .setSpaces("drive")
                        .setFields("nextPageToken, files(id, name)")
                        .setPageToken(pageToken)
                        .execute()
                    for (file in result.files) {
                        Log.d(
                            "TAGg",
                            "fileName: " + file.name + " fileID: " + file.id
                        )
                    }
                    pageToken = result.nextPageToken
                } while (pageToken != null)
                null
            }
        )

    fun downloadFile(
        targetFile: java.io.File?,
        fileId: String?
    ): Task<File?> {
        return Tasks.call(
            mExecutor,
            Callable<File?> {
                val outputStream: OutputStream = FileOutputStream(targetFile)
                mDriveService.files()[fileId].executeMediaAndDownloadTo(outputStream)
                null
            }
        )
    }

    fun searchFile(fileName: String): Task<GoogleDriveFileHolder> {
        return Tasks.call<GoogleDriveFileHolder>(mExecutor,
            Callable<GoogleDriveFileHolder> {
                val result = mDriveService.files().list()
                    .setQ("name = '$fileName'")
                    .setSpaces("drive")
                    .setFields("files(id, name,size,createdTime,modifiedTime,starred)")
                    .execute()
                val googleDriveFileHolder =
                    GoogleDriveFileHolder()
                if (result.files.size > 0) {
                    googleDriveFileHolder.setId(result.files[0].id)
                    googleDriveFileHolder.setName(result.files[0].name)
                    googleDriveFileHolder.setModifiedTime(
                        result.files[0].modifiedTime
                    )
                    googleDriveFileHolder.setSize(result.files[0].getSize())
                }
                Log.i("TAGg", result.toString())
                googleDriveFileHolder
            }
        )
    }

}