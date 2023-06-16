using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace model
{
    [Serializable]
    public class User : Entity<long>
    {
        public string username { get; set; }
        public string password { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }

        public User(string username, string password) : base(0L)
        {
            this.username = username;
            this.password = password;
            this.firstName = "";
            this.lastName = "";
        }

        public User() : base(0L)
        {
            username = "";
            password = "";
            lastName = "";
            firstName = "";
        }
        public User(long id, string username, string password, string firstName, string lastName) : base(id)
        {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public override string ToString()
        {
            return username + " " + password + " " + firstName + " " + lastName;
        }
    }
}
