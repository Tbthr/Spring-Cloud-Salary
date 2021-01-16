package com.salary.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.salary.mapper.LogMapper;
import com.salary.model.Log;
import com.salary.model.User;
import com.salary.util.CusAccessObjectUtil;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 操作日志存入数据库
 */
@Service
public class LogService {
    @Resource
    private LogMapper logMapper;

    private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s";

    /**
     * 持久化日志
     *
     * @param joinPoint  切入点
     * @param methodName 方法名
     * @param module     操作
     * @param info       级别/类型
     */
    public void put(JoinPoint joinPoint, String methodName, String module, String info) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            Log log = new Log();
            // 获取当前登录用户
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getName();
            if (StringUtils.isEmpty(username)) {
                username = "未知用户";
            }
            // 获取IP地址
            String ip = CusAccessObjectUtil.getClientIpAddress(request);
            log.setUsername(username);
            log.setModule(module);
            log.setInfo(info);
            log.setIp(ip);
            log.setContent(operateContent(joinPoint, methodName, request));
            LocalDateTime dateTime = LocalDateTime.now(); // gets the current date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            log.setDate(dateTime.format(formatter));
            logMapper.insert(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有日志
     *
     * @param page 页码
     * @param rows 页数
     * @return 分页返回日志列表
     */
    public PageInfo<Log> getAllLog(HashMap<String, Object> map, int page, int rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<>(logMapper.selectAll(map));
    }

    /**
     * 查询所有日志
     *
     * @return 返回日志列表
     */
    public List<Log> getAllLogOrigin(HashMap<String, Object> map) {
        return logMapper.selectAll(map);
    }

    /**
     * 删除日志
     *
     * @param id 日志id
     * @return 成功：1 失败：0
     */
    public int deleteById(Integer id) {
        return logMapper.deleteByPrimaryKey(id);
    }

    /**
     * 格式化content内容
     *
     * @param joinPoint  切入点
     * @param methodName 方法名
     * @param request    请求request
     * @return 已格式化内容
     * @throws ClassNotFoundException 类找不到异常
     * @throws NotFoundException      找不到异常
     */
    public String operateContent(JoinPoint joinPoint, String methodName, HttpServletRequest request) throws ClassNotFoundException, NotFoundException {
        String className = joinPoint.getTarget().getClass().getName();
        Object[] params = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName, params);
        StringBuilder bf = new StringBuilder();
        if (!CollectionUtils.isEmpty(nameAndArgs)) {
            for (Map.Entry<String, Object> stringObjectEntry : nameAndArgs.entrySet()) {
                String key = stringObjectEntry.getKey();
                String value = JSONObject.toJSONString(stringObjectEntry.getValue());
                bf.append(key).append("=");
                bf.append(value).append("&");
            }
        }
        if (StringUtils.isEmpty(bf.toString())) {
            bf.append(request.getQueryString());
        }
        return String.format(LOG_CONTENT, className, methodName, bf.toString());
    }

    /**
     * 获取方法参数
     *
     * @param cls        类对象
     * @param clazzName  类名
     * @param methodName 方法名
     * @param args       参数列表
     * @return 方法参数
     * @throws NotFoundException 找不到异常
     */
    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String, Object> map = new HashMap<>();

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
            return map;
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            map.put(attr.variableName(i + pos), args[i]);//paramNames即参数名
        }
        return map;
    }
}