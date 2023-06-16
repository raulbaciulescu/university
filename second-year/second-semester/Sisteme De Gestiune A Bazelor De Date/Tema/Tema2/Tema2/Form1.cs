using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Tema2
{
    public partial class Form1 : Form
    {
        DataSet dataset = new DataSet();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        string connectionString = @"Server=DESKTOP-7JJM8E8\SQLEXPRESS;Database=ArtaCinematografica;Integrated Security=true;";
        BindingSource bsParent = new BindingSource();
        BindingSource bsChild = new BindingSource();
        int selectedParentId = 0;

        private int[] primaryKeyChild = {0, 0 };
        private int noOfPrimaryKeysChild = int.Parse(ConfigurationManager.AppSettings.Get("noOfPrimaryKeysChild"));
        private static readonly int noCols = int.Parse(ConfigurationManager.AppSettings.Get("noCols"));
        private static readonly int noDate = int.Parse(ConfigurationManager.AppSettings.Get("noDate"));
        TextBox[] textBoxes = new TextBox[noCols];
        DateTimePicker[] pickers = new DateTimePicker[noDate];
        public Form1()
        {
            InitializeComponent();
        }
        private void Form1_Load(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {

                string tableParent = ConfigurationManager.AppSettings.Get("tableParent");
                string tableChild = ConfigurationManager.AppSettings.Get("tableChild");
                connection.Open();
                parentAdapter.SelectCommand = new SqlCommand(ConfigurationManager.AppSettings.Get("selectParent"), connection);
                childAdapter.SelectCommand = new SqlCommand(ConfigurationManager.AppSettings.Get("selectChild"), connection);

                parentAdapter.Fill(dataset, tableParent);
                childAdapter.Fill(dataset, tableChild);

                int noOfPrimaryKeys = int.Parse(ConfigurationManager.AppSettings.Get("noOfPrimaryKeysParent"));
                DataColumn[] dataColumns = new DataColumn[noOfPrimaryKeys];
                for (int i = 1; i <= noOfPrimaryKeys; i++)
                {
                    dataColumns[i - 1] = dataset.Tables[tableParent].Columns[ConfigurationManager.AppSettings.Get($"primaryKeyParent{i}")];
                }
                
                DataColumn[] fkColumn = { dataset.Tables[tableChild].Columns[ConfigurationManager.AppSettings.Get("foreignKey")] };
                DataRelation relation = new DataRelation("fk", dataColumns, fkColumn);
                dataset.Relations.Add(relation);

                bsParent.DataSource = dataset.Tables[tableParent];
                dataGridViewParent.DataSource = bsParent;
                labelParent.Text = tableParent;

                bsChild.DataSource = bsParent;
                bsChild.DataMember = "fk";


                dataGridViewChild.DataSource = bsChild;
                labelChild.Text = tableChild;
                InitializeTextBoxes();
            }
        }

        private void InitializeTextBoxes()
        {
            int space = 0;
            for (int i = 0; i < noCols; i++)
            {
                textBoxes[i] = new TextBox();
                textBoxes[i].Location = new Point(20, 400 + space);
                Controls.Add(textBoxes[i]);
                space += 25;
            }

            for (int i = 0; i < noDate; i++)
            {
                pickers[i] = new DateTimePicker();
                pickers[i].Location = new Point(20, 400 + space);
                Controls.Add(pickers[i]);
                space += 25;
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
                        selectedParentId = int.Parse(selectedCinemaIdString);
                        textBoxes[textBoxes.Length - 1].Text = selectedCinemaIdString;
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

                        for (int i = 0; i < noOfPrimaryKeysChild; i++)
                        {
                            primaryKeyChild[i] = int.Parse(dataGridViewChild.Rows[e.RowIndex].Cells[i].Value.ToString());
                        }
                        //string selectedChildIdString = dataGridViewChild.Rows[e.RowIndex].Cells[0].Value.ToString();
                        //selectedChildId = int.Parse(selectedChildIdString);

                        for (int i = 0; i < textBoxes.Length; i++)
                            if (noOfPrimaryKeysChild == 2)
                                textBoxes[i].Text = dataGridViewChild.Rows[e.RowIndex].Cells[i].Value.ToString();
                            else
                                textBoxes[i].Text = dataGridViewChild.Rows[e.RowIndex].Cells[i + 1].Value.ToString();
                        int j = textBoxes.Length;
                        for (int i = 0; i < pickers.Length; i++)
                        {
                            pickers[i].Value =  DateTime.Parse(dataGridViewChild.Rows[e.RowIndex].Cells[j].Value.ToString());
                            j++;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {

                    childAdapter.InsertCommand = new SqlCommand(ConfigurationManager.AppSettings.Get("insertChild"), connection);
                    //INSERT INTO Proiectare(IdSala, IdFilm, DataProiectare) VALUES (@value1, @value2, @value3) 
                    //INSERT INTO Angajat (Nume, Prenume, varsta, IdCinema) VALUES (@value1, @value2, @value3, @value4);
                    for (int i = 0; i < noCols; i++)
                    {
                        int numericValue;
                        bool isNumber = int.TryParse(textBoxes[i].Text, out numericValue);
                        string paramName = $"@value{i+1}";
                        if (isNumber)
                            childAdapter.InsertCommand.Parameters.AddWithValue(paramName, numericValue);
                        else
                        {
                            childAdapter.InsertCommand.Parameters.AddWithValue(paramName, textBoxes[i].Text);
                        }
                    }

                    for (int i = 0; i < noDate; i++)
                    {
                        string paramName = $"@value{noCols+i+1}";
                        childAdapter.InsertCommand.Parameters.AddWithValue(paramName, pickers[i].Value);
                    }
                    connection.Open();
                    childAdapter.InsertCommand.ExecuteNonQuery();
                    Console.WriteLine(connection.State);
                }
            }
            catch (SqlException ex)
            {
                MessageBox.Show(ex.Message);
                Console.WriteLine(ex);
            }
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                childAdapter.SelectCommand.Connection = connection;
                dataset.Tables[ConfigurationManager.AppSettings.Get("tableChild")].Clear();
                childAdapter.Fill(dataset, ConfigurationManager.AppSettings.Get("tableChild"));
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    childAdapter.DeleteCommand = new SqlCommand(ConfigurationManager.AppSettings.Get("deleteChild"), connection);
                    //DELETE FROM Angajat WHERE IDAngajat = @IdAngajat;
                    //DELETE FROM Proiectare WHERE IdFilm = @value1 AND IdSala = @value2;
                    for (int i = 0; i < noOfPrimaryKeysChild; i++)
                        childAdapter.DeleteCommand.Parameters.AddWithValue($"@value{i+1}", primaryKeyChild[i]);
                    connection.Open();
                    childAdapter.DeleteCommand.ExecuteNonQuery();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    //UPDATE Angajat SET Nume = @value2, Prenume = @value3, varsta = @value4, IdCinema = @value4 WHERE IdAngajat = @value1;
                    //UPDATE Proiectare SET DataProiectare = @value3 WHERE IdFilm = @value1 AND IdSala = @value2;
                    childAdapter.UpdateCommand = new SqlCommand(ConfigurationManager.AppSettings.Get("updateChild"), connection);
                    for (int i = 0; i < noOfPrimaryKeysChild; i++)
                        childAdapter.UpdateCommand.Parameters.AddWithValue($"@value{i+1}", primaryKeyChild[i]);

                    int noUpdate = int.Parse(ConfigurationManager.AppSettings.Get("noUpdate"));
                    for (int i = noUpdate; i < noCols; i++)
                    {
                        int numericValue;
                        bool isNumber = int.TryParse(textBoxes[i].Text, out numericValue);
                        string paramName = $"@value{noOfPrimaryKeysChild+1+i}";
                        if (isNumber)
                            childAdapter.UpdateCommand.Parameters.AddWithValue(paramName, numericValue);
                        else
                        {
                            childAdapter.UpdateCommand.Parameters.AddWithValue(paramName, textBoxes[i].Text);
                        }
                    }
                    for (int i = 0; i < noDate; i++)
                    {
                        string paramName = $"@value{noCols+i+1}";
                        childAdapter.UpdateCommand.Parameters.AddWithValue(paramName, pickers[i].Value);
                    }
                    connection.Open();
                    childAdapter.UpdateCommand.ExecuteNonQuery();
                    Console.WriteLine(connection.State);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
