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

namespace test_baze
{
    public partial class Form1 : Form
    {
        private string connectionString = @"Server=DESKTOP-7JJM8E8\SQLEXPRESS;Database=P152022;Integrated Security=true;";

        private DataSet dataSet = new DataSet();
        private SqlDataAdapter CategoriiAdapter = new SqlDataAdapter();
        private SqlDataAdapter PaturiAdapter = new SqlDataAdapter();
        private BindingSource bsPaturi = new BindingSource();
        private BindingSource bsCategorii = new BindingSource();

        public Form1()
        {
            InitializeComponent();
        }

        private void btnCommit_Click(object sender, EventArgs e)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                PaturiAdapter.SelectCommand.Connection = conn;
                SqlCommandBuilder builder = new SqlCommandBuilder(PaturiAdapter);
                PaturiAdapter.Update(dataSet, "Paturi");
                dataSet.Tables["Paturi"].Clear();
                PaturiAdapter.Fill(dataSet, "Paturi");
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                //Comenzi
                CategoriiAdapter.SelectCommand = new SqlCommand("SELECT * FROM Categorii", conn);
                PaturiAdapter.SelectCommand = new SqlCommand("SELECT * FROM Paturi", conn);

                //Fill dataSource
                CategoriiAdapter.Fill(dataSet, "Categorii");
                PaturiAdapter.Fill(dataSet, "Paturi");

                // Setare relatie fk
                DataColumn pkColumn = dataSet.Tables["Categorii"].Columns["cod_c"];
                DataColumn fkColumn = dataSet.Tables["Paturi"].Columns["cod_c"];
                DataRelation dataRelation = new DataRelation("fk_Categorii_Paturi", pkColumn, fkColumn);
                dataSet.Relations.Add(dataRelation);

                // Setare BindingSource
                bsCategorii.DataSource = dataSet.Tables["Categorii"];
                bsPaturi.DataSource = bsCategorii;
                bsPaturi.DataMember = "fk_Categorii_Paturi";

                dataGridPaturi.DataSource = bsPaturi;
                // Afisare listBox
                listBoxCategorii.DataSource = bsCategorii;
                listBoxCategorii.DisplayMember = "nume_c";
                listBoxCategorii.ValueMember = "cod_c";
            }
        }
    }
}
