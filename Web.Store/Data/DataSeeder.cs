using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Web.Store.Data.Entities;
using Web.Store.Data.Entities.Identity;

namespace Web.Store.Data
{
    public class DataSeeder
    {
        public static void Initialize(ref RoleManager<AppRole> roleManager, ref UserManager<AppUser> userManager, ref EFAppContext context)
        {
            if (!roleManager.Roles.Any())
            {
                var roles = new List<AppRole>() { new AppRole { Name = "admin" }, new AppRole { Name = "user" } };
                foreach (var role in roles)
                {
                    var result = roleManager.CreateAsync(role).Result;
                }
            }

            if (!userManager.Users.Any())
            {
                var users = new List<AppUser> {
                    new AppUser { UserName = "Wyoming", Name = "Eagan", Surname = "Fitzgerald", MiddleName = "Curran", PhoneNumber = "(04732) 6245418", Email = "sagittis@ipsum.com" },
                    new AppUser { UserName = "Flavia", Name = "Leonard", Surname = "Banks", MiddleName = "Nathaniel", PhoneNumber = "(03409) 7713903", Email = "eu@musProinvel.co.uk" },
                    new AppUser { UserName = "Clare", Name = "Brody", Surname = "Meyer", MiddleName = "Cullen", PhoneNumber = "(078) 88165577", Email = "diam@dapibus.org" },
                    new AppUser { UserName = "Shelley", Name = "Dalton", Surname = "Cohen", MiddleName = "Barclay", PhoneNumber = "(0755) 29784033", Email = "risus.Morbi.metus@risusDonecnibh.com" },
                    new AppUser { UserName = "Abraham", Name = "Griffin", Surname = "Cochran", MiddleName = "Jonas", PhoneNumber = "(08015) 8908014", Email = "aliquet.nec.imperdiet@Phasellusdolor.co.uk" },
                    new AppUser { UserName = "Ivory", Name = "Tasha", Surname = "Kidd", MiddleName = "Harrison", PhoneNumber = "(030796) 375022", Email = "dolor.Fusce@mus.org" },
                    new AppUser { UserName = "Xenos", Name = "Cyrus", Surname = "Powers", MiddleName = "Holmes", PhoneNumber = "(068) 32315770", Email = "gravida.Aliquam@ut.org" },
                    new AppUser { UserName = "Keefe", Name = "Keaton", Surname = "Rush", MiddleName = "Fritz", PhoneNumber = "(08033) 0855894", Email = "quis@nascetur.co.uk" },
                    new AppUser { UserName = "Halla", Name = "Derek", Surname = "Spencer", MiddleName = "Otto", PhoneNumber = "(09858) 1968340", Email = "dolor.vitae@luctus.ca" },
                    new AppUser { UserName = "Lucy", Name = "Steel", Surname = "Galloway", MiddleName = "Wesley", PhoneNumber = "(0368) 64388593", Email = "faucibus@pellentesquetellussem.edu" },
                    new AppUser { UserName = "Jaime", Name = "Kibo", Surname = "Butler", MiddleName = "Xander", PhoneNumber = "(010) 40673743", Email = "Nulla.dignissim.Maecenas@Vestibulum.co.uk" },
                    new AppUser { UserName = "Mariam", Name = "Raja", Surname = "Mays", MiddleName = "Trevor", PhoneNumber = "(0271) 88272281", Email = "ipsum.primis.in@massa.org" },
                    new AppUser { UserName = "Merritt", Name = "Jared", Surname = "Fowler", MiddleName = "Barry", PhoneNumber = "(045) 51483996", Email = "scelerisque@semper.edu" },
                    new AppUser { UserName = "Castor", Name = "Hanna", Surname = "Estrada", MiddleName = "Thaddeus", PhoneNumber = "(02619) 6806110", Email = "vel@torquentperconubia.co.uk" },
                    new AppUser { UserName = "Luke", Name = "Levi", Surname = "Obrien", MiddleName = "Colton", PhoneNumber = "(033857) 580300", Email = "enim.Etiam@elitpellentesquea.ca" },
                    new AppUser { UserName = "Bert", Name = "Arsenio", Surname = "Byrd", MiddleName = "Dennis", PhoneNumber = "(0416) 40524281", Email = "Nulla.interdum@atauctorullamcorper.co.uk" },
                    new AppUser { UserName = "Clementine", Name = "Ivy", Surname = "Byrd", MiddleName = "Grant", PhoneNumber = "(033462) 894603", Email = "orci@luctusCurabitur.ca" },
                    new AppUser { UserName = "Myra", Name = "Ila", Surname = "Mason", MiddleName = "Marvin", PhoneNumber = "(0220) 20737812", Email = "imperdiet.ullamcorper@condimentumeget.org" },
                    new AppUser { UserName = "Stewart", Name = "Kylynn", Surname = "Sweet", MiddleName = "Bradley", PhoneNumber = "(02791) 2032473", Email = "hendrerit.neque@sagittisDuis.net" },
                    new AppUser { UserName = "Desiree", Name = "Wing", Surname = "Campbell", MiddleName = "David", PhoneNumber = "(089) 05735792", Email = "Aliquam.tincidunt@egestas.com" },
                    new AppUser { UserName = "Eliana", Name = "Basil", Surname = "Powell", MiddleName = "James", PhoneNumber = "(034364) 131936", Email = "faucibus.orci@atfringillapurus.ca" },
                    new AppUser { UserName = "Travis", Name = "Emily", Surname = "Palmer", MiddleName = "Timon", PhoneNumber = "(033233) 978036", Email = "nec.diam@pede.co.uk" },
                    new AppUser { UserName = "Kessie", Name = "Sierra", Surname = "Marshall", MiddleName = "Dexter", PhoneNumber = "(096) 71310843", Email = "Cum.sociis.natoque@velitegetlaoreet.com" },
                    new AppUser { UserName = "Ruby", Name = "Hilel", Surname = "Scott", MiddleName = "Griffith", PhoneNumber = "(0413) 34310293", Email = "amet@vitaeorciPhasellus.net" },
                    new AppUser { UserName = "Galvin", Name = "Calvin", Surname = "Sutton", MiddleName = "Addison", PhoneNumber = "(087) 83507141", Email = "ridiculus.mus@utaliquamiaculis.com" },
                    new AppUser { UserName = "Garth", Name = "Stacey", Surname = "Tyler", MiddleName = "Dylan", PhoneNumber = "(039314) 313311", Email = "tincidunt.Donec@Innecorci.net" },
                    new AppUser { UserName = "Colt", Name = "Kibo", Surname = "Glass", MiddleName = "Scott", PhoneNumber = "(06846) 9961623", Email = "ipsum.Suspendisse.non@estarcuac.org" },
                    new AppUser { UserName = "Aphrodite", Name = "Honorato", Surname = "Vargas", MiddleName = "Reuben", PhoneNumber = "(033853) 895099", Email = "sed@non.co.uk" },
                    new AppUser { UserName = "Erin", Name = "Zephr", Surname = "Mullins", MiddleName = "Callum", PhoneNumber = "(031615) 277300", Email = "pede@sitamet.net" },
                    new AppUser { UserName = "Matthew", Name = "Emery", Surname = "Peterson", MiddleName = "Roth", PhoneNumber = "(031) 83548239", Email = "Pellentesque.tincidunt.tempus@dictumeleifend.net" },
                    new AppUser { UserName = "Stacey", Name = "Teagan", Surname = "Hendrix", MiddleName = "Luke", PhoneNumber = "(039127) 707315", Email = "lorem.vitae.odio@liberoatauctor.net" },
                    new AppUser { UserName = "Phillip", Name = "Hayes", Surname = "Watson", MiddleName = "Jared", PhoneNumber = "(031725) 799925", Email = "enim.nec.tempus@lectusrutrumurna.co.uk" },
                    new AppUser { UserName = "Leo", Name = "Janna", Surname = "Ray", MiddleName = "Colin", PhoneNumber = "(06646) 8531133", Email = "ut@Phasellusfermentumconvallis.org" },
                    new AppUser { UserName = "Adrian", Name = "Olympia", Surname = "Bird", MiddleName = "Eric", PhoneNumber = "(06614) 4135959", Email = "tempor.augue.ac@Loremipsumdolor.ca" },
                    new AppUser { UserName = "Harrison", Name = "Whilemina", Surname = "Meyer", MiddleName = "Dean", PhoneNumber = "(01394) 1035449", Email = "mauris.eu.elit@eusemPellentesque.org" },
                    new AppUser { UserName = "Penelope", Name = "Carol", Surname = "Bryant", MiddleName = "Dylan", PhoneNumber = "(0678) 88491961", Email = "id.libero.Donec@risusodio.co.uk" },
                    new AppUser { UserName = "Jolene", Name = "Micah", Surname = "Glover", MiddleName = "Nathan", PhoneNumber = "(01133) 8610313", Email = "adipiscing.elit.Curabitur@tellus.ca" },
                    new AppUser { UserName = "Allegra", Name = "Hayfa", Surname = "Bentley", MiddleName = "Alfonso", PhoneNumber = "(0865) 03237693", Email = "nonummy@massa.net" },
                    new AppUser { UserName = "Ramona", Name = "Igor", Surname = "Marquez", MiddleName = "Gary", PhoneNumber = "(02621) 8293977", Email = "Nulla.facilisis@Sedmalesuadaaugue.net" },
                    new AppUser { UserName = "Gisela", Name = "Eve", Surname = "Clarke", MiddleName = "Hakeem", PhoneNumber = "(02916) 9574701", Email = "Ut.semper@risusDuisa.com" },
                    new AppUser { UserName = "Stacey", Name = "Desirae", Surname = "Barnett", MiddleName = "Jonah", PhoneNumber = "(034849) 699984", Email = "ut@eu.com" },
                    new AppUser { UserName = "Dominic", Name = "Henry", Surname = "Singleton", MiddleName = "Adrian", PhoneNumber = "(032472) 532403", Email = "pede.Nunc@venenatis.com" },
                    new AppUser { UserName = "Benedict", Name = "Carlos", Surname = "Campbell", MiddleName = "David", PhoneNumber = "(0268) 85339242", Email = "Nullam.vitae.diam@mauris.net" },
                    new AppUser { UserName = "Cole", Name = "Kermit", Surname = "Paul", MiddleName = "Mason", PhoneNumber = "(0796) 67088940", Email = "Donec.tincidunt.Donec@atlacus.edu" },
                    new AppUser { UserName = "Charles", Name = "Minerva", Surname = "Duran", MiddleName = "Rooney", PhoneNumber = "(04761) 4256322", Email = "facilisis@ligulatortordictum.co.uk" },
                    new AppUser { UserName = "Deborah", Name = "Dara", Surname = "Knight", MiddleName = "Jermaine", PhoneNumber = "(030146) 979605", Email = "Curabitur.vel.lectus@vitaesodalesat.ca" },
                    new AppUser { UserName = "Fritz", Name = "Dominique", Surname = "Copeland", MiddleName = "Randall", PhoneNumber = "(02827) 8463382", Email = "ante.ipsum.primis@loremut.net" },
                    new AppUser { UserName = "Gil", Name = "Joseph", Surname = "Perez", MiddleName = "Aquila", PhoneNumber = "(034044) 551702", Email = "aliquam.arcu.Aliquam@dignissim.edu" },
                    new AppUser { UserName = "Naomi", Name = "Damon", Surname = "Lawson", MiddleName = "Lance", PhoneNumber = "(053) 41651559", Email = "et.tristique@orciUt.ca" },
                    new AppUser { UserName = "Cadman", Name = "Athena", Surname = "Battle", MiddleName = "Addison", PhoneNumber = "(028) 27027896", Email = "mattis.ornare.lectus@id.com" },
                    new AppUser { UserName = "Alisa", Name = "Daryl", Surname = "Gray", MiddleName = "Bert", PhoneNumber = "(01717) 2506860", Email = "facilisis.non@aliquetProin.net" },
                    new AppUser { UserName = "Savannah", Name = "Philip", Surname = "Gross", MiddleName = "Hakeem", PhoneNumber = "(098) 67806985", Email = "est@nibh.ca" },
                    new AppUser { UserName = "Guy", Name = "Evangeline", Surname = "Boyer", MiddleName = "John", PhoneNumber = "(0890) 36644095", Email = "blandit.enim.consequat@tortor.ca" },
                    new AppUser { UserName = "Madison", Name = "Ahmed", Surname = "Hopper", MiddleName = "Francis", PhoneNumber = "(0954) 63022663", Email = "mollis@Aliquam.co.uk" },
                    new AppUser { UserName = "Cameron", Name = "Neil", Surname = "Cook", MiddleName = "Amir", PhoneNumber = "(025) 03313712", Email = "Integer.vitae.nibh@Donecelementumlorem.org" },
                    new AppUser { UserName = "Isabella", Name = "Petra", Surname = "Welch", MiddleName = "Carson", PhoneNumber = "(0307) 93825927", Email = "faucibus@Phasellusfermentum.co.uk" },
                    new AppUser { UserName = "Zelenia", Name = "Ginger", Surname = "Kline", MiddleName = "Allistair", PhoneNumber = "(0120) 47473571", Email = "sagittis.augue.eu@turpis.net" },
                    new AppUser { UserName = "Declan", Name = "Cruz", Surname = "Spencer", MiddleName = "Hunter", PhoneNumber = "(037167) 754366", Email = "vulputate.risus.a@turpisegestasAliquam.edu" },
                    new AppUser { UserName = "Kelly", Name = "Maxine", Surname = "Oliver", MiddleName = "Moses", PhoneNumber = "(030840) 492867", Email = "ligula@velitjusto.edu" },
                    new AppUser { UserName = "Jocelyn", Name = "Fulton", Surname = "Clark", MiddleName = "Quinn", PhoneNumber = "(06479) 6440936", Email = "odio@dictumcursus.org" },
                    new AppUser { UserName = "Rachel", Name = "Rajah", Surname = "Franklin", MiddleName = "Samson", PhoneNumber = "(00315) 5967125", Email = "Sed.diam.lorem@enimEtiam.ca" },
                    new AppUser { UserName = "Harrison", Name = "Haviva", Surname = "Acosta", MiddleName = "Damon", PhoneNumber = "(05082) 0712090", Email = "arcu.imperdiet@pretiumneque.net" },
                    new AppUser { UserName = "James", Name = "Hiroko", Surname = "Meyers", MiddleName = "Abel", PhoneNumber = "(07392) 6585004", Email = "montes.nascetur@Suspendissesed.com" },
                    new AppUser { UserName = "Claudia", Name = "Lillith", Surname = "Rivas", MiddleName = "Axel", PhoneNumber = "(074) 89146385", Email = "elementum.at.egestas@arcuVivamussit.org" },
                    new AppUser { UserName = "Gail", Name = "Paul", Surname = "Cobb", MiddleName = "Phillip", PhoneNumber = "(038687) 180811", Email = "molestie.tortor@risus.co.uk" },
                    new AppUser { UserName = "Emerson", Name = "Indigo", Surname = "Horne", MiddleName = "Garth", PhoneNumber = "(0322) 43647222", Email = "Quisque.fringilla.euismod@nislelementumpurus.org" },
                    new AppUser { UserName = "Perry", Name = "Aspen", Surname = "Battle", MiddleName = "Stuart", PhoneNumber = "(08331) 2590053", Email = "sit.amet@semNulla.com" },
                    new AppUser { UserName = "Deirdre", Name = "Claudia", Surname = "Velasquez", MiddleName = "Evan", PhoneNumber = "(0246) 63342670", Email = "a@Nunc.co.uk" },
                    new AppUser { UserName = "Samuel", Name = "Yuli", Surname = "Mathews", MiddleName = "Cain", PhoneNumber = "(038865) 566871", Email = "odio.Nam.interdum@elitNullafacilisi.edu" },
                    new AppUser { UserName = "Acton", Name = "May", Surname = "Lara", MiddleName = "Gary", PhoneNumber = "(030743) 203854", Email = "Vivamus.non@libero.org" },
                    new AppUser { UserName = "Quon", Name = "Deanna", Surname = "Blankenship", MiddleName = "Graiden", PhoneNumber = "(01084) 2339678", Email = "Nunc@maurisidsapien.co.uk" },
                    new AppUser { UserName = "Leila", Name = "Willa", Surname = "Mullen", MiddleName = "Marshall", PhoneNumber = "(06889) 2040503", Email = "cursus.purus@arcuVestibulumante.edu" },
                    new AppUser { UserName = "Illana", Name = "Jade", Surname = "Sharpe", MiddleName = "Colt", PhoneNumber = "(0864) 47597012", Email = "nascetur.ridiculus@nullaCras.co.uk" },
                    new AppUser { UserName = "Alisa", Name = "Keegan", Surname = "Franklin", MiddleName = "Yuli", PhoneNumber = "(0474) 55888514", Email = "pharetra.sed@montesnasceturridiculus.com" },
                    new AppUser { UserName = "Knox", Name = "Brent", Surname = "William", MiddleName = "Wang", PhoneNumber = "(04010) 7133367", Email = "ipsum.primis.in@mollislectus.ca" },
                    new AppUser { UserName = "Ahmed", Name = "Claire", Surname = "Potts", MiddleName = "George", PhoneNumber = "(092) 75247131", Email = "magna.Ut@magnaUttincidunt.org" },
                    new AppUser { UserName = "Stephanie", Name = "Isabelle", Surname = "Kemp", MiddleName = "Drake", PhoneNumber = "(07051) 7946621", Email = "metus.In@cubilia.net" },
                    new AppUser { UserName = "Zeph", Name = "Ifeoma", Surname = "Campbell", MiddleName = "Holmes", PhoneNumber = "(004) 98532155", Email = "mauris@turpisegestasAliquam.org" },
                    new AppUser { UserName = "Joelle", Name = "Halla", Surname = "George", MiddleName = "Channing", PhoneNumber = "(09223) 3877484", Email = "mauris@Quisquenonummyipsum.com" },
                    new AppUser { UserName = "Hyatt", Name = "Tiger", Surname = "Maxwell", MiddleName = "Driscoll", PhoneNumber = "(037313) 740835", Email = "nunc@Suspendissesagittis.net" },
                    new AppUser { UserName = "Derek", Name = "Nelle", Surname = "Chen", MiddleName = "Marvin", PhoneNumber = "(035453) 314602", Email = "amet@placerat.com" },
                    new AppUser { UserName = "Grady", Name = "Marvin", Surname = "Odonnell", MiddleName = "Nash", PhoneNumber = "(079) 78362864", Email = "at.arcu.Vestibulum@at.com" },
                    new AppUser { UserName = "Allen", Name = "Chloe", Surname = "Gamble", MiddleName = "Kieran", PhoneNumber = "(0503) 71436581", Email = "cursus.vestibulum@utcursus.org" },
                    new AppUser { UserName = "Cathleen", Name = "Dacey", Surname = "Wooten", MiddleName = "Griffin", PhoneNumber = "(098) 80749873", Email = "placerat.orci.lacus@magnased.edu" },
                    new AppUser { UserName = "Abbot", Name = "Lamar", Surname = "Vance", MiddleName = "Oren", PhoneNumber = "(074) 19773101", Email = "in.hendrerit.consectetuer@Sednulla.co.uk" },
                    new AppUser { UserName = "Savannah", Name = "Shad", Surname = "Boone", MiddleName = "Myles", PhoneNumber = "(030549) 338184", Email = "Morbi.metus@egetvenenatis.ca" },
                    new AppUser { UserName = "Oren", Name = "Kitra", Surname = "Reyes", MiddleName = "Howard", PhoneNumber = "(0401) 19359550", Email = "ut.dolor.dapibus@Phasellus.co.uk" },
                    new AppUser { UserName = "Violet", Name = "Keely", Surname = "Powers", MiddleName = "Geoffrey", PhoneNumber = "(0131) 61965160", Email = "lacus@quam.org" },
                    new AppUser { UserName = "Caleb", Name = "Jeremy", Surname = "Graves", MiddleName = "Acton", PhoneNumber = "(08976) 4656209", Email = "auctor.velit.eget@blanditviverraDonec.co.uk" },
                    new AppUser { UserName = "Samson", Name = "Aurora", Surname = "Copeland", MiddleName = "George", PhoneNumber = "(05216) 1235477", Email = "risus.a.ultricies@Maecenasmalesuadafringilla.co.uk" },
                    new AppUser { UserName = "Emery", Name = "Sybill", Surname = "Bernard", MiddleName = "Sawyer", PhoneNumber = "(00312) 3838253", Email = "dictum@malesuadaInteger.net" },
                    new AppUser { UserName = "Burke", Name = "Karen", Surname = "Gonzalez", MiddleName = "Cameron", PhoneNumber = "(0070) 56596250", Email = "tortor.nibh.sit@purusmauris.net" },
                    new AppUser { UserName = "Darius", Name = "Ira", Surname = "Berger", MiddleName = "Camden", PhoneNumber = "(0220) 13968107", Email = "inceptos.hymenaeos@massa.net" },
                    new AppUser { UserName = "Gavin", Name = "Charity", Surname = "Rivas", MiddleName = "Samson", PhoneNumber = "(007) 51825438", Email = "tellus.Nunc.lectus@antelectusconvallis.co.uk" },
                    new AppUser { UserName = "Leroy", Name = "Gail", Surname = "Fuller", MiddleName = "Xander", PhoneNumber = "(0402) 16862000", Email = "eleifend.egestas@dictum.com" },
                    new AppUser { UserName = "Halee", Name = "Dahlia", Surname = "Rasmussen", MiddleName = "Cole", PhoneNumber = "(0889) 85510483", Email = "ac.libero@Nuncsollicitudin.com" },
                    new AppUser { UserName = "Amber", Name = "Angela", Surname = "Mcgee", MiddleName = "Abel", PhoneNumber = "(088) 30039736", Email = "quis.accumsan@enim.net" },
                    new AppUser { UserName = "Barrett", Name = "Jordan", Surname = "Carey", MiddleName = "Ferdinand", PhoneNumber = "(02339) 3811349", Email = "penatibus.et.magnis@anuncIn.org" },
                    new AppUser { UserName = "Amity", Name = "Carl", Surname = "Wilson", MiddleName = "Ignatius", PhoneNumber = "(030768) 746717", Email = "Nam.porttitor@Nullamscelerisque.ca" },
                    new AppUser { UserName = "Laurel", Name = "Lars", Surname = "Sharpe", MiddleName = "Malik", PhoneNumber = "(09488) 3941775", Email = "Nunc.sed@tellus.net" }
                };

                foreach (var user in users)
                {
                    var result = userManager.CreateAsync(user, "123456").Result;
                }
            }

            if (!context.Products.Any())
            {
                var prods = new List<Product>
                {
                    new Product{ Name = "Potato", Price = 7, ProductImages = new List<ProductImage>{ new ProductImage{ Name="potato.png", Priority = 1 } } },
                    new Product{ Name = "Salo", Price = 70, ProductImages = new List<ProductImage>{ new ProductImage{ Name="salo.jpg", Priority = 1 } } }
                };
                context.Products.AddRange(prods);

                context.SaveChanges();
            }
        }
    }
}
