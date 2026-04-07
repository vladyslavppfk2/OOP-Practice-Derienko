package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тестування паралельної обробки колекції та worker thread.
 */
public class MainTest {
    private ViewResult view;

    @BeforeEach
    void setUp() {
        view = new ViewResult();
        view.viewInit("10", "11", "1");   // 2*3*1 = 6
        view.viewInit("101", "10", "11"); // 5*2*3 = 30
        view.viewInit("11", "11", "11");  // 3*3*3 = 27
        view.viewInit("100", "101", "10"); // 4*5*2 = 40
    }

    @Test
    void testMax() {
        MaxCommand max = new MaxCommand(view);
        max.execute();
        assertEquals(3, max.getResult());
    }

    @Test
    void testAvg() {
        AvgCommand avg = new AvgCommand(view);
        avg.execute();
        assertEquals(25.75, avg.getResult(), 1.0e-9);
    }

    @Test
    void testMin() {
        MinMaxCommand minMax = new MinMaxCommand(view);
        minMax.execute();
        assertEquals(0, minMax.getResultMin());
        assertEquals(3, minMax.getResultMax());
    }

    @Test
    void testMaxQueue() throws Exception {
        CommandQueue queue = new CommandQueue();
        MaxCommand max = new MaxCommand(view);
        queue.put(max);
        while (max.running()) {
            TimeUnit.MILLISECONDS.sleep(20);
        }
        queue.shutdown();
        queue.awaitTermination();
        assertEquals(3, max.getResult());
    }

    @Test
    void testAvgQueue() throws Exception {
        CommandQueue queue = new CommandQueue();
        AvgCommand avg = new AvgCommand(view);
        queue.put(avg);
        while (avg.running()) {
            TimeUnit.MILLISECONDS.sleep(20);
        }
        queue.shutdown();
        queue.awaitTermination();
        assertEquals(25.75, avg.getResult(), 1.0e-9);
    }

    @Test
    void testMinQueue() throws Exception {
        CommandQueue queue = new CommandQueue();
        MinMaxCommand minMax = new MinMaxCommand(view);
        queue.put(minMax);
        while (minMax.running()) {
            TimeUnit.MILLISECONDS.sleep(20);
        }
        queue.shutdown();
        queue.awaitTermination();
        assertTrue(minMax.getResultMin() >= 0);
        assertTrue(minMax.getResultMax() >= 0);
        assertEquals(0, minMax.getResultMin());
        assertEquals(3, minMax.getResultMax());
    }
}
