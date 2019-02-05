using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace DefuseBomb
{
    public partial class MainPage : ContentPage
    {
        static String bomb = new Random().Next(1, 4).ToString();
        static int score = 0;

        public MainPage()
        {
            InitializeComponent();
        }

        async void ButtonClicked(object sender , EventArgs e)
        {
            Button button = sender as Button;

            if(button.Text == bomb)
            {
                await DisplayAlert("Bomb Exploded", "GAME OVER", "Retry");
                bomb = new Random().Next(1, 4).ToString();
                score = 0;
            }
            else
            {
                score += 1;
                await DisplayAlert("Bomb Defused", "Score " + score, "Continue");
                bomb = new Random().Next(1, 4).ToString();
            }
        }
    }
}
