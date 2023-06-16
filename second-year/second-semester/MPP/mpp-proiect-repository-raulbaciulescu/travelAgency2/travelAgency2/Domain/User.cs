using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace travelAgency2.src.Domain
{
    internal class User : Entity<long>
    {
        public string username { get; set; }
        public string hashedPassword { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public User(long id, string username, string hashedPassword, string firstName, string lastName) : base(id)
        {
            this.username = username;
            this.hashedPassword = hashedPassword;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public override string ToString()
        {
            //base.ToString() + ": " +
            return username + " " + hashedPassword + " " + firstName + " " + lastName;
        }
    }
}
