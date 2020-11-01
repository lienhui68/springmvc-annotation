package com.eh.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

@Controller
public class AsyncController {

    /**
     * 1. 控制器返回Callable
     * 2. Spring异步处理，将Callable提交到TaskExecutor，使用一个隔离的线程执行
     * 3. DispatcherServlet和所有的Filter退出web容器的线程，但是response保持打开状态，还能向浏览器写数据
     * 4. Callable返回结果，SpringMVC将请求重新派发给容器（所以日志打印两次请求开始），恢复之前的处理
     * 5. 根据Callable返回的结果，SpringMVC继续进行视图渲染流程等（从头开始，请求会再次发给mvc，从收请求到视图渲染，
     * 目标方法不用执行，异步返回的结果就是目标方法执行的结果）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {
        System.out.println("主线程开始..." + Thread.currentThread() + "====" + System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println("副线程开始..." + Thread.currentThread() + "====" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("副线程结束..." + Thread.currentThread() + "====" + System.currentTimeMillis());
            return "Callable<String> async01()";
        };

        System.out.println("主线程结束..." + Thread.currentThread() + "====" + System.currentTimeMillis());
        return callable;
    }


    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> deferredResult = new DeferredResult<>(3000L, "create order failed");

        DeferedResultQueue.save(deferredResult);

        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create() {
        // 在这儿创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferedResultQueue.get();
        deferredResult.setResult(order);
        return "success";
    }
}
