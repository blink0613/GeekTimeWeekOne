package WeekOne;
import java.lang.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;


public class HelloClassLoader extends ClassLoader{
    public static void main(String args[]) throws Exception {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> clazz = helloClassLoader.loadClass("Hello");
        // 创建对象
        Object instance = clazz.newInstance();
        // 调用方法
        Method method = clazz.getMethod("hello");
        method.invoke(instance);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException{
        try{
            InputStream inputStream = this.getClass().getResourceAsStream("/Hello.xlass");

            OutputStream outputStream = new FileOutputStream("src/main/resources/Hello.class");
            byte[] bytes = new byte[inputStream.available()];
            for(int i = 0; i < bytes.length; i++ ) {
                bytes[i] =(byte) (255 - inputStream.read());
            }
            outputStream.write(bytes);
            inputStream.close();
            outputStream.close();
            return defineClass(name,bytes,0,bytes.length);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
