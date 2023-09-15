package exercise;

import java.lang.reflect.Proxy;
import java.util.Arrays;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{

    private boolean toLog;
    private String logLevel;
    private Logger LOGGER = LoggerFactory.getLogger("Def Logger");

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Inspect.class)) {
            toLog = true;

            String logLevel = bean.getClass().getAnnotation(Inspect.class).level();

            this.logLevel = logLevel;
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Object resultBean = bean;
        if (toLog) {
            Object proxyCalculator = Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        if (method.getName().equals("sum")
                                || method.getName().equals("mult")) {
                            if (logLevel.equals("info")) {
                                LOGGER.info("Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args));
                            } else {
                                LOGGER.debug("Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args));
                            }

                            return method.invoke(bean, args);
                    } else {
                            throw new UnsupportedOperationException(
                                    "UnsupportedOperation " + method.getName()
                            );
                        }
            });

            resultBean = proxyCalculator;
            toLog = false;
        }

        return resultBean;
    }
}
// END
