package com.example.githubusers.feature.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.githubusers.data.db.AppDatabase
import com.example.githubusers.data.db.dao.UserFavoriteDao
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class MyContentProvider : ContentProvider() {
    @Inject
    lateinit var userFavoriteDao: UserFavoriteDao

    companion object {
        private const val USER = 1
        private const val AUTHORITY = "com.example.githubusers.provider"
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "user_favorite_database", USER)
        }
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException()
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException()
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException()
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when(sUriMatcher.match(uri)) {
            USER -> userFavoriteDao.cursorGetAllUserFavorite()
            else -> null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        throw UnsupportedOperationException()
    }
}