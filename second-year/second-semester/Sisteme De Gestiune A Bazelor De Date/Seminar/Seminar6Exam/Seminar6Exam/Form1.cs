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

//namespace Seminar6Exam
//{
//    public partial class Form1 : Form
//    {
//        private string connectionString = @"Server=DESKTOP-7JJM8E8\SQLEXPRESS;Database=Problema1;Integrated Security=true;";
//        private DataSet dataSet = new DataSet();
//        private SqlDataAdapter cofetarieAdapter = new SqlDataAdapter();
//        private SqlDataAdapter briosaAdapter = new SqlDataAdapter();
//        private BindingSource bsBriosa = new BindingSource();
//        private BindingSource bsCofetarie = new BindingSource();

//        private void SetUpAdapters()
//        {
//            using (SqlConnection connection = new SqlConnection(connectionString))
//            {
//                cofetarieAdapter.SelectCommand = new System.Data.SqlClient.SqlCommand("SELECT * FROM Cofetarii", connection);
//                briosaAdapter.SelectCommand = new System.Data.SqlClient.SqlCommand("SELECT * FROM Briose", connection);

//                cofetarieAdapter.Fill(dataSet, "Cofetarii");
//                briosaAdapter.Fill(dataSet, "Briose");
//            }
//        }

//        private void DisplayData()
//        {
//            listBoxCofetarie.DataSource = dataSet.Tables["Cofetarii"];
//            listBoxCofetarie.DisplayMember = "nume_cofetarie";
//            listBoxCofetarie.ValueMember = "cod_cofetarie";
//            dataGridViewBriosa.DataSource = dataSet.Tables["Briose"];
//        }

//        private void BindData()
//        {
//            bsCofetarie.DataSource = dataSet.Tables["Cofetarii"];
//            bsBriosa.DataSource = dataSet.Tables["Briose"];

//        }
//        public Form1()
//        {
//            InitializeComponent();
//        }

//        private void Form1_Load(object sender, EventArgs e)
//        {
//            //SetUpAdapters();
//            //BindData();
//            //DisplayData();
//            using (SqlConnection connection = new SqlConnection(connectionString))
//            {
//                cofetarieAdapter.SelectCommand = new System.Data.SqlClient.SqlCommand("SELECT * FROM Cofetarii", connection);
//                briosaAdapter.SelectCommand = new System.Data.SqlClient.SqlCommand("SELECT * FROM Briose", connection);


//                cofetarieAdapter.Fill(dataSet, "Cofetarii");
//                briosaAdapter.Fill(dataSet, "Briose");


//                DataColumn pkColumn = dataSet.Tables["Cofetarii"].Columns["cod_cofetarie"];
//                DataColumn fkColumn = dataSet.Tables["Briose"].Columns["cod_cofetarie"];
//                DataRelation relation = new DataRelation("fk_Cofetarii_Briose", pkColumn, fkColumn);
//                dataSet.Relations.Add(relation);

//                bsCofetarie.DataSource = dataSet.Tables["Cofetarii"];
//                bsBriosa.DataSource = bsCofetarie;
//                bsBriosa.DataMember = "fk_Cofetarii_Briose";

//                dataGridViewBriosa.DataSource = bsBriosa;

//                listBoxCofetarie.DataSource = bsCofetarie;
//                listBoxCofetarie.DisplayMember = "nume_cofetarie";
//                listBoxCofetarie.ValueMember = "cod_cofetarie";
//            }
//        }

//        private void btnCommit_Click(object sender, EventArgs e)
//        {
//            using (SqlConnection connection = new SqlConnection(connectionString))
//            {
//                briosaAdapter.SelectCommand.Connection = connection;
//                SqlCommandBuilder builder = new SqlCommandBuilder(briosaAdapter);
//                briosaAdapter.Update(dataSet, "Briose");
//                MessageBox.Show(builder.GetUpdateCommand().CommandText);
//                dataSet.Tables["Briose"].Clear();
//                briosaAdapter.Fill(dataSet, "Briose");
//            }
//        }
//    }
//}

using System.Data;
using System.Data.SqlClient;

namespace Seminar6Exam
{
    public partial class Form1 : Form
    {

        private string connectionString = @"Server=DESKTOP-7JJM8E8\SQLEXPRESS;Database=Problema1;Integrated Security=true;";
        private DataSet dataSet = new DataSet();
        private SqlDataAdapter cofetarieAdapter = new SqlDataAdapter();
        private SqlDataAdapter briosaAdapter = new SqlDataAdapter();
        private BindingSource bsBriosa = new BindingSource();
        private BindingSource bsCofetarie = new BindingSource();

        public Form1()
        {
            InitializeComponent();
        }


        private void btnCommit_Click(object sender, EventArgs e)
        {
            // Update
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                briosaAdapter.SelectCommand.Connection = conn;
                SqlCommandBuilder builder = new SqlCommandBuilder(briosaAdapter);
                // MessageBox.Show(builder.GetUpdateCommand().CommandText);
                briosaAdapter.Update(dataSet, "Briose");
                dataSet.Tables["Briose"].Clear();
                briosaAdapter.Fill(dataSet, "Briose");
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            using (SqlConnection con = new SqlConnection(connectionString))
            {
                // Comenzi
                cofetarieAdapter.SelectCommand = new System.Data.SqlClient.SqlCommand("SELECT * FROM Cofetarii", con);
                briosaAdapter.SelectCommand = new System.Data.SqlClient.SqlCommand("SELECT * FROM Briose", con);

                // Fill dataSource
                cofetarieAdapter.Fill(dataSet, "Cofetarii");
                briosaAdapter.Fill(dataSet, "Briose");

                // Setare relatie fk
                DataColumn pkColumn = dataSet.Tables["Cofetarii"].Columns["cod_cofetarie"];
                DataColumn fkColumn = dataSet.Tables["Briose"].Columns["cod_cofetarie"];
                DataRelation dataRelation = new DataRelation("fk_Cofetarii_Briose", pkColumn, fkColumn);
                dataSet.Relations.Add(dataRelation);

                // Setare BindingSource
                bsCofetarie.DataSource = dataSet.Tables["Cofetarii"];
                bsBriosa.DataSource = bsCofetarie;
                bsBriosa.DataMember = "fk_Cofetarii_Briose";

                dataGridViewBriosa.DataSource = bsBriosa;
                // Afisare listBox
                listBoxCofetarie.DataSource = bsCofetarie;
                listBoxCofetarie.DisplayMember = "nume_cofetarie";
                listBoxCofetarie.ValueMember = "cod_cofetarie";
            }
        }
    }
}
