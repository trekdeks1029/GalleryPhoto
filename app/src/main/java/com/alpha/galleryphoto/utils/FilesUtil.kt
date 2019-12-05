package com.alpha.galleryphoto.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException
import java.io.OutputStream


class FilesUtil {

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver, inImage,
            "Title", null
        )
        return Uri.parse(path)
    }

    fun normalizeImageForUri(
        context: Context,
        uri: Uri
    ) {

        try {
            val exif = ExifInterface(uri.path!!)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )
            val bitmap =
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            val rotatedBitmap = rotateBitmap(bitmap, orientation)

            rotatedBitmap?.let {
                if (bitmap != it) {
                    saveBitmapToFile(context, rotatedBitmap!!, uri)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("trekdeks", "error")
        }
    }


    private fun rotateBitmap(bitmap: Bitmap, orientation: Int): Bitmap? {
        val matrix = Matrix()

        when (orientation) {
            ExifInterface.ORIENTATION_NORMAL -> return bitmap
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.setScale(-1f, 1f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.setRotate(180f)
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> {
                matrix.setRotate(180f)
                matrix.postScale(-1f, 1f)
            }
            ExifInterface.ORIENTATION_TRANSPOSE -> {
                matrix.setRotate(90f)
                matrix.postScale(-1f, 1f)
            }
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.setRotate(90f)
            ExifInterface.ORIENTATION_TRANSVERSE -> {
                matrix.setRotate(-90f)
                matrix.postScale(-1f, 1f)
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.setRotate(-90f)
            else -> return bitmap
        }



        return try {

            var width = bitmap.width
            var height = bitmap.height

            if (bitmap.height > 1000 || bitmap.width > 1000) {
                for (i in 2..10) {
                    if (bitmap.height / i < 1000 && bitmap.width / i < 1000) {
                        width = width.div(i)
                        height = height.div(i)
                        break
                    }
                }
            }


            var bitmapAs = Bitmap.createScaledBitmap(
                bitmap,
                width,
                height,
                false
            )


            val bmRotated = Bitmap.createBitmap(
                bitmapAs,
                0,
                0,
                bitmapAs.width,
                bitmapAs.height,
                matrix,
                true
            )

            bitmap.recycle()
            bmRotated
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            null
        }
    }

    private fun saveBitmapToFile(
        context: Context,
        croppedImage: Bitmap,
        saveUri: Uri?
    ) {
        if (saveUri != null) {
            var outputStream: OutputStream? = null
            try {
                outputStream = context.contentResolver.openOutputStream(saveUri)
                if (outputStream != null) {
                    croppedImage.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
                }
            } catch (e: IOException) {
            } finally {
                closeSilently(outputStream)
                croppedImage.recycle()
            }
        }
    }

    private fun closeSilently(c: Closeable?) {
        if (c == null) {
            return
        }
        try {
            c.close()
        } catch (t: Throwable) { // Do nothing
        }
    }
}