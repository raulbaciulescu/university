using System.Windows.Forms;
using model;
using services;

namespace client;

public partial class LoginForm : Form
{
    private Controller controller;
    private IService server;
    
    public LoginForm(Controller controller)
    {
        InitializeComponent();
        this.controller = controller;
    }

    private void btnLogin_Click(object sender, EventArgs e)
    {
        string username = txtBoxUsername.Text;
        string password = txtBoxPassword.Text;
        try 
        {
            controller.Login(username, password);
            Console.WriteLine("Login Succes!!");
            MainForm mainForm = new MainForm(controller);
            mainForm.Initialize();
            mainForm.Text = "Main form for " + username;
            mainForm.Show();
            Hide();
        } catch (Exception ex) {
            Console.WriteLine("Login exception " + ex);
        }
    }
}