using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Deadlock
{
    class Program
    {
        private static int nr1 = 0;
        private static int nr2 = 0;
        private static String connectionString;
        static void Main()
        {
            connectionString = @"Data Source=DESKTOP-7JJM8E8\SQLEXPRESS;Initial Catalog=ArtaCinematografica;Integrated Security=true";

            Thread thread1 = new Thread(Thread1);
            Thread thread2 = new Thread(Thread2);
            
            thread1.Start();
            thread2.Start();
        }

        private static void Thread1()
        {
            Console.WriteLine("Entered in thread1");
            try 
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();
                    SqlCommand command = new SqlCommand("thread1", connection);
                    command.CommandType = CommandType.StoredProcedure;
                    command.ExecuteNonQuery();
                    Console.WriteLine("Exited in thread1");
                }
            } 
            catch(SqlException ex)
            {
                if (ex.Number == 1205)
                {
                    Console.WriteLine("Deadlock in thread1");
                    if (nr1 < 5)
                    {
                        Console.WriteLine("Try again thread1 " + nr1);
                        nr1++;
                        Thread1();
                    }
                }
            }
        }

        private static void Thread2()
        {   
            try 
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();
                    Console.WriteLine("Entered in thread2");
                    SqlCommand command = new SqlCommand("thread2", connection);
                    command.CommandType = CommandType.StoredProcedure;
                    command.ExecuteNonQuery();
                    Console.WriteLine("Exited in thread2");
                }
            } 
            catch(SqlException ex)
            {
                if (ex.Number == 1205)
                {
                    Console.WriteLine("Deadlock in thread2");
                    if (nr2 < 5)
                    {
                        Console.WriteLine("Try again thread2 " + nr2);
                        nr2++;
                        Thread2();
                    }
                }
            }
        }
    }
}