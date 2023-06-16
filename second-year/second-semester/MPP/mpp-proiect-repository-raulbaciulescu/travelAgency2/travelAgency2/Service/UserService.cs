using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using travelAgency2.Repository;
using travelAgency2.src.Domain;

namespace travelAgency2.Service
{
    internal class UserService
    {
        private UserRepository userRepository;
        private Random random;

        public UserService(UserRepository userRepository)
        {
            this.userRepository = userRepository;
            this.random = new Random();
        }

        void Add(string username, string password, string firstName, string lastName)
        {
            long id = random.Next(0, 999999999);
            User user = new User(id, username, password, firstName, lastName);
            userRepository.Add(user);
        }

        User FindById(long id)
        {
            return userRepository.FindByID(id);
        }

        List<User> GetAll()
        {
            return userRepository.GetAll();
        }

        public User FindUser(string username, string password)
        {
            return userRepository.findUser(username, password);
        }
    }
}
