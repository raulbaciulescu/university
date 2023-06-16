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

namespace pregatire_sgbd
{
    public partial class Form1 : Form
    {
        private string connectionString = @"Server=DESKTOP-7JJM8E8\SQLEXPRESS;Database=Problema1;Integrated Security=true;";

        private DataSet dataSet = new DataSet();
        private SqlDataAdapter parentAdapter = new SqlDataAdapter();
        private SqlDataAdapter childAdapter = new SqlDataAdapter();
        private BindingSource bsChild = new BindingSource();
        private BindingSource bsParent = new BindingSource();

        public Form1()
        {
            InitializeComponent();
        }

        private void btnCommit_Click(object sender, EventArgs e)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                childAdapter.SelectCommand.Connection = conn; 
                SqlCommandBuilder builder = new SqlCommandBuilder(childAdapter);
                childAdapter.Update(dataSet, "Briose");
                dataSet.Tables["Briose"].Clear();
                childAdapter.Fill(dataSet, "Briose");
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                //Comenzi
                parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Cofetarii", conn);
                childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Briose", conn);

                //Fill dataSource
                parentAdapter.Fill(dataSet, "Cofetarii");
                childAdapter.Fill(dataSet, "Briose");

                // Setare relatie fk
                DataColumn pkColumn = dataSet.Tables["Cofetarii"].Columns["cod_cofetarie"];
                DataColumn fkColumn = dataSet.Tables["Briose"].Columns["cod_cofetarie"];
                DataRelation dataRelation = new DataRelation("fk_Cofetarii_Briose", pkColumn, fkColumn);
                dataSet.Relations.Add(dataRelation);

                // Setare BindingSource
                bsParent.DataSource = dataSet.Tables["Cofetarii"];
                bsChild.DataSource = bsParent;
                bsChild.DataMember = "fk_Cofetarii_Briose";

                dataGridChild.DataSource = bsChild;
                // Afisare listBox
                listBoxParent.DataSource = bsParent;
                listBoxParent.DisplayMember = "nume_cofetarie";
                listBoxParent.ValueMember = "cod_cofetarie";
            }
        }
    }
}
