package com.imhqs.web.modules.sys.system.service.impl;

import com.imhqs.web.modules.sys.system.mapper.SysDictMapper;
import com.imhqs.web.modules.sys.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public String findDict(String table, String text, String key, String value) {
        return sysDictMapper.findDict(table, text, key, value);
    }
}
