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

namespace Tema1
{
    public partial class Form1 : Form
    {

        DataSet dataset = new DataSet();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        string connectionString = @"Server=DESKTOP-7JJM8E8\SQLEXPRESS;Database=ArtaCinematografica;Integrated Security=true;";
        BindingSource bsParent = new BindingSource();
        BindingSource bsChild = new BindingSource();
        int selectedId = 0;
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
                //childAdapter.DeleteCommand = new SqlCommand("DELETE FROM Angajat WHERE IDAngajat = @IdAngajat;", connection);


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
        private void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    
                    childAdapter.DeleteCommand = new SqlCommand("DELETE FROM Angajat WHERE IDAngajat = @IdAngajat;", connection);
                    childAdapter.DeleteCommand.Parameters.AddWithValue("@IdAngajat", selectedId);

                    Console.WriteLine(connection.State);
                    connection.Open();
                    childAdapter.DeleteCommand.ExecuteNonQuery();
                    Console.WriteLine(connection.State);
                    labelSucces.Visible = true;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

       
        }
        private void btnRefresh_Click(object sender, EventArgs e)
        {
            labelSucces.Visible = false;
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                childAdapter.SelectCommand.Connection = connection;
                dataset.Tables["Angajat"].Clear();
                childAdapter.Fill(dataset, "Angajat");
            }
        }


        private void dataGridViewChild_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                if (dataGridViewChild.SelectedRows.Count > 0)
                {
                    //selectedId = int.Parse(dataGridViewChild.SelectedRows[0].Cells[0].Value.ToString());
                    selectedId = int.Parse(dataGridViewChild.Rows[e.RowIndex].Cells[0].Value.ToString());
                    txtBoxId.Text = selectedId.ToString();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void dataGridViewChild_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                if (dataGridViewChild.SelectedCells.Count > 0)
                {
                    //selectedId = int.Parse(dataGridViewChild.SelectedRows[0].Cells[0].Value.ToString());
                    selectedId = int.Parse(dataGridViewChild.SelectedRows[0].Cells[4].Value.ToString());
                    txtBoxId.Text = selectedId.ToString();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
