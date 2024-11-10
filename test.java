public class test {
    public static void main(String[] args) {
        task task1=new task("Task1", "Testing1");
        System.out.println(task1.getTitle()+" "+task1.getDescription()+" "+task1.getStatus()+" "+task1.getStartDate()+" "+task1.getEndDate()+" "+task1);
        task1.startTask();
        System.out.println(task1.getTitle()+" "+task1.getDescription()+" "+task1.getStatus()+" "+task1.getStartDate()+" "+task1.getEndDate()+" "+task1);
        task1.completeTask();
        System.out.println(task1.getTitle()+" "+task1.getDescription()+" "+task1.getStatus()+" "+task1.getStartDate()+" "+task1.getEndDate()+" "+task1);
    }
}
