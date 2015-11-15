package top.ddys.multhread.impl;

import top.ddys.multhread.ISelectTask;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 带获取任务的多线程处理任务封装
 * className: SelectTaskMulThreadService
 *
 * @version 1.0
 *          Date Time: a
 *@author: ddys
 */
public class SelectTaskMulThreadService<T,V> extends TaskMulThreadService<T,V> {
    public SelectTaskMulThreadService(ISelectTask<T, V> taskHandle, Integer maxThread) {
        super(taskHandle, maxThread);
    }

    public SelectTaskMulThreadService(ISelectTask<T, V> taskHandle) {
        super(taskHandle);
    }

    /**
     * @param '' 传递参数
     * @return a返回类型
     * @throws
     * @Title: a
    * @Description: 执行任务，返回所有任务的结果集合
     * @author ddys
     * @date 2015-11-15 21:06
     */
    public List<V> execute(T... task) {
        if(this.taskHandle instanceof ISelectTask){
            task = ((ISelectTask<T,V>) this.taskHandle).getTaskItem();
        }else{
            throw new RuntimeException("SelectTaskMulThreadService 必须使用 ISelectTask的实现");
        }
        return super.execute(task);
    }

}
