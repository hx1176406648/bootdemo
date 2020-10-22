package com.test.bootdemo.aspect;

import com.test.bootdemo.Annotation.UserAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//切面
@Aspect
@Component
@Lazy(value = false)
public class SelectAspect {

    //应用于较少切点，只需要配置切面
    @Before(value = "execution(void com.test.bootdemo.PointCut.SelectPointCut.test(..))")
    public void printInfo(){
        System.out.println("11111111111111");
    }

    //应用于较多切点，需要配置注解类，切点，切面
    @Pointcut("@annotation(com.test.bootdemo.Annotation.UserAnnotation)")
    private void cutMethod() {
        /**
         * 定义切入点：对要拦截的方法进行定义与限制，如包、类
         *
         * 1、execution(public * *(..)) 任意的公共方法
         * 2、execution（* set*（..）） 以set开头的所有的方法
         * 3、execution（* com.lingyejun.annotation.LoggerApply.*（..））com.lingyejun.annotation.LoggerApply这个类里的所有的方法
         * 4、execution（* com.lingyejun.annotation.*.*（..））com.lingyejun.annotation包下的所有的类的所有的方法
         * 5、execution（* com.lingyejun.annotation..*.*（..））com.lingyejun.annotation包及子包下所有的类的所有的方法
         * 6、execution(* com.lingyejun.annotation..*.*(String,?,Long)) com.lingyejun.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
         * 7、execution(@annotation(com.lingyejun.annotation.Lingyejun))
         */
    }

    /**
     * 前置通知：在目标方法执行前调用
     */
    @Before("cutMethod()")
    public void begin() {
        System.out.println("==@Before== 执行业务");
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @AfterReturning("cutMethod()")
    public void afterReturning() {
        System.out.println("==@AfterReturning== 执行业务");
    }

    /**
     * 后置/最终通知：无论目标方法在执行过程中出现都会在它之后调用
     */
    @After("cutMethod()")
    public void after() {
        System.out.println("==@After== 执行业务");
    }

    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing("cutMethod()")
    public void afterThrowing() {
        System.out.println("==@AfterThrowing== 执行业务");
    }

    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     */
    @Around("cutMethod()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        UserAnnotation userAnnotation = getDeclaredAnnotation(joinPoint);
        System.out.println("==@Around== 执行日志业务 --》 method name " + methodName + " args " + params[0]);
        // 执行源方法
        joinPoint.proceed();
        // 模拟进行验证
        if (params != null && params.length > 0 && params[0].equals("Blog Home")) {
            System.out.println("==@Around== lingyejun blog logger --》 " + userAnnotation.module() + " auth success");
        } else {
            System.out.println("==@Around== lingyejun blog logger --》 " + userAnnotation.module() + " auth failed");
        }
    }

    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    public UserAnnotation getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        UserAnnotation annotation = objMethod.getDeclaredAnnotation(UserAnnotation.class);
        // 返回
        return annotation;
    }
}
