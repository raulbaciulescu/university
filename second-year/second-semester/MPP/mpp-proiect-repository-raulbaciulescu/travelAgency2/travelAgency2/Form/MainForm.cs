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
    public partial class MainForm : Form
    {
        FlightService flightService = Resources.GetInstance().GetFlightService();
        public MainForm()
        {
            InitializeComponent();
        }
        private void MainForm_Load(object sender, EventArgs e)
        {
            dataGridFlights.ColumnCount = 4;
            dataGridFlights.Columns[0].Name = "Start";
            dataGridFlights.Columns[1].Name = "Destination";
            dataGridFlights.Columns[2].Name = "Start Date";
            dataGridFlights.Columns[3].Name = "Nr Of Seats";

            List<Flight> flights = flightService.GetAll();
            foreach (Flight flight in flights)
            {
                string[] row = { flight.start.ToString(), flight.destination.ToString(),
                    flight.startDate.ToString(), flight.nrOfSeats.ToString() };
                dataGridFlights.Rows.Add(row);
            }
        }
        private void btnLogout_Click(object sender, EventArgs e)
        {
            Resources.GetInstance().GetLoginService().LogoutCurrentUser();
            this.Close();
            LoginForm loginForm = new LoginForm();
            loginForm.Show();
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            this.Close();
            SearchForm searchForm = new SearchForm();
            searchForm.Show();
        }


    }
}
