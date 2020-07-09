package com.mazaiting.utils

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*
import java.io.IOException

/**
 * 文件请求体, 可打印上传进度
 */
class FileRequestBody(
    private val mRequestBody: RequestBody,
    private val mLoadingListener: LoadingListener
) : RequestBody() {
    private var mContentLength: Long = 0

    //文件的总长度
    override fun contentLength(): Long {
        try {
            if (mContentLength == 0L) mContentLength = mRequestBody.contentLength()
            return mContentLength
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return -1
    }

    override fun contentType(): MediaType? {
        return mRequestBody.contentType()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val byteSink = ByteSink(sink)
        val mBufferedSink = Okio.buffer(byteSink)
//        val mBuffer*/edSink: BufferedSink = RealBufferedSink(byteSink)
        mRequestBody.writeTo(mBufferedSink)
        mBufferedSink.flush()
    }

    private inner class ByteSink internal constructor(delegate: Sink) : ForwardingSink(delegate) {
        //已经上传的长度
        private var mByteLength = 0L

        @Throws(IOException::class)
        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)
            mByteLength += byteCount
            mLoadingListener.onProgress(mByteLength, contentLength())
        }
    }

    /**
     * 上传进度监听
     */
    interface LoadingListener {
        /**
         * 进度
         * @param currentLength 当前长度
         * @param contentLength 总内容长度
         */
        fun onProgress(currentLength: Long, contentLength: Long)
    }

}