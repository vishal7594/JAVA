package oops;

public abstract class Manager {

    public abstract void manage();

}

class  Developer extends Manager
{
    public  void  manage()
    {
        System.out.println("COO managing");
    }
}
