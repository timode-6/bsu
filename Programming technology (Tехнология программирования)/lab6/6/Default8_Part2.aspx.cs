using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Default8_Part2 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        for (int i = 0; i < 5; i++)
        {
            Label lab = new Label();
            lab.Text = DateTime.Now.ToString();
            lab.Font.Size = 20;
            lab.ForeColor = Color.FromArgb(255, 0, 255, 55);
            lab.Height = 35;
            lab.Width = 150;
            Page.Controls.Add(lab);
        }

    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        foreach (Control control in Page.Controls)
        {
            Response.Write(control.GetType().ToString() + " - <b>" + control.ID + "</b><br/>");
        }
        Response.Write("<hr/>");
    }
}