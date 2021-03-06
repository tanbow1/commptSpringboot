package com.tanb.commpt.core.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.SysConstant;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.UUID;

/***
 * 公共Helper
 *
 * @author Tanbo
 *
 */
public class CommonUtil {

    /**
     * 直接取spring数据源
     *
     * @param request
     * @return
     */
    public static DataSource springDataSource(HttpServletRequest request, String dataSourceBeanName) {
        DataSource dataSource = null;
        ServletContext context = request.getSession().getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils
                .getRequiredWebApplicationContext(context);
        dataSource = (DataSource) webApplicationContext
                .getBean(dataSourceBeanName);
        return dataSource;
    }

    public static DefaultSqlSessionFactory getSqlSessionFactory(
            HttpServletRequest request) {
        DefaultSqlSessionFactory defaultSqlSessionFactory = null;
        ServletContext context = request.getSession().getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils
                .getRequiredWebApplicationContext(context);
        defaultSqlSessionFactory = (DefaultSqlSessionFactory) webApplicationContext
                .getBean("sqlSessionFactory");
        return defaultSqlSessionFactory;
    }

    /**
     * @param request
     * @param autoCommit 批量提交时设置false
     * @return
     */
    public static SqlSession getSqlSession(HttpServletRequest request, boolean autoCommit) {
        return getSqlSessionFactory(request).openSession(
                ExecutorType.BATCH, autoCommit);
    }

    /**
     * java uuid
     */
    public static String getSystemUUID(String replacement) {
        if (StringUtils.isEmpty(replacement))
            replacement = "";
        return UUID.randomUUID().toString().replace("-", replacement);
    }


    /**
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @return
     */
    public static Integer[] getPageStartAndEnd(Integer pageNumber,
                                               Integer pageSize) {
        if (null == pageNumber || pageNumber < 1) {
            pageNumber = SysConstant.DEFAULT_PAGE_START;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = SysConstant.DEFAULT_PAGE_SIZE;
        }
        int pageStart = pageSize * (pageNumber - 1) + 1;
        int pageEnd = pageSize * pageNumber;

        Integer[] pageArr = new Integer[]{pageStart, pageEnd};
        return pageArr;
    }

    /**
     * 通过类的简写返回类全名
     *
     * @param simpleClassName
     * @return
     */
    public static Class getClassName(String simpleClassName) throws ClassNotFoundException {
        simpleClassName = simpleClassName.toLowerCase();
        if ("byte".equals(simpleClassName)) {
            return byte.class;
        } else if ("short".equals(simpleClassName)) {
            return short.class;
        } else if ("int".equals(simpleClassName)) {
            return int.class;
        } else if ("long".equals(simpleClassName)) {
            return long.class;
        } else if ("float".equals(simpleClassName)) {
            return float.class;
        } else if ("double".equals(simpleClassName)) {
            return double.class;
        } else if ("boolean".equals(simpleClassName)) {
            return boolean.class;
        } else if ("char".equals(simpleClassName)) {
            return char.class;
        } else if ("string".equals(simpleClassName)) {
            return String.class;
        } else if ("array".equals(simpleClassName)) {
            return Array.class;
        } else {
            return Class.forName(simpleClassName);
        }
    }

    /**
     * 用于objectMapper 泛型转换
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 请求是否ajax
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1));
    }
}
