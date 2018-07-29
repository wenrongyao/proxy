package com.wry.dynamicproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author rongyao wen
 * @date 2018/7/29
 */
public class Proxy {
    public static Object newInstance(Class inface, InvocationHandler ih) throws Exception{
        String methodStr= "";
        String rt = "\r\t";
        Method methods[] = inface.getMethods();
        // 循环生成方法
        for(Method m: methods){
            methodStr =
                    "    @Override"+rt +
                            "    public void "+m.getName()+"() {"+rt +
                            "       try{"+rt+
                            "           Method md = "+inface.getName()+".class.getMethod(\""+ m.getName() + "\");"+rt +
                            "           ih.invoke(md);"+rt +
                            "       }catch(Exception e){e.printStackTrace();}"+rt+
                            "    }";
        }

        String src =
                "package com.wry.dynamicproxy;" + rt +
                "import java.lang.reflect.Method; "+rt+
                "public class $Proxy implements "+inface.getName()+" {" +rt +
                "    com.wry.dynamicproxy.InvocationHandler ih;"+rt +

                "    public $Proxy(InvocationHandler ih) {"+rt +
                "        this.ih = ih;"+rt +
                "    }"+rt +
                methodStr +
                "}";

        String fileName = System.getProperty("user.dir") + "/com/wry/dynamicproxy/$Proxy.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(src);
        fw.flush();
        fw.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(fileName);
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();


        URL urls[] = new URL[]{new URL("file:/" + System.getProperty("user.dir")+"/")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class c = urlClassLoader.loadClass("com.wry.dynamicproxy.$Proxy");
        Object obj = c.getConstructor(InvocationHandler.class).newInstance(ih);
        return obj;
    }

}
