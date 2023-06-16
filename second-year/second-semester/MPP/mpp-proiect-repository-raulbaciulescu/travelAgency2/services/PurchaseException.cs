namespace services;

public class PurchaseException : Exception
{
    public PurchaseException():base() { }

    public PurchaseException(String msg) : base(msg) { }

    public PurchaseException(String msg, Exception ex) : base(msg, ex) { }

}