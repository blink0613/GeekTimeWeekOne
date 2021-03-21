import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class HelloClassLoader extends ClassLoader{
    public static void main(String args[]) throws ClassNotFoundException {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        helloClassLoader.loadClass("Hello");
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
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
