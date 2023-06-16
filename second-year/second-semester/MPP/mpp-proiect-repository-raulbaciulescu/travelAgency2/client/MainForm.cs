
using client;
using model;
using services;
using Flight = model.Flight;

namespace client
{

    public partial class MainForm : Form
    {
        private readonly Controller controller;
        private Flight flight;
        private List<Flight> flights;
        
        public MainForm(Controller controller)
        {
            InitializeComponent();
            this.controller = controller;
            controller.updateEvent += UpdateFlight;
        }

        private void UpdateFlight(object? sender, FlightEventArgs e)
        {
            Console.Write("UpdateFlight in form 1");
            List<Flight> flightsNew = new List<Flight>();
            Flight flightNew = e.data;
            foreach (Flight flight in flights)
            {
                if (flightNew.Id == flight.Id && flightNew.nrOfSeats != 0)
                    flightsNew.Add(flightNew);
                if (flight.Id != flightNew.Id)
                    flightsNew.Add(flight);
            }
            FillDataGrid(flightsNew);
            Console.Write("UpdateFlight in form 2");
        }
        
        private void MainForm_Load(object sender, EventArgs e)
        {
            dataGridFlights.ColumnCount = 5;
            dataGridFlights.Columns[0].Name = "Start";
            dataGridFlights.Columns[1].Name = "Destination";
            dataGridFlights.Columns[2].Name = "Start Date";
            dataGridFlights.Columns[3].Name = "Nr Of Seats";
            dataGridFlights.Columns[4].Name = "Id";
            dataGridFlights.Columns["Id"].Visible = false;
            List<Flight> flights = controller.GetFlights();
            this.flights = flights;
            foreach (Flight flight in flights)
            {
                string[] row = { flight.start.ToString(), flight.destination.ToString(),
                    flight.startDate.ToString(), flight.nrOfSeats.ToString(), flight.Id.ToString()};
                dataGridFlights.Rows.Add(row);
            }
        }

        public void Initialize()
        {
            // dataGridFlights.ColumnCount = 5;
            // dataGridFlights.Columns[0].Name = "Start";
            // dataGridFlights.Columns[1].Name = "Destination";
            // dataGridFlights.Columns[2].Name = "Start Date";
            // dataGridFlights.Columns[3].Name = "Nr Of Seats";
            // dataGridFlights.Columns[4].Name = "Id";
            // dataGridFlights.Columns["Id"].Visible = false;
            // List<Flight> flights = controller.GetFlights();
            // this.flights = flights;
            // foreach (Flight flight in flights)
            // {
            //     string[] row = { flight.start.ToString(), flight.destination.ToString(),
            //         flight.startDate.ToString(), flight.nrOfSeats.ToString(), flight.Id.ToString()};
            //     dataGridFlights.Rows.Add(row);
            // }
        }
        private void btnLogout_Click(object sender, EventArgs e)
        {
            controller.Logout();
            controller.updateEvent -= UpdateFlight;
            Application.Exit();
        }
        private void FillDataGrid(List<Flight> flights)
        {
            dataGridFlights.Rows.Clear();

            foreach (Flight flight in flights)
            {
                string[] row = { flight.start.ToString(), flight.destination.ToString(),
                    flight.startDate.ToString(), flight.nrOfSeats.ToString(), flight.Id.ToString() };
                dataGridFlights.Rows.Add(row);
            }
        }

        private void btnSearch_Click(object sender, EventArgs e)
        {
            string destination = txtBoxDestination.Text;
            DateTime dateTime = dateTimePicker.Value.Date;
            List<Flight> flights = controller.GetFlights();
            List<Flight> flightsNew = flights.Where(flight => (flight.destination.name.Contains(destination) ||
            flight.destination.airport.Contains(destination)) && flight.startDate.Day == dateTime.Day &&
            flight.startDate.Month == dateTime.Month).ToList();
            FillDataGrid(flightsNew);
        }

        private void btnPurchase_Click(object sender, EventArgs e)
        {
            labelError.Visible = false;
            if (flight != null)
            {
                PurchaseForm purchaseForm = new PurchaseForm(flight, controller);
                purchaseForm.Text = "purchase form for " + flight;
                purchaseForm.Show();
                Hide();    
            }
            else
            {
                labelError.Visible = true;
            }
        }

        private void dataGridFlights_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                if (e.RowIndex < dataGridFlights.RowCount - 1)
                {
                    string selectedChildIdString = dataGridFlights.Rows[e.RowIndex].Cells[4].Value.ToString();
                    long id = long.Parse(selectedChildIdString);

                    foreach (Flight flight in flights)
                    {
                        if (id == flight.Id)
                            this.flight = flight;
                    }
                    if (id == null)
                        throw new Exception();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }
    }
}
