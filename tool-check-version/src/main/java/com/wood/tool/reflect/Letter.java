package com.wood.tool.reflect;

import java.util.List;

/**
 * @author:thinkpad
 * @date:2019/6/15
 * @desc: TODO
 **/
public class Letter {

    private String name;

    private List<LetterAccounts> list;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LetterAccounts> getList() {
        return list;
    }

    public void setList(List<LetterAccounts> list) {
        this.list = list;
    }
}
