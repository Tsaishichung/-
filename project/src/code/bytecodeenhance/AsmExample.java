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
 * @date 2020��06��17�� 9:10
 */
public class AsmExample {

    public static void main(String[] args) throws IOException {
        ClassReader reader = new ClassReader("code.bytecodeenhance.AsmExample");
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor classAdapter = new AsmClassAdapter(writer);
        reader.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = writer.toByteArray();
        File file = new File("G:\\��ԴԴ��\\learning\\project\\out\\production\\project\\code\\bytecodeenhance\\AsmExample.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }

    public void function(){
        System.out.println("ִ����");
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

    // visitCode������ASM��ʼ���ʷ�����Code��ʱ�ص�
    @Override
    public void visitCode() {
        super.visitCode();
        // System.out.println("start")��Ӧ���ֽ��룬��visitCode���ʷ���֮�����
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("ִ��ǰ����");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    // ÿ��ASM���ʵ��޲���ָ��ʱ���������visitInsn����
    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
            // System.out.println("end")��Ӧ���ֽ��룬��visitInsn��������ǰ���
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("ִ�к����");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        }
        super.visitInsn(opcode);
    }

}
