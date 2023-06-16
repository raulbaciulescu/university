using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
//using travelAgency2.Service;
using travelAgency2.src.Domain;
//using travelAgency2.Utils;

namespace travelAgency2
{
    public partial class SearchForm : Form
    {
        //FlightService flightService = Resources.GetInstance().GetFlightService();
        public SearchForm()
        {
            InitializeComponent();
        }
        private void SearchForm_Load(object sender, EventArgs e)
        {
            dataGridSearch.ColumnCount = 5;
            dataGridSearch.Columns[0].Name = "Start";
            dataGridSearch.Columns[1].Name = "Destination";
            dataGridSearch.Columns[2].Name = "Start Date";
            dataGridSearch.Columns[3].Name = "Nr Of Seats";
            dataGridSearch.Columns[4].Name = "Id";
            dataGridSearch.Columns["Id"].Visible = false;
            //List<Flight> flights = flightService.GetAll();
            //fillDataGrid(flights);
        }

        // private void fillDataGrid(List<Flight> flights)
        // {
        //     dataGridSearch.Rows.Clear();
        //     foreach (Flight flight in flights)
        //     {
        //         string[] row = { flight.start.ToString(), flight.destination.ToString(),
        //             flight.startDate.ToString(), flight.nrOfSeats.ToString(), flight.Id.ToString() };
        //         dataGridSearch.Rows.Add(row);
        //     }
        // }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            // string destination = txtBoxDestination.Text;
            // DateTime dateTime = dateTimePicker.Value.Date;
            // List<Flight> flights = flightService.GetAll();
            // List<Flight> flightsNew = flights.Where(flight => (flight.destination.name.Contains(destination) ||
            // flight.destination.airport.Contains(destination)) && flight.startDate.Day == dateTime.Day &&
            // flight.startDate.Month == dateTime.Month).ToList();
            // fillDataGrid(flightsNew);
        }

        private void btnPurchase_Click(object sender, EventArgs e)
        {
            // if (Resources.GetInstance().GetSelectedFlight() != null)
            // {
            //     this.Hide();
            //     PurchaseForm purchaseForm = new PurchaseForm();
            //     purchaseForm.Show();
            // }
            // else
            // {
            //     labelError.Visible = true;
            // }
        }

        private void btnBack_Click(object sender, EventArgs e)
        {
            this.Hide();
            //MainForm mainForm = new MainForm();
            //mainForm.Show();
        }

        private void dataGridSearch_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {   
            try
            {
                // if (dataGridSearch.SelectedCells.Count > 0)
                // {
                //     long id = long.Parse(dataGridSearch.SelectedRows[0].Cells[4].Value.ToString());
                //     Flight flight = flightService.FindById(id);
                //     if (flight == null)
                //         throw new Exception();
                //     Resources.GetInstance().SetSelectedFlight(flight);
                // }
            }
            catch (Exception ex)
            {
                labelError.Visible = true;
            }
        }
    }
}
