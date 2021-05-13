package com.example.qzystart.Base;


import android.app.Activity;

import java.util.Stack;

/**
 * activity管理类
 */
public class ActivityManager {
    /** 用于记录activity栈，方便退出程序 */
    private Stack<Activity> mActivityStack = new Stack<Activity>();
    private static ActivityManager manager;

    public static ActivityManager getActivityManager() {
        if (manager == null) {
            manager = new ActivityManager();
        }
        return manager;
    }

    /**
     * 构造方法
     */
    private ActivityManager() { }

    public final void putActivity(Activity atv) {
        mActivityStack.add(atv);
    }

    public final void removeActivity(Activity atv) {
        mActivityStack.remove(atv);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){
        int size = mActivityStack.size();
        if (size > 0) {
            return mActivityStack.lastElement();
        } else {
            return null;
        }
    }

    /**
     * 清除应用的task栈，如果程序正常运行这会导致应用退回到桌面
     */
    public final void exit() {
        //for(Activity activity: mActivityStack){
        //    if(!activity.isFinishing()){
        //        activity.finish();
        //    }
        //}
        int size = mActivityStack.size();
        for (int i = 0; i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }
}
