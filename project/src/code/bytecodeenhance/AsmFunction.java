package code.bytecodeenhance;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月17日 13:25
 */
public class AsmFunction extends ClassLoader{



    public static void main(String[] args)
        throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException,
        NoSuchMethodException, InvocationTargetException {
       /* ClassLoader myClassLoader = new ClassLoader() {
            @Override public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                Class c = findLoadedClass(name);
                if(c == null){
                    try {
                        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                        InputStream is = getClass().getResourceAsStream(fileName);
                        if(is == null){
                            return super.loadClass(name);
                        }
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        return defineClass(name, b, 0 , b.length);
                    } catch (IOException e) {
                        throw new ClassNotFoundException("找不到类");
                    }
                }
               if(resolve){
                   resolveClass(c);
               }
               return c;
            }
        };*/
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = Class.forName("code.bytecodeenhance.AsmExample", true, myClassLoader);
        Object target = clazz.newInstance();
        Method method = target.getClass().getMethod("function");
        method.invoke(target);
    }
}
class MyClassLoader extends ClassLoader
{
    public MyClassLoader()
    {

    }

    public MyClassLoader(ClassLoader parent)
    {
        super(parent);
    }

    @Override protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        File file = getClassFile(name);
        try
        {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    private File getClassFile(String name)
    {
        File file = new File("G:\\开源源码\\learning\\project\\out\\production\\project\\code\\bytecodeenhance\\AsmExample.class");
        return file;
    }

    private byte[] getClassBytes(File file) throws Exception
    {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true)
        {
            int i = fc.read(by);
            if (i == 0 || i == -1){
                break;
            }
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return baos.toByteArray();
    }
}
