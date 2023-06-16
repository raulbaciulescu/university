using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.src.Domain;

namespace travelAgency2.Service
{
    internal class LoginService
    {

        private User currentUser = null;
        public User GetCurrentUser()
        {
            return currentUser;
        }

        public void LoginCurrentUser(User currentUser)
        {
            this.currentUser = currentUser;
        }

        public void LogoutCurrentUser()
        {
            this.currentUser = null;
        }
    }
}
