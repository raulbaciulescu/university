using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace Seminar1226SGBD
{
    class Program
    {
        static void Main(string[] args)
        {
            //setarea titlului consolei
            Console.Title = "Hello ADO.NET";
            //setarea culorii caracterelor afisate in consola
            Console.ForegroundColor = ConsoleColor.Blue;
            //setarea culorii de fundal a consolei
            Console.BackgroundColor = ConsoleColor.White;
            Console.Clear();
            Console.WriteLine("Hello ADO.NET!");
            try
            {
                //pentru ca aplicatia sa functioneze corect, va trebui sa specificati numele server-ului de baze de date personal
                string connectionString = @"Server=DESKTOP-01E0F0G\SQLEXPRESS;
                Initial Catalog=Seminar1226SGBD;Integrated Security=true;";
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                Console.WriteLine("Starea conexiunii: {0}", connection.State);
                connection.Open();
                Console.WriteLine("Starea conexiunii dupa apelul metodei Open: {0}", connection.State);
                //inserarea unei inregistrari in tabel
                SqlCommand insertCommand = new SqlCommand("INSERT INTO Cadouri(descriere, valoare, posesor) " +
                        "VALUES (@descriere, @valoare, @posesor);", connection);
                insertCommand.Parameters.AddWithValue("@descriere", "urs adevarat");
                insertCommand.Parameters.AddWithValue("@valoare", 30000.0F);
                insertCommand.Parameters.AddWithValue("@posesor", "Dragos");
                int numberofrowsaffectedbyinsert=insertCommand.ExecuteNonQuery();
                //afisarea numarului de inregistrari afectate de comanda insert executata
                Console.WriteLine("Numarul de inregistrari afectate de insert este {0}", numberofrowsaffectedbyinsert);
                //citirea datelor din tabel si afisarea acestora in consola
                SqlCommand selectCommand = new SqlCommand("SELECT descriere, valoare, posesor FROM Cadouri;", connection);
                SqlDataReader reader = selectCommand.ExecuteReader();
                if(reader.HasRows)
                    {
                        Console.WriteLine("Afisarea datelor din tabelul Cadouri");
                        while(reader.Read())
                        {
                            Console.WriteLine("{0}\t{1}\t{2}", reader.GetString(0), reader.GetFloat(1), reader.GetString(2));

                        }
                    }
                    reader.Close();
                //actualizarea datelor din tabel
                SqlCommand updateCommand = new SqlCommand("UPDATE Cadouri SET valoare=@valoarenoua WHERE posesor=@posesor;", connection);
                updateCommand.Parameters.AddWithValue("@valoarenoua", 45000.0F);
                updateCommand.Parameters.AddWithValue("@posesor", "Dragos");
                updateCommand.ExecuteNonQuery();
                reader = selectCommand.ExecuteReader();
                if (reader.HasRows)
                {
                    Console.WriteLine("Afisarea datelor din tabelul Cadouri dupa actualizare");
                    while(reader.Read())
                    {
                        Console.WriteLine("{0}\t{1}\t{2}", reader.GetString(0), reader.GetFloat(1), reader.GetString(2));

                    }
                }
                reader.Close();
                //stergerea datelor din tabel
                SqlCommand deleteCommand = new SqlCommand("DELETE FROM Cadouri WHERE posesor=@posesor;", connection);
                deleteCommand.Parameters.AddWithValue("@posesor", "Dragos");
                int nrRanduriSterse = deleteCommand.ExecuteNonQuery();
                Console.WriteLine("Numarul de inregistrari sterse: {0}", nrRanduriSterse);
                }
                
            



            }
            catch(Exception ex)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("Mesajul erorii care ne-a adus in catch este {0}", ex.Message);
            }
        }
    }
}
