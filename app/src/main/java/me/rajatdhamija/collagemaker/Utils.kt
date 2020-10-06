package me.rajatdhamija.collagemaker

import android.graphics.*

/**
 *
 * @param bmp input bitmap
 * @param contrast 0..10 1 is default
 * @param brightness -255..255 0 is default
 * @return new bitmap
 */
fun changeBitmapContrastBrightness(
    bmp: Bitmap,
    contrast: Float,
    brightness: Float,
    saturation: Float
): Bitmap? {
    var cm = floatArrayOf(
        contrast,
        0f,
        0f,
        0f,
        brightness,
        0f,
        contrast,
        0f,
        0f,
        brightness,
        0f,
        0f,
        contrast,
        0f,
        brightness,
        0f,
        0f,
        0f,
        1f,
        0f
    )

    val invSat: Float = 1 - saturation
    val R = 0.213f * invSat
    val G = 0.715f * invSat
    val B = 0.072f * invSat
    cm[0] = R + saturation + contrast
    cm[1] = G
    cm[2] = B

    cm[5] = R
    cm[6] = G + saturation
    cm[7] = B + contrast

    cm[10] = R
    cm[11] = G
    cm[12] = B + saturation + contrast

//    val ret = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
//    val canvas = Canvas(ret)
//    val paint = Paint()
//    paint.colorFilter = ColorMatrixColorFilter(cm)
//    canvas.drawBitmap(bmp, 0f, 0f, paint)
//    return ret


    val bitmapResult = Bitmap.createBitmap(bmp.width, bmp.height, Bitmap.Config.ARGB_8888)
    val canvasResult = Canvas(bitmapResult)
    val paint = Paint()
    val colorMatrix = ColorMatrix()
    colorMatrix.set(cm)

    val filter = ColorMatrixColorFilter(colorMatrix)
    paint.colorFilter = filter
    canvasResult.drawBitmap(bmp, 0f, 0f, paint)
    return bitmapResult
}


fun adjustBitmapExposure(
    bitmap: Bitmap,
    exposure: Float
): Bitmap? {
    val colorTransform = floatArrayOf(
        exposure, 0f, 0f, 0f, 0f,
        0f, exposure, 0f, 0f, 0f,
        0f, 0f, exposure, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    colorMatrix.set(colorTransform)
    val colorFilter =
        ColorMatrixColorFilter(colorMatrix)
    val paint = Paint()
    paint.colorFilter = colorFilter
    val resultBitmap =
        bitmap.copy(Bitmap.Config.ARGB_8888, true)
    val canvas = Canvas(resultBitmap)
    canvas.drawBitmap(resultBitmap, 0f, 0f, paint)
    return resultBitmap
}

fun brightBitmap(
    bitmap: Bitmap,
    brightness: Float
): Bitmap? {
    val colorTransform = floatArrayOf(
        1f,
        0f,
        0f,
        0f,
        brightness,
        0f,
        1f,
        0f,
        0f,
        brightness,
        0f,
        0f,
        1f,
        0f,
        brightness,
        0f,
        0f,
        0f,
        1f,
        0f
    )
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    colorMatrix.set(colorTransform)
    val colorFilter =
        ColorMatrixColorFilter(colorMatrix)
    val paint = Paint()
    paint.colorFilter = colorFilter
    val resultBitmap =
        bitmap.copy(Bitmap.Config.ARGB_8888, true)
    val canvas = Canvas(resultBitmap)
    canvas.drawBitmap(resultBitmap, 0f, 0f, paint)
    return resultBitmap
}

fun saturationBitmap(
    bitmap: Bitmap,
    saturation: Float
): Bitmap? {
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(saturation)
    val colorFilter =
        ColorMatrixColorFilter(colorMatrix)
    val paint = Paint()
    paint.colorFilter = colorFilter
    val resultBitmap =
        bitmap.copy(Bitmap.Config.ARGB_8888, true)
    val canvas = Canvas(resultBitmap)
    canvas.drawBitmap(resultBitmap, 0f, 0f, paint)
    return resultBitmap
}

