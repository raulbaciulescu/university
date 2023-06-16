

using model;

namespace client
{
    public partial class PurchaseForm : Form
    {
        private readonly Controller controller;
        private readonly Flight flight;
        
        public PurchaseForm(Flight flight, Controller controller)
        {
            InitializeComponent();
            this.controller = controller;
            this.flight = flight;
        }
        private void PurchaseForm_Load(object sender, EventArgs e)
        {
            labelPurchase.Text = $"Flight from {flight.start} to {flight.destination}"; 
        }
        private void btnBack_Click(object sender, EventArgs e)
        {
            Hide();
            MainForm mainForm = new MainForm(controller);
            mainForm.Initialize();
            mainForm.Show();
        }

        private void btnPurchase_Click(object sender, EventArgs e)
        {
            try
            {
                string clientName = txtBoxClientName.Text;
                string clientAddress = txtBoxClientAddress.Text;
                int nrOfSeats = int.Parse(txtBoxSeats.Text);
                string tourist1 = txtBoxTourist1.Text;
                string tourist2 = txtBoxTourist2.Text;
                string tourist3 = txtBoxTourist3.Text;
                List<string> tourists = new List<string>();
                tourists.Add(tourist1);
                tourists.Add(tourist2);
                tourists.Add(tourist3);
                controller.Purchase(flight, clientName, clientAddress, tourists, nrOfSeats);
                labelError.Visible = false;
                labelSucces.Visible = true;
            }
            catch (Exception)
            {
                labelError.Visible = true;
            }
        }
    }
}
