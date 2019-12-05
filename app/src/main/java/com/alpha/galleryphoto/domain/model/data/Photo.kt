package com.alpha.galleryphoto.domain.model.data

import android.graphics.Bitmap
import java.io.Serializable

class Photo : Serializable{
    var small = ""
    var large = ""
    var bitmap: Bitmap? = null
}