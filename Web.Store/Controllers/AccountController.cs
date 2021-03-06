using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.IO;
using System.Threading.Tasks;
using Web.Store.Data.Entities.Identity;
using Web.Store.Models;
using Web.Store.Services;

namespace Web.Store.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        private readonly UserManager<AppUser> _userManager;
        private readonly SignInManager<AppUser> _signInManager;
        private readonly IJwtTokenService _jwtTokenService;

        public AccountController(UserManager<AppUser> userManager, SignInManager<AppUser> signInManager,
            IJwtTokenService jwtTokenService)
        {
            _userManager = userManager;
            _signInManager = signInManager;
            _jwtTokenService = jwtTokenService;
        }

        [HttpPost]
        [Route("register")]
        public async Task<IActionResult> Register([FromBody] RegisterViewModel model)
        {
            var user = new AppUser
            {
                Email = model.Email,
                UserName = model.Email,
                Name = model.Name,
                Surname = model.Surname,
                MiddleName = model.MiddleName,
                PhoneNumber = model.Phone,
                Image = SaveImageBase64(model.Image)
            };
            var result = await _userManager.CreateAsync(user, model.Password);

            result = await _userManager.AddToRoleAsync(user, "user");
            if (!result.Succeeded)
            {
                return BadRequest(result.Errors);
            }
            return Ok(new
            {
                token = _jwtTokenService.CreateToken(user)
            });
        }

        [HttpPost]
        [Route("login")]
        public async Task<IActionResult> Login([FromBody] LoginViewModel model)
        {
            var user = await _userManager.FindByEmailAsync(model.Email);
            var result = await _signInManager.CheckPasswordSignInAsync(user, model.Password, false);

            if (!result.Succeeded)
            {
                return BadRequest(new { errors = new { Password = new string[] { "Wrong password!" } } });
            }
            return Ok(new
            {
                token = _jwtTokenService.CreateToken(user)
            });
        }

        protected string SaveImageBase64(string base64)
        {
            string fileName = string.Format(@"{0}.txt", Guid.NewGuid()) + ".bmp";
            string filePath = Directory.GetCurrentDirectory() + "\\images\\" + fileName;

            System.IO.File.WriteAllBytes(filePath, Convert.FromBase64String(base64));

            return fileName;
        }
    }
}