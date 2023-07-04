package etu1820.framework;

import java.util.HashMap;

public class ModelView 
{
    String url;
    HashMap<String, Object> data = new HashMap<>();

    // getters & setters
    public String getUrl() 
    {
        return url;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }
    public HashMap<String, Object> getData()
    {
        return data;
    }
    public void setData(HashMap<String, Object> data)
    {
        this.data = data;
    }

    // constructor
    public ModelView() {}
    public ModelView(String url) 
    {
        this.url = url;
    }

    public void addItem(String key, Object value)
    {
        HashMap<String, Object> temp = new HashMap<>();
        temp.put(key, value);
        this.setData(temp);
    }
}
