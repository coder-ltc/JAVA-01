作业：

## class1：

**1.**使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

```
F:\极客大学\文件>java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:11229

F:\极客大学\文件>java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:9309

F:\极客大学\文件>java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:11538

F:\极客大学\文件>java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:12909
```

```
F:\极客大学\文件>java -XX:+UseSerialGC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:9203

F:\极客大学\文件>java -XX:+UseParallelGC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:13625

F:\极客大学\文件>java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:11411

F:\极客大学\文件>java -XX:+UseG1GC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
执行结束!共生成对象次数:16430
```

**2.**使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

**3.（选做）** 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

**4.（必做）** 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。

1. 如果系统考虑吞吐优先，CPU 资源都用来最大程度处理业务，用 Parallel GC; 
2. 一般 4G 以上，用 G1 的性价比较高。

## class2：

**1.（选做）**运行课上的例子，以及 Netty 的例子，分析相关现象。

**2.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [http://localhost:8801 ](http://localhost:8801/)，代码提交到 Github。 

```

import java.io.IOException;
import jdk.nashorn.internal.codegen.CompilerConstants;
import okhttp3.*;
import java.io.IOException;

public class HttpDemo {

    public static void main(String[] args) {
        String url = "http://localhost:8801";
        OkHttpClient okHttpClient = new OkHttpClient(); 
        Request request = new Request.Builder().url(url).build(); 
        okHttpClient.newCall(request).enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }

            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

}
```

