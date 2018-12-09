package com.ilucky.test.jvm.jmx.one;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

/**
 * ManagementFactory基本使用
 * 
 * @author IluckySi
 *
 */
public class MBeanDemo {

    public static void main(String[] args) {
        visitMBean();
        
        showSystem();
        showJvmInfo();
        showMemoryInfo();
        showClassLoading();
        showCompilation();
        showThread();
        showGarbageCollector();
        showMemoryManager();
        showMemoryPool();
    }

    /** 访问 MXBean有三种方法 */
    public static void visitMBean() { 
        System.out.println("------visitMBean------");
        
        // 第一种: 直接调用Java虚拟机内的MXBean中的方法
        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        String vendor1 = mxbean.getVmVendor();
        System.out.println("vendor1:" +  vendor1); 
         
        //第二种: 通过一个连接到正在运行的虚拟机的平台MBeanServer的MBeanServerConnection
        MBeanServerConnection mbs = null; 
        try { 
            // Returns the platform MBeanServer. On the first call to this method, 
            // it first creates the platform MBeanServer by calling the MBeanServerFactory.createMBeanServer method 
            // and registers each platform MXBean in this platform MBeanServer with its ObjectName. 
            // This method, in subsequent calls, will simply return the initially created platform MBeanServer. 
            mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName on = new ObjectName(ManagementFactory.RUNTIME_MXBEAN_NAME);
            String vendor2 = (String) mbs.getAttribute(on, "VmVendor");
            System.out.println("vendor2:" + vendor2); 
        } catch (Exception e) {
             e.printStackTrace(); 
        } 
                                       
        //第三种使用MXBean代理
        MBeanServerConnection mbs2 = null;
        RuntimeMXBean proxy = null;
        try {
            mbs2 = ManagementFactory.getPlatformMBeanServer();
            // Returns a proxy for a platform MXBean interface of a given MXBean name that forwards its method calls through the given MBeanServerConnection. 
            proxy = ManagementFactory.newPlatformMXBeanProxy(mbs2, ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class); 
            String vendor3 =  proxy.getVmVendor();
            System.out.println("vendor3:" + vendor3); 
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    /** 操作系统信息 */
    public static void showSystem() {
        System.out.println("------getOperatingSystemMXBean------");
        
        OperatingSystemMXBean mxbean = ManagementFactory.getOperatingSystemMXBean();
        // Returns an ObjectName instance representing the object name of this platform managed object.
        System.out.println("getObjectName:" + mxbean.getObjectName());
        
        // Returns the operating system architecture. This method is equivalent to System.getProperty("os.arch").
        System.out.println("getArch: " + mxbean.getArch());
        // Returns the operating system name. This method is equivalent to System.getProperty("os.name").
        System.out.println("getName: " + mxbean.getName());
        // Returns the operating system version. This method is equivalent to System.getProperty("os.version").
        System.out.println("getName: " + mxbean.getName());
        // Returns the number of processors available to the Java virtual machine. This method is equivalent to the Runtime.availableProcessors() method. 
        System.out.println("getAvailableProcessors: " + mxbean.getAvailableProcessors());
        // Returns the system load average for the last minute. 
        System.out.println("getSystemLoadAverage: " + mxbean.getSystemLoadAverage());
    }
    
    /** Java虚拟机的运行时信息 */
    public static void showJvmInfo() {
        System.out.println("------getRuntimeMXBean------");
        
        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        // Returns an ObjectName instance representing the object name of this platform managed object.
        System.out.println("getObjectName:" + mxbean.getObjectName());
        // Returns the name representing the running Java virtual machine.
        System.out.println("getName:" + mxbean.getName());
        
        // Returns the Java virtual machine implementation vendor. This method is equivalent to System.getProperty("java.vm.vendor").
        System.out.println("getVmVendor:" + mxbean.getVmVendor());
        // Returns the Java virtual machine implementation name. This method is equivalent to System.getProperty("java.vm.name").
        System.out.println("getVmName:" + mxbean.getVmName());
        // Returns the Java virtual machine implementation name. This method is equivalent to System.getProperty("java.vm.name").
        System.out.println("getVmVersion:" + mxbean.getVmVersion());
        
        // Returns the Java virtual machine specification vendor. This method is equivalent to System.getProperty("java.vm.specification.vendor").
        System.out.println("getSpecVendor:" + mxbean.getSpecVendor());
        // Returns the Java virtual machine specification name. This method is equivalent to System.getProperty("java.vm.specification.name").
        System.out.println("getSpecName:" + mxbean.getSpecName());
        // Returns the Java virtual machine specification version. This method is equivalent to System.getProperty("java.vm.specification.version").
        System.out.println("getSpecVersion:" + mxbean.getSpecVersion());
        // Returns the version of the specification for the management interface implemented by the running Java virtual machine.
        System.out.println("getManagementSpecVersion:" + mxbean.getManagementSpecVersion());
        
        // Returns the boot class path that is used by the bootstrap class loader to search for class files. 
        System.out.println("getBootClassPath:" + mxbean.getBootClassPath());
        // Tests if the Java virtual machine supports the boot class path mechanism used by the bootstrap class loader to search for class files.
        System.out.println("isBootClassPathSupported:" + mxbean.isBootClassPathSupported());
        // Returns the Java library path. This method is equivalent to System.getProperty("java.library.path"). 
        System.out.println("getLibraryPath:" + mxbean.getLibraryPath());
        
        // Returns the start time of the Java virtual machine in milliseconds. This method returns the approximate time when the Java virtual machine started
        System.out.println("getStartTime:" + mxbean.getStartTime());
        // Returns the uptime of the Java virtual machine in milliseconds.
        System.out.println("getUptime:" + mxbean.getUptime());
        // Returns the input arguments passed to the Java virtual machine which does not include the arguments to the main method. This method returns an empty list if there is no input argument to the Java virtual machine. 
        System.out.println("getInputArguments:" + mxbean.getInputArguments());
        
        // Returns a map of names and values of all system properties. This method calls System.getProperties to get all system properties. Properties whose name or value is not a String are omitted. 
        System.out.println("getSystemProperties:" + mxbean.getSystemProperties());
        for(Entry<String, String> entry: mxbean.getSystemProperties().entrySet()) {
            System.out.println("getSystemProperties:" + entry.getKey() + "-" + entry.getValue());
        }
    }

    /** Java 虚拟机的内存信息 */
    public static void showMemoryInfo() {
        System.out.println("------getMemoryMXBean------");
        
        MemoryMXBean mxbean = ManagementFactory.getMemoryMXBean();
        // Returns an ObjectName instance representing the object name of this platform managed object.
        System.out.println("getObjectName:" + mxbean.getObjectName());
        
        // Returns the current memory usage of the heap that is used for object allocation. The heap consists of one or more memory pools. 
        MemoryUsage heap = mxbean.getHeapMemoryUsage();
        // Returns the amount of memory in bytes that is committed for the Java virtual machine to use. 
        System.out.println("getCommitted:" + heap.getCommitted());
        // Returns the amount of memory in bytes that the Java virtual machine initially requests from the operating system for memory management.
        System.out.println("getInit:" + heap.getInit());
        // Returns the maximum amount of memory in bytes that can be used for memory management. This method returns -1 if the maximum memory size is undefined. 
        System.out.println("getMax:" + heap.getMax());
        // Returns the amount of used memory in bytes.
        System.out.println("getUsed:" + heap.getUsed());
    }

    /** Java 虚拟机的类加载信息 */
    public static void showClassLoading() {
        System.out.println("------getClassLoadingMXBean------");
        
        ClassLoadingMXBean mxbean = ManagementFactory.getClassLoadingMXBean();
        // Returns an ObjectName instance representing the object name of this platform managed object.
        System.out.println("getObjectName:" + mxbean.getObjectName());
        
        // Returns the total number of classes that have been loaded since the Java virtual machine has started execution.
        System.out.println("TotalLoadedClassCount: " + mxbean.getTotalLoadedClassCount());
        // Returns the number of classes that are currently loaded in the Java virtual machine.
        System.out.println("LoadedClassCount" + mxbean.getLoadedClassCount());
        // Returns the total number of classes unloaded since the Java virtual machine has started execution
        System.out.println("UnloadedClassCount:" + mxbean.getUnloadedClassCount());
    }

    /** Java 虚拟机的编译信息 */
    public static void showCompilation() {
        System.out.println("------getCompilationMXBean------");
        
        CompilationMXBean mxbean = ManagementFactory.getCompilationMXBean();
        // Returns an ObjectName instance representing the object name of this platform managed object.
        System.out.println("getObjectName:" + mxbean.getObjectName());
        
        // Returns the approximate accumulated elapsed time (in milliseconds) spent in compilation.
        System.out.println("getTotalCompilationTime:" + mxbean.getTotalCompilationTime());
        // Returns the name of the Just-in-time (JIT) compiler.
        System.out.println("getName:" + mxbean.getName());
        // Tests if the Java virtual machine supports the monitoring of compilation time.
        System.out.println("isCompilationTimeMonitoringSupported:" + mxbean.isCompilationTimeMonitoringSupported());
    }

    /** Java 虚拟机的线程信息 */
    public static void showThread() {
        System.out.println("------getThreadMXBean------");
        
        ThreadMXBean mxbean = ManagementFactory.getThreadMXBean();
        // Returns an ObjectName instance representing the object name of this platform managed object.
        System.out.println("getObjectName:" + mxbean.getObjectName());
        
        // Returns all live thread IDs. Some threads included in the returned array may have been terminated when this method returns.
        System.out.println("getAllThreadIds:" + Arrays.toString(mxbean.getAllThreadIds()));
        // Returns the current number of live threads including both daemon and non-daemon threads.
        System.out.println("ThreadCount: " + mxbean.getThreadCount());
        // Returns the current number of live daemon threads.
        System.out.println("DaemonThreadCount: " + mxbean.getDaemonThreadCount());
        // Returns the peak live thread count since the Java virtual machine started or peak was reset.
        System.out.println("PeakThreadCount:" + mxbean.getPeakThreadCount());
        // Returns the total number of threads created and also started since the Java virtual machine started.
        System.out.println("TotalStartedThreadCount:" + mxbean.getTotalStartedThreadCount());
        
        // Returns the CPU time that the current thread has executed in user mode in nanoseconds.
        System.out.println("CurrentThreadUserTime: " + mxbean.getCurrentThreadUserTime());
        
        System.out.println("getAllThreadIds:");
        for(long id : mxbean.getAllThreadIds()) {
            System.out.println("threadId: " + id);
            // Returns the total CPU time for a thread of the specified ID in nanoseconds.
            System.out.println("getThreadCpuTime: " + mxbean.getThreadCpuTime(id));
            // Returns the thread info for a thread of the specified id with no stack trace. This method is equivalent to calling: 
            // getThreadInfo(id, 0); 
            System.out.println("getThreadInfo: " + mxbean.getThreadInfo(id));
        }
    }

    /** Java 虚拟机中的垃圾回收器信息: 补充: 新生代收集器: Parallel Scavenge, 老年代选择器: Serial Old */
    public static void showGarbageCollector() {
        System.out.println("------getGarbageCollectorMXBeans------");
        
        // Returns a list of GarbageCollectorMXBean objects in the Java virtual machine. 
        // The Java virtual machine may have one or more GarbageCollectorMXBean objects. It may add or remove GarbageCollectorMXBean during execution.
        List<GarbageCollectorMXBean> mxbean = ManagementFactory.getGarbageCollectorMXBeans();
        
        for (GarbageCollectorMXBean gc : mxbean) {
            // Returns an ObjectName instance representing the object name of this platform managed object.
            System.out.println("getObjectName:" + gc.getObjectName());
            // Returns the name representing this memory manager.
            System.out.println("getName:" + gc.getName());
            // Returns the total number of collections that have occurred. This method returns -1 if the collection count is undefined for this collector.
            System.out.println("getCollectionCount: " + gc.getCollectionCount());
            // Returns the approximate accumulated collection elapsed time in milliseconds. This method returns -1 if the collection elapsed time is undefined for this collector. 
            System.out.println("getCollectionTime: " + gc.getCollectionTime());
            // Returns the name of memory pools that this memory manager manages.
            System.out.println("getMemoryPoolNames: " + Arrays.toString(gc.getMemoryPoolNames()));
        }
    }

    /** Java 虚拟机中的内存管理器 */
    public static void showMemoryManager() {
        System.out.println("------getMemoryManagerMXBeans------");
        
        List<MemoryManagerMXBean> mxbean = ManagementFactory.getMemoryManagerMXBeans();
        for (MemoryManagerMXBean mm : mxbean) {
            // Returns an ObjectName instance representing the object name of this platform managed object.
            System.out.println("getObjectName:" + mm.getObjectName());
            System.out.println("getName:" + mm.getName());
            // Returns the name of memory pools that this memory manager manages.
            System.out.println("getMemoryPoolNames:" + Arrays.toString(mm.getMemoryPoolNames()));
        }
    }

    /** * Java 虚拟机中的内存池 */
    public static void showMemoryPool() {
        System.out.println("------getMemoryPoolMXBeans------");
        
        List<MemoryPoolMXBean> mps = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mp : mps) {
            // Returns an ObjectName instance representing the object name of this platform managed object.
            System.out.println("object name:" + mp.getObjectName());
            System.out.println("name:" + mp.getName());
            // Returns the type of this memory pool. 
            System.out.println("type:" + mp.getType());
            // Returns an estimate of the memory usage of this memory pool. 
            System.out.println("getUsage:" + mp.getUsage());
            // Returns the usage threshold value of this memory pool in bytes. 
            // System.out.println("getUsageThreshold:" + mp.getUsageThreshold());
            // Returns the number of times that the memory usage has crossed the usage threshold.
            // System.out.println("getUsageThresholdCount:" + mp.getUsageThresholdCount());
            // Returns the memory usage after the Java virtual machine most recently expended effort in recycling unused objects in this memory pool. 
            System.out.println("CollectionUsage:" + mp.getCollectionUsage());
            
            // 执行如下代码报错: java.lang.UnsupportedOperationException: CollectionUsage threshold is not supported
            // System.out.println("getCollectionUsageThreshold:" + mp.getCollectionUsageThreshold());
            // System.out.println("getCollectionUsageThresholdCount:" + mp.getCollectionUsageThresholdCount());
        }
    }
}
