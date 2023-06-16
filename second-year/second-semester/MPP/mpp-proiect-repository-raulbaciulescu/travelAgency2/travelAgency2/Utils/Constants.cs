using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace travelAgency2.Utils
{
    internal class Constants
    {
        public static class Db
        {
            private static string file = "bd.config";
            public enum Queries
            {
                ADD,
                DELETE,
                FIND_BY_ID,
                GET_ALL,
                FIND2,
                UPDATE
            }
            public enum Tables
            {
                FLIGHT,
                LOCATION,
                PURCHASE,
                USER
            }
        }
    }
}
