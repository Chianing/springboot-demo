package com.demo.chianing.test.queue.delay;

import com.demo.chianing.test.queue.delay.entity.Message;
import com.demo.chianing.util.DateUtil;
import com.demo.chianing.util.GsonUtil;

import java.util.Arrays;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        Message<String> msg1 = new Message<>("msg1", 1, TimeUnit.SECONDS);
        Message<String> msg2 = new Message<>("msg2", 3, TimeUnit.SECONDS);
        Message<String> msg3 = new Message<>("msg3", 5, TimeUnit.SECONDS);
        DelayQueue<Message<String>> delayQueue = new DelayQueue<>(Arrays.asList(msg1, msg2, msg3));

        System.out.printf("start deal message, time is: %s%n", DateUtil.getDefaultFormatDate());
        System.out.println("----------------");
        while (!delayQueue.isEmpty()) {
            Message<String> msg = delayQueue.poll();
            if (msg != null) {
                System.out.printf("get message success, time is: %s, msg is: %s%n",
                        DateUtil.getDefaultFormatDate(), GsonUtil.toJsonString(msg));
            } else {
                System.out.printf("no message, time: %s%n", DateUtil.getDefaultFormatDate());
            }

            if (delayQueue.isEmpty()) {
                break;
            }

            Thread.sleep(1000);

        }
        System.out.println("----------------");
        System.out.printf("end deal message, time is: %s%n", DateUtil.getDefaultFormatDate());

    }

}