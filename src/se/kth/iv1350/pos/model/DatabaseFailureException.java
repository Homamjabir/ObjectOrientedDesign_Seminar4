package se.kth.iv1350.pos.model;

public class DatabaseFailureException extends java.lang.RuntimeException
{
    public DatabaseFailureException()
    {
        super();
    }

    public DatabaseFailureException(String msg) {
        super(msg);
    }
}
