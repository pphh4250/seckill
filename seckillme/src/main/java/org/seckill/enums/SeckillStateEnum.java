package org.seckill.enums;

/**
 * Created by Admin on 2016/6/26.
 */
public enum  SeckillStateEnum {

    SUCCEESS(1,"SUCCESS"),

    END(0,"CLOSED"),
    REPEAT_KILL(-1,"REPEATED"),
    INNER_ERROR(-2,"INNER-ERROR"),
    DATA_REWRITE(-3,"HACK");

    private int state;
    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index){
        for (SeckillStateEnum state : values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
