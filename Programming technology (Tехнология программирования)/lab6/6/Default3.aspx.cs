using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Default3 : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    protected void UserNameTextBox_TextChanged(object sender, EventArgs e)
    {
        Label2.Visible = true;
        Label2.Text = "Здравствуйте, " + UserNameTextBox.Text + ", вам следует освоить ASP.NET";
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        Label2.Visible = true;
        Label2.Text = "Здравствуйте, " + UserNameTextBox.Text + ", вам следует освоить ASP.NET";
    }
}