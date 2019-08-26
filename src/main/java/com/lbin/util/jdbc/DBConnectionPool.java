package com.lbin.util.jdbc;

import com.lbin.util.ConfigProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接池对象，单例
 * Created by yuandl on 2016-12-16.
 */
public class DBConnectionPool {
    private static volatile DBConnectionPool dbConnection;
    private ComboPooledDataSource cpds;

    /**
     * 在构造函数初始化的时候获取数据库连接
     */
    private DBConnectionPool() {
        if ("mysql".equals(ConfigProperties.getConfig("jdbc.database"))){
            mysqlinit();
        }else if ("oracle".equals(ConfigProperties.getConfig("jdbc.database"))){
            //未实现
            //oracleinti();
        }
    }

    private void oracleinti() {
        try {
            // 数据源进行各种有效的控制：

            /**获取属性文件中的值**/
            String driverClassName = ConfigProperties.getConfig("oracle.driver");
            String url = ConfigProperties.getConfig("oracle.url");
            String username = ConfigProperties.getConfig("oracle.username");
            String password = ConfigProperties.getConfig("oracle.password");
            // 设置驱动
            cpds.setDriverClass(driverClassName);
            // 设置数据库URL
//            cpds.setJdbcUrl("jdbc:oracle:thin:@10.58.178.162:1521:ORCL");
            cpds.setJdbcUrl(url);
            // 设置用户名
            cpds.setUser(username);
            // 设置密码
            cpds.setPassword(password);
            // 当连接池中的连接用完时，C3PO一次性创建新的连接数目;
            cpds.setAcquireIncrement(3);
            // 定义在从数据库获取新的连接失败后重复尝试获取的次数，默认为30;
            cpds.setAcquireRetryAttempts(30);
            // 两次连接中间隔时间默认为1000毫秒
            cpds.setAcquireRetryDelay(1000);
            // 连接关闭时默认将所有未提交的操作回滚 默认为false;
            cpds.setAutoCommitOnClose(false);
            // 获取连接失败将会引起所有等待获取连接的线程异常,但是数据源仍有效的保留,并在下次调用getConnection()的时候继续尝试获取连接.如果设为true,那么尝试获取连接失败后该数据源将申明已经断开并永久关闭.默认为false
            cpds.setBreakAfterAcquireFailure(false);
            // 当连接池用完时客户端调用getConnection()后等待获取新连接的时间,超时后将抛出SQLException,如设为0则无限期等待.单位毫秒,默认为0
            cpds.setCheckoutTimeout(0);
            // 隔多少秒检查所有连接池中的空闲连接,默认为0表示不检查;
            cpds.setIdleConnectionTestPeriod(0);
            // 初始化时创建的连接数,应在minPoolSize与maxPoolSize之间取值.默认为3
            cpds.setInitialPoolSize(10);
            // 最大空闲时间,超过空闲时间的连接将被丢弃.为0或负数据则永不丢弃.默认为0;
            cpds.setMaxIdleTime(0);
            // 连接池中保留的最大连接数据.默认为15
            cpds.setMaxPoolSize(20);
            // JDBC的标准参数,用以控制数据源内加载的PreparedStatement数据.但由于预缓存的Statement属于单个Connection而不是整个连接池.所以设置这个参数需要考滤到多方面的因素,如果maxStatements
            // 与maxStatementsPerConnection均为0,则缓存被关闭.默认为0;
            cpds.setMaxStatements(0);
            // 连接池内单个连接所拥有的最大缓存被关闭.默认为0;
            cpds.setMaxStatementsPerConnection(0);
            // C3P0是异步操作的,缓慢的JDBC操作通过帮助进程完成.扩展这些操作可以有效的提升性能,通过多数程实现多个操作同时被执行.默为为3
            cpds.setNumHelperThreads(3);
            // 用户修改系统配置参数执行前最多等待的秒数.默认为300;
            cpds.setPropertyCycle(300);
            /** 获取连接失败将会引起所有等待获取连接的线程异常,
             但是数据源仍有效的保留,并在下次调用getConnection()的时候继续尝试获取连接.如果设为true,
             那么尝试获取连接失败后该数据源将申明已经断开并永久关闭.默认为false**/
            cpds.setBreakAfterAcquireFailure(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void mysqlinit(){
        try {
            /**获取属性文件中的值**/
            String driverClassName = ConfigProperties.getConfig("mysql.driver");
            String url = ConfigProperties.getConfig("mysql.url");
            String username = ConfigProperties.getConfig("mysql.username");
            String password = ConfigProperties.getConfig("mysql.password");

            /**数据库连接池对象**/
            cpds = new ComboPooledDataSource();

            /**设置数据库连接驱动**/
            cpds.setDriverClass(driverClassName);
            /**设置数据库连接地址**/
            cpds.setJdbcUrl(url);
            /**设置数据库连接用户名**/
            cpds.setUser(username);
            /**设置数据库连接密码**/
            cpds.setPassword(password);

            /**初始化时创建的连接数,应在minPoolSize与maxPoolSize之间取值.默认为3**/
            cpds.setInitialPoolSize(3);
            /**连接池中保留的最大连接数据.默认为15**/
            cpds.setMaxPoolSize(10);
            /**当连接池中的连接用完时，C3PO一次性创建新的连接数目;**/
            cpds.setAcquireIncrement(1);
            /**隔多少秒检查所有连接池中的空闲连接,默认为0表示不检查;**/
            cpds.setIdleConnectionTestPeriod(60);
            /**最大空闲时间,超过空闲时间的连接将被丢弃.为0或负数据则永不丢弃.默认为0;**/
            cpds.setMaxIdleTime(3000);

            /**因性能消耗大请只在需要的时候使用它。如果设为true那么在每个connection提交的
             时候都将校验其有效性。建议使用idleConnectionTestPeriod或automaticTestTable
             等方法来提升连接测试的性能。Default: false**/
            cpds.setTestConnectionOnCheckout(true);

            /**如果设为true那么在取得连接的同时将校验连接的有效性。Default: false **/
            cpds.setTestConnectionOnCheckin(true);
            /**定义在从数据库获取新的连接失败后重复尝试获取的次数，默认为30;**/
            cpds.setAcquireRetryAttempts(30);
            /**两次连接中间隔时间默认为1000毫秒**/
            cpds.setAcquireRetryDelay(1000);
            /** 获取连接失败将会引起所有等待获取连接的线程异常,
             但是数据源仍有效的保留,并在下次调用getConnection()的时候继续尝试获取连接.如果设为true,
             那么尝试获取连接失败后该数据源将申明已经断开并永久关闭.默认为false**/
            cpds.setBreakAfterAcquireFailure(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象，单例
     *
     * @return
     */
    public static DBConnectionPool getInstance() {
        if (dbConnection == null) {
            synchronized (DBConnectionPool.class) {
                if (dbConnection == null) {
                    dbConnection = new DBConnectionPool();
                }
            }
        }
        return dbConnection;
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public final synchronized Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

    /**
     * finalize()方法是在垃圾收集器删除对象之前对这个对象调用的。
     *
     * @throws Throwable
     */
    protected void finalize() throws Throwable {
        DataSources.destroy(cpds);
        super.finalize();
    }
}
