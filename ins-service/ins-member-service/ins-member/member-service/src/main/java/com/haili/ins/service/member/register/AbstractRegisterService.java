package com.haili.ins.service.member.register;

/**
 * @author MaTang
 */
public abstract class AbstractRegisterService {

    public abstract  void registerBefore();

    public abstract void registerAfter();

    public void register(){
        registerBefore();



        registerAfter();
    }



}
