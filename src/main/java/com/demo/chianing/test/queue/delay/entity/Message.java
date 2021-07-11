package com.demo.chianing.test.queue.delay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class Message<T> implements Delayed {

    private T data;
    private long time;

    public Message(T data) {
        this.data = data;
    }

    public Message(T data, long time, TimeUnit unit) {
        this.data = data;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(Delayed o) {
        Message<T> o1 = (Message<T>) o;
        return (int) (this.time - o1.getTime());
    }
}
