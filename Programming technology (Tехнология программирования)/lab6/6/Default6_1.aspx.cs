using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Default6_1 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        double res = Convert.ToDouble(Number1TB.Text) * Convert.ToDouble(Number2TB.Text);
        Response.Redirect("Default6_2.aspx?Result=" + res.ToString());
    }
}