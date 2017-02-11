/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.util.concurrent;
import java.util.List;
import java.util.Collection;

/**
 * An {@link Executor} that provides methods to manage termination and
 * methods that can produce a {@link Future} for tracking progress of
 * one or more asynchronous tasks.
 *
 * <p>An {@code ExecutorService} can be shut down, which will cause
 * it to reject new tasks.  Two different methods are provided for
 * shutting down an {@code ExecutorService}. The {@link #shutdown}
 * method will allow previously submitted tasks to execute before
 * terminating, while the {@link #shutdownNow} method prevents waiting
 * tasks from starting and attempts to stop currently executing tasks.
 * Upon termination, an executor has no tasks actively executing, no
 * tasks awaiting execution, and no new tasks can be submitted.  An
 * unused {@code ExecutorService} should be shut down to allow
 * reclamation of its resources.
 *
 *
 * <p>Method {@code submit} extends base method {@link
 * Executor#execute(Runnable)} by creating and returning a {@link Future}
 * that can be used to cancel execution and/or wait for completion.
 * Methods {@code invokeAny} and {@code invokeAll} perform the most
 * commonly useful forms of bulk execution, executing a collection of
 * tasks and then waiting for at least one, or all, to
 * complete. (Class {@link ExecutorCompletionService} can be used to
 * write customized variants of these methods.)
 *
 *
 * <p>The {@link Executors} class provides factory methods for the
 * executor services provided in this package.
 *
 * <h3>Usage Examples</h3>
 *
 * Here is a sketch of a network service in which threads in a thread
 * pool service incoming requests. It uses the preconfigured {@link
 * Executors#newFixedThreadPool} factory method:
 *
 * ExecutorService可以被停止,但是这样会使它拒绝执行新的任务.可以使用两种方法来
 * 关闭.
 * ExecutorService.shutdown方法允许在executor被终止之前能够执行先前已经被提交的任务.
 * shutdownNow方法会阻止executor执行正在等待的方法,并会
 * 尝试停止当前正在执行的任务.在终止的时候,不会有任务正在executor中执行,也不会有任务
 * 等待着被执行,新的任务也无法提交.没有被使用的ExecutorService应该能够被停止并允许
 * 资源回收.
 *
 * submit方法集成了Executor接口的execute方法,该方法可以创建并返回Future.
 * invokeAny和invokeAll方法可以用来执行大量的任务,可以执行一批任务并等待至少一个任务完成.
 * ExecutorCompletionService类可以用来自定义上述方法
 *
 *
 * Executors类提供了此包中执行服务的工厂方法
 * 这里是一个网络服务的简述,利用线程池里的线程来服务传入的请求.它使用了预先配置的newFixedThreadPool的
 * 工厂方法
 *  <pre> {@code
 * class NetworkService implements Runnable {
 *   private final ServerSocket serverSocket;
 *   private final ExecutorService pool;
 *
 *   public NetworkService(int port, int poolSize)
 *       throws IOException {
 *     serverSocket = new ServerSocket(port);
 *     pool = Executors.newFixedThreadPool(poolSize);
 *   }
 *
 *   public void run() { // run the service
 *     try {
 *       for (;;) {
 *         pool.execute(new Handler(serverSocket.accept()));
 *       }
 *     } catch (IOException ex) {
 *       pool.shutdown();
 *     }
 *   }
 * }
 *
 * class Handler implements Runnable {
 *   private final Socket socket;
 *   Handler(Socket socket) { this.socket = socket; }
 *   public void run() {
 *     // read and service request on socket
 *   }
 * }}</pre>
 *
 * The following method shuts down an {@code ExecutorService} in two phases,
 * first by calling {@code shutdown} to reject incoming tasks, and then
 * calling {@code shutdownNow}, if necessary, to cancel any lingering tasks:
 * 下面的方法在两个阶段里关闭ExecutorService,首先调用shutdown拒绝传入任务,然后调用shutdownNow取消所有
 * 遗留的任务.
 *  <pre> {@code
 * void shutdownAndAwaitTermination(ExecutorService pool) {
 *   pool.shutdown(); // Disable new tasks from being submitted 拒绝提交新的任务
 *   try {
 *     // Wait a while for existing tasks to terminate
 *     // 终止遗留的任务需要一段时间的等待
 *     if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
 *       pool.shutdownNow(); // Cancel currently executing tasks 取消现在仍在执行的任务
 *       // Wait a while for tasks to respond to being cancelled
 *       if (!pool.awaitTermination(60, TimeUnit.SECONDS))
 *           System.err.println("Pool did not terminate");
 *     }
 *   } catch (InterruptedException ie) {
 *     // (Re-)Cancel if current thread also interrupted
 *     pool.shutdownNow();
 *     // Preserve interrupt status
 *     Thread.currentThread().interrupt();
 *   }
 * }}</pre>
 *
 * <p>Memory consistency effects: Actions in a thread prior to the
 * submission of a {@code Runnable} or {@code Callable} task to an
 * {@code ExecutorService}
 * <a href="package-summary.html#MemoryVisibility"><i>happen-before</i></a>
 * any actions taken by that task, which in turn <i>happen-before</i> the
 * result is retrieved via {@code Future.get()}.
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface ExecutorService extends Executor {

    /**
     * Initiates an orderly shutdown in which previously submitted
     * tasks are executed, but no new tasks will be accepted.
     * Invocation has no additional effect if already shut down.
     *
     * <p>This method does not wait for previously submitted tasks to
     * complete execution.  Use {@link #awaitTermination awaitTermination}
     * to do that.
     * 发起一个有序地关闭命令,执行以前提交的任务,拒绝接收新的任务.如果已经关闭,再调用
     * 没有其他作用
     * 这个方法不会等着之前提交的任务完成.使用awaitTermination方法来完成这一步
     *
     * @throws SecurityException if a security manager exists and
     *         shutting down this ExecutorService may manipulate
     *         threads that the caller is not permitted to modify
     *         because it does not hold {@link
     *         java.lang.RuntimePermission}{@code ("modifyThread")},
     *         or the security manager's {@code checkAccess} method
     *         denies access.
     */
    void shutdown();

    /**
     * Attempts to stop all actively executing tasks, halts the
     * processing of waiting tasks, and returns a list of the tasks
     * that were awaiting execution.
     *
     * <p>This method does not wait for actively executing tasks to
     * terminate.  Use {@link #awaitTermination awaitTermination} to
     * do that.
     *
     * <p>There are no guarantees beyond best-effort attempts to stop
     * processing actively executing tasks.  For example, typical
     * implementations will cancel via {@link Thread#interrupt}, so any
     * task that fails to respond to interrupts may never terminate.
     *
     * 试图停止所有正在执行的任务,暂停处理正在等待的任务,返回待执行的任务的列表
     *
     * 这个方法不关心正在执行的任务是否终止了.使用awaitTermination来处理.
     *
     * 不能保证会停止正在执行的任务,但是会尽最大的努力.举个例子,会执行Thread.interrupt来取消典型的实现
     * 所有任何任务无法相应中断的话,可能就无法停止了.
     *
     * @return list of tasks that never commenced execution
     *          从未开始执行的任务列表
     * @throws SecurityException if a security manager exists and
     *         shutting down this ExecutorService may manipulate
     *         threads that the caller is not permitted to modify
     *         because it does not hold {@link
     *         java.lang.RuntimePermission}{@code ("modifyThread")},
     *         or the security manager's {@code checkAccess} method
     *         denies access.
     */
    List<Runnable> shutdownNow();

    /**
     * Returns {@code true} if this executor has been shut down.
     *
     * 判断executor是否被关闭了
     * @return {@code true} if this executor has been shut down
     */
    boolean isShutdown();

    /**
     * Returns {@code true} if all tasks have completed following shut down.
     * Note that {@code isTerminated} is never {@code true} unless
     * either {@code shutdown} or {@code shutdownNow} was called first.
     * 如果关闭后所有任务都完成了,返回true
     * i除非一开始就调用了shutdown或者shutdownNow,否则sTerminated永远不会为true
     *
     * @return {@code true} if all tasks have completed following shut down
     */
    boolean isTerminated();

    /**
     * Blocks until all tasks have completed execution after a shutdown
     * request, or the timeout occurs, or the current thread is
     * interrupted, whichever happens first.
     * 下达关闭请求,,或者已经超时,或者当前的线程被中断了,无论哪个首先发生,都将导致阻塞直到
     * 所有的任务都已经完成.
     *
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @return {@code true} if this executor terminated and
     *         {@code false} if the timeout elapsed before termination
     * @throws InterruptedException if interrupted while waiting
     */
    boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * Submits a value-returning task for execution and returns a
     * Future representing the pending results of the task. The
     * Future's {@code get} method will return the task's result upon
     * successful completion.
     * 提交带返回值的任务并返回一个Future,它代表一个任务待定的结果.Future的get方法在
     * 任务成功返回时会返回任务的结果
     *
     * <p>
     * If you would like to immediately block waiting
     * for a task, you can use constructions of the form
     * {@code result = exec.submit(aCallable).get();}
     * 如果你想阻塞任务的等待,你可以使用下面的构造形式result = exec.submit(aCallable).get()
     *
     * <p>Note: The {@link Executors} class includes a set of methods
     * that can convert some other common closure-like objects,
     * for example, {@link java.security.PrivilegedAction} to
     * {@link Callable} form so they can be submitted.
     *
     * @param task the task to submit
     * @param <T> the type of the task's result
     * @return a Future representing pending completion of the task
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    <T> Future<T> submit(Callable<T> task);

    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's {@code get} method will
     * return the given result upon successful completion.
     * 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future.
     * 该 Future 的 get 方法在成功完成时将会返回给定的结果。
     *
     * @param task the task to submit
     * @param result the result to return
     * @param <T> the type of the result
     * @return a Future representing pending completion of the task
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    <T> Future<T> submit(Runnable task, T result);

    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's {@code get} method will
     * return {@code null} upon <em>successful</em> completion.
     *
     * @param task the task to submit
     * @return a Future representing pending completion of the task
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    Future<?> submit(Runnable task);

    /**
     * Executes the given tasks, returning a list of Futures holding
     * their status and results when all complete.
     * {@link Future#isDone} is {@code true} for each
     * element of the returned list.
     * Note that a <em>completed</em> task could have
     * terminated either normally or by throwing an exception.
     * The results of this method are undefined if the given
     * collection is modified while this operation is in progress.
     *
     * @param tasks the collection of tasks
     * @param <T> the type of the values returned from the tasks
     * @return a list of Futures representing the tasks, in the same
     *         sequential order as produced by the iterator for the
     *         given task list, each of which has completed
     * @throws InterruptedException if interrupted while waiting, in
     *         which case unfinished tasks are cancelled
     * @throws NullPointerException if tasks or any of its elements are {@code null}
     * @throws RejectedExecutionException if any task cannot be
     *         scheduled for execution
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException;

    /**
     * Executes the given tasks, returning a list of Futures holding
     * their status and results
     * when all complete or the timeout expires, whichever happens first.
     * {@link Future#isDone} is {@code true} for each
     * element of the returned list.
     * Upon return, tasks that have not completed are cancelled.
     * Note that a <em>completed</em> task could have
     * terminated either normally or by throwing an exception.
     * The results of this method are undefined if the given
     * collection is modified while this operation is in progress.
     * 执行给定热任务,返回保持任务状态和结果的future列表.当所有都完成或者超时,无论哪一个先发生
     * 对于返回的列表中的每一个元素,Future.isDone都会返回true.
     * 会犯之后,没有完成的任务将会被取消
     * 可以正常的或通过抛出异常来终止任务.如果在正在进行此操作的时候修改collection,那么返回
     * 的结果将会是不确定的
     *
     * @param tasks the collection of tasks
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @param <T> the type of the values returned from the tasks
     * @return a list of Futures representing the tasks, in the same
     *         sequential order as produced by the iterator for the
     *         given task list. If the operation did not time out,
     *         each task will have completed. If it did time out, some
     *         of these tasks will not have completed.
     * @throws InterruptedException if interrupted while waiting, in
     *         which case unfinished tasks are cancelled
     * @throws NullPointerException if tasks, any of its elements, or
     *         unit are {@code null}
     * @throws RejectedExecutionException if any task cannot be scheduled
     *         for execution
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * Executes the given tasks, returning the result
     * of one that has completed successfully (i.e., without throwing
     * an exception), if any do. Upon normal or exceptional return,
     * tasks that have not completed are cancelled.
     * The results of this method are undefined if the given
     * collection is modified while this operation is in progress.
     * 执行给定的任务，如果某个任务已成功完成（也就是未抛出异常），则返回其结果。
     * 一旦正常或异常返回后，则取消尚未完成的任务。
     * 如果此操作正在进行时修改了给定的 collection，则此方法的结果是不确定的。
     * @param tasks the collection of tasks
     * @param <T> the type of the values returned from the tasks
     * @return the result returned by one of the tasks
     * @throws InterruptedException if interrupted while waiting
     * @throws NullPointerException if tasks or any element task
     *         subject to execution is {@code null}
     * @throws IllegalArgumentException if tasks is empty
     * @throws ExecutionException if no task successfully completes
     * @throws RejectedExecutionException if tasks cannot be scheduled
     *         for execution
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException;

    /**
     * Executes the given tasks, returning the result
     * of one that has completed successfully (i.e., without throwing
     * an exception), if any do before the given timeout elapses.
     * Upon normal or exceptional return, tasks that have not
     * completed are cancelled.
     * The results of this method are undefined if the given
     * collection is modified while this operation is in progress.
     *
     * @param tasks the collection of tasks
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @param <T> the type of the values returned from the tasks
     * @return the result returned by one of the tasks
     * @throws InterruptedException if interrupted while waiting
     * @throws NullPointerException if tasks, or unit, or any element
     *         task subject to execution is {@code null}
     * @throws TimeoutException if the given timeout elapses before
     *         any task successfully completes
     * @throws ExecutionException if no task successfully completes
     * @throws RejectedExecutionException if tasks cannot be scheduled
     *         for execution
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
}
