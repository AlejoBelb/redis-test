package com.pragma.redis.redistest.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
 
import com.pragma.redis.redistest.model.User;
 
@Repository
public class UserRepository {
    
    public static final String USER_KEY = "USER";
 
    private HashOperations hashOperations;
 
    private RedisTemplate redisTemplate;
 
    public UserRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }
 
    public void save(User user) {
        hashOperations.put(USER_KEY, user.getId(), user);
    }
 
    public List findAll(){
        return hashOperations.values(USER_KEY);
    }
 
    public User findById(String id) {
        return (User) hashOperations.get(USER_KEY, id);
    }
 
    public void update(User user) {
        save(user);
    }
 
    public void delete(String id) {
        hashOperations.delete(USER_KEY, id);
    }
 
}
