package com.lw.entity;

import java.util.List;

public class ImportResult<T>
{
    String message;
 
    List<T> resultList;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<T> getResultList()
    {
        return resultList;
    }

    public void setResultList(List<T> resultList)
    {
        this.resultList = resultList;
    }

 
    
}
