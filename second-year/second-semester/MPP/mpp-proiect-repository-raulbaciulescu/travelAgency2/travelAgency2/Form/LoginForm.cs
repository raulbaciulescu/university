using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using travelAgency2.Service;
using travelAgency2.src.Domain;
using travelAgency2.Utils;

namespace travelAgency2
{
    public partial class LoginForm : Form
    {
        private UserService userService = Resources.GetInstance().GetUserService();

        public LoginForm()
        {
            InitializeComponent();
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            string username = txtBoxUsername.Text;
            string password = txtBoxPassword.Text;
            User user = userService.FindUser(username, password);
            if (user == null)
            {
                labelError.Visible = true;
            }
            else
            {
                Resources.GetInstance().GetLoginService().LoginCurrentUser(user);
                this.Hide();
                MainForm mainForm = new MainForm();
                mainForm.Show();
            }
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {

        }
    }
}
