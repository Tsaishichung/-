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
 * @date 2020��06��17�� 10:14
 */
public class CglibExample {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "G:\\��ԴԴ��\\learning\\project");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibExample.class);
        enhancer.setCallback(new CglibExampleInterceptor());
        CglibExample cglibExample = (CglibExample) enhancer.create();
        cglibExample.function();
    }

    public void function(){
        System.out.println("ִ����");
    }
}

class CglibExampleInterceptor implements MethodInterceptor{

    @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable {
        System.out.println("ִ��ǰ����");
        Object response = methodProxy.invokeSuper(o, objects);
        System.out.println("ִ�к����");
        return response;
    }
}
