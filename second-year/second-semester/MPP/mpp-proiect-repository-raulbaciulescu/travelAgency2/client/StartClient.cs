using networking;
using services;

namespace client;

static class StartClient
{
    /// <summary>
    /// The main entry point for the application.
    /// </summary>
    [STAThread]
    static void Main()
    {
        Application.EnableVisualStyles();
        Application.SetCompatibleTextRenderingDefault(false);
            
           
        //IChatServer server=new ChatServerMock();          
        IService server = new Proxy("127.0.0.1", 54321);
        Controller controller = new Controller(server);
        LoginForm loginForm = new LoginForm(controller);
        Application.Run(loginForm);
    }
}