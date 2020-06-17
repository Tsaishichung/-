package code.bytecodeenhance;

/**
 * @author caizhichong
 * @version V1.0.0
 * @date 2020Äê06ÔÂ17ÈÕ 13:25
 */
public class AsmFunction {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
        InstantiationException {
        Class clazz = Class.forName("code.bytecodeenhance.AsmExample");
        AsmExample asmExample = (AsmExample)clazz.newInstance();
        asmExample.function();
    }
}
