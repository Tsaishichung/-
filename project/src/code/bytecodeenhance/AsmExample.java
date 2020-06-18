package code.bytecodeenhance;



import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

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
        ClassVisitor classAdapter = new AsmClassAdapter(writer);
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

}

class AsmClassAdapter extends ClassVisitor {

    public AsmClassAdapter(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
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

class EnhanceOutPutMethodAdapter extends MethodVisitor  {

    public EnhanceOutPutMethodAdapter(MethodVisitor methodVisitor){
        super(Opcodes.ASM5, methodVisitor);
    }

    // visitCode方法在ASM开始访问方法的Code区时回调
    @Override
    public void visitCode() {
        super.visitCode();
        // System.out.println("start")对应的字节码，在visitCode访问方法之后添加
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("执行前调用");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    // 每当ASM访问到无参数指令时，都会调用visitInsn方法
    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
            // System.out.println("end")对应的字节码，在visitInsn方法返回前添加
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("执行后调用");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        }
        super.visitInsn(opcode);
    }

}
