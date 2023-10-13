package de.telran.g10170123ebeshop.logging;

import de.telran.g10170123ebeshop.domain.entity.common.CommonProduct;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.addProduct(..))")
    public void addProduct() {}

    @Before("addProduct()")
    public void beforeAddingProduct(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        logger.info("Вызван метод addProduct класса JpaProductService с параметром {}.", params[0]);
    }

    @After("addProduct()")
    public void afterAddingProduct(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        logger.info("Сохранённому продукту присвоен идентификатор {}.",
                ((CommonProduct) params[0]).getId());
    }

    @Pointcut("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.getCount(..))")
    public void getCount() {}

    @AfterReturning("getCount()")
    public void afterProductCountReturning() {
        logger.info("Метод getCount успешно вернул значение");
    }

    @Pointcut("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.deleteById(..))")
    public void deleteById() {}

    @AfterThrowing("deleteById()")
    public void throwsExceptionIfIdIsIncorrect(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        logger.error("Метод deleteById выбросил исключение. Некорректный ID - {}.",
                params[0]);
    }

    @Around("getCount()")
    public Object aroundGetProductCount(ProceedingJoinPoint joinPoint) {
        logger.info("Отработал Around метода getCount()");
        try {
            Object result = joinPoint.proceed();
            logger.info("Метод getCount() отработал с результатом {}", result);
            logger.info("Подменяем результат и возвращаем 777");
            return 777;
        } catch (Throwable e) {
            logger.error("Тут какая-то ошибка!");
            throw new RuntimeException(e);
        }
    }

    // =====================================================================================

    //При помощи АОП сделать логирование всех методов сервиса продуктов.
    //Для задания Pointcut использовать JpaProductService.*(..). В лог должно выводиться:
    //А. Какой метод и с какими параметрами вызван.
    //Б. Какой метод завершил работу.

//    @Before("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.*(..))")
//    public void logMethodEntry(JoinPoint joinPoint) {
//        Object[] methodArgs = joinPoint.getArgs();
//        System.out.println("Вызван метод их пакета JpaProductService " + methodArgs + " с аргументами: " + Arrays.toString(methodArgs));
//    }
//
//    @After("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.*(..))")
//    public void logMethodExit(JoinPoint joinPoint) {
//        Object[] methodArgs = joinPoint.getArgs();
//        System.out.println("Метод их пакета JpaProductService " + methodArgs + " отработал успешно.");
//    }

    // =======================================================================================

    //При помощи АОП сделать логирование всех сервисов (то есть создать Pointcut сразу на пакет). В лог должно выводиться:
    //А. Какой метод какого класса и с какими параметрами вызван.
    //Б. Какой метод какого класса завершил работу.
    //В. Какой метод какого класса успешно вернул результат.
    //Г. Какой метод какого класса вызвал ошибку.

    @Before("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        System.out.println("Метод " + methodName + " класса " + className + " вызван с аргументами: " + Arrays.toString(methodArgs));
    }

    @After("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Метод " + methodName + " класса " + className + " успешно выполнен.");
    }

    @AfterReturning(pointcut = "execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.*(..))", returning = "result")
    public void logMethodSuccess(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Метод" + methodName + " класса " + className + " успешно вернул результат: " + result);
    }

    @AfterThrowing(pointcut = "execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.*(..))", throwing = "ex")
    public void logMethodError(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Метод" + methodName + " класса " + className + " выкинул исключение: " + ex.getMessage());
    }


    @Before("execution(* de.telran.g10170123ebeshop.service.jpa.JpaCustomerService.*(..))")
    public void logMethodEntry2(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        System.out.println("Метод " + methodName + " класса " + className + " вызван с аргументами: " + Arrays.toString(methodArgs));
    }

    @After("execution(* de.telran.g10170123ebeshop.service.jpa.JpaCustomerService.*(..))")
    public void logMethodExit2(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Метод " + methodName + " класса " + className + " успешно выполнен.");
    }

    @AfterReturning(pointcut = "execution(* de.telran.g10170123ebeshop.service.jpa.JpaCustomerService.*(..))", returning = "result")
    public void logMethodSuccess2(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Метод" + methodName + " класса " + className + " успешно вернул результат: " + result);
    }

    @AfterThrowing(pointcut = "execution(* de.telran.g10170123ebeshop.service.jpa.JpaCustomerService.*(..))", throwing = "ex")
    public void logMethodError2(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Метод" + methodName + " класса " + className + " выкинул исключение: " + ex.getMessage());
    }

}

