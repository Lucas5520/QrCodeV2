package com.example.qrcodev2

import android.graphics.ImageFormat.*
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.multi.qrcode.QRCodeMultiReader


class QRCodeImageAnalyzer(val listener: QRCodeFoundListener) : ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        if (image.format == YUV_420_888 || image.format == YUV_422_888 || image.format == YUV_444_888) {
            val byteBuffer = image.planes[0].buffer
            val imageData = ByteArray(byteBuffer.capacity())
            byteBuffer[imageData]
            val source = PlanarYUVLuminanceSource(
                imageData,
                image.width,
                image.height,
                0,
                0,
                image.width,
                image.height,
                false
            )

            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

            try {
                val result = QRCodeMultiReader().decode(binaryBitmap)
                listener?.onQRCodeFound(result.toString())
            } catch (e: FormatException) {
                e.printStackTrace()
            } catch (e: ChecksumException) {
                e.printStackTrace()
            } catch (e: NotFoundException) {
                e.printStackTrace()
            }
        }
        image.close()
    }

}