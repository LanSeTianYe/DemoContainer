package com.xiaotian.demo.algorithm.su;

import org.junit.Assert;
import org.junit.Test;

public class ShortUrlGeneratorTest {

    @Test
    public void next() {
        ShortUrlGenerator shortUrlGenerator = new ShortUrlGenerator(1000000000);
        Assert.assertNotNull(shortUrlGenerator.next());
    }
}
