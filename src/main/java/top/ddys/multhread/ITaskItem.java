package top.ddys.multhread;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 获取任务项接口
 * className: ITaskItem
 *
 * @version 1.0
 *          Date Time: a
 *@author: ddys
 */
public interface ITaskItem<T> {
    /**
     * @param 'a 传递参数
     * @return a 回类型
     * @throws
     * @Title: a
    * @Description: 获取一批任务
     * @author ddys
     * @date 2015-11-15 21:09
     */
    public T[] getTaskItem();
}
