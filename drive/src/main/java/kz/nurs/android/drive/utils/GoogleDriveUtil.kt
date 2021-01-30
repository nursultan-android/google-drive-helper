package kz.nurs.android.drive.utils

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import kz.nurs.android.drive.helper.DriveServiceHelper
import java.util.*

class GoogleDriveUtil(val context: Context) {

    val scope=Scope(DriveScopes.DRIVE)
    lateinit var authIntent: Intent

    fun signIn(data: Intent): Task<GoogleSignInAccount> {
        return GoogleSignIn.getSignedInAccountFromIntent(data)
    }

    fun checkException(exception: Exception): Boolean {
        if (exception is UserRecoverableAuthIOException){
            authIntent = exception.intent
        }
        return exception is UserRecoverableAuthIOException
    }

    fun createGoogleDriveHelper(acc: GoogleSignInAccount): DriveServiceHelper {
        val credential: GoogleAccountCredential = GoogleAccountCredential.usingOAuth2(
            context, Collections.singleton(DriveScopes.DRIVE)
        )
        credential.selectedAccount = acc.account
        val drive = Drive.Builder(
            AndroidHttp.newCompatibleTransport(),
            GsonFactory(),
            credential)
            .setApplicationName("OnlineMektep")
            .build()
        return DriveServiceHelper(drive)
    }
}