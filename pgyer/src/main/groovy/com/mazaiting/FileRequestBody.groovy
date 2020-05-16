package com.mazaiting

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Okio
import okio.RealBufferedSink
import okio.Sink

/**
 * 文件请求体, 可打印上传进度
 */
public class FileRequestBody extends RequestBody {
    private RequestBody mRequestBody
    private LoadingListener mLoadingListener
    private long mContentLength

    public FileRequestBody(RequestBody requestBody, LoadingListener loadingListener) {
        mRequestBody = requestBody
        mLoadingListener = loadingListener
    }

    //文件的总长度
    @Override
    public long contentLength() {
        try {
            if (mContentLength == 0)
                mContentLength = mRequestBody.contentLength()
            return mContentLength
        } catch (IOException e) {
            e.printStackTrace()
        }
        return -1
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType()
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        ByteSink byteSink = new ByteSink(sink)
//        BufferedSink mBufferedSink = Okio.buffer(byteSink)
        BufferedSink mBufferedSink = new RealBufferedSink(byteSink)
        mRequestBody.writeTo(mBufferedSink)
        mBufferedSink.flush()
    }

    private final class ByteSink extends ForwardingSink {
        //已经上传的长度
        private long mByteLength = 0L

        ByteSink(Sink delegate) {
            super(delegate)
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            mByteLength += byteCount
            mLoadingListener.onProgress(mByteLength, contentLength())
        }
    }

    /**
     * 上传进度监听
     */
    public interface LoadingListener {
        /**
         * 进度
         * @param currentLength 当前长度
         * @param contentLength 总内容长度
         */
        void onProgress(long currentLength, long contentLength);
    }

}