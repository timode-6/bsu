using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Default5 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void AddButton_Click(object sender, EventArgs e)
    {
        double res = Convert.ToDouble(Number1TB.Text) + Convert.ToDouble(Number2TB.Text);
        Result.Visible = true;
        Result.Text = res.ToString();
    }

    protected void SubButton_Click(object sender, EventArgs e)
    {
        double res = Convert.ToDouble(Number1TB.Text) - Convert.ToDouble(Number2TB.Text);
        Result.Visible = true;
        Result.Text = res.ToString();
    }

    protected void MultiplyButton_Click(object sender, EventArgs e)
    {
        double res = Convert.ToDouble(Number1TB.Text) * Convert.ToDouble(Number2TB.Text);
        Result.Visible = true;
        Result.Text = res.ToString();
    }

    protected void DivideButton_Click(object sender, EventArgs e)
    {
        double res = Convert.ToDouble(Number1TB.Text) / Convert.ToDouble(Number2TB.Text);
        Result.Visible = true;
        Result.Text = res.ToString();
    }
}