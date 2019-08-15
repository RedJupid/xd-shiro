package com.xd.xdshiro.base;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author xd
 * @Description
 * @Date 2019/7/4 16:55
 */
public class BaseController<T> {

    @Autowired
    IService<T> service;

    @GetMapping("/list")
    public List<T> list(){
        return service.list();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public String add(@RequestBody T o){
        boolean flag = service.save(o);
        if (flag){
            return "success";
        }else{
            return "fail";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id){
        boolean flag = service.removeById(id);
        if (flag){
            return "success";
        }else{
            return "fail";
        }
    }

    @DeleteMapping("/ids")
    public String deleteByIds(@RequestBody List<Integer> ids){
        boolean flag = service.removeByIds(ids);
        if (flag){
            return "success";
        }else{
            return "fail";
        }
    }

    @PutMapping
    public String updateById(@RequestBody T o){
        boolean flag = service.updateById(o);
        if (flag){
            return "success";
        }else{
            return "fail";
        }
    }



}
