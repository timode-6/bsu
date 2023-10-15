using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Default12_Part2 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        TextBox textBox = new TextBox();
        textBox.Text = "This is a dynamically created TextBox!";
        textBox.BorderStyle = BorderStyle.Dotted;
        textBox.BorderColor = Color.Chocolate;
        textBox.ForeColor = Color.DarkViolet;
        textBox.Font.Underline = true;
        textBox.Font.Italic = true;
        textBox.Height = Unit.Pixel(30);
        textBox.Width = Unit.Pixel(250);
        Panel1.Controls.Add(textBox);
    }
}