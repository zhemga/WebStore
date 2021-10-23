using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Web.Store.Data;
using Web.Store.Data.Entities;
using Web.Store.Models;

namespace Web.Store.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductsController : ControllerBase
    {
        private readonly EFAppContext _context;
        public ProductsController(EFAppContext context)
        {
            _context = context;
        }

        [Authorize(Roles = "user")]
        [HttpGet("all")]
        public async Task<IActionResult> GetAll()
        {
            var list2 = _context.Products;
            var list = await _context.Products
                .Select(x => new ProductItemVM
                {
                    Id = x.Id,
                    Name = x.Name,
                    Price = x.Price,
                    Image = x.ProductImages
                        .Select(x => x.Name)
                        .FirstOrDefault() ?? "noimage.jpg"
                })
                .ToListAsync();
            return Ok(list);
        }

        [HttpGet("get/{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var list = await _context.ProductImages
                .Select(x => new ProductInfoVM
                {
                    Id = x.Id,
                    Path = "/images/" + x.Name
                })
                .ToListAsync();
            return Ok(list);
        }

        [HttpGet("allImages")]
        public async Task<IActionResult> GetAllImages()
        {
            var list = Directory.GetFiles(Directory.GetCurrentDirectory() + "\\images").ToList();
            list = list.Select(x => Path.GetFileName(x)).ToList();
            return Ok(list);
        }

        [HttpGet("images/{id}")]
        public async Task<IActionResult> GetImagesById(int id)
        {
            var list = await _context.ProductImages
                .Where(x => x.ProductId == id)
                .Select(x => x.Name)
                .ToListAsync();
            return Ok(list);
        }

        [HttpPost("add")]
        public async Task<IActionResult> Add(ProductItemVM model)
        {
            await _context.Products.AddAsync(new Product
            {
                Name = model.Name,
                Price = model.Price,
                ProductImages = new List<ProductImage>{ 
                    new ProductImage { Name = SaveImageBase64(model.Image), Priority = 1 }
                }
            });
            await _context.SaveChangesAsync();
            return Ok();
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
