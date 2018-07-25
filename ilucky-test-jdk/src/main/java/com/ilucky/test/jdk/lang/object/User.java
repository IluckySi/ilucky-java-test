package com.ilucky.test.jdk.lang.object;

public class User {

    private String name;
    private int page;
    
    
    public String getName() {
        return name;
    }
    public int getPage() {
        return page;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + page;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (page != other.page)
            return false;
        return true;
    }
    
    public User(String name, int page) {
        super();
        this.name = name;
        this.page = page;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", page=" + page + "]";
    }
}
