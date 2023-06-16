using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Tema1_2
{
    public partial class Form1 : Form
    {

        DataSet dataset = new DataSet();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        string connectionString = @"Server=DESKTOP-7JJM8E8\SQLEXPRESS;Database=ArtaCinematografica;Integrated Security=true;";
        BindingSource bsParent = new BindingSource();
        BindingSource bsChild = new BindingSource();
        int selectedCinemaId = 0;
        int selectedAngajatId = 0;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();
                parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Cinema;", connection);
                childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Angajat;", connection);
           

                parentAdapter.Fill(dataset, "Cinema");
                childAdapter.Fill(dataset, "Angajat");

                DataColumn pkColumn = dataset.Tables["Cinema"].Columns["IDCinema"];
                DataColumn fkColumn = dataset.Tables["Angajat"].Columns["IdCinema"];
                DataRelation relation = new DataRelation("fk_Cinema_Angajat", pkColumn, fkColumn);
                dataset.Relations.Add(relation);

                bsParent.DataSource = dataset.Tables["Cinema"];
                dataGridViewParent.DataSource = bsParent;
                labelParent.Text = "Cinema";

                bsChild.DataSource = bsParent;
                bsChild.DataMember = "fk_Cinema_Angajat";


                dataGridViewChild.DataSource = bsChild;
                labelChild.Text = "Angajat";
            }
        }

        private void dataGridViewParent_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    if (e.RowIndex < dataGridViewParent.RowCount - 1)
                    {
                        string selectedCinemaIdString = dataGridViewParent.Rows[e.RowIndex].Cells[0].FormattedValue.ToString();
                        selectedCinemaId = int.Parse(selectedCinemaIdString);
                        txtIdCinema.Text = selectedCinemaIdString;
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void dataGridViewChild_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    if (e.RowIndex < dataGridViewChild.RowCount - 1)
                    {
                        string selectedAngajatIdString = dataGridViewChild.Rows[e.RowIndex].Cells[0].Value.ToString();
                        selectedAngajatId = int.Parse(selectedAngajatIdString);

                        txtNume.Text = dataGridViewChild.Rows[e.RowIndex].Cells[1].Value.ToString(); 
                        txtPrenume.Text = dataGridViewChild.Rows[e.RowIndex].Cells[2].Value.ToString(); 
                        txtVarsta.Text = dataGridViewChild.Rows[e.RowIndex].Cells[3].Value.ToString(); 
                    }
                    else
                    {
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {

                childAdapter.UpdateCommand = new SqlCommand("UPDATE Angajat SET Nume = @Nume, Prenume = @Prenume, " +
                    "varsta = @varsta, IdCinema = @IdCinema WHERE IdAngajat = @IdAngajat;", connection);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@IdAngajat", selectedAngajatId);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@Nume", txtNume.Text);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@Prenume", txtPrenume.Text);
                childAdapter.UpdateCommand.Parameters.AddWithValue("@varsta", int.Parse(txtVarsta.Text));
                childAdapter.UpdateCommand.Parameters.AddWithValue("@IdCinema", selectedCinemaId);

                connection.Open();
                childAdapter.UpdateCommand.ExecuteNonQuery();
                Console.WriteLine(connection.State);
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {

                    childAdapter.InsertCommand = new SqlCommand("INSERT INTO Angajat (Nume, Prenume, varsta, IdCinema) " +
                        "VALUES (@Nume, @Prenume, @varsta, @IdCinema);", connection);
                    childAdapter.InsertCommand.Parameters.AddWithValue("@Nume", txtNume.Text);
                    childAdapter.InsertCommand.Parameters.AddWithValue("@Prenume", txtPrenume.Text);
                    childAdapter.InsertCommand.Parameters.AddWithValue("@varsta", int.Parse(txtVarsta.Text));
                    childAdapter.InsertCommand.Parameters.AddWithValue("@IdCinema", selectedCinemaId);

                    connection.Open();
                    childAdapter.InsertCommand.ExecuteNonQuery();
                    Console.WriteLine(connection.State);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {

                    childAdapter.DeleteCommand = new SqlCommand("DELETE FROM Angajat WHERE IDAngajat = @IdAngajat;", connection);
                    childAdapter.DeleteCommand.Parameters.AddWithValue("@IdAngajat", selectedAngajatId);

                    Console.WriteLine(connection.State);
                    connection.Open();
                    childAdapter.DeleteCommand.ExecuteNonQuery();
                    Console.WriteLine(connection.State);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                childAdapter.SelectCommand.Connection = connection;
                dataset.Tables["Angajat"].Clear();
                childAdapter.Fill(dataset, "Angajat");
            }
        }
    }
}
