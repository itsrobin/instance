//
//
//package java.io;
//
//import java.nio.channels.FileChannel;
//
//import sun.nio.ch.FileChannelImpl;
//
//
///**
// * A <code>FileInputStream</code> obtains input bytes
// * from a file in a file system. What files
// * are  available depends on the host environment.
// * <p>
// * <p>
// * <p><code>FileInputStream</code> is meant for reading streams of raw bytes
// * such as image data. For reading streams of characters, consider using
// * <code>FileReader</code>.
// * FileInputStream从文件系统的文件中获得字节.文件是否可用取决于主机的环境
// * FileInputStream可以读取未经处理的字节,例如图片数据.如果想阅读字符流,可以考虑使用FileReader
// *
// * @author Arthur van Hoff
// * @see java.io.File
// * @see java.io.FileDescriptor
// * @see java.io.FileOutputStream
// * @see java.nio.file.Files#newInputStream
// * @since JDK1.0
// */
//public class FileInputStream extends InputStream {
//    /* File Descriptor - handle to the open file */
//    private final FileDescriptor fd;
//
//    /**
//     * The path of the referenced file
//     * (null if the stream is created with a file descriptor)
//     * 文件的引用路径,如果用FileDescriptor来构造流的话,这个路径可以为空
//     */
//    private final String path;
//
//    private FileChannel channel = null;
//
//    private final Object closeLock = new Object();
//    private volatile boolean closed = false;
//
//    /**
//     * Creates a <code>FileInputStream</code> by
//     * opening a connection to an actual file,
//     * the file named by the path name <code>name</code>
//     * in the file system.  A new <code>FileDescriptor</code>
//     * object is created to represent this file
//     * connection.
//     * <p>
//     * First, if there is a security
//     * manager, its <code>checkRead</code> method
//     * is called with the <code>name</code> argument
//     * as its argument.
//     * <p>
//     * If the named file does not exist, is a directory rather than a regular
//     * file, or for some other reason cannot be opened for reading then a
//     * <code>FileNotFoundException</code> is thrown.
//     * <p>
//     * 通过开启和一个文件的连接来创建一个FileInputStream,这个文件的名称为该文件
//     * 在系统中的路径.一个新建的FileDescriptor对象也代表着文件连接
//     * <p>
//     * 首先,如果有安全管理器,它的checkRead方法会使用name参数
//     * <p>
//     * 如果这个路径的文件不存在,是一个文件夹而不是普通的文件,或者因为某些原因不能被读取,将会抛出FileNotFoundException异常.
//     *
//     * @param name the system-dependent file name.
//     * @throws FileNotFoundException if the file does not exist,
//     *                               is a directory rather than a regular file,
//     *                               or for some other reason cannot be opened for
//     *                               reading.
//     * @throws SecurityException     if a security manager exists and its
//     *                               <code>checkRead</code> method denies read access
//     *                               to the file.
//     * @see java.lang.SecurityManager#checkRead(java.lang.String)
//     */
//    public FileInputStream(String name) throws FileNotFoundException {
//        this(name != null ? new File(name) : null);
//    }
//
//    /**
//     * Creates a <code>FileInputStream</code> by
//     * opening a connection to an actual file,
//     * the file named by the <code>File</code>
//     * object <code>file</code> in the file system.
//     * A new <code>FileDescriptor</code> object
//     * is created to represent this file connection.
//     * <p>
//     * First, if there is a security manager,
//     * its <code>checkRead</code> method  is called
//     * with the path represented by the <code>file</code>
//     * argument as its argument.
//     * <p>
//     * If the named file does not exist, is a directory rather than a regular
//     * file, or for some other reason cannot be opened for reading then a
//     * <code>FileNotFoundException</code> is thrown.
//     * <p>
//     * 通过开启和一个文件的连接来创建一个FileInputStream,这个文件的名称为该文件
//     * 在系统中的路径.一个新建的FileDescriptor对象也代表着文件连接
//     * <p>
//     * 首先,如果有安全管理器,它的checkRead方法会使用name参数
//     * <p>
//     * 如果这个路径的文件不存在,是一个文件夹而不是普通的文件,或者因为某些原因不能被读取,将会抛出FileNotFoundException异常.
//     *
//     * @param file the file to be opened for reading.
//     * @throws FileNotFoundException if the file does not exist,
//     *                               is a directory rather than a regular file,
//     *                               or for some other reason cannot be opened for
//     *                               reading.
//     * @throws SecurityException     if a security manager exists and its
//     *                               <code>checkRead</code> method denies read access to the file.
//     * @see java.io.File#getPath()
//     * @see java.lang.SecurityManager#checkRead(java.lang.String)
//     */
//    public FileInputStream(File file) throws FileNotFoundException {
//        String name = (file != null ? file.getPath() : null);
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkRead(name);
//        }
//        if (name == null) {
//            throw new NullPointerException();
//        }
//        if (file.isInvalid()) {
//            throw new FileNotFoundException("Invalid file path");
//        }
//        fd = new FileDescriptor();
//        fd.attach(this);
//        path = name;
//        open(name);
//    }
//
//    /**
//     * Creates a <code>FileInputStream</code> by using the file descriptor
//     * <code>fdObj</code>, which represents an existing connection to an
//     * actual file in the file system.
//     * 通过使用文件描述符"dfObj"来构建一个FileInputStream,文件描述符代表着和文件系统中
//     * 已经存在的文件的连接
//     * <p>
//     * If there is a security manager, its <code>checkRead</code> method is
//     * called with the file descriptor <code>fdObj</code> as its argument to
//     * see if it's ok to read the file descriptor. If read access is denied
//     * to the file descriptor a <code>SecurityException</code> is thrown.
//     * 如果存在安全管理,它的checkRead方法使用fdObj作为参数,以此来判断这个文件描述符是否可读.如果
//     * 不能访问这个文件描述符,将会抛出SecurityException.
//     * <p>
//     * If <code>fdObj</code> is null then a <code>NullPointerException</code>
//     * is thrown.
//     * fdObj为空时将会抛出NullPointerException
//     * <p>
//     * This constructor does not throw an exception if <code>fdObj</code>
//     * is {@link java.io.FileDescriptor#valid() invalid}.
//     * However, if the methods are invoked on the resulting stream to attempt
//     * I/O on the stream, an <code>IOException</code> is thrown.
//     * 如果fdObj是无效的,构造器不会抛出异常
//     * 然而如果执行方法尝试io操作的话,将会抛出IOException
//     *
//     * @param fdObj the file descriptor to be opened for reading.
//     * @throws SecurityException if a security manager exists and its
//     *                           <code>checkRead</code> method denies read access to the
//     *                           file descriptor.
//     * @see SecurityManager#checkRead(java.io.FileDescriptor)
//     */
//    public FileInputStream(FileDescriptor fdObj) {
//        SecurityManager security = System.getSecurityManager();
//        if (fdObj == null) {
//            throw new NullPointerException();
//        }
//        if (security != null) {
//            security.checkRead(fdObj);
//        }
//        fd = fdObj;
//        path = null;
//
//        /*
//         * FileDescriptor is being shared by streams.
//         * Register this stream with FileDescriptor tracker.
//         * FileDescriptor被输入流分享可见.
//         *
//         */
//        fd.attach(this);
//    }
//
//    /**
//     * Opens the specified file for reading.
//     *
//     * @param name the name of the file
//     */
//    private native void open0(String name) throws FileNotFoundException;
//
//    // wrap native call to allow instrumentation
//
//    /**
//     * Opens the specified file for reading.
//     * 开启指定的文件用于读取
//     *
//     * @param name the name of the file
//     */
//    private void open(String name) throws FileNotFoundException {
//        open0(name);
//    }
//
//    /**
//     * Reads a byte of data from this input stream. This method blocks
//     * if no input is yet available.
//     * 从输入流中读取字节,如果没有输入可用这个方法将会阻塞
//     *
//     * @return the next byte of data, or <code>-1</code> if the end of the
//     * file is reached.
//     * @throws IOException if an I/O error occurs.
//     */
//    public int read() throws IOException {
//        return read0();
//    }
//
//    private native int read0() throws IOException;
//
//    /**
//     * Reads a subarray as a sequence of bytes.
//     * 有序地读取一连串的字节
//     *
//     * @param b   the data to be written
//     * @param off the start offset in the data
//     * @param len the number of bytes that are written
//     * @throws IOException If an I/O error has occurred.
//     */
//    private native int readBytes(byte b[], int off, int len) throws IOException;
//
//    /**
//     * Reads up to <code>b.length</code> bytes of data from this input
//     * stream into an array of bytes. This method blocks until some input
//     * is available.
//     * 从输入流读取字节数据进入字节数组,读取的数量取决于b[]的长度.这个方法会阻塞,知道输入可用
//     *
//     * @param b the buffer into which the data is read.
//     * @return the total number of bytes read into the buffer, or
//     * <code>-1</code> if there is no more data because the end of
//     * the file has been reached.
//     * @throws IOException if an I/O error occurs.
//     */
//    public int read(byte b[]) throws IOException {
//        return readBytes(b, 0, b.length);
//    }
//
//    /**
//     * Reads up to <code>len</code> bytes of data from this input stream
//     * into an array of bytes. If <code>len</code> is not zero, the method
//     * blocks until some input is available; otherwise, no
//     * bytes are read and <code>0</code> is returned.
//     * 从输入流读取字节数据进入字节数组,读取的数量取决于len,如果len不为0,那么这个方法将会阻塞直到
//     * 输入可用.不然,不会读取字节并返回0(0代表读取的字节数量)
//     *
//     * @param b   the buffer into which the data is read.
//     * @param off the start offset in the destination array <code>b</code>
//     * @param len the maximum number of bytes read.
//     * @return the total number of bytes read into the buffer, or
//     * <code>-1</code> if there is no more data because the end of
//     * the file has been reached.
//     * @throws NullPointerException      If <code>b</code> is <code>null</code>.
//     * @throws IndexOutOfBoundsException If <code>off</code> is negative,
//     *                                   <code>len</code> is negative, or <code>len</code> is greater than
//     *                                   <code>b.length - off</code>
//     * @throws IOException               if an I/O error occurs.
//     */
//    public int read(byte b[], int off, int len) throws IOException {
//        return readBytes(b, off, len);
//    }
//
//    /**
//     * Skips over and discards <code>n</code> bytes of data from the
//     * input stream.
//     * <p>
//     * <p>The <code>skip</code> method may, for a variety of
//     * reasons, end up skipping over some smaller number of bytes,
//     * possibly <code>0</code>. If <code>n</code> is negative, the method
//     * will try to skip backwards. In case the backing file does not support
//     * backward skip at its current position, an <code>IOException</code> is
//     * thrown. The actual number of bytes skipped is returned. If it skips
//     * forwards, it returns a positive value. If it skips backwards, it
//     * returns a negative value.
//     * <p>
//     * <p>This method may skip more bytes than what are remaining in the
//     * backing file. This produces no exception and the number of bytes skipped
//     * may include some number of bytes that were beyond the EOF of the
//     * backing file. Attempting to read from the stream after skipping past
//     * the end will result in -1 indicating the end of the file.
//     * 跳过并丢弃输入流中的字节
//     *
//     * skip方法可能会因为一些原因,停止跳过一些字节.如果n是负数,这个方法会逆向跳过.如果这个文件在当前位置
//     * 不支持逆向跳过,那么会排除IOException.会返回一实际跳过的字节数.如果正向条,会返回正数.如果反向跳,会返回负数
//     *
//     * 这个方法跳过的字节可能比从当前文件留有的更多,但是不会抛出异常,超过文件末尾的字节都可能被读取到.越过
//     * 文件末尾再尝试读取时,会返回标识符-1.
//     *
//     * @param n the number of bytes to be skipped.
//     * @return the actual number of bytes skipped.
//     * @throws IOException if n is negative, if the stream does not
//     *                     support seek, or if an I/O error occurs.
//     */
//    public native long skip(long n) throws IOException;
//
//    /**
//     * Returns an estimate of the number of remaining bytes that can be read (or
//     * skipped over) from this input stream without blocking by the next
//     * invocation of a method for this input stream. Returns 0 when the file
//     * position is beyond EOF. The next invocation might be the same thread
//     * or another thread. A single read or skip of this many bytes will not
//     * block, but may read or skip fewer bytes.
//     * <p>
//     * <p> In some cases, a non-blocking read (or skip) may appear to be
//     * blocked when it is merely slow, for example when reading large
//     * files over slow networks.
//     * 在不阻塞下次访问输入流的情况下,从输入流中返回还能再读取(跳过)的字节数的估计值.如果已经文件末尾,将会返回0.
//     * 下一次的访问可能是由同一个线程或者其他现成执行的.单次读取或者更跳过较多字节不会阻塞,但是读取或跳过的字节
//     * 可能会比预期的少
//     *
//     * @return an estimate of the number of remaining bytes that can be read
//     * (or skipped over) from this input stream without blocking.
//     * @throws IOException if this file input stream has been closed by calling
//     *                     {@code close} or an I/O error occurs.
//     */
//    public native int available() throws IOException;
//
//    /**
//     * Closes this file input stream and releases any system resources
//     * associated with the stream.
//     * <p>
//     * <p> If this stream has an associated channel then the channel is closed
//     * as well.
//     *
//     * 关闭输入流,并且释放和这个流有关的任何资源
//     * 如果流和某个通道有关联,也关闭这个通道.
//     * @throws IOException if an I/O error occurs.
//     * @revised 1.4
//     * @spec JSR-51
//     */
//    public void close() throws IOException {
//        synchronized (closeLock) {
//            if (closed) {
//                return;
//            }
//            closed = true;
//        }
//        if (channel != null) {
//            channel.close();
//        }
//
//        fd.closeAll(new Closeable() {
//            public void close() throws IOException {
//                close0();
//            }
//        });
//    }
//
//    /**
//     * Returns the <code>FileDescriptor</code>
//     * object  that represents the connection to
//     * the actual file in the file system being
//     * used by this <code>FileInputStream</code>.
//     *
//     * 返回FileDescriptor,这个对象代表文件系统中的文件,且该文件正被FileInputStream使用
//     * @return the file descriptor object associated with this stream.
//     * @throws IOException if an I/O error occurs.
//     * @see java.io.FileDescriptor
//     */
//    public final FileDescriptor getFD() throws IOException {
//        if (fd != null) {
//            return fd;
//        }
//        throw new IOException();
//    }
//
//    /**
//     * Returns the unique {@link java.nio.channels.FileChannel FileChannel}
//     * object associated with this file input stream.
//     * <p>
//     * <p> The initial {@link java.nio.channels.FileChannel#position()
//     * position} of the returned channel will be equal to the
//     * number of bytes read from the file so far.  Reading bytes from this
//     * stream will increment the channel's position.  Changing the channel's
//     * position, either explicitly or by reading, will change this stream's
//     * file position.
//     *
//     * 返回唯一的FileChannel对象,这个对象和文件输入流相关联
//     * FileChannel的初始位置和文件中被读取的字节位置一致.从流中读取字节会增加channel的位置.
//     * 利用channel来读取,也会改变文件流的读取位置.
//     * @return the file channel associated with this file input stream
//     * @spec JSR-51
//     * @since 1.4
//     */
//    public FileChannel getChannel() {
//        synchronized (this) {
//            if (channel == null) {
//                channel = FileChannelImpl.open(fd, path, true, false, this);
//            }
//            return channel;
//        }
//    }
//
//    private static native void initIDs();
//
//    private native void close0() throws IOException;
//
//    static {
//        initIDs();
//    }
//
//    /**
//     * Ensures that the <code>close</code> method of this file input stream is
//     * called when there are no more references to it.
//     *
//     * @throws IOException if an I/O error occurs.
//     * @see java.io.FileInputStream#close()
//     */
//    protected void finalize() throws IOException {
//        if ((fd != null) && (fd != FileDescriptor.in)) {
//            /* if fd is shared, the references in FileDescriptor
//             * will ensure that finalizer is only called when
//             * safe to do so. All references using the fd have
//             * become unreachable. We can call close()
//             */
//            close();
//        }
//    }
//}
