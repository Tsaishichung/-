package code.bytecodeenhance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JdkProxyExample
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��17�� 9:15
 */
public class JdkProxyExample {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IExample jdkProxyExample = (IExample)Proxy.newProxyInstance(
            IExample.class.getClassLoader(),
            new Class[]{IExample.class},
            new ExampleInvocationHandler(new IExampleImpl()));
        jdkProxyExample.function();
    }
}


interface IExample{

    void function();
}

class IExampleImpl implements IExample{

    @Override public void function() {
        System.out.println("ִ����");
    }
}


class ExampleInvocationHandler implements InvocationHandler{

    private Object proxyObject;

    public ExampleInvocationHandler(Object proxyObject){
        this.proxyObject = proxyObject;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ִ��ǰ����");
        Object response = method.invoke(proxyObject, args);
        System.out.println("ִ�к����");
        return response;
    }

}
