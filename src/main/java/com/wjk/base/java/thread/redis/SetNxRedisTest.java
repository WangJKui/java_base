package com.wjk.base.java.thread.redis;

import java.util.Collections;

import redis.clients.jedis.Jedis;
/**
 * https://www.cnblogs.com/linjiqin/p/8003838.html
 * @ClassName:  SetNxRedisTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年2月26日 上午9:21:18   
 *
 */
/**
 * https://blog.csdn.net/long2010110/article/details/82911168
 * RedisTemplate
 * https://blog.csdn.net/qq_28397259/article/details/80839072
 * @ClassName:  SetNxRedisTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年2月26日 上午9:46:10   
 *
 */
/**
 * 秒杀
 * https://github.com/zaiyunduan123/springboot-seckill
 * @ClassName:  SetNxRedisTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年2月26日 上午10:07:54   
 *
 */
public class SetNxRedisTest {
	private static final String LOCK_SUCCESS = "OK";
	//即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
	private static final String SET_IF_NOT_EXIST = "NX";
	//意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
	private static final String SET_WITH_EXPIRE_TIME = "PX";
	private static final Long RELEASE_SUCCESS = 1L;

	/**
	 * 尝试获取分布式锁
	 * @param jedis Redis客户端
	 * @param lockKey 锁
	 * @param requestId 请求标识
	 * @param expireTime 超期时间
	 * @return 是否获取成功
	 */
	public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

		String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

		if (LOCK_SUCCESS.equals(result)) {
			return true;
		}
		return false;
	}
	

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

}
