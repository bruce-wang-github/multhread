# multhread
多线程处理任务的封装
========
最近公司项目很多地方使用多线程处理一些任务，逻辑代码和多线程处理代码混合在一起，造成代码的可读性超级差，现在把多线程相关的处理抽出来，方面代码中重复使用。抽的不好，欢迎大家拍砖
========
使用方法很简单，有两种使用方法
1.直接传递一批任务给到多线程处理方法，返回处理结果
代码如下:
```java

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
```
2.附带一个查询任务的方法，实现这个查询任务方法和业务处理方法，然后执行返回处理结果
代码如下:
```java
/**
 * Created with IntelliJ IDEA.
 * 测试带获取任务的多线程处理任务
 * className: SelectTaskMulThreadServiceTest
 *
 * @version 1.0
 *          Date Time: a
 *@author: XWK
 */
public class SelectTaskMulThreadServiceTest extends TestCase implements ISelectTask<String,Boolean>{

    public void testExecute() throws Exception {
        IMulThreadService<String,Boolean> mulThreadService = new SelectTaskMulThreadService(this);
        long start = System.currentTimeMillis();
        List<Boolean> result = mulThreadService.execute();
        for (Boolean e : result){
            if(!e){
                System.out.println("任务处理失败");
            }
        }
        System.out.println("所有任务处理完成，耗时"+(System.currentTimeMillis()-start)+",任务成功数"+result.size());
    }
    /**
     * Created with IntelliJ IDEA.
     * 执行任务，返回所有执行的结果
     * className: TaskMulThreadService
     *
     * @author: ddys
     * @version 1.0
     * Date Time:
     */
    public Boolean execute(String s) {
        System.out.println(Thread.currentThread().getId()+"线程正在处理"+s);
        return true;
    }

    /**
     * @param 'a 传递参数
     * @return a 回类型
     * @throws
     * @Title: a
     * @Description: 获取一批任务
     * @author ddys
     * @date 2015-11-15 21:09
     */
    public String[] getTaskItem() {
        String [] taskItems = new String[100];
        for (int i=0;i<100;i++){
            taskItems[i]="任务"+i;
        }
        return taskItems;
    }
}
```