package code.bytecodeenhance;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibExample
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月17日 10:14
 */
public class CglibExample {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "G:\\开源源码\\learning\\project");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibExample.class);
        enhancer.setCallback(new CglibExampleInterceptor());
        CglibExample cglibExample = (CglibExample) enhancer.create();
        cglibExample.function();
    }

    public void function(){
        System.out.println("执行中");
    }
}

class CglibExampleInterceptor implements MethodInterceptor{

    @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable {
        System.out.println("执行前调用");
        Object response = methodProxy.invokeSuper(o, objects);
        System.out.println("执行后调用");
        return response;
    }
}
