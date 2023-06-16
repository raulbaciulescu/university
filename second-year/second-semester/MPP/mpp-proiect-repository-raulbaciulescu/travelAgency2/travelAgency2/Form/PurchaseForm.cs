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
    public partial class PurchaseForm : Form
    {
        private int position = 30;
        private PurchaseService purchaseService = Resources.GetInstance().GetPurchaseService();
        public PurchaseForm()
        {
            InitializeComponent();
        }
        private void PurchaseForm_Load(object sender, EventArgs e)
        {
            Flight flight = Resources.GetInstance().GetSelectedFlight();
            labelPurchase.Text = $"Flight from {flight.start} to {flight.destination}"; 
        }
        private void btnBack_Click(object sender, EventArgs e)
        {
            this.Hide();
            SearchForm searchForm = new SearchForm();
            searchForm.Show();
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
                purchaseService.Add(Resources.GetInstance().GetSelectedFlight(), clientName, clientAddress, tourists, nrOfSeats);
                labelError.Visible = false;
                labelSucces.Visible = true;
            }
            catch (Exception)
            {
                labelError.Visible = true;
            }
        }

        //private void btnNewFields_Click(object sender, EventArgs e)
        //{
        //    TextBox txtBox = new TextBox();
        //    txtBox.Text = "";
        //    Point point = new Point(335, 233);
        //    txtBox.Location = point;
        //    this.Controls.Add(txtBox);
        //    position += 10;
        //}
    }
}
