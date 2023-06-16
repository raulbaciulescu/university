using Sem11_12.Model;
using Sem11_12.Repository;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sem11_12.Service
{
    class AngajatService
    {
        private IRepository<string, Angajat> repo;

        public AngajatService(IRepository<string, Angajat> repo)
        {
            this.repo = repo;
        }


        public List<Angajat> FindAllAngajati()
        {
            return repo.FindAll().ToList();
        }


        public void ShowAngajati()
        {
            List<Angajat> angajati = FindAllAngajati();
            //angajati.ForEach(Console.WriteLine);

            List<Angajat> junior = angajati.Where(angajat => angajat.Nivel == KnowledgeLevel.Junior).ToList().
                OrderByDescending(angajat => angajat.VenitPeOra).ToList();
            List<Angajat> medium = angajati.Where(angajat => angajat.Nivel == KnowledgeLevel.Medium).ToList().
                OrderByDescending(angajat => angajat.VenitPeOra).ToList();
            List<Angajat> senior = angajati.Where(angajat => angajat.Nivel == KnowledgeLevel.Senior).ToList().
                OrderByDescending(angajat => angajat.VenitPeOra).ToList();
            Console.WriteLine("juniori:");
            junior.ForEach(Console.WriteLine);
            Console.WriteLine("mid:");
            medium.ForEach(Console.WriteLine);
            Console.WriteLine("senior:");
            senior.ForEach(Console.WriteLine);


            //angajati.GroupBy(angajat => angajat.Nivel == KnowledgeLevel.Junior)
            //.Select(group => new { Angajat = group.Key, Items = group.ToList() })
            //.ToList().OrderByDescending(angajat => angajat.VenitPeOra).ToList();
        }
    }
}

