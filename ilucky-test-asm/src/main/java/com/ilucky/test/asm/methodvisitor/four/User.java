package com.ilucky.test.asm.methodvisitor.four;

import java.util.HashMap;
import java.util.Map;

class User {
    
    private String username;
    private String password;
    private Map<String, Object> family = new HashMap<String, Object>();
    
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void addFamily(String key, String value) {
        this.family.put(key, value);
    }
    public void getFamily(String key) {
        this.family.get(key);
    }
}
