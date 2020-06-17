package code.bytecodeenhance;


import com.sun.xml.internal.ws.org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * AsmExample
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月17日 9:10
 */
public class AsmExample {

    public static void main(String[] args) throws IOException {
        ClassReader reader = new ClassReader("code.bytecodeenhance.AsmExample");
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AsmClassAdapter(writer);
        reader.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = writer.toByteArray();
        File file = new File("G:\\开源源码\\learning\\project\\out\\production\\project\\code\\bytecodeenhance\\AsmExample.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    public void function(){
        System.out.println("执行中");
    }

    public static void start(){
        System.out.println("执行前");
    }

    public static void end(){
        System.out.println("执行后");
    }

}

class AsmClassAdapter extends ClassAdapter {

    public AsmClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrapperMv = mv;
        if(mv != null){
            if(name.equals("function")){
                wrapperMv = new EnhanceOutPutMethodAdapter(mv);
            }
        }
        return wrapperMv;
    }
}

class EnhanceOutPutMethodAdapter extends MethodAdapter{

    public EnhanceOutPutMethodAdapter(MethodVisitor mv) {
        super(mv);
    }

    @Override
    public void visitCode(){
        visitMethodInsn(Opcodes.INVOKESTATIC, "code.bytecodeenhance.AsmExample", "start", "()V");
    }

    @Override
    public void visitEnd(){
        visitMethodInsn(Opcodes.INVOKESTATIC, "code.bytecodeenhance.AsmExample", "end", "()V");
    }
}
