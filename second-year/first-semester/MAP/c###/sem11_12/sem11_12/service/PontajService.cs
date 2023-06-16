using Sem11_12.Repository;
using Sem11_12.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sem11_12.Service
{
    class PontajService
    {
        private IRepository<string, Pontaj> repo;

        public PontajService(IRepository<string, Pontaj> repo)
        {
            this.repo = repo;
        }

        public List<Pontaj> FindAllPontaje()
        {
            return repo.FindAll().ToList();
        }



        public List<PontajDTO> Salar(int luna)  //4
        {
            return null;
        }

        public void AngajatiHarnici()
        {

            //angajati.GroupBy(angajat => angajat.Nivel == KnowledgeLevel.Junior)
            //.Select(group => new { Angajat = group.Key, Items = group.ToList() })
            //.ToList().OrderByDescending(angajat => angajat.VenitPeOra).ToList();
            List<Pontaj> pontaje = FindAllPontaje();
            List<Pontaj> pontaje2 = pontaje.OrderByDescending(pontaj => pontaj.Angajat.VenitPeOra * pontaj.Sarcina.NrOreEstimate).ToList();
            Console.WriteLine(pontaje2[0].Angajat);
            Console.WriteLine(pontaje2[1].Angajat);
        }
    }
}
