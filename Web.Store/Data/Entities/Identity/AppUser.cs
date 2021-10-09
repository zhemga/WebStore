using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Web.Store.Data.Entities.Identity
{
    public class AppUser : IdentityUser<long>
    {
        public string Name { get; set; }
        public string Surname { get; set; }
        public string MiddleName { get; set; }
        public virtual ICollection<AppUserRole> UserRoles { get; set; }
    }
}
