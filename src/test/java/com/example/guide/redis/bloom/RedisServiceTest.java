package com.example.guide.redis.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {
  private BloomFilterHelper<String> bloomFilterHelper;

  @Test
  public void testBloomFilter() {
    bloomFilterHelper
        = new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
        .putString(from, Charsets.UTF_8), 1, 0.01);

    redisService.addByBloomFilter(bloomFilterHelper, "test", "test");
    boolean b = redisService.includeByBloomFilter(bloomFilterHelper, "test", "test");
    assertTrue("test is in bloom filter", b);
  }

  @Test
  public void testBloomFilter2() {
    bloomFilterHelper
        = new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
        .putString(from, Charsets.UTF_8), 100000, 0.01);
    int count = 0;
//    for (int i = 0; i < 100000; i++) {
////      redisService.addByBloomFilter(bloomFilterHelper, "test" + i, "test" + i);
//    }
//    for (int i = 0; i < 100001; i++) {
//      boolean b = redisService.includeByBloomFilter(bloomFilterHelper, "test" + i, "test" + i);
//      if (!b) {
//        count++;
//      }
//    }
//    System.out.println("---->" + count);

  }


  @Autowired
  private RedisService redisService;
}