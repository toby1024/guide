package com.example.guide.redis.bloom;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @program: guide
 * @description:
 * @author: Jason
 * @date: 2019-12-10 15:37
 **/
@Service
public class RedisService {
  /**
   * 根据给定的布隆过滤器添加值
   */
  public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
    Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
    int[] offset = bloomFilterHelper.murmurHashOffset(value);
    for (int i : offset) {
      redisTemplate.opsForValue().setBit(key, i, true);
    }
  }

  /**
   * 根据给定的布隆过滤器判断值是否存在
   */
  public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
    Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
    int[] offset = bloomFilterHelper.murmurHashOffset(value);
    for (int i : offset) {
      if (!redisTemplate.opsForValue().getBit(key, i)) {
        return false;
      }
    }

    return true;
  }

  /**
   * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
   * @param redisConnectionFactory
   * @return
   */
  @Bean
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);

    // 使用Jackson2JsonRedisSerialize 替换默认序列化
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    // 设置value的序列化规则和 key的序列化规则
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Autowired
  private RedisTemplate redisTemplate;
}
