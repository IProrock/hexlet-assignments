package exercise;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    private final Map<String, String> toLogBeans= new HashMap<>();
    private Logger LOGGER = LoggerFactory.getLogger("Def Logger");

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Inspect.class)) {


            String logLevel = bean.getClass().getAnnotation(Inspect.class).level();

            toLogBeans.put(beanName, logLevel);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Object resultBean = bean;
        if (toLogBeans.containsKey(beanName)) {
            Object proxyCalculator = Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        if (method.getName().equals("sum")
                                || method.getName().equals("mult")) {
                            if (toLogBeans.get(beanName).equals("info")) {
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
        }

        return resultBean;
    }
}
// END
