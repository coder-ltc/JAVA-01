import javax.annotation.Resources;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;

public class HelloClassLoader extends ClassLoader {

    public  static void main(String[] args) {
        try {
            Class o = new HelloClassLoader().findClass("Hello");
            Method method = o.getMethod("hello");
            method.invoke(o.newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

//        URL resource = this.getClass().getClassLoader().getResource("");
        File file = new File("./" + name + ".xlass");
        byte[] bytes = fileToBytes(file);
        for (int i = 0; i < bytes.length; i++){
            bytes[i] = (byte) (255 - (int) bytes[i]);
        }

        return defineClass(name, bytes, 0, bytes.length);
    }

    public static byte[] fileToBytes(File file) {
        byte[] bytes = null;

        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try{
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            bytes = bos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }
}
