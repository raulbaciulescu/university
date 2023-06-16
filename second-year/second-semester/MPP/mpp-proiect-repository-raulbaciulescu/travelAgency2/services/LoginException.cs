using System;

namespace services
{
    public class LoginException : Exception
    {
        public LoginException():base() { }

        public LoginException(String msg) : base(msg) { }

        public LoginException(String msg, Exception ex) : base(msg, ex) { }

    }
}