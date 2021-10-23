using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Web.Store.Models
{
    public class RegisterViewModel
    {
        public string Email { get; set; }
        public string Phone { get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public string MiddleName { get; set; }
        public string Password { get; set; }
        public string Image { get; set; }
    }
}
