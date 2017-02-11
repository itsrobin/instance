//package test.javaIO.file.srvCodeComment;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by lifeng on 16/8/6.
// */
//public
//class ByteArrayInputStream extends InputStream {
//
//    /**
//     * An array of bytes that was provided
//     * by the creator of the stream. Elements <code>buf[0]</code>
//     * through <code>buf[count-1]</code> are the
//     * only bytes that can ever be read from the
//     * stream;  element <code>buf[pos]</code> is
//     * the next byte to be read.
//     * 一个字节流的字节数组.buf[0]到buf[count-1]的字节可以一直被用于读取.
//     * buf[pos]指的是下一个可以被读取的字节
//     */
//    protected byte buf[];
//
//    /**
//     * The index of the next character to read from the input stream buffer.
//     * This value should always be nonnegative
//     * and not larger than the value of <code>count</code>.
//     * The next byte to be read from the input stream buffer
//     * will be <code>buf[pos]</code>.
//     * 下一个可以从缓冲区读取的字节位置
//     * 这个只能为非负数,并且不能比count大
//     * 下一个从缓冲区读取的字节为buf[pos]
//     */
//    protected int pos;
//
//    /**
//     * The currently marked position in the stream.
//     * ByteArrayInputStream objects are marked at position zero by
//     * default when constructed.  They may be marked at another
//     * position within the buffer by the <code>mark()</code> method.
//     * The current buffer position is set to this point by the
//     * <code>reset()</code> method.
//     * <p>
//     * If no mark has been set, then the value of mark is the offset
//     * passed to the constructor (or 0 if the offset was not supplied).
//     *
//     * 在流中第一个被标记的位置
//     * ByteArrayInputStream对象被构造的时候mark的值默认为0.当调用mark()的时候,会被标记到缓冲区的
//     * 另外一个位置.当调用reset的时候,当前缓冲区的位置被指向mark对应的值
//     *
//     * 如果没有设置过mark的值,
//     *
//     * @since   JDK1.1
//     */
//    protected int mark = 0;
//
//    /**
//     * The index one greater than the last valid character in the input
//     * stream buffer.
//     * This value should always be nonnegative
//     * and not larger than the length of <code>buf</code>.
//     * It  is one greater than the position of
//     * the last byte within <code>buf</code> that
//     * can ever be read  from the input stream buffer.
//     *
//     * 这个值取决于缓冲区的最后一个有效字符大
//     * 值不小于0.
//     *
//     */
//    protected int count;
//
//    /**
//     * Creates a <code>ByteArrayInputStream</code>
//     * so that it  uses <code>buf</code> as its
//     * buffer array.
//     * The buffer array is not copied.
//     * The initial value of <code>pos</code>
//     * is <code>0</code> and the initial value
//     * of  <code>count</code> is the length of
//     * <code>buf</code>.
//     *
//     * 构建一个ByteArrayInputStream,这个它可以使用buf作为它的缓冲数组
//     * 缓冲区不能被复制
//     * pos的初始值为0,count的初始值为buf的长度
//     *
//     * @param   buf   the input buffer.
//     */
//    public ByteArrayInputStream(byte buf[]) {
//        this.buf = buf;
//        this.pos = 0;
//        this.count = buf.length;
//    }
//
//    /**
//     * Creates <code>ByteArrayInputStream</code>
//     * that uses <code>buf</code> as its
//     * buffer array. The initial value of <code>pos</code>
//     * is <code>offset</code> and the initial value
//     * of <code>count</code> is the minimum of <code>offset+length</code>
//     * and <code>buf.length</code>.
//     * The buffer array is not copied. The buffer's mark is
//     * set to the specified offset.
//     *
//     * 构建一个ByteArrayInputStream,这个它可以使用buf作为它的缓冲数组
//     * pos的初始值为bufa的偏移量(offset),count的初始值是offset+length和buf.length中最小的值
//     * 缓冲数组不能是拷贝的.缓冲区的标记值被设置为offset
//     *
//     * @param   buf      the input buffer.
//     * @param   offset   the offset in the buffer of the first byte to read.
//     * @param   length   the maximum number of bytes to read from the buffer.
//     */
//    public ByteArrayInputStream(byte buf[], int offset, int length) {
//        this.buf = buf;
//        this.pos = offset;
//        this.count = Math.min(offset + length, buf.length);
//        this.mark = offset;
//    }
//
//    /**
//     * Reads the next byte of data from this input stream. The value
//     * byte is returned as an <code>int</code> in the range
//     * <code>0</code> to <code>255</code>. If no byte is available
//     * because the end of the stream has been reached, the value
//     * <code>-1</code> is returned.
//     * <p>
//     * This <code>read</code> method
//     * cannot block.
//     * 读取输入流的下一个字节.字节以int形式返回,范围是0到255.如果已经到达流的底部且没有字节可用,将会
//     * 返回-1
//     *
//     * @return  the next byte of data, or <code>-1</code> if the end of the
//     *          stream has been reached.
//     */
//    public synchronized int read() {
//        return (pos < count) ? (buf[pos++] & 0xff) : -1;
//    }
//
//    /**
//     * Reads up to <code>len</code> bytes of data into an array of bytes
//     * from this input stream.
//     * If <code>pos</code> equals <code>count</code>,
//     * then <code>-1</code> is returned to indicate
//     * end of file. Otherwise, the  number <code>k</code>
//     * of bytes read is equal to the smaller of
//     * <code>len</code> and <code>count-pos</code>.
//     * If <code>k</code> is positive, then bytes
//     * <code>buf[pos]</code> through <code>buf[pos+k-1]</code>
//     * are copied into <code>b[off]</code>  through
//     * <code>b[off+k-1]</code> in the manner performed
//     * by <code>System.arraycopy</code>. The
//     * value <code>k</code> is added into <code>pos</code>
//     * and <code>k</code> is returned.
//     * <p>
//     * This <code>read</code> method cannot block.
//     *
//     * 从输入流将字节读到数组中
//     * 如果pos和count相等,将会返回-1标识着文件读取完毕.不然,读取的字节数k等于len,count或者pos的最小值
//     * 如果k是整数,那么buf[pos]到buf[pos+k-1]将会使用System.arraycopy拷贝到b[off]到b[off+k-1]
//     * read方法不会阻塞.
//     *
//     * @param   b     the buffer into which the data is read.
//     * @param   off   the start offset in the destination array <code>b</code>
//     * @param   len   the maximum number of bytes read.
//     * @return  the total number of bytes read into the buffer, or
//     *          <code>-1</code> if there is no more data because the end of
//     *          the stream has been reached.
//     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
//     * @exception  IndexOutOfBoundsException If <code>off</code> is negative,
//     * <code>len</code> is negative, or <code>len</code> is greater than
//     * <code>b.length - off</code>
//     */
//    public synchronized int read(byte b[], int off, int len) {
//        if (b == null) {
//            throw new NullPointerException();
//        } else if (off < 0 || len < 0 || len > b.length - off) {
//            throw new IndexOutOfBoundsException();
//        }
//
//        if (pos >= count) {
//            return -1;
//        }
//
//        int avail = count - pos;
//        if (len > avail) {
//            len = avail;
//        }
//        if (len <= 0) {
//            return 0;
//        }
//        System.arraycopy(buf, pos, b, off, len);
//        pos += len;
//        return len;
//    }
//
//    /**
//     * Skips <code>n</code> bytes of input from this input stream. Fewer
//     * bytes might be skipped if the end of the input stream is reached.
//     * The actual number <code>k</code>
//     * of bytes to be skipped is equal to the smaller
//     * of <code>n</code> and  <code>count-pos</code>.
//     * The value <code>k</code> is added into <code>pos</code>
//     * and <code>k</code> is returned.
//     *
//     * 跳过n个字节,如果输入流即将读取完毕,那么可能达不到预期跳过n个字节的目的.跳过的数可能比n要少
//     * 实际跳过的数量k和n,count-pos的最小值相等
//     * 最终结果k将会被作用到k上,并且会返回k
//     *
//     * @param   n   the number of bytes to be skipped.
//     * @return  the actual number of bytes skipped.
//     */
//    public synchronized long skip(long n) {
//        long k = count - pos;
//        if (n < k) {
//            k = n < 0 ? 0 : n;
//        }
//
//        pos += k;
//        return k;
//    }
//
//    /**
//     * Returns the number of remaining bytes that can be read (or skipped over)
//     * from this input stream.
//     * <p>
//     * The value returned is <code>count&nbsp;- pos</code>,
//     * which is the number of bytes remaining to be read from the input buffer.
//     *
//     * 返回剩余可读(或可跳过的字节)
//     * 返回值为count-pos,返回的字节数是缓冲区中剩余字节
//     * @return  the number of remaining bytes that can be read (or skipped
//     *          over) from this input stream without blocking.
//     */
//    public synchronized int available() {
//        return count - pos;
//    }
//
//    /**
//     * Tests if this <code>InputStream</code> supports mark/reset. The
//     * <code>markSupported</code> method of <code>ByteArrayInputStream</code>
//     * always returns <code>true</code>.
//     *
//     * 测试这个inputStream类是否支持mark或者reset.ByteArrayInputStream的markSupported方法总是返回
//     * true
//     * @since   JDK1.1
//     */
//    public boolean markSupported() {
//        return true;
//    }
//
//    /**
//     * Set the current marked position in the stream.
//     * ByteArrayInputStream objects are marked at position zero by
//     * default when constructed.  They may be marked at another
//     * position within the buffer by this method.
//     * <p>
//     * If no mark has been set, then the value of the mark is the
//     * offset passed to the constructor (or 0 if the offset was not
//     * supplied).
//     *
//     * 标记流的当前位置
//     * ByteArrayInputStream默认设置标记位置为0.它可以用这个方法标记缓冲区的其他位置.
//     * 如果没有调用该方法,默认标记的值为0或者为构造时传入的参数offset
//     *
//     * <p> Note: The <code>readAheadLimit</code> for this class
//     *  has no meaning.
//     *
//     * @since   JDK1.1
//     */
//    public void mark(int readAheadLimit) {
//        mark = pos;
//    }
//
//    /**
//     * Resets the buffer to the marked position.  The marked position
//     * is 0 unless another position was marked or an offset was specified
//     * in the constructor.
//     *
//     * 重置缓冲区的读取位置.默认重置的位置为0.除非在其他位置执行了mark()方法,或者已经在构造对象
//     * 的时候已经指定了mark的值.
//     */
//    public synchronized void reset() {
//        pos = mark;
//    }
//
//    /**
//     * Closing a <tt>ByteArrayInputStream</tt> has no effect. The methods in
//     * this class can be called after the stream has been closed without
//     * generating an <tt>IOException</tt>.
//     *
//     * 关闭ByteArrayInputStream不起作用.即使ByteArrayInputStream被关闭了,再调用close方法
//     * 也不会产生IOException
//     */
//    public void close() throws IOException {
//    }