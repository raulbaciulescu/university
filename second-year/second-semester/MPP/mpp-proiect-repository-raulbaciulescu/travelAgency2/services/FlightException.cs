﻿using System;

namespace travelAgency2.Service
{
    public class FlightException : Exception
    {
        public FlightException():base() { }

        public FlightException(String msg) : base(msg) { }

        public FlightException(String msg, Exception ex) : base(msg, ex) { }

    }
}