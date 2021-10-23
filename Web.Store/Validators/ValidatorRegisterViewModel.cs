using FluentValidation;
using Microsoft.AspNetCore.Identity;
using Web.Store.Data.Entities.Identity;
using Web.Store.Models;

public class ValidatorRegisterViewModel : AbstractValidator<RegisterViewModel>
{
    private readonly UserManager<AppUser> _userManager;

    public ValidatorRegisterViewModel(UserManager<AppUser> userManager)
    {
        _userManager = userManager;
        RuleFor(x => x.Email)
           .NotEmpty().WithMessage("Email address is required!")
           .EmailAddress().WithMessage("Email is not valid!")
           .DependentRules(() =>
           {
               RuleFor(x => x.Email).Must(BeUniqueEmail)

                .WithMessage("Дана пошта уже зареєстрована!");
           });
        //RuleFor(x => x.User)
        //  .NotEmpty().WithMessage("Email address is required!")
        //  .EmailAddress().WithMessage("Email is not valid!")
        //  .DependentRules(() =>
        //  {
        //      RuleFor(x => x.Email).Must(BeUniqueEmail)

        //       .WithMessage("Дана пошта уже зареєстрована!");
        //  });
        RuleFor(x => x.Password)
           .NotEmpty().WithName("Password").WithMessage("Password is required")
           .MinimumLength(5).WithName("Password").WithMessage("Password minimum length is 5");
        //.Matches("[A-Z]").WithName("Password").WithMessage("Password must contain one or more capital letters.")
        //.Matches("[a-z]").WithName("Password").WithMessage("Password must contain one or more lowercase letters.")
        //.Matches(@"\d").WithName("Password").WithMessage("Password must contain one or more digits.")
        //.Matches(@"[][""!@$%^&*(){}:;<>,.?/+_=|'~\\-]").WithName("Password").WithMessage("Password must contain one or more special characters.")
        //.Matches("^[^£# “”]*$").WithName("Password").WithMessage("Password must not contain the following characters £ # “” or spaces.");
        //RuleFor(x => x.ConfirmPassword)
        // .NotEmpty().WithName("ConfirmPassword").WithMessage("Confirm your Password")
        // .Equal(x => x.Password).WithMessage("Password Confirmation do not match");
        RuleFor(x => x.Name)
          .NotEmpty().WithMessage("Name is required!");
        RuleFor(x => x.Surname)
          .NotEmpty().WithMessage("Surname is required!");
        RuleFor(x => x.MiddleName)
          .NotEmpty().WithMessage("MiddleName is required!");
        RuleFor(x => x.Phone)
          .NotEmpty().WithMessage("Phone is required")
          .Matches(@"^((?:[0-9]\-?){6,14}[0-9])|((?:[0-9]\x20?){6,14}[0-9])$").WithMessage("It should be a phone.");
    }

    private bool BeUniqueEmail(string email)
    {
        var user = _userManager.FindByEmailAsync(email).Result;
        return user == null;
    }
}